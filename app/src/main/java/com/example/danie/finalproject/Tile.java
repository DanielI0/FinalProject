package com.example.danie.finalproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    protected List<Rect> self = new ArrayList<>();
    int cost;
    public final int DEFAULT_PROJ_SIZE = 128;
    int size = DEFAULT_PROJ_SIZE;
    private final int DEFAULT_TILE_SIZE = 16;
    int left, top;
    public Tile(){

    }
    //private Bitmap self;
    BitmapFactory.Options options = new BitmapFactory.Options();
    public Tile(String name){

        switch (name){

            case "grass_1":
                left = DEFAULT_TILE_SIZE*2;
                top = 0;

                //self = BitmapFactory.decodeResource(DrawThread.context.getResources(),R.drawable.landscape_tiles, options);
                self.add(new Rect(left, top, left + 16, top +16));
                break;
            case "grass_2":
                left = DEFAULT_TILE_SIZE*3;
                top = 0;
                self.add(new Rect(left, top, left + DEFAULT_TILE_SIZE, top + DEFAULT_TILE_SIZE));
                break;
            case "castle":
                left = DEFAULT_TILE_SIZE*3;
                top = DEFAULT_TILE_SIZE*3;
                //top = 0;
                cost = 2000;
                self.add(new Rect(left, top, left + DEFAULT_TILE_SIZE, top + DEFAULT_TILE_SIZE));

                break;
            case "farmer":
                left = DEFAULT_TILE_SIZE*6;
                top = DEFAULT_TILE_SIZE*13;
                self.add(new Rect(left, top, left + DEFAULT_TILE_SIZE, top + DEFAULT_TILE_SIZE));
                break;
            case "racks":

                break;
            case "townhall_1":
                top = 7*DEFAULT_TILE_SIZE;
                left = 2*DEFAULT_TILE_SIZE;
                size = DEFAULT_PROJ_SIZE*2;
                self.add(new Rect(left, top, left + 2*DEFAULT_TILE_SIZE, top + 2*DEFAULT_TILE_SIZE));
                break;
            case "townhall_2":
                top = 7*DEFAULT_TILE_SIZE;
                left = 0;
                size = DEFAULT_PROJ_SIZE*2;
                self.add(new Rect(left, top, left + 2*DEFAULT_TILE_SIZE, top + 2*DEFAULT_TILE_SIZE));
                break;
            case "townhall_3":
                top = 7*DEFAULT_TILE_SIZE;
                left = 4*DEFAULT_TILE_SIZE;
                size = DEFAULT_PROJ_SIZE*2;
                self.add(new Rect(left, top, left + 2*DEFAULT_TILE_SIZE, top + 2*DEFAULT_TILE_SIZE));
                break;
            case "flag_1":

                break;
        }
    }
    public int getSize(){
        return size;
    }
    public List<Rect> getSelf() {
        return self;
    }

}
