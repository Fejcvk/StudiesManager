package com.example.tomasz.studiesmenager;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import com.example.tomasz.studiesmenager.Model.Attendence;

/**
 * Created by pmoren on 27.05.2017.
 */
public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        if (AppConstant.YES_ACTION.equals(action)) {
            Attendence pastAttendences = Attendence.findById(Attendence.class,intent.getLongExtra("ID",0));
            pastAttendences.WasPresent = true;
            pastAttendences.save();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel((int)intent.getLongExtra("ID",0));

        }
        else if (AppConstant.STOP_ACTION.equals(action)) {
            Attendence pastAttendences = Attendence.findById(Attendence.class,intent.getLongExtra("ID",0));
            pastAttendences.WasPresent = false;
            pastAttendences.save();
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.cancel((int)intent.getLongExtra("ID",0));
        }

        else if(AppConstant.GRADE_ACTION.equals(action)) {
            Attendence pastAttendences = Attendence.findById(Attendence.class,intent.getLongExtra("ID",0));
            pastAttendences.WasPresent = true;
            pastAttendences.save();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
        }
    }
}
