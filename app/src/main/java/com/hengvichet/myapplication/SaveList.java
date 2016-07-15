package com.hengvichet.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SaveList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerVeiw);

        MyDatabase myDatabase = new MyDatabase(getApplicationContext()); // open database
        String[] args = {};
        Cursor cursor = myDatabase.getReadableDatabase().rawQuery("SELECT * FROM tb_telnet", args); // query

//        List<Name_List> namelist = new ArrayList<>();
//
//        while (!cursor.isLast()) {
//            cursor.moveToNext();
//            String talName = cursor.getString(0);
//            String telIP = cursor.getString(1);
//            String telType = cursor.getString(2);
//            int qty = cursor.getInt(2);
//            Name_List nameItem = new Name_List(talName,telIP,telType);
//            namelist.add(nameItem);
//        }

        Name_List_Adapter namelistadapter = new Name_List_Adapter();
        namelistadapter.setCursor(cursor);
        recyclerView.setAdapter(namelistadapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }
}
