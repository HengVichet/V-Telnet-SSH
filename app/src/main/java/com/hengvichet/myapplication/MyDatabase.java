package com.hengvichet.myapplication;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Vichet on 07/01/2016.
 */
public class MyDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "telnet.db";
    private static final int DATABASE_VERSION = 1;

    public MyDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}