package com.example.tomasz.studiesmenager.addView;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;


import com.example.tomasz.studiesmenager.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.example.tomasz.studiesmenager.addView.addViewActivity.labEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.labStartCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.lecEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.lecStartCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.tutEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.tutStartCalendar;

/**
 * Created by Tomasz on 27/05/2017.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener{

    static int hour;
    static int minute;
    public View Editview;
    public Calendar calendar;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    public TimePickerFragment(View v)
    {
        super();
        Editview = v;

    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
        hour = hourOfDay;
        minute = minutes;
        calendar = Calendar.getInstance();
        TextView textView = (TextView) Editview;
        java.text.DateFormat df = new SimpleDateFormat("HH:mm");
        calendar.set(calendar.MINUTE, minute);
        calendar.set(calendar.HOUR_OF_DAY, hour);
        String myTime = calendar.get(calendar.HOUR_OF_DAY) + ":" + calendar.get(calendar.MINUTE);
        try {
            Date time = df.parse(myTime);
            System.out.println("*********************************"+df.format(time));
            textView.setText(df.format(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        TextView textViewTS = (TextView) getActivity().findViewById(R.id.startHourTxtTut);
        TextView textViewTE = (TextView) getActivity().findViewById(R.id.endHourTxtTut);
        TextView textViewLeS = (TextView) getActivity().findViewById(R.id.startHourTxtLec);
        TextView textViewLeE = (TextView) getActivity().findViewById(R.id.endHourTxtLec);
        TextView textViewLS = (TextView) getActivity().findViewById(R.id.startHourTxtLab);
        TextView textViewLE = (TextView) getActivity().findViewById(R.id.endHourTxtLab);

        if(textView == textViewLS) {
            labStartCalendar.set(labStartCalendar.MINUTE, minute);
            labStartCalendar.set(labStartCalendar.HOUR_OF_DAY,hour);
        }
        if(textView == textViewLE)
        {
            labEndCalendar.set(labEndCalendar.MINUTE, minute);
            labEndCalendar.set(labEndCalendar.HOUR_OF_DAY,hour);
        }
        if(textView == textViewTS)
        {
            tutStartCalendar.set(tutStartCalendar.MINUTE, minute);
            tutStartCalendar.set(tutStartCalendar.HOUR_OF_DAY, hour);
        }
       if(textView == textViewTE)
       {
           tutEndCalendar.set(tutEndCalendar.MINUTE, minute);
           tutEndCalendar.set(tutEndCalendar.HOUR_OF_DAY, hour);
       }
        if(textView == textViewLeS)
        {
            lecStartCalendar.set(lecStartCalendar.MINUTE, minute);
            lecStartCalendar.set(lecStartCalendar.HOUR_OF_DAY, hour);
        }
        if(textView == textViewLeE)
        {
            lecEndCalendar.set(lecEndCalendar.MINUTE, minute);
            lecEndCalendar.set(lecEndCalendar.HOUR_OF_DAY, hour);
        }
    }
}
