package com.example.danie.finalproject.API;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.danie.finalproject.Player;
import com.example.danie.finalproject.Unit;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Loader {

    HttpURLConnection connection;
    Player outNet;

    public Loader(String name, String password) {

        try {
            outNet = new Load().execute(name, password).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Loader(){

    }
    public static class Onliner extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) new URL("https://peaceful-citadel-74350.herokuapp.com/setter?id=" + integers[0] + "&mode=" + integers[1]).
                        openConnection();
                connection.setRequestMethod("GET");

            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder content;
            try (
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())))

            {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }


                
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    class Load extends AsyncTask<String, String, Player> {
        String o;
        @Override
        protected Player doInBackground(String... strings) {
            try {
                connection = (HttpURLConnection) new URL("https://peaceful-citadel-74350.herokuapp.com/hello?name="+strings[0]+"&password="+strings[1]).
                        openConnection();
                connection.setRequestMethod("GET");
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder content;
            try (
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())))

            {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
                o = content.toString();
                o = o.substring(o.indexOf("<li>"), o.indexOf("</div>"));
                o = o.substring(o.indexOf("<li>")+4, o.indexOf("</li>"));
                String[] outs = o.split(" ");
                int mmr = Integer.parseInt(outs[0]), id = Integer.parseInt(outs[1]);


                return new Player(id, strings[0], strings[1], mmr, 1);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }




    }

    public Player getOutNet() {
        return outNet;
    }
    static class TeaGetter extends AsyncTask<String, Void, ArrayList<Unit>>{
        HttpURLConnection connection;
        @Override
        protected ArrayList doInBackground(String... strings) {
            ArrayList<Unit> result = new ArrayList<>();

            try {
                connection = (HttpURLConnection) new URL("https://peaceful-citadel-74350.herokuapp.com/battle?player1="+strings[0]+"&player2="+strings[1]+"&owner="+strings[2]+"").
                        openConnection();
                connection.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            StringBuilder content;
            try (
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())))

            {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
                line = content.toString();

            }catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        }
    static class TeaSetter extends AsyncTask<String, Void, Void>{
        HttpURLConnection connection;
        @Override
        protected Void doInBackground(String... strings) {
            try {
                connection = (HttpURLConnection) new URL("https://peaceful-citadel-74350.herokuapp.com/hello?name="+strings[0]+"&password="+strings[1]).
                        openConnection();
                connection.setRequestMethod("GET");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}


