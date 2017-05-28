package com.example.tomasz.studiesmenager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

        Class c1 = new Class();
        c1.Type = Class.ClassType.Lab;
        c1.EndHour = new Date(2017, 05, 27);
        c1.StartHour = new Date(2017, 05, 27);
        c1.FreqInWeeks = 1;
        c1.MaxScore = 100;
        c1.MinPassScore = 50;
        c1.Subject = s;
        c1.save();

        Attendence a = new Attendence();
        a.Date = new Date(2010, 04, 10);
        a.PointsEarned = 14;
        a.WasPresent = true;
        a.Class = c;
        a.save();

        Attendence a1 = new Attendence();
        a.Date = new Date(2017, 05, 27);
        a.PointsEarned = 25;
        a.WasPresent = true;
        a.Class = c1;
        a.save();

    }

    public void openScheduleView(View view) {
        Intent intent = new Intent(this, ScheduleViewActivity.class);
        startActivity(intent);
    }
}
