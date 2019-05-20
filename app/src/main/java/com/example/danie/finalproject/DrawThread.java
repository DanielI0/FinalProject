package com.example.danie.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.danie.finalproject.API.Loader;

import java.io.IOException;
import java.util.List;

import static com.example.danie.finalproject.Unit.draw;

public class DrawThread extends Thread {

    public static SurfaceHolder surfaceHolder;
    static int c = 2, iter = 0;
    public static Context context;
    public static Bitmap landscapes;
    public static Canvas canvas;
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

        landscapes = BitmapFactory.decodeResource(context.getResources(), R.drawable.landscape_tiles );

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        while (running) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    paint.setColor(Color.DKGRAY);
                    paint.setStyle(Paint.Style.FILL);

                    /*
                            Заполнение ландшафта
                            **Starting match**
                            ** Logging in
                     */

                    for (int width = 0; width < 1280; width+=64)
                        for (int height = 0; height < 720; height+=64)
                                canvas.drawBitmap(landscapes, new Tile("grass_2").getSelf().get(0), new Rect(width, height, width+64, height+64), paint);
                    //Stats
                    canvas.drawRect(new Rect(0, 440, 450, 720),paint );
                    //Shop
                    canvas.drawRect(new Rect(500, 620, 500+200, 720), paint);
                    paint.setColor(Color.WHITE);
                    paint.setTextSize(28);
                    canvas.drawText("Stats:", 10, 470, paint );
                    canvas.drawText("Defend:", 10, 498, paint );
                    canvas.drawText(new Loader("admin", "101").getOutNet().toString(), 10, 526, paint );

                    Unit units[] = new Unit[3];
                    units[0] = new Unit("castle", 0, 0);
                    units[1] = new Unit("castle", 1200, 222);
                    units[2] = new Unit("townhall_1", 128, 128);
                    /*
                            Заворачиваем в функцию
                     */
                    List<Rect> frames = units[0].getSelf();

                    canvas.drawBitmap(DrawThread.landscapes, frames.get(iter%frames.size()), new Rect(units[0].x, units[0].y, units[0].x + units[0].getSize(), units[0].y + units[0].getSize()), new Paint());
                    iter++;

                }  finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);

                }
            }
        }
    }

}