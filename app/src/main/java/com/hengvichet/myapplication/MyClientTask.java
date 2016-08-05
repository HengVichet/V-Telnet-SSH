package com.hengvichet.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Vichet on 08/05/2016.
 */
public class MyClientTask extends AsyncTask<Void, Void, String> {

    String dstAddress;
    int dstPort;
    String response = "";

    MyClientTask(String addr, int port){
        dstAddress = addr;
        dstPort = port;
    }

    @Override
    protected String doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            Log.d("telnet", "connecting to " + dstAddress + " on port " + dstPort);
            socket = new Socket(dstAddress, dstPort);

            ByteArrayOutputStream byteArrayOutputStream =
                    new ByteArrayOutputStream(1024);
            byte[] buffer = new byte[1024];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

    /*
     * notice:
     * inputStream.read() will block if no data return
     */
            while ((bytesRead = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, bytesRead);
                response += byteArrayOutputStream.toString("UTF-8");
            }

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        }finally{
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

//    @Override
//    protected void onPostExecute(Void result) {
//        textResponse.setText(response);
//        super.onPostExecute(result);
//    }

}