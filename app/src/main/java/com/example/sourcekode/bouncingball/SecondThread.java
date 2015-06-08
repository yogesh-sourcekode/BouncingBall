package com.example.sourcekode.bouncingball;

import android.os.Message;
/**
 * Created by sourcekode on 08/06/15.
 *
 *
 * Thread for second ball...
 */


public class SecondThread extends Thread {

    String Time;

    public SecondThread() {

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i=0 ; i<=3000 ; i++)
        {

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Message msg = Message.obtain();
            msg.obj = Time;
            MainActivity.messageHandler2.sendMessage(msg);

        }

    }
}