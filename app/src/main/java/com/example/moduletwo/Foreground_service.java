package com.example.moduletwo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.messaging.CommonNotificationBuilder;

public class Foreground_service extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Log.e("Foreground","service is running");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        final String channel_id = "Foreground_service";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channel_id,channel_id, NotificationManager.IMPORTANCE_LOW);
            getSystemService(NotificationManager.class).createNotificationChannel(channel);
            Notification.Builder builder = new Notification.Builder(this,channel_id)
                    .setContentText("service is running")
                    .setContentTitle("Service");

            startForeground(1001,builder.build());
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
