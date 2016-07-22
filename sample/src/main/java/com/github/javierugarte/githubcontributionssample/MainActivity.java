package com.github.javierugarte.githubcontributionssample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.javierugarte.GitHubContributionsView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // JavierUgarte
        GitHubContributionsView contributionJavierugarte =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view);
        contributionJavierugarte.setUserName("javierugarte");


        // Alorma
        GitHubContributionsView contributionAlorma =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view2);
        contributionAlorma.setUserName("alorma");

    }
}
