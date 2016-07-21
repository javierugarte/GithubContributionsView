package com.github.javierugarte.githubcontributionssample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.javierugarte.GitHubContributionsView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GitHubContributionsView gitHubContributionsView =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view);

        gitHubContributionsView.init();

    }
}
