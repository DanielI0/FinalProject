package com.example.danie.finalproject;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.danie.finalproject.FullscreenActivity.load;

public class MainView extends SurfaceView implements SurfaceHolder.Callback
{
    Context context;

    Player you, opponent, user = load();
    private DrawThread drawThread;
    public MainView(Context context) {
        super(context);
        getHolder().addCallback(this);
        //getHolder().setFormat(PixelFormat.RGB_565);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        user = load();
        Thread searchThread = new Thread(){
            String line = " ";
            boolean havePair = false;
            @Override
            public void run() {
                while(you==null ) {
                    try {
                        HttpURLConnection connection = (HttpURLConnection) new URL("https://peaceful-citadel-74350.herokuapp.com/db?id=" + user.id).openConnection();
                        connection.setRequestMethod("GET");
                        StringBuilder content;
                        try (
                                BufferedReader in = new BufferedReader(
                                        new InputStreamReader(connection.getInputStream())))

                        {


                            content = new StringBuilder();

                            while ((line = in.readLine()) != null) {
                                content.append(line);
                                content.append(System.lineSeparator());
                            }
                            line = content.toString();
                            line = line.substring(line.indexOf("<li>"), line.indexOf("</li>"));
                            line = line.substring(4);
                            String[] data = line.split(" ");
                            Log.e("WLC", data[0]+" "+data[1]+" "+data[3]+" "+data[4]);
                            //if(data[0].equals("resume")) continue;

                            if (user.id == Integer.parseInt(data[0])) {
                                you = new Player(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), 0);
                                Log.e("WLC", you.toString());
                                opponent = new Player(Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5]), 2);
                            }else {
                                opponent = new Player(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), 0);
                                you = new Player(Integer.parseInt(data[3]), data[4], Integer.parseInt(data[5]), 2);
                            }

                        }
                    } catch (Exception e) {

                        e.printStackTrace();

                    }
                }
            }
        };
        searchThread.start();
        try {
            searchThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        Log.e("MyLog:", you.toString()+" " +opponent.toString() );
        drawThread = new DrawThread(getContext(),getHolder(),you, opponent);
        //drawThread = new DrawThread(getContext(), getHolder());
        drawThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(drawThread.shopClosed) {
            if(event.getY()>=620 && event.getY()<=720 && event.getX()>=500 && event.getX()<=700){
                drawThread.shopClosed = false;

            }
        }else{
            float x = event.getX(), y = event.getY();
            int iter = 0;
            for(int j = 300;j<y;j+=128)
            for (int i = 255; i < ((j+128 > y)? x:1030); i+=128) {
                iter ++;
            }Log.e("WLC", ""+iter);
            try {
                //user.buy_unit(, (int) x, (int) y));
                user.money -= new Unit(new Tile(Tile.articles[iter-1]),(int) x, (int) y).cost;
                user.team.add(new Unit(new Tile(Tile.articles[iter-1]),(int) x, (int) y));
                Log.e("wlc", ""+new Unit(new Tile(Tile.articles[iter-1]), (int) x, (int) y).cost);
                FullscreenActivity.save(user);
                drawThread.personChanged = true;

            }catch (IndexOutOfBoundsException e){
                drawThread.shopClosed = true;
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
