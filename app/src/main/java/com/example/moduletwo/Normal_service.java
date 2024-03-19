package com.example.moduletwo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class Normal_service extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

         new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Log.e("Service", "service is running");
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
