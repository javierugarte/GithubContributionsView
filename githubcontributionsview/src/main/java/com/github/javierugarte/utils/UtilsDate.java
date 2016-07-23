package com.github.javierugarte.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Copyright 2016 Javier González
 * All right reserved.
 */
public class UtilsDate {

    /**
     * Get the day of week from a date.
     * 0 for SUN.
     * 1 for MON.
     * .
     * .
     * .
     * 6 for SAT.
     *
     * @param year The year of the date.
     * @param month The month of the date.
     * @param day The day of month of the date.
     * @return Integer to determine the day of week.
     */
    public static int getWeekDayFromDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        calendar.add(Calendar.SECOND, 0);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * Get the short month name for a certain date.
     *
     * @param year The year of the date.
     * @param month The month of the date.
     * @param day The day of the date.
     * @return The short name of the month.
     */
    public static String getShortMonthName(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        calendar.add(Calendar.SECOND, 0);
        SimpleDateFormat month_date = new SimpleDateFormat("MMM", Locale.US);
        return month_date.format(calendar.getTime());
    }

    /**
     * Get the first letter of a weekday.
     *
     * @param weekDay Integer from 0 to 6 for weekday.
     * @return The first letter for the weekday.
     */
    public static String getWeekdayFirstLetter(int weekDay) {
        switch (weekDay) {
            case 0: return "S";
            case 1: return "M";
            case 2: return "T";
            case 3: return "W";
            case 4: return "T";
            case 5: return "F";
            case 6: return "S";
            default: return "";
        }
    }

}
