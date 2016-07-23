package com.github.javierugarte;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.github.javierugarte.listeners.OnContributionsListener;
import com.github.javierugarte.listeners.OnContributionsRequestListener;
import com.github.javierugarte.utils.UtilsColor;
import com.github.javierugarte.utils.UtilsDate;

import java.util.List;

/**
 * Copyright 2016 Javier González
 * All right reserved.
 */

public class GitHubContributionsView extends ImageView {

    private int baseColor = Color.parseColor("#d6e685"); // default color of GitHub
    private int textColor = Color.BLACK;
    private boolean displayText;

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
                Bitmap bitmap = get2DBitmap(contributionsDay, getMeasuredWidth(), baseColor,
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

    public static Bitmap get2DBitmap(
            List<ContributionsDay> contributions,
            int bitmapWidth,
            int baseColor,
            int textColor,
            boolean displayText) {

        Bitmap bitmap;
        Canvas canvas;
        Paint blockPaint;
        Paint monthTextPaint;
        Paint weekDayTextPaint;

        int daysForWeek = 7;

        int verticalBlockNumber = daysForWeek;
        int horizontalBlockNumber = contributions.size() / daysForWeek;

        float marginBlock = 0.9F;
        float blockWidth = bitmapWidth / horizontalBlockNumber * marginBlock;
        float spaceWidth = bitmapWidth / (horizontalBlockNumber) - blockWidth;
        float monthTextHeight = (displayText) ? blockWidth * 1.5F : 0;
        float weekTextHeight = (displayText) ? blockWidth : 0;
        float topMargin = 7f;

        int bitmapHeight = (int)(monthTextHeight + topMargin
                + verticalBlockNumber * (blockWidth + spaceWidth));

        bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        blockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blockPaint.setStyle(Paint.Style.FILL);

        monthTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        monthTextPaint.setTextSize(monthTextHeight);
        monthTextPaint.setColor(textColor);

        weekDayTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        weekDayTextPaint.setTextSize(weekTextHeight);
        weekDayTextPaint.setColor(textColor);

        // draw the text for weekdays
        float textStartHeight = (monthTextHeight + topMargin)
                + blockWidth + spaceWidth;
        Paint.FontMetricsInt fontMetrics = monthTextPaint.getFontMetricsInt();
        float baseline = (
                textStartHeight + blockWidth +
                        textStartHeight -
                        fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(UtilsDate.getWeekdayFirstLetter((1) % 7),
                0, baseline, weekDayTextPaint);
        canvas.drawText(UtilsDate.getWeekdayFirstLetter((3) % 7),
                0, baseline + 2 * (blockWidth + spaceWidth), weekDayTextPaint);
        canvas.drawText(UtilsDate.getWeekdayFirstLetter((5) % 7),
                0, baseline + 4 * (blockWidth + spaceWidth), weekDayTextPaint);

        // draw the blocks
        int currentWeekDay = UtilsDate.getWeekDayFromDate(
                contributions.get(0).year,
                contributions.get(0).month,
                contributions.get(0).day);
        float x = weekTextHeight + topMargin;
        float y = (currentWeekDay - 7) % 7
                * (blockWidth + spaceWidth)
                + (topMargin + monthTextHeight);
        int startMonth = contributions.get(0).month;
        int lastMonth = contributions.get(0).month - 1;
        for (ContributionsDay day : contributions) {
            blockPaint.setColor(UtilsColor.calculateLevelColor(baseColor, day.level));
            canvas.drawRect(x, y, x + blockWidth, y + blockWidth, blockPaint);

            currentWeekDay = (currentWeekDay + 1) % 7;
            if (currentWeekDay == 0) {
                // another column
                x += blockWidth + spaceWidth;
                y = topMargin + monthTextHeight;
                if (day.month != lastMonth) {
                    // judge whether we should draw the text of month
                    canvas.drawText(
                            UtilsDate.getShortMonthName(day.year, day.month, day.day),
                            x, monthTextHeight, monthTextPaint);
                    lastMonth = day.month;
                }
            } else {
                y += blockWidth + spaceWidth;
            }
        }

        return bitmap;
    }

}

