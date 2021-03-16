package com.english.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
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
import java.util.Map;
import java.util.Random;

public class SubjectThirdTest extends AppCompatActivity implements MedalsResultFragment.ResultListener, MedalsStartFragment.StartTaskListener{

    LinearLayout mainContainer;
    RelativeLayout firstContainer;
    LinearLayout secondContainer;
    ProgressBar progressBar;
    TextView tvProgress;
    TextView firstSentence;
    Chronometer timer;
    EditText emptyText;
    TextView secondSentence;
    TextView sentenceRus;
    Button checkButton;

    DialogFragment endDialogFrg;
    DialogFragment startDialogFrg;

    Tasker tasker;
    String numDisplay;
    int progress;
    int tasksComplete;
    int seconds;
    boolean success;
    List<String> taskFirstSentenceArray;
    List<String> taskSecondSentenceArray;
    List<String> translationsArray;
    HashMap<String, String> values;
    TextView congratsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_third_test);
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
        timer = findViewById(R.id.subject_chronometer3);
        //initializing mainViews
        mainContainer = findViewById(R.id.subject_main_container3);
        firstContainer = findViewById(R.id.subject_first_side_container3);
        secondContainer = findViewById(R.id.subject_second_side_container3);
        firstSentence = findViewById(R.id.first_sentence);
        emptyText = findViewById(R.id.editTextLine);
        secondSentence = findViewById(R.id.second_sentence);
        sentenceRus = findViewById(R.id.sentenceRus3);
        //initialize check button and set a listener to it
        checkButton = findViewById(R.id.subject_checkBtn3);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(emptyText.getText().toString())){
                    Toast.makeText(getBaseContext(), "Пока что нечего проверять!", Toast.LENGTH_SHORT).show();
                }
                else{
                    success = tasker.check();
                    if (success){
                        tasker.updateProgress();
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Неверно, попробуй еще раз!", Toast.LENGTH_SHORT).show();
                        emptyText.setText("");
                    }
                    tasker.update();
                }
            }
        });
    }

    private void initVars(){
        progress = 0;
        tasksComplete = 0;
        //initialize progress bar
        //initialize progress bar
        progressBar = findViewById(R.id.subject_progressBar3);
        progressBar.setProgress(progress);
        progressBar.setMax(160);
        //progress bar text
        tvProgress = findViewById(R.id.subject_tv_progress_horizontal3);
        numDisplay = tasksComplete + " / 16";
        tvProgress.setText(numDisplay);
        //initiating arrays
        taskFirstSentenceArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.thirdTaskFirstSentences)));
        taskSecondSentenceArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.thirdTaskSecondSentences)));
        translationsArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.thirdTaskSentencesTranslation)));
        values = new HashMap<>();
        values.put("Джон", "He");
        values.put("дом","It");
        values.put("рубеж","They");
        values.put("ноутбук","It");
        values.put("телефон","She");
        values.put("вечеринку","I");
        values.put("офисе","He");
        values.put("футбол","We");
        values.put("столе","It");
        values.put("кино","You");
        values.put("машина","It");
        values.put("коллеги","They");
        values.put("компании","She");
        values.put("платье","It");
        values.put("долго","They");
        values.put("дверь","I");
        //setting success variable to false
        success = false;
    }

    private boolean isEmpty(String s){
        return s.trim().length() == 0;
    }

    private void makeInvisible(){
        firstContainer.setVisibility(View.GONE);
        secondContainer.setVisibility(View.GONE);
        checkButton.setVisibility(View.GONE);
    }

    private void makeVisible(){
        mainContainer.removeView(congratsTv);
        firstContainer.setVisibility(View.VISIBLE);
        secondContainer.setVisibility(View.VISIBLE);
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
        intent.putExtra("thirdResult", true);
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

    private class Tasker {

        private int num;

        private void start() {
            //setting a random sentence for main TV from a tasks array
            Random random = new Random();
            num = random.nextInt(taskFirstSentenceArray.size());
            firstSentence.setText(taskFirstSentenceArray.get(num));
            secondSentence.setText(taskSecondSentenceArray.get(num));
            sentenceRus.setText(translationsArray.get(num));
            emptyText.setHint("???");
        }

        //compare the user answer and the right answer
        private boolean check(){
            boolean correct = false;
            String userAnswer = emptyText.getText().toString();
            String sentence = sentenceRus.getText().toString();
            String buf = sentence.replace("(", "")
                    .replace(")", "")
                    .replace(",", "")
                    .replace(".", "")
                    .replace("–", "");
            String[] parts = buf.split(" ");
            List<String> bufList = Arrays.asList(parts);
            //iterating all the values of HashMap set;
            for (Map.Entry<String, String> pair : values.entrySet()){
                String s = pair.getKey();
                if (bufList.contains(s)){
                    String rightAnswer = pair.getValue();
                    correct = userAnswer.equalsIgnoreCase(rightAnswer);
                }
            }
            return correct;
        }

        //updating the pronouns container and array of tasks
        private void update(){
            if (taskFirstSentenceArray.size() == 0){
                seconds = (int)(SystemClock.elapsedRealtime() - timer.getBase()) / 1000;
                checkButton.setClickable(false);
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
            numDisplay = ++tasksComplete + " / 16";
            tvProgress.setText(numDisplay);
            taskFirstSentenceArray.remove(num);
            taskSecondSentenceArray.remove(num);
            translationsArray.remove(num);
            emptyText.setText("");
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

        private void createEndFragmentDialog(){
            Bundle bundle = new Bundle();
            bundle.putInt("time_result", seconds);
            bundle.putInt("level", 3);
            // set FragmentClass Arguments
            endDialogFrg = new MedalsResultFragment();
            endDialogFrg.setArguments(bundle);
            endDialogFrg.show(getSupportFragmentManager(), "ResultFragmentDialog");
        }

        private void createStartFragmentDialog(){
            Bundle bundle = new Bundle();
            bundle.putInt("level", 3);
            bundle.putString("taskName", "subject");
            startDialogFrg = new MedalsStartFragment();
            startDialogFrg.setArguments(bundle);
            startDialogFrg.show(getSupportFragmentManager(), "StartFragmentDialog");
        }
    }
}