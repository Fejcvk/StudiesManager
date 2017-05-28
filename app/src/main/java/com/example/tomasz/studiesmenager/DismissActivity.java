package com.example.tomasz.studiesmenager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.content.Context;
import android.app.NotificationManager;
import android.text.InputType;
import android.widget.EditText;

import com.example.tomasz.studiesmenager.Model.Attendence;


public class DismissActivity extends Activity {
    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";

    private int m_Text = 0;
    @Override
    protected void onStart() {
        super.onStart();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER );

        builder.setView(input);
        builder.setTitle("Wprowadź liczbę punktów");


        // Set up the buttons
        builder.setPositiveButton("Zatwierdź", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = Integer.parseInt(input.getText().toString());
                Attendence pastAttendences = Attendence.findById(Attendence.class,getIntent().getLongExtra("ID",0));
                pastAttendences.PointsEarned = m_Text;
            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }
}