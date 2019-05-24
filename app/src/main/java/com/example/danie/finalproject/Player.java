package com.example.danie.finalproject;

import com.example.danie.finalproject.API.Loader;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

    int id, mmr , status;
    String name, password;
    ArrayList<Unit> squad = new ArrayList<>();
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
        money = 3000;
    }
    public Player(int id, String name,  int mmr, int edge){
        this.id = id;
        this.name = name;
       // this.password = password;
        this.mmr = mmr;
        this.edge = edge;
        money = 3000;
    }


    //@androidx.annotation.NonNull
    @Override
    public String toString() {
        return name+" id: "+id+" "+mmr;
    }
    public void changeMoney(){
        for(Unit i : team){
            money += i.income;
        }
    }
    public void buy_unit(Unit unit){
        money -= unit.cost;
        team.add(unit);
    }

}
