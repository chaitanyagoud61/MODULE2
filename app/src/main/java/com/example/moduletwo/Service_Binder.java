package com.example.moduletwo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class Service_Binder extends Service {

    public String name ="chaitanya";
    public class Myservicebinder extends Binder {

        public Service_Binder getservice(){
            return Service_Binder.this;
        }
    }
    public IBinder iBinder = new Myservicebinder();
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

}
