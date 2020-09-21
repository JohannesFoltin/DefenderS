package com.example.defenders;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;

import androidx.annotation.LongDef;

import java.util.Random;
// Ein einzelnes Selbstdenkendes Enemy
public class Enemy extends Figures implements ValueAnimator.AnimatorUpdateListener {
    private Player player;
    private MainActivity mainActivity;
    private static final String TAG = Enemy.class.getSimpleName();
    private ViewPropertyAnimator animator;

//Konstruktor mit Context, dem Spieler und der MainActivity (Damit man Collision aufrufen kann)
    public Enemy(Context context, Player spieler,MainActivity activity) {
        super(context);
        mainActivity = activity;
        player = spieler;
    }
//Bewege dich zu einer bestimmten Y-Coordinate mit einer gewissen geschwindigkeit(Duration)
    // Animatorupdate wird gestartet damit man eine Collision mitbekommt
    // wenn der Gegner seine Y-Coordinate gefunden hat löscht er sich von alleine
    public void move(int ZielY,int Geschwindigkeit){
        animator = animate().setUpdateListener(this).y(ZielY+500).setDuration(Geschwindigkeit);
        derFreiTot();
    }
//Listner der auf eine Collision mit dem PLayer wartet
    // Wenn eine Collision gefunden wird, wird collision aus der Mainklasse aufgerufen
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if((player.getY()-getY())*(player.getY()-getY())+(getX()-player.getX())*(getX()-player.getX()) <= (player.getWidth()/2)+(getWidth()/2)*(player.getWidth()/2)+(getWidth()/2) ){
            //Log.d(TAG, "onAnimationUpdate: COLLISION " + getY() + getX());
            mainActivity.collison(Enemy.this);
        }

    }
    // Zufälliger xWert und negativer Ywert damit der Komet ins bild reinfliegt
    // Runnable da displaywidth noch nicht bekannt
    public void startX(){
        setY(-600);
        post(new Runnable() {
            @Override
            public void run() {
                setVisibility(VISIBLE);
                setX(new Random().nextInt(displaywidht-getWidth()));

            }
        });
    }
    // Stoppt die laufende Animation
    public void freeze(){
        animator.cancel();
    }
    // Selbstzerstörung
    // Aus der Lister der Gegner gelöscht
    public void derFreiTot(){
        mainActivity.enemyList.remove(this);
        setVisibility(View.GONE);
    }
}
