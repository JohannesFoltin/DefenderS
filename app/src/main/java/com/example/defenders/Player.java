package com.example.defenders;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import com.example.defenders.Activitys.MainActivity;

public class Player extends Figure {
    private ImageView background;
    private static final String TAG = Player.class.getSimpleName();
    private ViewPropertyAnimator animator;
    private MainActivity mainActivity;


    public Player(Context context, MainActivity mainActivity) {
        super(context);
        this.mainActivity = mainActivity;
    }

    //Playermovement
    //Verhinderung von Sprüngen
    //wird bei jedem kleinsten Bewegen des Fingers auf die X und Y Koordinate gesetzt
    //return true sehr wichtig!
    public void setCoordssystem(ImageView Background) {
        background = Background;
        background.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                float xZiel = event.getX() - (getWidth() / 2);
                float yZiel = event.getY() - (getHeight() / 2);
                if (pythagoras(xZiel, yZiel) <= 150) {
                    setX(xZiel);
                    setY(yZiel);
                }
                // mainActivity.spwanGunShot(100);
                //Log.d(TAG, "onTouch: Move");
                return true;
            }
        });
    }

    //Macht den Player bewegungsunfähig
    public void stopPlayer() {
        background.setOnTouchListener(null);
    }
    //Player wird ins Bild geschoben
    //Er wird erst jetzt visible gesetzt wegen den enemys
    public void startSequenz() {
        post(new Runnable() {
            @Override
            public void run() {
                setVisibility(VISIBLE);
                setX((displaywidht / 2) - (getWidth() / 2));
                setY(displayheight);
                animate().y(displayheight - 1000).setDuration(30);
            }
        });
    }

    //Berechnung von Entferung durch Pythagoras.
    // XZiel und YZiel
    // PLayerposition wird genutzt
    private float pythagoras(float kathte1x, float kathte2y) {
        return (float) Math.sqrt((double) (((getX() - kathte1x) * (getX() - kathte1x)) + ((getY() - kathte2y) * (getY() - kathte2y))));
    }
}
