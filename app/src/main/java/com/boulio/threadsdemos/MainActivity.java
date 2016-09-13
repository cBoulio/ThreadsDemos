package com.boulio.threadsdemos;

import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //handler that updates UI
    Handler h = new Handler(){
        @Override
        public void handleMessage(Message msg){
            TextView msgText = (TextView) findViewById(R.id.messageView);
            msgText.setText("Download finished");
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickMe(View view){

        //second thread runs in  the background
        Runnable  r = new Runnable() {
            @Override
            public void run() {
                long futureTime = System.currentTimeMillis()+1000;

                while(System.currentTimeMillis()<futureTime) {
                    synchronized (this) {
                        try {
                            wait(futureTime - System.currentTimeMillis());
                        } catch (Exception e) {

                        }
                    }
                }
                h.sendEmptyMessage(0);
            }
        };
        // Thread
        Thread myThread = new Thread(r);
        myThread.start();



    }
}
