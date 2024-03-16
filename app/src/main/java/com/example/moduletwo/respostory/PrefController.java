package com.example.moduletwo.respostory;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefController {

    public static void saveData(String key, String value, Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("Pref_database",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static String getData_from_key(String key,String value,Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("Pref_database",Context.MODE_PRIVATE);
        String valu = sharedPreferences.getString(key,"");
        return valu;
    }

    public static void delete_data(String key,String value,Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Pref_database",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);

    }
}


