package com.example.tomasz.studiesmenager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.tomasz.studiesmenager.Model.Attendence;
import com.example.tomasz.studiesmenager.Model.Class;
import com.example.tomasz.studiesmenager.Model.Subject;
import com.example.tomasz.studiesmenager.SubjectDetail.SubjectDetailActivity;

import java.util.Date;

public class Menager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menager);

        Subject s = new Subject();
        s.Name = "Algebra";
        s.EndDate = new Date(1990, 12, 1);
        s.StartDate = new Date(1990, 12, 1);
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

        Class d = new Class();
        d.Type = Class.ClassType.Lecture;
        d.EndHour = new Date(1996, 12, 10);
        d.StartHour = new Date(1996, 12, 10);
        d.FreqInWeeks = 1;
        d.MaxScore = 100;
        d.MinPassScore = 50;
        d.Subject = s;
        d.save();

        Class e = new Class();
        e.Type = Class.ClassType.Class;
        e.EndHour = new Date(1996, 12, 10);
        e.StartHour = new Date(1996, 12, 10);
        e.FreqInWeeks = 1;
        e.MaxScore = 100;
        e.MinPassScore = 50;
        e.Subject = s;
        e.save();

        for (int i = 0; i < 20; i++) {
            Attendence a = new Attendence();
            a.Class = d;
            a.PointsEarned = 200;
            a.Date = new Date(0, 20, 9, d.StartHour.getHours(), d.StartHour.getMinutes());
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 20; i++) {
            Attendence a = new Attendence();
            a.Class = c;
            a.PointsEarned = 200;
            a.Date = new Date(120, 12, 1, c.StartHour.getHours(), c.StartHour.getMinutes());
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 20; i++) {
            Attendence a = new Attendence();
            a.Class = e;
            a.PointsEarned = 200;
            a.Date = new Date(90, 12, 1, e.StartHour.getHours(), e.StartHour.getMinutes());
            a.WasPresent = false;
            a.save();
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SubjectDetailActivity.class);
        startActivity(intent);
    }
}
