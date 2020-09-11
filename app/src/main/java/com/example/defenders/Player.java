package com.example.defenders;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Dimension;

public class Player extends Figures {
       private ImageView background;
    private static final String TAG = Player.class.getSimpleName();


    public Player(Context context) {
        super(context);
    }

    public void setCoordssystem(ImageView Background){
        background = Background;
        background.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                float xZiel = event.getX() - (getWidth()/2);
                float yZiel = event.getY() - (getHeight()/2);
                setX(xZiel);
                setY(yZiel);
                //Log.d(TAG, "onTouch: Move");
                return true;
            }
        });
    }

    public void stopPlayer() {
        background.setOnTouchListener(null);
    }

    public void startSequenz(){
        post(new Runnable() {
            @Override
            public void run() {
              setX((displaywidht/2)-(getWidth()/2));
              setY(displayheight);
              animate().y(displayheight-1000).setDuration(30);
            }
        });
    }

}
