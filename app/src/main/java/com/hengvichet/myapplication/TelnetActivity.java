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
import android.content.Intent;
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
public class TelnetActivity extends AppCompatActivity {

    TelnetClient telnetClient;
    public Button but_go,but_clear;
//    private TelnetTask telnetTask;
    private TextView responseTextView;

    public void init(){
        but_go=(Button)findViewById(R.id.but_go);
        but_clear=(Button)findViewById(R.id.but_clear);
        final EditText commandEditText = (EditText)  findViewById(R.id.commandEditText);

        but_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command = commandEditText.getText().toString();
                sendCommand(command);
                Log.d("Command = ", command);
                commandEditText.setText("");
            }

        });

        but_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                responseTextView.setText("");
            }
        });
    }

    private void sendCommand(String command) {
        responseTextView.append("\n\r");
        telnetClient.sendCommand(command);
//        telnetTask.sendCommand(command);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telnet);

        Intent intent = getIntent();
        final String ip = intent.getStringExtra("IP");
        final int port = intent.getIntExtra("Port", 23);
        init();
        responseTextView = (TextView) findViewById(R.id.responseTextView);

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        telnetClient = new TelnetClient(ip, port);
                        final InputStreamReader a = telnetClient.spawnSpy();
                        final BufferedReader reader = new BufferedReader(a, 5);


                        try {
                            int read;
                            while(!Thread.currentThread().isInterrupted()){
                                read = reader.read();
                                char ch = (char) read;
                                Log.d("TelnetActivity", "" + ch);
                                final String string = ch + "";
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        responseTextView.append(string);
                                    }
                                });

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();



//        telnetTask = new TelnetTask(ip, port){
//            @Override
//            protected void onProgressUpdate(String... values) {
//                super.onProgressUpdate(values);
//                responseTextView.append(values[0]);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                responseTextView.setText("Ended");
//            }
//        };
//
//      //  Log.d("TEst", "connecting");
//
//        telnetTask.execute();

//        telnetTask.cancel(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(telnetClient != null && telnetClient.isConnected()) {
            try {
                telnetClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
