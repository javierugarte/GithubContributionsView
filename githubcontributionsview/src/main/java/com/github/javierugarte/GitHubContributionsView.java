package com.github.javierugarte;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.VolleyError;
import com.github.javierugarte.listeners.OnContributionsRequestListener;
import com.github.javierugarte.utils.ColorsUtils;
import com.github.javierugarte.utils.DatesUtils;

import java.util.List;

/**
 * Copyright 2016 Javier González
 * All right reserved.
 */

public class GitHubContributionsView extends View implements OnContributionsRequestListener {

    private int baseColor = Color.parseColor("#d6e685"); // default color of GitHub
    private int baseBackgroundColor = Color.TRANSPARENT;
    private int textColor = Color.BLACK;
    private boolean displayMonth = false;
    private String username = "";
    private int lastWeeks = 53;
    private List<ContributionsDay> contributions;
    private List<ContributionsDay> contributionsFilter;
    private Rect rect;
    private Paint monthTextPaint;
    private Paint blockPaint;

    public GitHubContributionsView(Context context) {
        super(context);
        init();
    }

    public GitHubContributionsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GitHubContributionsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GitHubContributionsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        rect = new Rect();

        monthTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blockPaint.setStyle(Paint.Style.FILL);
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
        invalidate();
    }

    /**
     * Set a base color for blocks.
     * The tone depends on the number of contributions for a day.
     * @param color resource color
     */
    public void setBaseColor(int color) {
        this.baseColor = color;
        invalidate();
    }

    /**
     * Sets the background color for this contributions view.
     * @param baseBackgroundColor the color of the background
     */
    public void setBaseBackgroundColor(int baseBackgroundColor) {
        this.baseBackgroundColor = baseBackgroundColor;
        invalidate();
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
        invalidate();
    }

    /**
     * Set a text color for month names.
     * @param textColor resource color
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
        invalidate();
    }

    /**
     * Set the number of weeks that you want to display.
     * You can set minimum 2 weeks but is not recommended. 1 week is impossible.
     * You can set maximum 53 weeks (1 year = 52.14 weeks).
     * By default is 53 (52 weeks and the current week).
     * @param lastWeeks number of week (2..53)
     */
    public void setLastWeeks(int lastWeeks) {
        if (lastWeeks >= 2 && lastWeeks <= 53) {
            this.lastWeeks = lastWeeks;
            invalidate();
        } else {
            throw new RuntimeException("The last weeks should be a number between 2 and 53");
        }
    }

    /**
     * Set if you want to see the name of the months
     * If you send true, the component height increase
     * @param displayMonth true or false
     */
    public void displayMonth(boolean displayMonth) {
        this.displayMonth = displayMonth;
        invalidate();
    }

    /**
     * Load and show contributions information for a user / organization
     * @param username also, can be an organization
     */
    public void loadUserName(String username) {
        this.username = username;

        clearContribution();

        ContributionsRequest contributionsRequest = new ContributionsRequest(getContext());
        contributionsRequest.launchRequest(username, this);

    }

    /**
     * Clean de component.
     */
    public void clearContribution() {
        this.contributions = null;
        invalidate();
    }

    @Override
    public void onResponse(List<ContributionsDay> contributionsDay) {
        this.contributions = contributionsDay;
        invalidate();
    }

    @Override
    public void onError(VolleyError error) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (contributions != null) {
            contributionsFilter = getLastContributions(contributions, lastWeeks);
            drawOnCanvas(canvas);
        } else {
            drawPlaceholder(canvas);
        }
    }

    private void drawOnCanvas(Canvas canvas) {
        canvas.getClipBounds(rect);

        int width = rect.width();

        int verticalBlockNumber = 7;
        int horizontalBlockNumber = getHorizontalBlockNumber(contributionsFilter.size(), verticalBlockNumber);

        float marginBlock = (1.0F - 0.1F);
        float blockWidth = width / (float) horizontalBlockNumber * marginBlock;
        float spaceWidth = width / (float)  horizontalBlockNumber - blockWidth;

        float topMargin = (displayMonth) ? 7f : 0;
        float monthTextHeight = (displayMonth) ? blockWidth * 1.5F : 0;

        int height = (int) ((blockWidth + spaceWidth) * 7 + topMargin + monthTextHeight);

        // Background
        blockPaint.setColor(baseBackgroundColor);
        canvas.drawRect(0, (topMargin + monthTextHeight), width, height + monthTextHeight, blockPaint);

        monthTextPaint.setColor(textColor);
        monthTextPaint.setTextSize(monthTextHeight);

        // draw the blocks
        int currentWeekDay = DatesUtils.getWeekDayFromDate(
            contributions.get(0).year,
            contributions.get(0).month,
            contributions.get(0).day);

        float x = 0;
        float y = (currentWeekDay - 7) % 7
            * (blockWidth + spaceWidth)
            + (topMargin + monthTextHeight);

        for (ContributionsDay day : contributionsFilter) {
            blockPaint.setColor(ColorsUtils.calculateLevelColor(baseColor, day.level));
            canvas.drawRect(x, y, x + blockWidth, y + blockWidth, blockPaint);

            if (DatesUtils.isFirstDayOfWeek(day.year, day.month, day.day+1)) {
                // another column
                x += blockWidth + spaceWidth;
                y = topMargin + monthTextHeight;

                if (DatesUtils.isFirstWeekOfMount(day.year, day.month, day.day+1)) {
                    canvas.drawText(
                        DatesUtils.getShortMonthName(day.year, day.month, day.day+1),
                        x, monthTextHeight, monthTextPaint);
                }

            } else {
                y += blockWidth + spaceWidth;
            }
        }

        // Resize component
        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) getLayoutParams();
        ll.height = height;
        setLayoutParams(ll);
    }

    private void drawPlaceholder(Canvas canvas) {
        canvas.getClipBounds(rect);
        int width = rect.width();

        int verticalBlockNumber = 7;
        int horizontalBlockNumber = getHorizontalBlockNumber((lastWeeks-1) * 7, verticalBlockNumber);

        float marginBlock = (1.0F - 0.1F);
        float blockWidth = width / (float) horizontalBlockNumber * marginBlock;
        float spaceWidth = width / (float)  horizontalBlockNumber - blockWidth;

        float monthTextHeight = (displayMonth) ? blockWidth * 1.5F : 0;
        float topMargin = (displayMonth) ? 7f : 0;

        monthTextPaint.setTextSize(monthTextHeight);

        int height = (int) ((blockWidth + spaceWidth) * 7 + topMargin + monthTextHeight);

        // Background
        blockPaint.setColor(baseBackgroundColor);
        canvas.drawRect(0, (topMargin + monthTextHeight), width, height + monthTextHeight, blockPaint);


        float x = 0;
        float y = 0
                * (blockWidth + spaceWidth)
                + (topMargin + monthTextHeight);

        for (int i = 1; i < (lastWeeks * 7) + 1; i++) {

                blockPaint.setColor(ColorsUtils.calculateLevelColor(baseColor, 0));
            canvas.drawRect(x, y, x + blockWidth, y + blockWidth, blockPaint);

            if (i % 7 == 0) {
                // another column
                x += blockWidth + spaceWidth;
                y = topMargin + monthTextHeight;

            } else {
                y += blockWidth + spaceWidth;
            }
        }

        // Resize component
        LinearLayout.LayoutParams ll = (LinearLayout.LayoutParams) getLayoutParams();
        ll.height = height;
        setLayoutParams(ll);
    }

    private static int getHorizontalBlockNumber(int total, int divider) {
        boolean isInteger = (total / divider) % 7 == 0;
        int result = total / divider;
        return (isInteger) ? result : result + 1;
    }

    private static List<ContributionsDay> getLastContributions(
            List<ContributionsDay> contributions,
            int lastWeeks) {

        int lastWeekDays = contributions.size() % 7;
        int lastDays = (lastWeekDays > 0) ? lastWeekDays + (lastWeeks-1) * 7 : lastWeeks * 7;

        return contributions.subList(contributions.size() - lastDays, contributions.size());
    }
}

