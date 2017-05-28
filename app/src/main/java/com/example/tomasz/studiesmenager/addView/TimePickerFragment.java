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

import java.util.Calendar;

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
        textView.setText(hour+":"+minute);
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),hour,minute);
        TextView textViewLS = (TextView) getActivity().findViewById(R.id.startHourTxtLab);
        if(textView == textViewLS)
            labStartCalendar = calendar;
        TextView textViewLE = (TextView) getActivity().findViewById(R.id.endHourTxtLab);
        if(textView == textViewLE)
            labEndCalendar = calendar;
        TextView textViewTS = (TextView) getActivity().findViewById(R.id.startHourTxtTut);
        TextView textViewTE = (TextView) getActivity().findViewById(R.id.endHourTxtTut);
        TextView textViewLeS = (TextView) getActivity().findViewById(R.id.startHourTxtLec);
        TextView textViewLeE = (TextView) getActivity().findViewById(R.id.endHourTxtLec);
        if(textView == textViewTS)
            tutStartCalendar = calendar;
        if(textView == textViewTE)
            tutEndCalendar = calendar;
        if(textView == textViewLeS)
            lecStartCalendar = calendar;
        if(textView == textViewLeE)
            lecEndCalendar = calendar;
    }
}
