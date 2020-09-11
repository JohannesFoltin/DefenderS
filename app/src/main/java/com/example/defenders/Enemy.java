package com.example.defenders;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.LongDef;

import java.util.Random;

public class Enemy extends Figures implements ValueAnimator.AnimatorUpdateListener {
    private Player player;
    private MainActivity mainActivity;
    private static final String TAG = Enemy.class.getSimpleName();



    public Enemy(Context context, Player spieler,MainActivity activity) {
        super(context);
        mainActivity =activity;
        player = spieler;
    }

    public void move(int ZielY,int Geschwindigkeit){
        animate().setUpdateListener(this).y(ZielY).setDuration(Geschwindigkeit);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if((player.getY()-getY())*(player.getY()-getY())+(getX()-player.getX())*(getX()-player.getX()) <= (player.getWidth()/2)+(getWidth()/2)*(player.getWidth()/2)+(getWidth()/2) ){
            //Log.d(TAG, "onAnimationUpdate: COLLISION " + getY() + getX());
            mainActivity.collison();
        }

    }
    public void startX(){
        post(new Runnable() {
            @Override
            public void run() {
                setX(new Random().nextInt(displaywidht-getWidth()));
            }
        });
    }
}
