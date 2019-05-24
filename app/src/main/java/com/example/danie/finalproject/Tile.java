package com.example.danie.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    public final static String[] articles = //{"castle", "farmer", "racks", "townhall_2", "townhall_3"};
            {"castle", "farmer", "Archer", "Knighy", "Witcher"};
    //for animations
    protected List<Rect> self = new ArrayList<>();
    //stats
    int cost, income, damage, defend;
    //pos in shop
    public int shopX, shopY;

    public final int DEFAULT_PROJ_SIZE = 128;
    int size = DEFAULT_PROJ_SIZE;
    private final int DEFAULT_TILE_SIZE = 16;
    public boolean is_tile = true;
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
                cost = 100;
                self.add(new Rect(left, top, left + DEFAULT_TILE_SIZE, top + DEFAULT_TILE_SIZE));
                break;
            case "racks":
                cost = 500;
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
                cost = 5000;
                size = DEFAULT_PROJ_SIZE*2;
                self.add(new Rect(left, top, left + 2*DEFAULT_TILE_SIZE, top + 2*DEFAULT_TILE_SIZE));
                break;
            case "townhall_3":
                top = 7*DEFAULT_TILE_SIZE;
                left = 4*DEFAULT_TILE_SIZE;
                cost = 20000;
                size = DEFAULT_PROJ_SIZE*2;
                self.add(new Rect(left, top, left + 2*DEFAULT_TILE_SIZE, top + 2*DEFAULT_TILE_SIZE));
                break;

             /*
                    UI tile properties:
                    40 ???
                        size = 32*32,   left margin = ?
              */

            case "ui_window":
                self.add(new Rect(40, 320,240, 360));
                break;
            case "block":
                self.add(new Rect());
                break;
            case "arrow_right":
                self.add(new Rect(40, 40, 80, 80));
                break;
            default:
                is_tile = false;
                break;
        }
    }

    public int getSize(){
        return size;
    }
    public List<Rect> getSelf() {
        return self;
    }
    public Object getImg(String name, Context context, int x, int y){
        switch (name){
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
                cost = 100;
                self.add(new Rect(left, top, left + DEFAULT_TILE_SIZE, top + DEFAULT_TILE_SIZE));
                break;
            case "racks":
                cost = 500;
                break;
            case "townhall_2":
                top = 7*DEFAULT_TILE_SIZE;
                left = 0;
                cost = 5000;
                size = DEFAULT_PROJ_SIZE*2;
                self.add(new Rect(left, top, left + 2*DEFAULT_TILE_SIZE, top + 2*DEFAULT_TILE_SIZE));
                break;
            case "townhall_3":
                top = 7*DEFAULT_TILE_SIZE;
                left = 4*DEFAULT_TILE_SIZE;
                cost = 20000;
                size = DEFAULT_PROJ_SIZE*2;
                self.add(new Rect(left, top, left + 2*DEFAULT_TILE_SIZE, top + 2*DEFAULT_TILE_SIZE));
                break;
            case "Knighy":
                is_tile = false;
                return new Knighy(context, x, y).model;

            case "Witcher":
                is_tile = false;
                return new Witcher(context, x, y).model;
            case "Archer":
                is_tile = false;
                return  new Archer(context, x , y).model;
        }
        //Override with normal returnings
        return null;
    }

}
