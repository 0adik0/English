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


public class MedalsResultFragment extends DialogFragment implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    public interface ResultListener{
        void sendCompleteResult();
        void sendRepeatResult();
    }

    ResultListener resultListener;

    //views
    TextView tvResult;
    TextView mResultSecondsTextView;
    ImageView mResultMedalImageView;
    TextView mGoldInfoTextView, mSilverInfoTextView, mBronzeInfoTextView;
    Button mContinueButton;
    Button mRetryButton;

    //vars
    int mSeconds;
    int mLevel;
    int mMedal;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSeconds = getArguments().getInt("time_result", 0);
        mLevel = getArguments().getInt("level", 1);
        mMedal = LevelMedalsUtils.computeResult(mSeconds, mLevel);
        Log.d(LOG_TAG, "FragmentResult onCreate");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.result_medals_fragment, null);
        //connecting fragment with its activity
        resultListener = (ResultListener)getActivity();
        //initializing main view components
        tvResult = v.findViewById(R.id.textdescripton);
        mResultSecondsTextView = v.findViewById(R.id.result_seconds_tv);
        mResultMedalImageView = v.findViewById(R.id.result_medal_iv);
        mGoldInfoTextView = v.findViewById(R.id.gold_info_tv);
        mSilverInfoTextView = v.findViewById(R.id.silver_info_tv);
        mBronzeInfoTextView = v.findViewById(R.id.bronze_info_tv);
        mContinueButton = v.findViewById(R.id.continue_btn);
        mContinueButton.setOnClickListener(this);
        mRetryButton = v.findViewById(R.id.retry_btn);
        mRetryButton.setOnClickListener(this);
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
        switch (mMedal) {
            case LevelMedalsUtils.GOLD:
                mResultMedalImageView.setImageResource(R.drawable.gold_medals);
                tvResult.setText(R.string.gold_medal_text);
                break;
            case LevelMedalsUtils.SILVER:
                mResultMedalImageView.setImageResource(R.drawable.silver_medals);
                tvResult.setText(R.string.silver_medal_text);
                break;
            case LevelMedalsUtils.BRONZE:
                mResultMedalImageView.setImageResource(R.drawable.bronze_medals);
                tvResult.setText(R.string.bronze_medal_text);
                break;
            case LevelMedalsUtils.NONE:
                tvResult.setText(R.string.no_medal_text);
        }
        //showing the result in seconds and possible results per medals
        String timeResult = "Your result is " + mSeconds + " " + getString(R.string.seconds);
        mResultSecondsTextView.setText(timeResult);
        mGoldInfoTextView.setText("" + LevelMedalsUtils.getAllLevelsList().get(mLevel - 1).get(0) + " sec = ");
        mSilverInfoTextView.setText("" + LevelMedalsUtils.getAllLevelsList().get(mLevel - 1).get(1) + " sec = ");
        mBronzeInfoTextView.setText("" + LevelMedalsUtils.getAllLevelsList().get(mLevel - 1).get(2) + " sec = ");
    }

    public void onClick(View v) {
        Log.d(LOG_TAG, "Dialog 1: " + ((Button) v).getText());
        switch (v.getId()){
            case R.id.continue_btn:
                resultListener.sendCompleteResult();
                break;
            case R.id.retry_btn:
                resultListener.sendRepeatResult();
                break;
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        resultListener = null;
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        resultListener = null;
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }

}