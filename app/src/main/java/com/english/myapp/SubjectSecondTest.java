package com.english.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.gridlayout.widget.GridLayout;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SubjectSecondTest extends AppCompatActivity implements View.OnDragListener, MedalsResultFragment.ResultListener, MedalsStartFragment.StartTaskListener  {

    LinearLayout mainContainer;
    RelativeLayout firstSideContainer;
    LinearLayout secondSideContainer;
    ProgressBar progressBar;
    TextView tvProgress;
    Chronometer timer;
    LinearLayout receiveContainer;
    TextView emptyLine;
    TextView sentence;
    TextView sentenceRus;
    GridLayout firstContainer;
    GridLayout secondContainer;
    Button checkButton;
    TextView congratsTv;

    DialogFragment endDialogFrg;
    DialogFragment startDialogFrg;

    int seconds;
    boolean success;
    int progress;
    int tasksComplete;
    String numDisplay;
    HashMap<String, String> values;
    Tasker tasker;
    String[] pronouns;
    List<String> tasks;
    List<String> translations;

    public final static String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_second_test);
        tasker = new Tasker();
        tasker.createStartFragmentDialog();
        //initializing views and variables
        initVars();
        initViews();
    }

    protected void onStop(){
        super.onStop();
        if (endDialogFrg!= null){
            endDialogFrg.dismissAllowingStateLoss();
        }
    }

    private void initViews(){
        //initializing timer
        timer = findViewById(R.id.subject_chronometer2);
        //initialize main views
        mainContainer = findViewById(R.id.subject_main_container2);
        firstSideContainer = findViewById(R.id.subject_first_side_container2);
        secondSideContainer = findViewById(R.id.subject_second_side_container2);
        emptyLine = findViewById(R.id.subject_empty_line);
        sentence = findViewById(R.id.subject_sentence);
        sentenceRus = findViewById(R.id.subject_sentenceRus);
        firstContainer = findViewById(R.id.subject_firstLine);
        secondContainer = findViewById(R.id.subject_secondLine);
        //initialize check button and set a listener to it
        checkButton = findViewById(R.id.subject_checkBtn2);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((emptyLine.getText().toString()).equals("_______")){
                    Toast.makeText(getBaseContext(), "Пока что нечего проверять!", Toast.LENGTH_SHORT).show();
                }
                else{
                    success = tasker.check();
                    if (success){
                        tasker.updateProgress();
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Неверно, попробуй еще раз!", Toast.LENGTH_SHORT).show();
                    }
                    tasker.update();
                }
            }
        });
        //set drag event listener for accepting view
        receiveContainer = findViewById(R.id.subject_receiveContainer);
        receiveContainer.setOnDragListener(this);
    }

    private void initVars(){
        progress = 0;
        tasksComplete = 0;
        //initialize progress bar
        progressBar = findViewById(R.id.subject_progressBar2);
        progressBar.setProgress(progress);
        progressBar.setMax(140);
        //progress bar text
        tvProgress = findViewById(R.id.subject_tv_progress_horizontal2);
        numDisplay = tasksComplete + " / 14";
        tvProgress.setText(numDisplay);
        //initiating arrays
        tasks = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.secondTaskSentences)));
        translations = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.secondTaskSentencesTranslation)));
        pronouns = getResources().getStringArray(R.array.pronouns);
        //initialize russian/english answer pull
        values = new HashMap<>();
        values.put("Я", "I");
        values.put("Мы", "We");
        values.put("Ты","You");
        values.put("Вы", "You");
        values.put("Они","They");
        values.put("Он","He");
        values.put("Она","She");
        values.put("Оно","It");
        success = false;
    }

    private void makeInvisible(){
        firstSideContainer.setVisibility(View.GONE);
        secondSideContainer.setVisibility(View.GONE);
        firstContainer.setVisibility(View.GONE);
        secondContainer.setVisibility(View.GONE);
        checkButton.setVisibility(View.GONE);
    }

    private void makeVisible(){
        mainContainer.removeView(congratsTv);
        firstContainer.setVisibility(View.VISIBLE);
        secondContainer.setVisibility(View.VISIBLE);
        firstSideContainer.setVisibility(View.VISIBLE);
        secondSideContainer.setVisibility(View.VISIBLE);
        checkButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void startTask() {
        tasker.start();
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }

    @Override
    public void sendCompleteResult() {
        Intent intent = new Intent();
        intent.putExtra("secondResult", true);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void sendRepeatResult() {
        makeVisible();
        initVars();
        startTask();
        checkButton.setClickable(true);
        endDialogFrg.dismiss();
    }

    @Override
    public boolean onDrag(View view, DragEvent event) {
        int action = event.getAction();
        //Handling of each expected action
        switch(action){
            case DragEvent.ACTION_DRAG_STARTED:
                //Returns false, cuz during current drag and drop operations, this view will not receive events again until DRAG_ENDED
                Log.d(TAG, "Started");
                return true;

            case DragEvent.ACTION_DRAG_ENTERED:
                view.getBackground().setColorFilter(Color.CYAN, PorterDuff.Mode.SRC_IN);
                // Invalidate the view to force a redraw in the new tint
                view.invalidate();
                Log.d(TAG, "Entered");
                return true;

            case DragEvent.ACTION_DRAG_LOCATION:

                return true;

            case DragEvent.ACTION_DRAG_EXITED:
                view.getBackground().clearColorFilter();
                Log.d(TAG, "Exited");
                // Invalidate the view to force a redraw in the new tint
                view.invalidate();
                return true;

            case DragEvent.ACTION_DROP:
                view.getBackground().clearColorFilter();
                view.invalidate();
                Log.d(TAG, "Dropped");
                // Turns off any color tints
                if ((emptyLine.getText().toString()).equals("_______")){
                    Log.d(TAG, "Dropped successfully");
                    // Gets the item containing the dragged data
                    ClipData.Item item = event.getClipData().getItemAt(0);
                    // Gets the text data from the item.
                    String dragData = item.getText().toString();
                    emptyLine.setText(dragData);
                    // Invalidates the view to force a redraw
                    //handle the dragged view being dropped over a drop view
                    View itemView = (View)event.getLocalState();
                    ViewGroup itemParent = (ViewGroup)itemView.getParent();
                    itemParent.removeView(itemView);
                }
                else{
                    Log.d(TAG, "Dropped unsuccessfully");
                    return false;
                }
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(TAG, "Drag ended + " + event.getResult());
                View initialView = (View)event.getLocalState();
                initialView.setVisibility(View.VISIBLE);
                return true;

            default:
                break;
        }
        return false;
    }


    private class Tasker implements View.OnLongClickListener {

        private int num;

        @Override
        public boolean onLongClick(View v) {
            ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
            ClipData data = new ClipData(v.getTag().toString(), mimeTypes, item);
            //Initiates the drag shadow builder
            View.DragShadowBuilder dragShadow = new View.DragShadowBuilder(v);
            //Starts the drag
            v.startDrag(data, dragShadow, v, 0);
            v.setVisibility(View.INVISIBLE);
            return true;
        }

        private void start() {
            //clearing the display before creating task
            firstContainer.removeAllViews();
            secondContainer.removeAllViews();
            emptyLine.setText("_______");
            //setting a random sentence for main TV from a tasks array
            Random random = new Random();
            num = random.nextInt(tasks.size());
            sentence.setText(tasks.get(num));
            sentenceRus.setText(translations.get(num));
            randomize(pronouns, pronouns.length);
            for (int i = 0; i < pronouns.length; i++){
                TextView textView = new TextView(getBaseContext());
                textView.setText(pronouns[i]);
                textView.setBackgroundColor(Color.CYAN);
                textView.setGravity(Gravity.CENTER);
                textView.setTag(textView.getText().toString());
                textView.setOnLongClickListener(this);
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
                lp.width = 0;
                lp.height = 0;
                lp.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                lp.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                lp.setMargins(10,10,10,10);
                if (i < 4){
                    firstContainer.addView(textView, lp);
                }
                else {
                    secondContainer.addView(textView, lp);
                }
            }
        }

        //compare the user answer and the right answer
        private boolean check(){
            String userAnswer = emptyLine.getText().toString();
            String sentence = sentenceRus.getText().toString();
            String buf = sentence.replace("(", "").replace(")", "").replace(",", "");
            String[] parts = buf.split(" ");
            String rightAnswer = values.get(parts[0]);
            return userAnswer.equals(rightAnswer);
        }

        //updating the pronouns container and array of tasks
        private void update(){
            if (tasks.size() == 0){
                seconds = (int)(SystemClock.elapsedRealtime() - timer.getBase()) / 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tasker.finish();
                    }
                }, 100);
            }
            else{
                start();
            }
        }

        private void updateProgress(){
            progress = progress + 10;
            progressBar.setProgress(progress);
            numDisplay = ++tasksComplete + " / 14";
            tvProgress.setText(numDisplay);
            tasks.remove(num);
            translations.remove(num);
        }

        private void finish(){
            makeInvisible();
            congratsTv = new TextView(getBaseContext());
            congratsTv.setTextSize(25);
            congratsTv.setText(R.string.congratulations);
            congratsTv.setTextColor(Color.BLACK);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha_out);
            congratsTv.setLayoutParams(layoutParams);
            congratsTv.startAnimation(anim);
            mainContainer.addView(congratsTv);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    createEndFragmentDialog();
                }
            }, 3000);
        }

        //method which randomizes the array of pronouns for future assigning
        private void randomize(String[] arr, int n) {
            Random r = new Random();
            for (int i = n - 1; i > 0; i--) {
                int j = r.nextInt(i + 1);
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        private void createEndFragmentDialog(){
            Bundle bundle = new Bundle();
            bundle.putInt("time_result", seconds);
            bundle.putInt("level", 2);
            // set FragmentClass Arguments
            endDialogFrg = new MedalsResultFragment();
            endDialogFrg.setArguments(bundle);
            endDialogFrg.show(getSupportFragmentManager(), "ResultFragmentDialog");
        }

        private void createStartFragmentDialog(){
            Bundle bundle = new Bundle();
            bundle.putInt("level", 2);
            bundle.putString("taskName", "subject");
            startDialogFrg = new MedalsStartFragment();
            startDialogFrg.setArguments(bundle);
            startDialogFrg.show(getSupportFragmentManager(), "StartFragmentDialog");
        }
    }
}