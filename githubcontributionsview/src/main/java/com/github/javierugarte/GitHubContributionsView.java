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
    private boolean displayMonth = false;
    private String username = "";

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
        try {
            this.baseColor = Color.parseColor(baseColor);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        refresh();
    }

    public void setBaseColor(int color) {
        this.baseColor = color;
        refresh();
    }

    public void setTextColor(String textColor) {
        try {
            this.textColor = Color.parseColor(textColor);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        refresh();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        refresh();
    }

    public void displayMonth(boolean displayMonth) {
        this.displayMonth = displayMonth;
        refresh();
    }

    public void loadUserName(String username) {
        loadUserName(username, null);
    }

    public void loadUserName(String username, final OnContributionsListener listener) {
        this.username = username;

        clearContribution();

        ContributionsRequest contributionsRequest =
                new ContributionsRequest(getContext());

        contributionsRequest.launchRequest(username, new OnContributionsRequestListener() {
            @Override
            public void onResponse(List<ContributionsDay> contributionsDay) {
                Bitmap bitmap = BitmapUtils.getContributionsBitmap(contributionsDay, getMeasuredWidth(), baseColor,
                        textColor, displayMonth);

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

    public void clearContribution() {
        invalidate();
        setImageBitmap(null);
    }

    private void refresh() {
        loadUserName(this.username);
    }

}

