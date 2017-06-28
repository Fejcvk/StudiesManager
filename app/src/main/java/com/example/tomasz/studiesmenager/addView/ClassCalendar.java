package com.example.tomasz.studiesmenager.addView;


import java.io.IOException;
import java.util.Calendar;

/**
 * Created by Tomasz on 28/06/2017.
 */

public class ClassCalendar {

    Calendar calendar;
    String calendarName;
    ClassCalendar()
    {
        calendar = Calendar.getInstance();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
        calendarName = getClassName();
    }

    String className;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }


    int year, month, day, hour, minute;
    public void setCalendar()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(cal.YEAR);
        int month = getMonth();
        int day = getDay();
        int min = getMinute();
        int hour = getHour();
        cal.set(year,month,day,hour,min);
    }

}
