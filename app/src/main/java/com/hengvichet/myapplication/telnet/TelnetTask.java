package com.hengvichet.myapplication.telnet;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

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
            String ip = "192.168.1.110";
            int port = 23;
            telnetClient = new TelnetClient(ip, port);
            InputStreamReader inputStreamReader = telnetClient.spawnSpy();
            final BufferedReader reader = new BufferedReader(inputStreamReader);

//            while(isCancelled() != true) {
//                final String line = reader.readLine();
//                Log.d("Telnet", "reading line " + line);
//                if(line != null) {
//                    publishProgress(line);
//                }
//            }
            String line;
            while (true){
                line = reader.readLine();
                publishProgress(line);
            }

//            String line;
//            while((line = reader.readLine()) != null) {
//                if(line != null) {
//                publishProgress(line);
//                }
//            }

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        while(!Thread.currentThread().isInterrupted()){
//
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
