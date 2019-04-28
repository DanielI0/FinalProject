package com.example.danie.finalproject;

import android.graphics.Bitmap;

import java.util.Map;

public class GameField {

   Bitmap bgIMG = Bitmap.createBitmap("");
    public GameField(){


    }


    public static Player load(String name, String pswrd){
        Map<String, Player.Personalities> players;
        // = data from net

        if (players.get(name)==pswrd){}
    }
    class Player{
        final static short OFFLINE = 0;
        final static short ONLINE = 0;
        short status = OFFLINE;
        int mmr;
        String name;
        private String pswrd;
        public Player(String name, String pswrd){
            this.name = name;
            this.pswrd = pswrd;
            this.mmr = 0;
        }

        public void syncronize(){

        }
        class Personalities{
            @Override
            public String toString() {
                return super.toString();
            }


        }

    }
    public static void rankedMatch(Player p1, Player p2){

    }
}
