package com.example.sourcekode.bouncingball;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends Activity {
    static int xmax;
    static int ymax;
    static int x;
    static boolean flag=true;
    static boolean flag2=true;
    static TextView valueTV;
    static View linearLayout;
    static int i=450,a,b;
    static int j,temp=-1,temp2;
    static int g, h;

    static Context cntxt;
    private static RelativeLayout rlParent,relayout;
    private BallThread bThread=new BallThread();
    private SecondThread sThread = new SecondThread();
    private Handler lhandler;
    Button start,stop,Dstart,Dstop;
    ImageView imgball,imgball2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cntxt = getApplicationContext();
        linearLayout = findViewById(R.id.rlayout);
        rlParent = (RelativeLayout) findViewById(R.id.rlParent);
        relayout=(RelativeLayout) findViewById(R.id.relayout);
        start = (Button) findViewById(R.id.butstart);
        stop = (Button) findViewById(R.id.butstop);
        Dstart=(Button)findViewById(R.id.DStart);
        Dstop=(Button) findViewById(R.id.Dstop);
        imgball=(ImageView) findViewById(R.id.imgball);
        imgball2=(ImageView) findViewById(R.id.imgball2);

        imgball.setVisibility(View.GONE);
        imgball2.setVisibility(View.GONE);
        DisplayMetrics displaymetrics = getApplicationContext().getResources().getDisplayMetrics();
        int height = displaymetrics.heightPixels;
        ymax = height;
        int width = displaymetrics.widthPixels;
        xmax = width;
        x = xmax;

        Log.i("xmax= ", String.valueOf(xmax));
        Log.i("ymax= ", String.valueOf(ymax));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);

        valueTV = new TextView(this);
        valueTV.setLayoutParams(params);

        ((RelativeLayout) linearLayout).addView(valueTV);



        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                imgball.setVisibility(View.VISIBLE);

                if (i == 450) {
                    bThread.start();
                } else {
                    flag = true;
                    System.out.println("flag=" + flag);
                }
            }
        });



        Dstart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                imgball2.setVisibility(View.VISIBLE);

                if(a==0&&b==0){
                    sThread.start();
                }
                else {
                    flag2 =true;
                    System.out.println("flag="+flag);
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flag=false;

            }
        });
        Dstop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flag2=false;

            }
        });

    }


    //..........Handler For First Ball...................................

    public static Handler messageHandler = new Handler() {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(flag==true) {
                if (i < xmax - 250&&j<(ymax-550)/2&&j>temp) {
                    System.out.println("In if");
                    updateLocation(i, j);
                    temp=j;
                    i = i + 10;/*j=j+10*/
                    j=j+15;

                } else if (j < ymax - 600 && i >=xmax-650) {
                    System.out.println("In if else 1");
                    updateLocation(i, j);
                    j = j + 15;
                    i= i-10;
                } else if (i >= 0&&j>(ymax-500)/2) {
                    System.out.println("In if else 2");
                    updateLocation(i, j);

                    i = i-10;
                    j=j-15;
                    g=i ; h=j;
                } else if (h >= 0) {
                    System.out.println("In if else 3");
                    if(h>0) {
                        updateLocation(g, h);
                        h = h - 15;
                        g = g + 10;
                    }
                    else
                    {
                        i=450;
                        j=0;
                        temp=j;
                        //updateLocation(i,j);
                    }
                }
            }


        }
    };



    //........Handler For Second ball..........

    public static Handler messageHandler2 = new Handler() {

        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(flag2==true) {
                if (a < xmax - 250&&b==0) {
                    System.out.println("In if");
                    updateLocationSecond(a, b);
                    //temp2=b;
                    a = a + 10;

                } else if (b < ymax - 600 && a ==xmax-250) {
                    System.out.println("In if else 1");
                    updateLocationSecond(a, b);
                    b = b + 10;

                } else if (a > 0) {
                    System.out.println("In if else 2");
                    updateLocationSecond(a, b);

                    a = a-10;

                } else if (b >= 0&&a==0) {
                    System.out.println("In if else 3");
                    if(b>0) {
                        updateLocationSecond(a, b);
                        b = b - 10;

                    }
                    else
                    {
                        a=0;
                        b=0;
                    }
                }
            }


        }
    };

//...............update Location of second ball imag.................

    protected static void updateLocationSecond(int i, int j) {
        // TODO Auto-generated method stub

        Log.d("MA","MACTIVITY");

        AbsoluteLayout.LayoutParams param = new AbsoluteLayout.LayoutParams(120, 120, i,j);
        relayout.setLayoutParams(param);

        Log.d("MA","MACTIVITY i and j "+i+j);

    }


//............Update Location of first Ball img..........................

    protected static void updateLocation(int i, int j) {
        // TODO Auto-generated method stub

        Log.d("MA","MACTIVITY");

        AbsoluteLayout.LayoutParams param = new AbsoluteLayout.LayoutParams(120, 120, i,j);
        rlParent.setLayoutParams(param);

        Log.d("MA","MACTIVITY i and j "+i+j);

    }


}
