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



    }

    public void openScheduleView(View view) {
        Intent intent = new Intent(this, ScheduleViewActivity.class);
        startActivity(intent);
    }
}
