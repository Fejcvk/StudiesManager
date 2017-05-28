package com.example.tomasz.studiesmenager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.tomasz.studiesmenager.Model.Attendence;
import com.example.tomasz.studiesmenager.Model.Subject;
import com.example.tomasz.studiesmenager.Model.Class;
import com.example.tomasz.studiesmenager.SubjectsListCardView.SubjectsActivity;
import com.example.tomasz.studiesmenager.addView.addViewActivity;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import static com.example.tomasz.studiesmenager.R.id.chart;
import static com.example.tomasz.studiesmenager.R.id.screen;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private float[] yData = {50, 30, 20};
    private String[] xData = {"Niepotrzebne punkty", "Potrzebne punkty", "Zdobyte Punkty"};
    private int colors[] = {Color.argb(255,84,172,210),Color.argb(255,225,73,56), Color.argb(255,257,218,100)};
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Subject electronicsPrinciples = new Subject("Podstawy Elektroniki");
        electronicsPrinciples.save();
        Subject digitalSystems = new Subject("EKSC");
        digitalSystems.save();
        Subject algebra = new Subject("Algebra");
        algebra.save();
        Subject graphProg = new Subject("PwSG");
        graphProg.save();
        Subject discreteMath = new Subject("Matematyka Dyskretna");
        discreteMath.save();

        Date date2 = new Date(117, 3, 10, 16, 15);
        Date date1 = new Date(117, 3, 10, 18, 15);
        Date date = new Date(117, 3, 10, 20, 15);
        Date date3 = new Date(117, 3, 11, 8, 15);
        Date date4 = new Date(117, 3, 11, 10, 15);

        Class epLab = new Class(Class.ClassType.Lab, 100, 50, date, date1, 1, true, electronicsPrinciples);
        epLab.save();
        Class epLect = new Class(Class.ClassType.Lecture, 100, 50, date1, date2, 1, true, electronicsPrinciples);
        epLect.save();
        Class epTut = new Class(Class.ClassType.Class, 100, 50, date3, date4, 1, true, electronicsPrinciples);
        epTut.save();
        Class algebraTut = new Class(Class.ClassType.Class, 100, 50, new Date(117, 3, 8, 15, 15), new Date(117, 3, 8, 17, 15), 1, true, algebra);
        algebraTut.save();
        Class algebraLect = new Class(Class.ClassType.Lecture, 100, 50, new Date(117, 3, 8, 17, 15), new Date(117, 3, 8, 19, 15), 1, true, algebra);
        algebraLect.save();
        Class dsTut = new Class(Class.ClassType.Class, 100, 50, new Date(117, 3, 7, 15, 15), new Date(117, 3, 7, 17, 15), 1, true, digitalSystems);
        dsTut.save();
        Class dsLect = new Class(Class.ClassType.Lecture, 100, 50, new Date(117, 3, 6, 15, 15), new Date(117, 3, 6, 17, 15), 1, true, digitalSystems);
        dsLect.save();
        Class gpLab = new Class(Class.ClassType.Lab, 100, 50, new Date(117, 3, 8, 8, 15), new Date(117, 3, 8, 10, 15), 1, true, graphProg);
        gpLab.save();
        Class dmTut = new Class(Class.ClassType.Class, 100, 50, new Date(117, 3, 8, 12, 15), new Date(117, 3, 8, 14, 15), 1, true, discreteMath);
        dmTut.save();
        Class dmLect = new Class(Class.ClassType.Lecture, 100, 50, new Date(117, 3, 5, 15, 15), new Date(117, 3, 8, 17, 15), 1, true, discreteMath);
        dmLect.save();

        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = epLab;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(epLab.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = true;
            a.save();
        }

        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = epLect;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(epLect.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = epTut;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(epTut.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = algebraTut;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(algebraTut.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = algebraLect;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(algebraLect.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = dsTut;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(dsTut.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = dsLect;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(dsLect.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = gpLab;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(gpLab.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = dmTut;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(dmTut.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
        for (int i = 0; i < 15; i++) {
            Attendence a = new Attendence();
            a.Class = dmLect;
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(dmLect.StartHour);
            startDate.add(Calendar.DAY_OF_YEAR, 7 * i);
            a.PointsEarned = i;
            a.Date = startDate.getTime();
            a.WasPresent = false;
            a.save();
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;
        if (id == R.id.Subjects) {
            intent = new Intent(this, SubjectsActivity.class);
        }
        else if (id == R.id.Schedule) {
            intent = new Intent(this, ScheduleViewActivity.class);
        }
        else if (id == R.id.Stats){
            intent = new Intent(this, SubjectsActivity.class);
        }
        else if (id == R.id.AddSubject) {
            intent = new Intent(this, addViewActivity.class);
        }
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
