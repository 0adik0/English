package com.english.myapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.english.myapp.register.LoginActivity;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private final static int ID_HOME = 1;
    private final static int ID_NOTEPAD = 2;
    private final static int ID_PROFILE = 3;
    private FirebaseAuth firebaseAuth;

    private long backPressedTime;
    MeowBottomNavigation bottomNavMenu;
    private final int[] itemImage = {R.drawable.home, R.drawable.office, R.drawable.street};
    private final String[] itemTitle = {"At home", "At work", "Outside"};
    RecyclerView categoriesRecycler;

    private HashMap<Integer, View> findViewCache;


    public View findCachedViewById(int i) {
        if (this.findViewCache == null) {
            this.findViewCache = new HashMap<>();
        }
        View view = this.findViewCache.get(i);
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this.findViewCache.put(i, findViewById);
        return findViewById;
    }

    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCorouselViewPager();
        createRecyclerGrid();
        bottomNavMenu = findViewById(R.id.bottomNav);
        bottomNavMenu.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_black_18dp));
        bottomNavMenu.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_assignment_black_18dp));
        bottomNavMenu.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_account_box_black_18dp));
        bottomNavMenu.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //x
            }
        });

        bottomNavMenu.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                //
            }
        });

        bottomNavMenu.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //

            }
        });

        findViewById(R.id.cirSignOutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);//makesure user cant go back
                startActivity(intent);
            }
        });

    }

    private void createCorouselViewPager() {
        ImageSliderAdapter imageSlideAdapter = new ImageSliderAdapter(MainActivity.this, itemImage, itemTitle);
        ViewPager courseImageSlider = (ViewPager) findCachedViewById(R.id.vpCourseItems);
        courseImageSlider.setAdapter(imageSlideAdapter);
        ViewPager viewPager2 = (ViewPager) findCachedViewById(R.id.vpCourseItems);
        viewPager2.setClipChildren(false);
        ViewPager viewPager3 = (ViewPager) findCachedViewById(R.id.vpCourseItems);
        viewPager3.setPageMargin(25);
        ((ViewPager) findCachedViewById(R.id.vpCourseItems)).setPageTransformer(false, new SliderActivity1(this, this));
    }

    private void createRecyclerGrid() {
        categoriesRecycler = findViewById(R.id.home_screen_list_categories);
        Bitmap[] logos = new Bitmap[3];
        logos[0] = BitmapFactory.decodeResource(getResources(), R.drawable.book);
        logos[1] = BitmapFactory.decodeResource(getResources(), R.drawable.copybook);
        logos[2] = BitmapFactory.decodeResource(getResources(), R.drawable.games);
        CategoryListAdapter adapter = new CategoryListAdapter(getResources().getStringArray(R.array.home_screen_category_item_title_list), logos);
        categoriesRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        categoriesRecycler.setAdapter(adapter);
        int spanCount = 3; // 3 columns
        int spacing = 50; // 50px
        boolean includeEdge = true;
        categoriesRecycler.addItemDecoration(new CategoryItemDecoration(spanCount, spacing, includeEdge));
    }

    @Override
    public void onBackPressed() {

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            return;
        }
        backPressedTime = System.currentTimeMillis();
    }

}