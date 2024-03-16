package com.example.moduletwo.networkinit;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Network_initialization {

    public Context context;
    public ConnectivityManager connectivityManager;
    public Networkcheck networkcheck;

    public Network_initialization(Context context, ConnectivityManager connectivityManager,Networkcheck networkcheck) {
        this.context = context;
        this.connectivityManager = connectivityManager;
        this.networkcheck = networkcheck;
    }

    public void establish_connection(){

        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED){
            networkcheck.check_network(true );

        }else {
            networkcheck.check_network(false);
        }


    }
}
