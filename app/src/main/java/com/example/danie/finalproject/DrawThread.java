package com.example.danie.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private Context context;
    private volatile boolean running = true;//флаг для остановки потока

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        this.context = context;
    }

    public void requestStop() {
        running = false;
    }
    public static void visualise_Bound(){

    }
    @Override
    public void run() {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    Bitmap bg = BitmapFactory.decodeResource(context.getResources(), R.drawable.game_bg);
                    for (int i = -100; i < 1200; i+=100) {
                        canvas.drawBitmap(bg, i, -120, paint);

                    }


                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);

                }
            }
        }
    }
}