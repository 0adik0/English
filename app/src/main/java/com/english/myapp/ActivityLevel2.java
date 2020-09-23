package com.english.myapp;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.english.myapp.utils.Level2;


public class    ActivityLevel2 extends AppCompatActivity implements View.OnTouchListener, View.OnDragListener, View.OnClickListener {

    private TextView tvOption1,tvOption2,tvOption3,tvQuestion;
    private LinearLayout rlAns;
    private LinearLayout layout1;
    private ImageView imgDestination;
    private Level2 level2;
    private int index;
    private Dialog dialogEnd;


    private View views[] = new View[15];
   private int viewId[] = {R.id.vpoint1,R.id.vpoint2,R.id.vpoint3,R.id.vpoint4,R.id.vpoint5,R.id.vpoint6,R.id.vpoint7
            ,R.id.vpoint8,R.id.vpoint9,R.id.vpoint10,R.id.vpoint11,R.id.vpoint12,R.id.vpoint13,R.id.vpoint14,R.id.vpoint15};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_level_two);

        TextView text_levels = findViewById(R.id.text_levels);
        text_levels.setText(R.string.level2);
        intializeViews();

        imgDestination = findViewById(R.id.imgDestination);


        //Find all views and set Tag to all draggable views
        layout1 = (LinearLayout) findViewById(R.id.layout1);

        rlAns = (LinearLayout) findViewById(R.id.rlAns);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);


        tvOption1 = (TextView) findViewById(R.id.tvOption1);
        tvOption1.setOnTouchListener(this);


        tvOption2 = (TextView) findViewById(R.id.tvOption2);
        tvOption2.setOnTouchListener(this);


        tvOption3 = (TextView) findViewById(R.id.tvOption3);
        tvOption3.setOnTouchListener(this);


        tvOption1.setOnTouchListener(this);
        tvOption2.setOnTouchListener(this);
        tvOption3.setOnTouchListener(this);
        imgDestination.setOnDragListener(this);

         level2 = new Level2();
        level2.updateQuestions();
        updateQuestionsAnswers();


        Button btn_back = (Button)findViewById(R.id.button_back);
        btn_back.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        try {
            Intent intent = new Intent(this, GameLevels.class);
            startActivity(intent);
            finish();
        } catch (Exception e) {

        }
    }


    private void intializeViews(){

        for(int i=0;i<views.length;i++) {
            views[i] = (View) findViewById(viewId[i]);
        }


    }
    private void updateQuestionsAnswers(){


        tvQuestion.setText(level2.questAnsList.get(index).getQuestion());
        tvQuestion.setTag(level2.questAnsList.get(index).getQuestion());
        answer = level2.questAnsList.get(index).getAnswer();

        String optionA=level2.questAnsList.get(index).getOptionsList().get(0).getOptionA();
        tvOption1.setText(optionA);
        tvOption1.setTag(level2.questAnsList.get(index).getOptionsList().get(0).getOptionA());


        tvOption2.setText(level2.questAnsList.get(index).getOptionsList().get(1).getOptionA());
        tvOption2.setTag(level2.questAnsList.get(index).getOptionsList().get(1).getOptionA());


        tvOption3.setText(level2.questAnsList.get(index).getOptionsList().get(2).getOptionA());
        tvOption3.setTag(level2.questAnsList.get(index).getOptionsList().get(2).getOptionA());

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        View.DragShadowBuilder mShadow = new View.DragShadowBuilder(v);
        ClipData.Item item = new ClipData.Item(v.getTag().toString());
        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
        ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(data, mShadow, null, 0);
        } else {
            v.startDrag(data, mShadow, null, 0);
        }
        return false;
    }

    String answer;
    @Override
    public boolean onDrag(final View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:

                ((ImageView) v).setColorFilter(Color.WHITE);


                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:

                String clipData = event.getClipDescription().getLabel().toString();

                if(clipData!=answer&&clipData.equalsIgnoreCase(answer)){
                    ((ImageView) v).setColorFilter(ContextCompat.getColor(ActivityLevel2.this, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);
                    ((ImageView) v).setImageDrawable(getDrawable(R.drawable.img_true));

                    if(index<views.length) {
                        views[index].setBackgroundResource(R.drawable.style_points_green);

                        index = index + 1;

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 1 seconds
                                updateQuestionsAnswers();
                                ((ImageView) v).setColorFilter(Color.WHITE);
                            }
                        }, 700);

                    }else{

                        showDialogAfterLevelComplete();

                        return false;
                    }


                }else{
                    ((ImageView) v).setColorFilter(ContextCompat.getColor(ActivityLevel2.this, R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
                    ((ImageView) v).setImageDrawable(getDrawable(R.drawable.img_false));
                }

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:
                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                return true;

            case DragEvent.ACTION_DROP:
                clipData = event.getClipDescription().getLabel().toString();
                Toast.makeText(getApplicationContext(),clipData, Toast.LENGTH_SHORT).show();

                v.invalidate();
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                return true;

            default:
                return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        index=0;
    }

    private void showDialogAfterLevelComplete(){

        if(dialogEnd==null)
        dialogEnd = new Dialog(this);

        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogend);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.setCancelable(false);

       /* TextView btnclose = (TextView)dialogEnd.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent(ActivityLevel2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
                dialogEnd.dismiss();
            }
        });*/

        Button btncontinue2 = (Button)dialogEnd.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    finish();
                    Intent intent = new Intent(ActivityLevel2.this, GameLevels.class);
                    startActivity(intent);

                } catch (Exception e) {
                }
                dialogEnd.dismiss();
            }
        });

        dialogEnd.show();
    }

}
