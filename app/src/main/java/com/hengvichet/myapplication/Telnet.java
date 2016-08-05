package com.hengvichet.myapplication;

import android.app.Activity;

import java.io.BufferedReader;
import java.io.IOException;
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
import android.widget.TextView;

import com.hengvichet.myapplication.telnet.TelnetClient;
import com.hengvichet.myapplication.telnet.TelnetTask;


/**
 * Created by Vichet on 07/29/2016.
 */
public class Telnet extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telnet);

        final TextView responseTextView = (TextView) findViewById(R.id.responseTextView);

        TelnetTask telnetTask = new TelnetTask(){
            @Override
            protected void onProgressUpdate(String... values) {
                super.onProgressUpdate(values);
                responseTextView.setText(values[0]);
            }
        };

        Log.d("TEst", "connecting");

        telnetTask.execute();

    }


}
