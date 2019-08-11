package com.notificationdemo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NotificationManager notificationManager;
    private static final String NOTIFICATION_CHANNEL_ID = "4565";
    private static final String NOTIFICATION_CHANNEL_NAME = "location";


    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    public void showNotification(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new Notification.Builder(this).
                    setSmallIcon(R.drawable.ic_error_black_24dp).
                    setContentTitle("Main Title").
                    setContentText("Message: (some message)")
                    .setChannelId(NOTIFICATION_CHANNEL_ID).
                            build();

            //now we need to go to notification manager
            notificationManager.notify(1, notification);
        }
        else {
            makeNotification();
        }



    }


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = null;
            notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            if(notificationChannel != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }





    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void makeNotification() {

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        Notification notification = new Notification.Builder(this).
                setSmallIcon(R.drawable.ic_error_black_24dp).
                setContentTitle("Main Title").
                setContentText("Message: (some message)").
                setContentIntent(pendingIntent).
                setSound(alarmSound).
                build();

        //now we need to go to notification manager
        notificationManager.notify(1, notification);

    }
}