package com.example.tomasz.studiesmenager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.app.NotificationManager;


public class DismissActivity extends Activity {
    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";


    @Override
    protected void onStart() {
        super.onStart();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancel(getIntent().getIntExtra(NOTIFICATION_ID,-1));

    }
    public static PendingIntent getDismissIntent(int notificationId, Context context) {
        Intent intent = new Intent(context, DismissActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
        intent.putExtra(NOTIFICATION_ID, notificationId);
        PendingIntent dismissIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        return dismissIntent;
    }

}