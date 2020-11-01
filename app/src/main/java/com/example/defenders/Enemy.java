package com.example.defenders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.animation.LinearInterpolator;

import com.example.defenders.Activitys.MainActivity;

import java.util.Random;

// Ein einzelnes Selbstdenkendes Enemy
public class Enemy extends SelfThinkingFigure {
    private Player player;
    private int lives;
    private static final String TAG = Enemy.class.getSimpleName();

    //Konstruktor mit Context, dem Spieler und der MainActivity (Damit man Collision aufrufen kann)
    public Enemy(Context context, Player spieler, MainActivity activity, int Leben) {
        super(context, activity);
        player = spieler;
        lives = Leben;

    }

    // Bewege dich zu einer bestimmten Y-Coordinate mit einer gewissen geschwindigkeit(Duration)
    // Animatorupdate wird gestartet damit man eine Collision mitbekommt
    // wenn der Gegner seine Y-Coordinate gefunden hat löscht er sich von alleine
    public void move(int ZielY, int Geschwindigkeit) {
        animator = animate().setUpdateListener(this).setInterpolator(new LinearInterpolator()).y(ZielY).setDuration(Geschwindigkeit).withEndAction(new Runnable() {
            @Override
            public void run() {
                derFreiTot();
            }
        });

    }

    //Jedes mal wenn sich die Position (Somit auch die Animation) verändert wird mittels zwei Kollisionserkennungen (lvl1 und lvl2 siehe SelfThinkingFigure)
    //eine Kollsion zwischen Enemy und Player überprüft.
    //Wenn eine Kollision erkannt wird wird die Collisionsklasse in der MainActivity aufgerufen.
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if (kollisionDectionLvl1(player)) {
            if (kollisionDectionLvl2(player)) {
                mainActivity.collison();
            }
        }
    }

    // Zufälliger xWert und negativer Ywert damit der Komet ins bild reinfliegt
    // Runnable da displaywidth noch nicht bekannt
    public void startX() {
        setY(-600);
        post(new Runnable() {
            @Override
            public void run() {
                setVisibility(VISIBLE);
                setX(new Random().nextInt(displaywidht - getWidth()));

            }
        });
    }

    public void hitByMissle() {
        lives = lives - 1;
    }
}
