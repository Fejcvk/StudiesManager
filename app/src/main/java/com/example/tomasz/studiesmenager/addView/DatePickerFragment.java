package com.example.tomasz.studiesmenager.addView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import static com.example.tomasz.studiesmenager.addView.addViewActivity.labEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.labStartCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.lecEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.lecStartCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.tutEndCalendar;
import static com.example.tomasz.studiesmenager.addView.addViewActivity.tutStartCalendar;

import com.example.tomasz.studiesmenager.R;

import java.text.DateFormat;
import java.util.Calendar;

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
        month+=1;//koniecznie, zjeby iteruja miesiace 0-11
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    public DatePickerFragment(View v)
    {
        EditView = v;
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        TextView textView = (TextView) EditView;
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        calendar = Calendar.getInstance();
        calendar.set(year,month,day);
        System.out.println("**********************"+df.format(calendar.getTime()));
        textView.setText(df.format(calendar.getTime()));
        TextView textViewLS = (TextView) getActivity().findViewById(R.id.startHourTxtLab);
        TextView textViewTS = (TextView) getActivity().findViewById(R.id.startHourTxtTut);
        TextView textViewTE = (TextView) getActivity().findViewById(R.id.endHourTxtTut);
        TextView textViewLeS = (TextView) getActivity().findViewById(R.id.startHourTxtLec);
        TextView textViewLeE = (TextView) getActivity().findViewById(R.id.endHourTxtLec);
        TextView textViewLE = (TextView) getActivity().findViewById(R.id.endHourTxtLab);

        if(textView == textViewLS)
        {
            labStartCalendar.set(year,month,day);
        }
        if(textView == textViewLE)
        {
            labEndCalendar.set(year,month,day);
        }
        if(textView == textViewTS)
        {
            tutStartCalendar.set(year, month, day);
        }
        if(textView == textViewTE)
        {
            tutEndCalendar.set(year, month, day);
        }
        if(textView == textViewLeS)
        {
            lecStartCalendar.set(year, month, day);
        }
        if(textView == textViewLeE)
        {
            lecEndCalendar.set(year, month, day);
        }
    }
}
