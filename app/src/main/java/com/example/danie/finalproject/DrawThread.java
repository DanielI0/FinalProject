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
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.danie.finalproject.API.Loader;
import java.util.List;

public class DrawThread extends Thread {
    public volatile boolean shopClosed = true, personChanged = false;
    public static SurfaceHolder surfaceHolder;
    static int c = 2, iter = 0, location = 0;
    //location 0 - radiant, 1 - battle , 2 - dire
    public static Context context;
    public static Bitmap landscapes, interfaces;
    public static Canvas canvas;
    public Player you, opponent;

    private volatile boolean running = true;//флаг для остановки потока

    public DrawThread(Context context, SurfaceHolder surfaceHolder, Player you, Player opponent) {
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        this.you = you;
        this.opponent = opponent;
    }


    public void requestStop() {
        running = false;
    }
    public static void visualise_Bound(){

    }
    @Override
    public void run() {

        landscapes = BitmapFactory.decodeResource(context.getResources(), R.drawable.landscape_tiles );
        interfaces = BitmapFactory.decodeResource(context.getResources(), R.drawable.user_interface);
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        paint.setStyle(Paint.Style.STROKE);
        Knighy knighy = new Knighy(context, 100, 100);
        location = you.edge;
        Log.e("DTHR", you.money+" ");
        while (running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(personChanged) {
                you = FullscreenActivity.load();
                personChanged = false;
            }
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {

                try {

                        paint.setColor(Color.DKGRAY);
                        paint.setStyle(Paint.Style.FILL);

                        for (int width = 0; width < canvas.getWidth(); width += 64)
                            for (int height = 0; height < canvas.getHeight(); height += 64)
                                canvas.drawBitmap(landscapes, new Tile("grass_2").getSelf().get(0), new Rect(width, height, width + 64, height + 64), paint);

                        //Stats
                        canvas.drawRect(new Rect(0, canvas.getHeight()-280, 450, canvas.getHeight()), paint);
                        paint.setColor(Color.WHITE);
                    paint.setTextSize(28);

                        canvas.drawText("money: "+you.money, 5, canvas.getHeight()-252, paint);
                        //canvas.drawText("income"+you.calculate_income(), 5 , canvas.getHeight() - 252+28);

                        //Shop

                        canvas.drawRect(new Rect(500, 620, 500 + 200, 720), paint);
                        if (!shopClosed) {
                            drawShop();
                        }
                        switch (location) {
                            case 0:
                            //Next Location
                            canvas.drawRect(new Rect(1260, 290, 1280, 330), paint);


                            paint.setColor(Color.WHITE);
                            paint.setTextSize(28);

                            //Log.e("Null: ", you.toString());
                            canvas.drawText(
                                    //"Stats:",
                                    "you: "+you.name+" "+you.mmr,
                                    10, 470, paint);

                            canvas.drawText("Defend:", 10, 498, paint);
                            //canvas.drawText("id:"+new Loader("admin", "101").getOutNet().id, 10, 526, paint);


                            canvas.drawBitmap(knighy.run[iter], 200, 200, paint);
                            iter = (iter+1)%knighy.run.length;
                            break;
                        case 1:
                            opponent.team =
                            break;
                        case 2:
                            canvas.drawBitmap(landscapes, new Tile("townhall_1").getSelf().get(0), new Rect(canvas.getWidth()-256, canvas.getHeight()-256, canvas.getWidth(), canvas.getHeight()), paint);
                            canvas.drawBitmap((Bitmap) new Tile().getImg("Witcher", context, 100, 100), 100, 100, paint);
                            //canvas.drawBitmap((Bitmap) new Tile().getImg("Knighy", context, 100, 100), 100, 100, paint);
                            break;
                    }
                }  finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);

                }
            }
        }
    }

    public void drawShop(){
        int x = 255, y = 300;
        Tile bitItem;
        Paint locP = new Paint();
        locP.setColor(Color.rgb(62, 36, 26));

        locP.setTextSize(30);
        canvas.drawRect(new Rect(250, 250, 1030, 500), locP);
        canvas.drawBitmap(DrawThread.interfaces, new Tile("ui_window").getSelf().get(0), new Rect(240, 250, 1040, 300), locP);
        locP.setColor(Color.WHITE);
        for(String item : Tile.articles){
            bitItem = new Tile(item);

            if(bitItem.is_tile) canvas.drawBitmap(DrawThread.landscapes, bitItem.getSelf().get(0), new Rect(x,y, x+bitItem.DEFAULT_PROJ_SIZE, y+bitItem.DEFAULT_PROJ_SIZE), new Paint());
            else{canvas.drawBitmap((Bitmap)bitItem.getImg(item, context, 0, 0),x, y, locP);}
            canvas.drawText(bitItem.cost+"$", x, y+bitItem.DEFAULT_PROJ_SIZE, locP);
            x+=bitItem.DEFAULT_PROJ_SIZE;

            if(x>=1030){
                x = 255;
                y+=bitItem.DEFAULT_PROJ_SIZE;
            }
        }
    }
}