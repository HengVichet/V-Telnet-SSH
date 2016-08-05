package com.hengvichet.myapplication;

import android.app.Activity;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by Vichet on 07/29/2016.
 */
public class Telnet extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telnet);

        final TextView responseTextView = (TextView) findViewById(R.id.responseTextView);

        String ip = "223.223.174.141";
        int port = 21;

        MyClientTask myClientTask = new MyClientTask(ip, port){
            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                responseTextView.setText(response);
            }
        };
        myClientTask.execute();
    }
}
