package com.example.moduletwo.respostory;

import android.content.Context;

public class Moduletwo_repo {


    public Moduletwo_database moduletwoDatabase;
    public Context context;
    public Boolean is_network_connected = false;
    public Moduletwo_repo(Context context) {
        this.context = context;
        if(moduletwoDatabase==null){
            moduletwoDatabase= new Moduletwo_database(context);
            moduletwoDatabase.Open();
        }

    }


}
