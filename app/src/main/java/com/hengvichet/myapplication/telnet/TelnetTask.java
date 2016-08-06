package com.hengvichet.myapplication.telnet;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Vichet on 08/05/2016.
 */
public class TelnetTask extends AsyncTask<Void, String, String> {

    @Override
    protected String doInBackground(Void... params) {
        TelnetClient telnetClient = null;
        try {
            String ip = "192.168.21.7";
            int port = 23;
            telnetClient = new TelnetClient(ip, port);
            InputStreamReader inputStreamReader = telnetClient.spawnSpy();
            final BufferedReader reader = new BufferedReader(inputStreamReader);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(!Thread.currentThread().isInterrupted()){
                            final String line = reader.readLine();
                            if(line != null) {
                                onProgressUpdate(line);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
