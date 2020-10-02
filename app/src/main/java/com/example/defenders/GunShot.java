package com.example.defenders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

public class GunShot extends SelfThinkingFigure implements ValueAnimator.AnimatorUpdateListener {
      private Player player;

    public GunShot(Context context, Player player, MainActivity mainActivity) {
        super(context,mainActivity);
        this.player = player;
    }
    public void startGunShot(int speed){
        setX(player.getX()-(getWidth()/2));
        setY(player.getY()-(getHeight()/2));
        post(new Runnable() {
            @Override
            public void run() {
                setVisibility(VISIBLE);
            }
        });
        animator = animate().setUpdateListener(this).withEndAction(new Runnable() {
            @Override
            public void run() {
                derFreiTot();
            }
        }).y(-1000).setInterpolator(new LinearInterpolator()).setDuration(speed);

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {


    }
    private float pythagoras(float kathte1x, float kathte2y){
        return (float) Math.sqrt((double)(((getX()-kathte1x)*(getX()-kathte1x))+((getY()-kathte2y)*(getY()-kathte2y))));
    }

}
