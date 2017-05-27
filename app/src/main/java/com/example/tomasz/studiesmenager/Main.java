package com.example.tomasz.studiesmenager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tomasz.studiesmenager.Model.Attendence;
import com.example.tomasz.studiesmenager.Model.Subject;
import com.example.tomasz.studiesmenager.Model.Class;

import java.util.Date;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

        if (id == R.id.Subjects) {
            // Handle the camera action
        }
        else if (id == R.id.Schedule) {
        }
        else if (id == R.id.Stats){
        }
        else if (id == R.id.AddSubject){
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
