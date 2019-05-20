package com.example.danie.finalproject.API;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.danie.finalproject.Player;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
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
}


