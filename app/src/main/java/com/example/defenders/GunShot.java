package com.example.defenders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.animation.LinearInterpolator;

import com.example.defenders.Activitys.MainActivity;
//Ein selbstständiger Gunshot
public class GunShot extends SelfThinkingFigure implements ValueAnimator.AnimatorUpdateListener {
      private Player player;

    public GunShot(Context context, Player player, MainActivity mainActivity) {
        super(context,mainActivity);
        this.player = player;
    }
    //Playerpostion wird ermittelt.
    //Animation wird gestartet
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
    //Kollision mit einem Enemy wird überprüft.
    //Wenn Enemy getroffen, Highscore +1 und Enemy und Gunshot "Sterben"
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        for (int i = 0; i<mainActivity.selfThinkingFiguresList.size();i++){
            if (kollisionDectionLvl1(mainActivity.selfThinkingFiguresList.get(i)) && kollisionDectionLvl2(mainActivity.selfThinkingFiguresList.get(i))){
                mainActivity.setScore();
                mainActivity.selfThinkingFiguresList.get(i).derFreiTot();
                derFreiTot();
            }
        }
    }
}
