package com.english.myapp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;


public class SubjectFirstTest extends AppCompatActivity implements MedalsResultFragment.ResultListener, MedalsStartFragment.StartTaskListener {

    RelativeLayout firstContainer;
    private FrameLayout container;

    TextView congratsTv;
    Chronometer timer;

    DialogFragment endDialogFrg;
    DialogFragment startDialogFrg;

    DrawRect rect;
    private int xDelta, yDelta;
    int count;
    int seconds;
    String[] titles = {"Human", "Car", "House", "I", "Phone", "Road",
            "You", "Bird", "Table", "We", "Water", "Fire", "Cat", "Mouse",
            "He", "Dad", "Mom", "Laptop", "She", "Girl", "Boy", "It", "Table", "Sofa", "They"};

    String[] pronouns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_first_test);
        initVars();
        initViews();
        createStartFragmentDialog();
    }

    protected void onStop(){
        super.onStop();
        if (endDialogFrg!= null){
            endDialogFrg.dismissAllowingStateLoss();
        }
    }

    private void initViews(){
        firstContainer = findViewById(R.id.subject_first_side_container1);
        timer = findViewById(R.id.subject_chronometer1);
        container = findViewById(R.id.subject_container1);
    }

    private void initVars(){
        count = 0;
        pronouns = getResources().getStringArray(R.array.pronouns);
    }

    private void makeInvisible(){
        firstContainer.setVisibility(View.GONE);
        container.removeAllViews();
    }

    private void makeVisible(){
        container.removeView(congratsTv);
        firstContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void sendCompleteResult() {
        Intent intent = new Intent();
        intent.putExtra("firstResult", true);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void sendRepeatResult() {
        makeVisible();
        initVars();
        startTask();
        endDialogFrg.dismiss();
    }

    private void createEndFragmentDialog(){
        Bundle bundle = new Bundle();
        bundle.putInt("time_result", seconds);
        bundle.putInt("level", 1);
        // set FragmentClass Arguments
        endDialogFrg = new MedalsResultFragment();
        endDialogFrg.setArguments(bundle);
        endDialogFrg.show(getSupportFragmentManager(), "ResultFragmentDialog");
    }

    private void createStartFragmentDialog(){
        Bundle bundle = new Bundle();
        bundle.putInt("level", 1);
        bundle.putString("taskName", "subject");
        startDialogFrg = new MedalsStartFragment();
        startDialogFrg.setArguments(bundle);
        startDialogFrg.show(getSupportFragmentManager(), "StartFragmentDialog");
    }

    @Override
    public void startTask() {
        //creating a green rectangle for checking
        rect = new DrawRect(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 500);
        layoutParams.gravity = Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
        rect.setLayoutParams(layoutParams);
        //creating the rectangle field for future views
        GradientDrawable border = new GradientDrawable();
        border.setStroke(10, Color.GREEN); //black border with full opacity
        rect.setBackground(border);
        //adding the new created view to container;
        container.addView(rect);

        //creating objects to move
        for (int i = 0; i < titles.length; i++){
            TextView textView = new TextView(this);
            FrameLayout.LayoutParams lParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lParams.leftMargin = 450;
            lParams.topMargin = 350;
            textView.setLayoutParams(lParams);
            textView.setText(titles[i]);
            textView.setTextSize(15);
            textView.setPadding(15, 15, 15, 15);
            textView.setBackgroundResource(R.drawable.style_btn_white);
            textView.setOnTouchListener(touchListener);
            container.addView(textView);
        }
        //starting timer
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }

    public boolean containsView(View firstView, View secondView){
        int[] pointA = new int[2];
        firstView.getLocationOnScreen(pointA);
        Rect rectA = new Rect(pointA[0], pointA[1], pointA[0] + firstView.getWidth(), pointA[1] + firstView.getHeight());

        int[] pointB = new int[2];
        secondView.getLocationOnScreen(pointB);
        Rect rectB = new Rect(pointB[0], pointB[1], pointB[0] + secondView.getWidth(), pointB[1] + secondView.getHeight());

        return Rect.intersects(rectA, rectB);
    }

    public boolean isPronoun(View view){
        TextView text = (TextView)view;
        String s = text.getText().toString();
        return Arrays.asList(pronouns).contains(s);
    }

    private void showCongrats(){
        congratsTv = new TextView(SubjectFirstTest.this);
        congratsTv.setTextSize(25);
        String congratulations = getResources().getString(R.string.congratulations);
        congratsTv.setText(congratulations);
        congratsTv.setTextColor(Color.BLACK);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL;
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
        congratsTv.setLayoutParams(layoutParams);
        congratsTv.startAnimation(anim);
        container.addView(congratsTv);
    }

    private final View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override public boolean onTouch(View view, MotionEvent event) {
            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                    xDelta = x - lParams.leftMargin;
                    yDelta = y - lParams.topMargin;
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    if (containsView(view, rect)){
                       if (isPronoun(view)){
                           count++;
                           view.setBackgroundColor(Color.GREEN);
                           view.setOnTouchListener(null);
                           if (count == 7){
                               seconds = (int)(SystemClock.elapsedRealtime() - timer.getBase()) / 1000;
                               makeInvisible();
                               showCongrats();
                               new Handler().postDelayed(new Runnable() {
                                   @Override
                                   public void run() {
                                       createEndFragmentDialog();
                                   }
                               }, 3000);
                           }
                       }
                       else {
                           Toast.makeText(getApplicationContext(), "Это не местоимение, попробуй еще раз", Toast.LENGTH_SHORT).show();
                           FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                           lParams.leftMargin = 450;
                           lParams.topMargin = 350;
                           view.setLayoutParams(lParams);
                           break;
                       }
                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (x - xDelta + view.getWidth() <= container.getWidth()
                            && y - yDelta + view.getHeight() <= container.getHeight()
                            && x - xDelta >= 0
                            && y - yDelta >= 0) {
                        FrameLayout.LayoutParams layoutParams =
                                (FrameLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                    }
                    break;
                }
            }
            container.invalidate();
            return true;
        }
    };


    protected class DrawRect extends View{

        public DrawRect(Context context){
            super(context);
        }
    }
}