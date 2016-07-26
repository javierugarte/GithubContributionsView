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
            boolean displayMonth) {

        Bitmap bitmap;
        Canvas canvas;
        Paint blockPaint;
        Paint monthTextPaint;

        int verticalBlockNumber = 7;
        int horizontalBlockNumber = getHorizontalBlockNumber(contributions.size(), verticalBlockNumber);

        float marginBlock = (1.0F - 0.1F);
        float blockWidth = bitmapWidth / (float) horizontalBlockNumber * marginBlock;
        float spaceWidth = bitmapWidth / (float)  horizontalBlockNumber - blockWidth;

        float monthTextHeight = (displayMonth) ? blockWidth * 1.5F : 0;
        float topMargin = (displayMonth) ? 7f : 0;

        int bitmapHeight = (int)(monthTextHeight + topMargin
                + verticalBlockNumber * (blockWidth + spaceWidth));

        bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        blockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blockPaint.setStyle(Paint.Style.FILL);

        monthTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        monthTextPaint.setTextSize(monthTextHeight);
        monthTextPaint.setColor(textColor);

        // draw the blocks
        int currentWeekDay = DatesUtils.getWeekDayFromDate(
                contributions.get(0).year,
                contributions.get(0).month,
                contributions.get(0).day);

        float x = topMargin;
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

    private static int getHorizontalBlockNumber(int total, int divider) {
        boolean isInteger = (total / divider) % 7 == 0;
        int result = total / divider;
        return (isInteger) ? result : result + 1;
    }
}
