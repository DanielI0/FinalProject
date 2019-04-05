package com.example.danie.finalproject;

import android.graphics.Bitmap;

public class GameField {
    Medusa self = new Medusa();
    Bitmap bgIMG = Bitmap.createBitmap("");
    class Medusa{
       int hp = 100;

        float posX, posY;
        public Medusa(){

        }

    }
    class Bound{
        float x, y;
    }
    class Trap{

    }
}
