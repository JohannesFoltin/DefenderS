package com.example.defenders;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

public class Player extends Figures {
       private ImageView background;
    private static final String TAG = Player.class.getSimpleName();
    private ViewPropertyAnimator animator;



    public Player(Context context) {
        super(context);
    }

    public void setCoordssystem(ImageView Background){
        background = Background;
        background.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                float xZiel = event.getX() - (getWidth()/2);
                float yZiel = event.getY() - (getHeight()/2);
                if (animator != null){
                    return true;
                }
                animator = animate().x(xZiel).y(yZiel).setDuration((long)(pythagoras(xZiel,yZiel)/ Constants.playerSpeed));
                animator.withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        animator = null;
                    }
                });
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
              setVisibility(VISIBLE);
              setX((displaywidht/2)-(getWidth()/2));
              setY(displayheight);
              animate().y(displayheight-1000).setDuration(30);
            }
        });
    }
    private float pythagoras(float kathte1x, float kathte2y){
        return (float) Math.sqrt((double)(((getX()-kathte1x)*(getX()-kathte1x))+((getY()-kathte2y)*(getY()-kathte2y))));
    }
}
