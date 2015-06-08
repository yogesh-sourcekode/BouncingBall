package com.example.sourcekode.bouncingball;

import android.os.Message;

/**
 * Created by sourcekode on 03/06/15.
 *
 * Thread for first ball....
 */
public class BallThread extends Thread {

    String Time;

    public BallThread() {

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i=0 ; i<2000 ; i++)
        {

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Message msg = Message.obtain();
            msg.obj = Time;
            MainActivity.messageHandler.sendMessage(msg);

        }

    }
}
