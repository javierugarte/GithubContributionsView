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
            contributionJavierugarte.displayText(true);
            contributionJavierugarte.loadUserName("javierugarte");
        }


        // Alorma
        GitHubContributionsView contributionAlorma =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view2);
        if (contributionAlorma != null) {
            contributionAlorma.setBaseColor("#FF8A80");
            contributionAlorma.loadUserName("alorma");
        }

        // Mike Penz
        GitHubContributionsView contributionMikePenz =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view3);
        if (contributionMikePenz != null) {
            contributionMikePenz.setBaseColor("#EA80FC");
            contributionMikePenz.displayText(true);
            contributionMikePenz.loadUserName("mikepenz");
        }

        // JakeWharton
        GitHubContributionsView contributionJakeWharton =
                (GitHubContributionsView) findViewById(R.id.github_contributions_view4);
        if (contributionJakeWharton != null) {
            contributionJakeWharton.setBaseColor("#8C9EFF");
            contributionJakeWharton.displayText(false);
            contributionJakeWharton.loadUserName("JakeWharton");
        }


    }
}
