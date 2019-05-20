package com.example.danie.finalproject;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainView extends SurfaceView implements SurfaceHolder.Callback
{
    Context context;
    private DrawThread drawThread;
    public MainView(Context context) {
        super(context);
        getHolder().addCallback(this);
        //getHolder().setFormat(PixelFormat.RGB_565);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        /*Menu realisation
            **** Leaderboard
            **** play as *nick // guest*
            **** Settings ?
            Starts thread when press play
        */

        drawThread = new DrawThread(getContext(),getHolder());

        drawThread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getX()<=300 && event.getY()<=300){
            Paint paint = new Paint();
            paint.setTextSize(32);
            drawThread.canvas.drawText("damaged", event.getX(), event.getY(), paint);
            DrawThread.iter = (DrawThread.iter==1) ? 0 : 1;
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
