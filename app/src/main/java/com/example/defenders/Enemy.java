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

public class Enemy extends Figures implements ValueAnimator.AnimatorUpdateListener {
    private Player player;
    private MainActivity mainActivity;
    private static final String TAG = Enemy.class.getSimpleName();
    private ViewPropertyAnimator animator;


    public Enemy(Context context, Player spieler,MainActivity activity) {
        super(context);
        mainActivity = activity;
        player = spieler;
    }

    public void move(int ZielY,int Geschwindigkeit){
        animator = animate().setUpdateListener(this).y(ZielY+500).setDuration(Geschwindigkeit);
        derFreiTot();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        if((player.getY()-getY())*(player.getY()-getY())+(getX()-player.getX())*(getX()-player.getX()) <= (player.getWidth()/2)+(getWidth()/2)*(player.getWidth()/2)+(getWidth()/2) ){
            //Log.d(TAG, "onAnimationUpdate: COLLISION " + getY() + getX());
            mainActivity.collison(Enemy.this);
        }

    }
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
    public void freeze(){
        animator.cancel();
    }

    public void derFreiTot(){
        mainActivity.enemyList.remove(this);
        setVisibility(View.GONE);
    }
}
