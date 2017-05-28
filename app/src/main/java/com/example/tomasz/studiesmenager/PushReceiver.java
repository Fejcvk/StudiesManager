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

import java.util.Random;

public class PushReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent notificationIntent = new Intent(context, DismissActivity.class);

        Intent yesReceive = new Intent();
        yesReceive.putExtra("ID",intent.getLongExtra("ID",0));
        yesReceive.setAction(AppConstant.YES_ACTION);
        PendingIntent pendingIntentYes = PendingIntent.getBroadcast(context,new Random().nextInt(3929) , yesReceive, PendingIntent.FLAG_ONE_SHOT);

        Intent yesReceive2 = new Intent();
        yesReceive2.putExtra("ID",intent.getLongExtra("ID",0));
        yesReceive2.setAction(AppConstant.STOP_ACTION);
        PendingIntent pendingIntentYes2 = PendingIntent.getBroadcast(context, new Random().nextInt(3929), yesReceive2, PendingIntent.FLAG_ONE_SHOT);

        Intent yesReceive3 = new Intent();
        yesReceive3.putExtra("ID",intent.getLongExtra("ID",0));
        yesReceive3.setAction(AppConstant.GRADE_ACTION);
        PendingIntent pendingIntentYes3 = PendingIntent.getBroadcast(context, new Random().nextInt(3929), yesReceive3, PendingIntent.FLAG_ONE_SHOT);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(DismissActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent((int)intent.getLongExtra("ID",0), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        Notification notification = builder.setContentTitle("Czy byłeś na zajęciach?")
                .setContentText(intent.getExtras().getString("Subject") + " " + intent.getExtras().getString("Date"))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .addAction(R.drawable.tick,"TAK",pendingIntentYes)
                .addAction(R.drawable.cross,"NIE",pendingIntentYes2)
                .addAction(R.drawable.grade,"PKT",pendingIntentYes3)
                .setFullScreenIntent(pendingIntent,true)
                .setColor(Color.WHITE)
                .build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify((int)intent.getLongExtra("ID",0), notification);

    }
}
