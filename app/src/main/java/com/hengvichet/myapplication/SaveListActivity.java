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

public class SaveListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerVeiw);


        Cursor cursor = DatabaseHelper.getAll(getApplicationContext());


        Name_List_Adapter namelistadapter = new Name_List_Adapter(getApplicationContext());
        namelistadapter.setCursor(cursor);
        recyclerView.setAdapter(namelistadapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

    }
}
