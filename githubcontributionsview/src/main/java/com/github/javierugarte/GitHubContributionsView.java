package com.github.javierugarte;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.github.javierugarte.listeners.OnContributionsListener;
import com.github.javierugarte.listeners.OnContributionsRequestListener;
import com.github.javierugarte.utils.BitmapUtils;

import java.util.List;

/**
 * Copyright 2016 Javier González
 * All right reserved.
 */

public class GitHubContributionsView extends ImageView {

    private int baseColor = Color.parseColor("#d6e685"); // default color of GitHub
    private int textColor = Color.BLACK;
    private boolean displayText = true;

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

    public void setBaseColor(String baseColor) {
        this.baseColor = Color.parseColor(baseColor);
    }

    public void setBaseColor(int color) {
        this.baseColor = color;
    }

    public void setTextColor(String textColor) {
        this.textColor = Color.parseColor(textColor);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void displayText(boolean displayText) {
        this.displayText = displayText;
    }

    public void loadUserName(String userName) {
        loadUserName(userName, null);
    }

    public void loadUserName(String userName, final OnContributionsListener listener) {

        ContributionsRequest contributionsRequest =
                new ContributionsRequest(getContext());

        contributionsRequest.launchRequest(userName, new OnContributionsRequestListener() {
            @Override
            public void onResponse(List<ContributionsDay> contributionsDay) {
                Bitmap bitmap = BitmapUtils.getContributionsBitmap(contributionsDay, getMeasuredWidth(), baseColor,
                        textColor, displayText);

                setImageBitmap(bitmap);

                if (listener != null)
                    listener.onResponse(GitHubContributionsView.this, bitmap);
            }

            @Override
            public void onError(VolleyError error) {

                if (listener != null)
                    listener.onError(1);

            }
        });

    }

}

