package com.example.danie.finalproject;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainView extends SurfaceView implements SurfaceHolder.Callback
{
    Context context;
    Player you, opponent;
    private DrawThread drawThread;
    public MainView(Context context) {
        super(context);
        getHolder().addCallback(this);
        //getHolder().setFormat(PixelFormat.RGB_565);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Thread searchThread = new Thread(){
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL("https://peaceful-citadel-74350.herokuapp.com/db?id="+FullscreenActivity.user.id).openConnection();
                    connection.setRequestMethod("GET");
                    StringBuilder content;
                    try (
                            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(connection.getInputStream())))

                    {

                        String line;
                        content = new StringBuilder();

                        while ((line = in.readLine()) != null) {
                            content.append(line);
                            content.append(System.lineSeparator());
                        }
                        line = content.toString();
                        line = line.substring(line.indexOf("<li>"),line.indexOf("</li>"));
                        String[] data = line.split(" ");
                        if(


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        searchThread.start();
        try {
            searchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        drawThread = new DrawThread(getContext(),getHolder());
        drawThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(drawThread.shopClosed) {
            if (event.getX() <= 300 && event.getY() <= 300) {
                Paint paint = new Paint();
                paint.setTextSize(32);
                drawThread.canvas.drawText("damaged", event.getX(), event.getY(), paint);
                DrawThread.iter = (DrawThread.iter == 1) ? 0 : 1;
            }
            else if(event.getY()>=620 && event.getY()<=720 && event.getX()>=500 && event.getX()<=700){
                drawThread.shopClosed = false;

            }
        }else{

        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
