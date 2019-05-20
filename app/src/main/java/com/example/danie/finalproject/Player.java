package com.example.danie.finalproject;

import com.example.danie.finalproject.API.Loader;

public class Player {

    int id, mmr , status;
    String name, password;

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

    //@androidx.annotation.NonNull
    @Override
    public String toString() {
        return name+" "+id+""+mmr;
    }
}
