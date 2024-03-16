package com.example.moduletwo.respostory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.database.CursorWindowCompat;

public class Moduletwo_database {

    public Context context;
    public SQLiteDatabase sqLiteDatabase;
    public static String DATABASE_NAME = "MODLE_DATABASE";
    public static int DATABASE_VERSION =1;

    public static final String user="CREATE TABLE IF NOT EXISTS users(id integer primary key autoincrement, user_id int, username text );";
    public static final String location="CREATE TABLE IF NOT EXISTS locations(id integer primary key autoincrement, user_id int, cityname text );";
    public static final String area="CREATE TABLE IF NOT EXISTS areas(id integer primary key autoincrement, user_id int, areaname text );";
    public static final String USER_DATABASE ="users";
    public static final String Location_DATABASE ="locations";
    public static final String Area_DATABASE ="areas";
    public Moduletwo_database_helper moduletwoDatabaseHelper;

    public Moduletwo_database(Context context) {
        this.context = context;
    }
    
    public Moduletwo_database Open() throws SQLException{
        
        moduletwoDatabaseHelper = new Moduletwo_database_helper(context);
        sqLiteDatabase = moduletwoDatabaseHelper.getWritableDatabase();
        
        return this;
        
    }

    public class Moduletwo_database_helper extends SQLiteOpenHelper{

        public Moduletwo_database_helper(Context context) {
            super(context, DATABASE_NAME, null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(user);
            Log.v("TABLE CREATED",USER_DATABASE);

            sqLiteDatabase.execSQL(location);
            Log.v("TABLE CREATED",Location_DATABASE);

            sqLiteDatabase.execSQL(area);
            Log.v("TABLE CREATED",Area_DATABASE);


            sqLiteDatabase.execSQL("INSERT INTO users(user_id,username) VALUES (1,'chaitanya')");
            sqLiteDatabase.execSQL("INSERT INTO users(user_id,username) VALUES (2,'palli')");
            sqLiteDatabase.execSQL("INSERT INTO users(user_id,username) VALUES (3,'jansi')");

            sqLiteDatabase.execSQL("INSERT INTO locations(user_id,cityname) VALUES (1,'vizag')");
            sqLiteDatabase.execSQL("INSERT INTO locations(user_id,cityname) VALUES (2,'hyd')");
            sqLiteDatabase.execSQL("INSERT INTO locations(user_id,cityname) VALUES (3,'bihar')");

            sqLiteDatabase.execSQL("INSERT INTO areas(user_id,areaname) VALUES (1,'gurudwara')");
            sqLiteDatabase.execSQL("INSERT INTO areas(user_id,areaname) VALUES (2,'sangareedi')");
            sqLiteDatabase.execSQL("INSERT INTO areas(user_id,areaname) VALUES (3,'boka')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            if(i<i1){
                
            }
        }
    }
    
    public Cursor getData(){

        Cursor cursor=null;
        try{
            cursor = sqLiteDatabase.rawQuery("Select * from users",null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cursor; 
    }
    
    public Long insert_users(String user_name,int user_id){
        
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_id",user_id);
        contentValues.put("username",user_name);
        return sqLiteDatabase.insert(USER_DATABASE,null,contentValues);
    }

    public void update_users(String user_name,int user_id){

        ContentValues contentValues= new ContentValues();
        contentValues.put("user_id",user_id);
        contentValues.put("username",user_name);

        sqLiteDatabase.update(USER_DATABASE,contentValues,"where user_id='"+user_id+"'",null);

    }

    public void delete_users(){

        Cursor cursor=null;
        try {

        cursor = sqLiteDatabase.rawQuery("Delete from users",null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Cursor getarea_from_user_id(String user_id){

        Cursor cursor=null;
        try {

            cursor= sqLiteDatabase.rawQuery("Select areaname from areas as a" +
                    "INNER JOIN users as u ON u.user_id = a.user_id" +
                    "INNER JOIN locations as l ON l.user_id = u.user_id",null);
        }catch (Exception e){
            e.printStackTrace();
        }

        return cursor;
    }
}
