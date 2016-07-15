package com.hengvichet.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button but_load_save, but_save;
    public void init(){
        but_load_save=(Button)findViewById(R.id.but_load_save);
        but_load_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent savelist=new Intent(MainActivity.this,SaveList.class);
                startActivity(savelist);
            }
        });
        but_save=(Button)findViewById(R.id.but_save);
        but_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();

                Intent savelist=new Intent(MainActivity.this,SaveList.class);
                startActivity(savelist);

            }
        });
    }

    private void save() {
        MyDatabase myDatabase = new MyDatabase(getApplicationContext());
        String[] data = {"Cat", "A Pet","A Type"};
        myDatabase.getWritableDatabase().execSQL("INSERT INTO tb_telnet VALUES ( ?, ? ,?)", data);
        Log.d("Database", "added cat record");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
