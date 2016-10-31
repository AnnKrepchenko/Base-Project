package com.krepchenko.base_proj.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class DateTimeUtils {

    private static String DATE_PATTERN = "dd/MM/yyyy";
    private static String TIME_PATTERN_24 = "HH:mm";
    private static String TIME_PATTERN_12 = "h:mm a";

    private static DateTimeUtils dateTimeUtilsInstance;

    private Locale locale;
    private Calendar calendar;
    private Date date;
    private SimpleDateFormat dateFormatDate;
    private SimpleDateFormat dateFormatTime;

    public DateTimeUtils() {

    }

    private DateTimeUtils(Calendar calendar, Context context) {
        this.calendar = calendar;
        date = new Date();
        dateFormatDate = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());
        if (DateFormat.is24HourFormat(context)) {
            dateFormatTime = new SimpleDateFormat(TIME_PATTERN_24, Locale.getDefault());
            return;
        }
        dateFormatTime = new SimpleDateFormat(TIME_PATTERN_12, Locale.getDefault());
    }

    public static DateTimeUtils getInstance(Context context) {
        if (dateTimeUtilsInstance == null) {
            dateTimeUtilsInstance = new DateTimeUtils(Calendar.getInstance(), context);
        } else {
            dateTimeUtilsInstance.setDateFormatDate(new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()));
            if (DateFormat.is24HourFormat(context)) {
                dateTimeUtilsInstance.setDateFormatTime(new SimpleDateFormat(TIME_PATTERN_24, Locale.getDefault()));
                return dateTimeUtilsInstance;
            }
            dateTimeUtilsInstance.setDateFormatTime(new SimpleDateFormat(TIME_PATTERN_12, Locale.getDefault()));
        }
        return dateTimeUtilsInstance;
    }

    public void setDateFormatDate(SimpleDateFormat dateFormatDate) {
        this.dateFormatDate = dateFormatDate;
    }

    public void setDateFormatTime(SimpleDateFormat dateFormatTime) {
        this.dateFormatTime = dateFormatTime;
    }

    public String getDate(int year, int monthOfYear, int dayOfMonth) {
        calendar.setTimeInMillis(0);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        date.setTime(calendar.getTimeInMillis());

        return dateFormatDate.format(date);
    }

    public String getDate(long milliseconds) {
        date.setTime(milliseconds);

        return dateFormatDate.format(date);
    }

    public long getMillisecondsDate(int year, int monthOfYear, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, monthOfYear);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTimeInMillis();
    }

    public long getJustDate(long milliseconds) {
        calendar.setTimeInMillis(milliseconds);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);

        return calendar.getTimeInMillis();
    }

    public String getTime(int hourOfDay, int minute) {
        calendar.setTimeInMillis(0);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        date.setTime(calendar.getTimeInMillis());

        return dateFormatTime.format(date);
    }

    public String getTime(long milliseconds) {
        date.setTime(milliseconds);

        return dateFormatTime.format(date);
    }

    public long getMillisecondsDateTime(long date, int hourOfDay, int minute) {
        calendar.setTimeInMillis(date);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        return calendar.getTimeInMillis();
    }

    public String getDateTimeString(long startDate, long endDate) {
        calendar.setTimeInMillis(0);
        StringBuilder stringBuilder = new StringBuilder();
        calendar.setTimeInMillis(startDate);
        stringBuilder.append(getDate(startDate));
        stringBuilder.append(" | ");
        stringBuilder.append(getTime(startDate));
        stringBuilder.append("-");
        stringBuilder.append(getTime(endDate));
        return stringBuilder.toString();
    }

    public int getDifferenceHours(long startDate, long endDate) {
        if (endDate > startDate) {
            calendar.setTimeInMillis(startDate);
            int startHour = calendar.get(Calendar.HOUR_OF_DAY);
            calendar.setTimeInMillis(endDate);
            return calendar.get(Calendar.HOUR_OF_DAY) - startHour;
        }
        return 0;
    }

    public boolean isMinutePast(long previous, long current) {
        calendar.setTimeInMillis(previous);
        int firstMin = calendar.get(Calendar.MINUTE);
        calendar.setTimeInMillis(current);

        return firstMin < calendar.get(Calendar.MINUTE);
    }

}
