package com.example.tomasz.studiesmenager.addView;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.example.tomasz.studiesmenager.R;


public class addViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addview);
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
                    }
                    else {
                        labForm.setVisibility(View.GONE);
                        labButton.getBackground().clearColorFilter();
                    }
            }
        });
        tutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(tutForm.getVisibility() == View.GONE){
                    tutForm.setVisibility(View.VISIBLE);
                    tutButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                }
                else {
                    tutForm.setVisibility(View.GONE);
                    tutButton.getBackground().clearColorFilter();
                }
            }
        });
        lecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lecForm.getVisibility() == View.GONE) {
                    lecButton.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    lecForm.setVisibility(View.VISIBLE);
                }
                else {
                    lecForm.setVisibility(View.GONE);
                    lecButton.getBackground().clearColorFilter();
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
}
