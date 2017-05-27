package com.example.tomasz.studiesmenager;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

public class PushReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, DismissActivity.class);

        Intent yesReceive = new Intent();
        yesReceive.putExtra("ID",intent.getLongExtra("ID",0));
        yesReceive.setAction(AppConstant.YES_ACTION);
        PendingIntent pendingIntentYes = PendingIntent.getBroadcast(context, 12345, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent yesReceive2 = new Intent();
        yesReceive2.setAction(AppConstant.STOP_ACTION);
        yesReceive.putExtra("ID",intent.getLongExtra("ID",0));
        PendingIntent pendingIntentYes2 = PendingIntent.getBroadcast(context, 12345, yesReceive2, PendingIntent.FLAG_UPDATE_CURRENT);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(DismissActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent((int)intent.getLongExtra("ID",0), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle("Student Manager")
                .setContentText(intent.getExtras().getBoolean("Present") + " " + intent.getExtras().getString("Date"))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .addAction(R.drawable.tick,"Obecny",pendingIntentYes)
                .addAction(R.drawable.cross,"Nieobecny",pendingIntentYes2)
                .setFullScreenIntent(pendingIntent,false)
                .setColor(Color.WHITE)
                .build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify((int)intent.getLongExtra("ID",0), notification);
    }
}
