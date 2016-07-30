package com.github.javierugarte.githubcontributionssample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Switch;

import com.github.javierugarte.GitHubContributionsView;

public class MainActivity extends AppCompatActivity {

    private GitHubContributionsView mContributionView;

    private EditText mUsernameEditText;
    private Button mSearchUser;

    private EditText mLastWeekEditText;
    private Button mLastWeekButton;

    private EditText mBaseColorEditText;
    private Button mChangeColorButton;

    private EditText mBaseEmptyColorEditText;
    private Button mChangeEmptyColorButton;

    private EditText mBbColorEditText;
    private Button mChangeBgColorButton;

    private Switch mSwitchMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContributionView =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view);

        mUsernameEditText = (EditText) findViewById(R.id.et_username);
        mSearchUser = (Button) findViewById(R.id.btn_search);
        mSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContributionView.loadUserName(mUsernameEditText.getText().toString());
            }
        });


        mBaseColorEditText = (EditText) findViewById(R.id.et_base_color);
        mChangeColorButton = (Button) findViewById(R.id.btn_color);
        mChangeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContributionView.setBaseColor(mBaseColorEditText.getText().toString());
            }
        });

        mBbColorEditText = (EditText) findViewById(R.id.et_background);
        mChangeBgColorButton= (Button) findViewById(R.id.btn_background);
        mChangeBgColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContributionView.setBackgroundBaseColor(mBbColorEditText.getText().toString());
            }
        });

        mBaseEmptyColorEditText = (EditText) findViewById(R.id.et_empty);
        mChangeEmptyColorButton = (Button) findViewById(R.id.btn_empty);
        mChangeEmptyColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContributionView.setBaseEmptyColor(mBaseEmptyColorEditText.getText().toString());
            }
        });

        mLastWeekEditText = (EditText) findViewById(R.id.et_last_weeks);
        mLastWeekButton = (Button) findViewById(R.id.btn_last_weeks);
        mLastWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.parseInt(mLastWeekEditText.getText().toString());
                mContributionView.setLastWeeks(number);
            }
        });

        mSearchUser.performClick();

        mSwitchMonth = (Switch) findViewById(R.id.switch_display_month);
        mSwitchMonth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mContributionView.displayMonth(isChecked);
            }
        });

        mSearchUser.performClick();

    }
}
