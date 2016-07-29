package com.hengvichet.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * Created by Vichet on 07/22/2016.
 */
public class DatabaseHelper {

    public static Cursor getAll(Context context) {
        MyDatabase myDatabase = new MyDatabase(context); // open database
        String[] args = {};
        Cursor cursor = myDatabase.getReadableDatabase().rawQuery("SELECT * FROM tb_telnet", args); // query
        return cursor;
    }

    public static void save(Context context, String ip, String name, String type) {
        MyDatabase myDatabase = new MyDatabase(context);
        String[] data = {ip, name, type};
        myDatabase.getWritableDatabase().execSQL("INSERT INTO tb_telnet VALUES ( ?, ? ,?)", data);
        //Log.d("Database", "added cat record");
    }
    public static void delete(Context context, String name){
        MyDatabase myDatabase = new MyDatabase(context);
        String[] data = {name};
        myDatabase.getWritableDatabase().execSQL("DELETE FROM tb_telnet WHERE name = ?", data );

    }
}

