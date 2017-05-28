package com.example.tomasz.studiesmenager;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

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
            Intent i = new Intent();
            i.setClassName("com.test", "com.test.MainActivity");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
    }
}
