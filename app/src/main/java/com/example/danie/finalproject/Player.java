package com.example.danie.finalproject;

import com.example.danie.finalproject.API.Loader;

import java.util.ArrayList;

public class Player {

    int id, mmr , status;
    String name, password;
    int money = 3000, edge;

    public ArrayList<Unit> team = new ArrayList<>();

    public Player(String name, String password){
        Player main = new Loader(name, password).getOutNet();
    }
    public Player(int id, String name, String password, int mmr, int status){
        this.id = id;
        this.name = name;
        this.password = password;
        this.mmr = mmr;
        this.status = status;
    }
    public Player(int id, String name,  int mmr, int edge){
        this.id = id;
        this.name = name;
        this.password = password;
        this.mmr = mmr;
        this.edge = edge;
    }
    public void buy_unit(String tileName, int x, int y){
        if(money>=new Tile(tileName).cost) {
            /* post request*/
            new Unit(tileName, x, y);
        }
    }
    //@androidx.annotation.NonNull
    @Override
    public String toString() {
        return name+" "+id+""+mmr;
    }
    public void changeMoney(){
        for(Unit i : team){

        }
    }
}
