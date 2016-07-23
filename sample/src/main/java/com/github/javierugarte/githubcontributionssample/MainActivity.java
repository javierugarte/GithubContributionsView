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
        if (contributionJavierugarte != null) {
            contributionJavierugarte.setBaseColor("#d6e685");
            contributionJavierugarte.setTextColor("#d6e685");
            contributionJavierugarte.displayText(true);
            contributionJavierugarte.loadUserName("javierugarte");
        }


        // Alorma
        GitHubContributionsView contributionAlorma =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view2);
        if (contributionAlorma != null) {
            contributionAlorma.setBaseColor("#F44336");
            contributionAlorma.displayText(false);
            contributionAlorma.loadUserName("alorma");
        }

        // Mike Penz
        GitHubContributionsView contributionMikePenz =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view3);
        if (contributionMikePenz != null) {
            contributionMikePenz.setBaseColor("#9C27B0");
            contributionMikePenz.displayText(true);
            contributionMikePenz.loadUserName("mikepenz");
        }

        // JakeWharton
        GitHubContributionsView contributionJakeWharton =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view4);
        if (contributionJakeWharton != null) {
            contributionJakeWharton.setBaseColor("#3F51B5");
            contributionJakeWharton.displayText(false);
            contributionJakeWharton.loadUserName("JakeWharton");
        }


    }
}
