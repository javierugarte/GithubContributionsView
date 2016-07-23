package com.github.javierugarte.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.github.javierugarte.ContributionsDay;

import java.util.List;

/**
 * Copyright 2016 Javier González
 * All right reserved.
 */
public class BitmapUtils {

    public static Bitmap getContributionsBitmap (
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
        canvas.drawText(DatesUtils.getWeekdayFirstLetter((1) % 7),
                0, baseline, weekDayTextPaint);
        canvas.drawText(DatesUtils.getWeekdayFirstLetter((3) % 7),
                0, baseline + 2 * (blockWidth + spaceWidth), weekDayTextPaint);
        canvas.drawText(DatesUtils.getWeekdayFirstLetter((5) % 7),
                0, baseline + 4 * (blockWidth + spaceWidth), weekDayTextPaint);

        // draw the blocks
        int currentWeekDay = DatesUtils.getWeekDayFromDate(
                contributions.get(0).year,
                contributions.get(0).month,
                contributions.get(0).day);
        float x = weekTextHeight + topMargin;
        float y = (currentWeekDay - 7) % 7
                * (blockWidth + spaceWidth)
                + (topMargin + monthTextHeight);

        for (ContributionsDay day : contributions) {
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

        return bitmap;
    }

}
