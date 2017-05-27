package com.example.tomasz.studiesmenager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class Menager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menager);

        Subject s = new Subject();
        s.Name = "Algebra";
        s.save();

        Class c = new Class();
        c.Type = Class.ClassType.Lab;
        c.EndHour = new Date(1996, 12, 10);
        c.StartHour = new Date(1996, 12, 10);
        c.FreqInWeeks = 1;
        c.MaxScore = 100;
        c.MinPassScore = 50;
        c.Subject = s;
        c.save();

        Attendence a = new Attendence();
        a.WasPresent = true;
        a.PointsEarned = 0;
        a.Class = c;
        a.Date = new Date(1996,12,10);
        a.save();

        //Push intent i alarmmanager
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.putExtra("Present",a.WasPresent);
        notificationIntent.putExtra("Date",a.Date.toString());
        notificationIntent.putExtra("ID",a.getId());
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
    }
}
