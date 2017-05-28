package com.example.tomasz.studiesmenager.addView;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.Calendar;

/**
 * Created by Tomasz on 27/05/2017.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener{

    static int hour;
    public int minute;
    public View Editview;
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

        TextView textView = (TextView) Editview;
        textView.setText(hour+":"+minute);
    }
}
