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
    String ip;
    int port;

    public TelnetTask(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private TelnetClient telnetClient;



    public void sendCommand(String cmd){
//        if(telnetClient == null || !telnetClient.isConnected()){
//            return;
//        }
        telnetClient.sendCommand(cmd);
    }

    public void close() {
        if(telnetClient != null){
            if (telnetClient.isConnected()) {
                telnetClient.disconnect();
            }
        }
    }

    @Override
    protected String doInBackground(Void... params) {
         telnetClient = null;
        try {

            String ip = "192.168.1.118";
            int port = 23;
            telnetClient = new TelnetClient(ip, port);

            InputStreamReader inputStreamReader = telnetClient.spawnSpy();
            final BufferedReader reader = new BufferedReader(inputStreamReader);

            int read;
            while(true) {
                read = reader.read();
                char ch = (char) read;
                Log.d("TelnetActivity", "ch " + ch);
                String string = ch + "";
                    publishProgress(string);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "success";
    }

}
