package com.english.myapp;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.english.myapp.utils.LevelMedalsUtils;


public class MedalsStartFragment extends DialogFragment implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    public interface StartTaskListener{
        void startTask();
    }

    StartTaskListener startTaskListener;

    //views
    ImageView previewImg;
    TextView tvDescription;
    TextView mGoldInfoTextView, mSilverInfoTextView, mBronzeInfoTextView;
    Button mContinueButton;

    //vars
    int mLevel;
    String taskName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLevel = getArguments().getInt("level", 1);
        taskName = getArguments().getString("taskName", null);
        Log.d(LOG_TAG, "FragmentStart onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.start_medals_fragment, null);
        //connecting fragment with its activity
        startTaskListener = (StartTaskListener) getActivity();
        //initializing main view components
        previewImg = v.findViewById(R.id.preview_img);
        tvDescription = v.findViewById(R.id.text_description);
        mGoldInfoTextView = v.findViewById(R.id.gold_info_tv);
        mSilverInfoTextView = v.findViewById(R.id.silver_info_tv);
        mBronzeInfoTextView = v.findViewById(R.id.bronze_info_tv);
        mContinueButton = v.findViewById(R.id.continue_btn);
        mContinueButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        //making dialog to fit the screen
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        //showing the medal and text relevant to the result gained
        switch (taskName){
            case "subject":
                switch (mLevel){
                    case 1:
                        tvDescription.setText(R.string.subject_first_test_entry_dialog);
                        break;
                    case 2:
                        tvDescription.setText(R.string.subject_second_test_entry_dialog);
                        break;
                    case 3:
                        tvDescription.setText(R.string.subject_third_test_entry_dialog);
                        break;
                    case 4:
                        tvDescription.setText(R.string.subject_fourth_test_entry_dialog);
                        break;
                }
            default:
                break;
        }

        //showing possible results per medals
        mGoldInfoTextView.setText("" + LevelMedalsUtils.getAllLevelsList().get(mLevel - 1).get(0) + " sec = ");
        mSilverInfoTextView.setText("" + LevelMedalsUtils.getAllLevelsList().get(mLevel - 1).get(1) + " sec = ");
        mBronzeInfoTextView.setText("" + LevelMedalsUtils.getAllLevelsList().get(mLevel - 1).get(2) + " sec = ");
    }

    public void onClick(View v) {
        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
        startTaskListener.startTask();
        dismiss();
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        startTaskListener = null;
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        startTaskListener = null;
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }

}