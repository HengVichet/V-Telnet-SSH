package com.hengvichet.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    public Button but_load_save, but_save,but_connect;
    public void init(){
        but_connect=(Button)findViewById(R.id.but_connect);
        but_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextName = (EditText) findViewById(R.id.textViewName);
                String TName = editTextName.getText().toString();
                EditText editTextIP = (EditText) findViewById(R.id.textViewIP);
                String TIP = editTextIP.getText().toString();
                String Type;
                RadioButton BTelnet = (RadioButton) findViewById(R.id.radioButTelnet);
                boolean checktel = BTelnet.isChecked();
                if (checktel)
                    Type="Telnet";
                else
                    Type="SSH";

                Intent intent = new Intent(getApplicationContext(), Telnet.class);
                startActivity(intent);
            }
        });

        but_save=(Button)findViewById(R.id.but_save);
        but_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
//                Intent savelist=new Intent(MainActivity.this,SaveList.class);
//                startActivity(savelist);
            }
        });
        but_load_save=(Button)findViewById(R.id.but_load_save);
        but_load_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent savelist=new Intent(MainActivity.this,SaveList.class);
                startActivity(savelist);
            }
        });
    }

    private void save() {
        MyDatabase myDatabase = new MyDatabase(getApplicationContext());
        EditText editTextName = (EditText) findViewById(R.id.textViewName);
        String TName = editTextName.getText().toString();
        EditText editTextIP = (EditText) findViewById(R.id.textViewIP);
        String TIP = editTextIP.getText().toString();
        String Type;

        RadioButton BTelnet = (RadioButton) findViewById(R.id.radioButTelnet);
        boolean checktel = BTelnet.isChecked();
        if (checktel)
            Type="Telnet";
        else
            Type="SSH";

        String[] data = {TName, TIP,Type};
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
