package com.example.tomasz.studiesmenager.addView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.tomasz.studiesmenager.Model.Attendence;
import com.example.tomasz.studiesmenager.Model.Subject;
import com.example.tomasz.studiesmenager.Model.Class;
import com.example.tomasz.studiesmenager.R;
import com.example.tomasz.studiesmenager.SubjectsListCardView.SubjectsActivity;

import java.util.Calendar;

import static com.example.tomasz.studiesmenager.Model.Class.ClassType.Lab;
import static com.example.tomasz.studiesmenager.Model.Class.ClassType.Class;
import static com.example.tomasz.studiesmenager.Model.Class.ClassType.Lecture;

public class addViewActivity extends AppCompatActivity {

    private Boolean labPressed = false;
    private Boolean tutPressed = false;
    private Boolean lecPressed = false;
    public static Calendar labStartCalendar;
    public static Calendar labEndCalendar;
    public static Calendar tutStartCalendar;
    public static Calendar tutEndCalendar;
    public static Calendar lecStartCalendar;
    public static Calendar lecEndCalendar;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addview);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.AddSubjectToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Dodaj przedmiot");
    }
    public void setFormVisibility(View view)
    {

        final CardView labForm = (CardView) findViewById(R.id.labcardview);
        final CardView tutForm = (CardView) findViewById(R.id.tutcardview);
        final CardView lecForm = (CardView) findViewById(R.id.leccardview);

        final Button labButton = (Button) findViewById(R.id.labButtonId);
        final Button tutButton = (Button) findViewById(R.id.tutButtonId);
        final Button lecButton = (Button) findViewById(R.id.lecButtonId);


        labButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(labForm.getVisibility() == View.GONE) {
                        labButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                        labForm.setVisibility(View.VISIBLE);
                        labPressed = true;
                    }
                    else {
                        labForm.setVisibility(View.GONE);
                        labButton.getBackground().clearColorFilter();
                        labPressed = false;
                    }
            }
        });
        tutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(tutForm.getVisibility() == View.GONE){
                    tutForm.setVisibility(View.VISIBLE);
                    tutButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    tutPressed = true;
                }
                else {
                    tutForm.setVisibility(View.GONE);
                    tutButton.getBackground().clearColorFilter();
                    tutPressed = false;
                }
            }
        });
        lecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lecForm.getVisibility() == View.GONE) {
                    lecButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    lecForm.setVisibility(View.VISIBLE);
                    lecPressed = true;
                }
                else {
                    lecForm.setVisibility(View.GONE);
                    lecButton.getBackground().clearColorFilter();
                    lecPressed = false;
                }
            }
        });
    }

    public void showCalendar(View v) {

        final TimePickerFragment newFragment = new TimePickerFragment(v);
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void showCalendarDay(View v)
    {
        final DatePickerFragment newFragment = new DatePickerFragment(v);
        newFragment.show(getFragmentManager(),"dayPicker");
    }


    public void addSubject(View v){
        Subject subject = new Subject();
        Class lab;
        Class tut;
        Class lec;
        int totalScore = 0;
        EditText editText = (EditText) findViewById(R.id.subject_name);
        subject.Name = editText.getText().toString();
        subject.save();
        final Button labButton = (Button) findViewById(R.id.labButtonId);
        final Button tutButton = (Button) findViewById(R.id.tutButtonId);
        final Button lecButton = (Button) findViewById(R.id.lecButtonId);

        if(labPressed) {
            lab = new Class();
            lab.Type = Lab;
            final EditText hScore = (EditText) findViewById(R.id.maxScoreLab);
            totalScore += Integer.parseInt(hScore.getText().toString());
            lab.MaxScore = Integer.parseInt(hScore.getText().toString());
            final EditText pScore = (EditText) findViewById(R.id.passScoreLab);
            lab.MinPassScore = Integer.parseInt((pScore.getText().toString()));
            lab.StartHour = labStartCalendar.getTime();
            lab.EndHour = labEndCalendar.getTime();
            final RadioButton onceButton = (RadioButton) findViewById(R.id.lab_radio_once);
            if (onceButton.isChecked())
                lab.FreqInWeeks = 1;
            else
                lab.FreqInWeeks = 2;
            final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBoxLab);
            if (checkBox.isChecked())
                lab.ShowNotifications = true;
            else
                lab.ShowNotifications = false;
            lab.Subject = subject;
            lab.save();
            for (int i = 0; i < 15 / lab.FreqInWeeks; i++) {
                Attendence attendence = new Attendence();
                if (lab.FreqInWeeks == 1) {
                    attendence.Date = labStartCalendar.getTime();
                    labStartCalendar.add(Calendar.DAY_OF_YEAR, +7);
                } else {
                    attendence.Date = labStartCalendar.getTime();
                    labStartCalendar.add(Calendar.DAY_OF_YEAR, +14);
                }
                attendence.PointsEarned = 0;
                attendence.WasPresent = false;
                attendence.Class = lab;
                attendence.save();
            }
        }
        if(tutPressed)
        {
            tut = new Class();
            tut.Type = Class;
            final EditText hScore = (EditText)findViewById(R.id.maxScoreTut);
            totalScore += Integer.parseInt(hScore.getText().toString());
            tut.MaxScore = Integer.parseInt(hScore.getText().toString());
            final EditText pScore = (EditText)findViewById(R.id.passScoreTut);
            tut.MinPassScore = Integer.parseInt((pScore.getText().toString()));
            tut.StartHour = tutStartCalendar.getTime();
            tut.EndHour = tutEndCalendar.getTime();
            final RadioButton onceButton = (RadioButton)findViewById(R.id.tut_radio_once);
            if(onceButton.isChecked())
                tut.FreqInWeeks = 1;
            else
                tut.FreqInWeeks = 2;
            final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBoxTut);
            if(checkBox.isChecked())
                tut.ShowNotifications = true;
            else
                tut.ShowNotifications = false;
            tut.Subject = subject;
            tut.save();
            for(int i =0;i<15/tut.FreqInWeeks;i++)
            {
                Attendence attendence = new Attendence();
                if(tut.FreqInWeeks == 1) {
                    attendence.Date = tutStartCalendar.getTime();
                    tutStartCalendar.add(Calendar.DAY_OF_YEAR, +7);
                }
                else
                {
                    attendence.Date = tutStartCalendar.getTime();
                    tutStartCalendar.add(Calendar.DAY_OF_YEAR, +14);
                }
                attendence.PointsEarned = 0;
                attendence.WasPresent = false;
                attendence.Class = tut;
                attendence.save();
            }
        }
        if(lecPressed)
        {
            lec = new Class();
            lec.Type = Lecture;
            final EditText hScore = (EditText)findViewById(R.id.maxScoreLec);
            totalScore += Integer.parseInt(hScore.getText().toString());
            lec.MaxScore = Integer.parseInt(hScore.getText().toString());
            final EditText pScore = (EditText)findViewById(R.id.passScoreLec);
            lec.MinPassScore = Integer.parseInt((pScore.getText().toString()));
            lec.StartHour = lecStartCalendar.getTime();
            lec.EndHour = lecEndCalendar.getTime();
            final RadioButton onceButton = (RadioButton)findViewById(R.id.lec_radio_once);
            final RadioButton twiceButton = (RadioButton)findViewById(R.id.lec_radio_twice);
            if(onceButton.isChecked())
                lec.FreqInWeeks = 1;
            else
                lec.FreqInWeeks = 2;
            final CheckBox checkBox = (CheckBox)findViewById(R.id.checkBoxLec);
            if(checkBox.isChecked())
                lec.ShowNotifications = true;
            else
                lec.ShowNotifications = false;
            lec.Subject = subject;
            lec.save();
            for(int i =0;i<15/lec.FreqInWeeks;i++)
            {
                Attendence attendence = new Attendence();
                if(lec.FreqInWeeks == 1) {
                    attendence.Date = lecStartCalendar.getTime();
                    lecStartCalendar.add(Calendar.DAY_OF_YEAR, +7);
                }
                else
                {
                    attendence.Date = lecStartCalendar.getTime();
                    lecStartCalendar.add(Calendar.DAY_OF_YEAR, +14);
                }
                attendence.PointsEarned = 0;
                attendence.WasPresent = false;
                attendence.Class = lec;
                attendence.save();
            }
        }
        Intent intent = new Intent(this, SubjectsActivity.class);
        startActivity(intent);

    }
}
