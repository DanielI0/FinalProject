package com.example.danie.finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.view.SurfaceHolder;

import java.io.Serializable;
import java.util.List;

public class Unit implements Serializable {
    // Image
    public static volatile boolean draw = true;
    Bitmap[] run,attack;
    Bitmap model;
    //int frames = 1;
    // Location
    public int x, y;
    int cost, income, damage, hp, range, ms;
    public void stopDraw(){
        draw = false;
    }

    public Unit(Tile tile, int x, int y) {
        //this.type = new Tile(name);
        this.x = x;
        this.y = y;
        this.cost = tile.cost;
    }
    public Unit(){

    }

    /*
    @Override
    public List<Rect> getSelf() {
        return type.getSelf();
    }

    @Override
    public int getSize() {
        return type.getSize();
    }

    public static void drawUnits(Unit[] units, Canvas canvas,  Paint paint){


        for (Unit i : units){

            if(i.getSelf().size()==1)
            canvas.drawBitmap(DrawThread.landscapes, i.getSelf().get(0), new Rect(i.x, i.y, i.x+i.getSize(), i.y+i.getSize()), paint);

        }
    }
*/

}
