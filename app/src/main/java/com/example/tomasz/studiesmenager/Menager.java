package com.example.tomasz.studiesmenager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 1988);
        cal2.set(Calendar.MONTH, Calendar.JANUARY);
        cal2.set(Calendar.DAY_OF_MONTH, 1);
        a.Date = cal2.getTime();
        a.save();
        Log.w("myapp--", a.getId().toString());
        Attendence a2 = new Attendence();
        a2.WasPresent = true;
        a2.PointsEarned = 0;
        a2.Class = c;
        Calendar cal3 = Calendar.getInstance();
        cal3.set(Calendar.YEAR, 1999);
        cal3.set(Calendar.MONTH, Calendar.JANUARY);
        cal3.set(Calendar.DAY_OF_MONTH, 1);
        a2.Date = cal3.getTime();
        a2.save();
        Log.w("myapp--", a2.getId().toString());
        //Push intent i alarmmanager
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.putExtra("Present",a.WasPresent);
        notificationIntent.putExtra("Date",a.Date.toString());
        notificationIntent.putExtra("ID",a.getId());
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        Intent notificationIntent2 = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent2.putExtra("Present",a2.WasPresent);
        notificationIntent2.putExtra("Date",a2.Date.toString());
        notificationIntent2.putExtra("ID",a2.getId());
        notificationIntent2.addCategory("android.intent.category.DEFAULT");



        PendingIntent broadcast = PendingIntent.getBroadcast(this, new Random().nextInt(3929), notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 1);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
        cal.add(Calendar.SECOND, 4);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(this, new Random().nextInt(3929), notificationIntent2, PendingIntent.FLAG_ONE_SHOT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast2);

    }
}
