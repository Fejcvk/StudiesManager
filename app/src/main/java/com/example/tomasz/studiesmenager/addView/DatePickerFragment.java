package com.example.tomasz.studiesmenager.addView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.tomasz.studiesmenager.R;

import java.util.Calendar;

import static com.example.tomasz.studiesmenager.addView.TimePickerFragment.hour;
import static com.example.tomasz.studiesmenager.addView.TimePickerFragment.minute;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.labEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.labStartCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.lecEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.lecStartCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.tutEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.tutStartCalendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    View EditView;
    Calendar calendar;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public DatePickerFragment(View v)
    {
        EditView = v;
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView textView = (TextView) EditView;
        textView.setText(day+" - "+month+" - "+year);
        calendar = Calendar.getInstance();
        calendar.set(year,month,day,calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
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
