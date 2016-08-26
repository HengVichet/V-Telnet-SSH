package com.hengvichet.myapplication;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hengvichet.myapplication.telnet.TelnetClient;
import com.hengvichet.myapplication.telnet.TelnetTask;


/**
 * Created by Vichet on 07/29/2016.
 */
public class Telnet extends AppCompatActivity {

    public Button but_go;
    private TelnetTask telnetTask;
    private TextView responseTextView;

    public void init(){
        but_go=(Button)findViewById(R.id.but_go);
        final EditText commandEditText = (EditText)  findViewById(R.id.commandEditText);

        but_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command = commandEditText.getText().toString();
                sendCommand(command);

//                TelnetClient telnetClient = null;
//                OutputStream outputStream = telnetClient.getOutputStream();
            }
        });
    }

    private void sendCommand(String command) {
        responseTextView.append("\n");
        telnetTask.sendCommand(command);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telnet);

        init();
        responseTextView = (TextView) findViewById(R.id.responseTextView);

        telnetTask = new TelnetTask(){
            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                responseTextView.append(values[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                responseTextView.setText("Ended");
            }
        };

        Log.d("TEst", "connecting");

        telnetTask.execute();

//        telnetTask.cancel(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        telnetTask.close();
    }
}
