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

    /**
     * Set a base color for blocks.
     * The tone depends on the number of contributions for a day.
     * Supported formats See {@link Color#parseColor(String)}
     * @param baseColor base color supported formats
     */
    public void setBaseColor(String baseColor) {
        try {
            this.baseColor = Color.parseColor(baseColor);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        refresh();
    }

    /**
     * Set a base color for blocks.
     * The tone depends on the number of contributions for a day.
     * @param color resource color
     */
    public void setBaseColor(int color) {
        this.baseColor = color;
        refresh();
    }

    /**
     * Set a text color for month names.
     * Supported formats See {@link Color#parseColor(String)}
     * @param textColor text color supported formats
     */
    public void setTextColor(String textColor) {
        try {
            this.textColor = Color.parseColor(textColor);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        refresh();
    }

    /**
     * Set a text color for month names.
     * @param textColor resource color
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        refresh();
    }

    /**
     * Set if you want to see the name of the months
     * If you send true, the component height increase
     * @param displayMonth true or false
     */
    public void displayMonth(boolean displayMonth) {
        this.displayMonth = displayMonth;
        refresh();
    }

    /**
     * Load and show contributions information for a user / organization
     * @param username also, can be an organization
     */
    public void loadUserName(String username) {
        loadUserName(username, null);
    }

    /**
     * Load and show contributions information for a user / organization.
     * Responds if it has been loaded successfully or an error has occurred.
     * @param username also, can be an organization.
     * @param listener OnContributionsRequestListener
     *                 onResponse(View, bitmap)
     *                 onError(int cause)
     *                      1: No load information
     *                      2: No generate the bitmap
     */
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

                if (bitmap != null) {
                    setImageBitmap(bitmap);
                    if (listener != null)
                        listener.onResponse(GitHubContributionsView.this, bitmap);
                } else {
                    if (listener != null)
                        listener.onError(2);
                }

            }

            @Override
            public void onError(VolleyError error) {

                if (listener != null)
                    listener.onError(1);

            }
        });

    }

    /**
     * Clean de component.
     */
    public void clearContribution() {
        invalidate();
        setImageBitmap(null);
    }

    /**
     * Refresh component with all information.
     */
    private void refresh() {
        loadUserName(this.username);
    }

}

