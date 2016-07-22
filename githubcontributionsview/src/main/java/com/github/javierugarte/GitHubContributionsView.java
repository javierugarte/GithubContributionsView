package com.github.javierugarte;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.VolleyError;

import java.util.List;

/**
 * Copyright 2016 Javier González
 * All right reserved.
 */

public class GitHubContributionsView extends ImageView {

    private String userName;
    private String primaryColor;

    public GitHubContributionsView(Context context) {
        super(context);
    }

    public GitHubContributionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GitHubContributionsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GitHubContributionsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void init() {
        setBackgroundColor(Color.rgb(100, 100, 50));

    }

    public void setUserName(String userName) {
        this.userName = userName;

        GitHubContributionsRequest contributionsRequest =
                new GitHubContributionsRequest(getContext());

        contributionsRequest.launchRequest(userName,
                new GitHubContributionsRequest.GithubContributionsListener() {

            @Override
            public void onResponse(List<ContributionsDay> contributionsDay) {

            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

}

