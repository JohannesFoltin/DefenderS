package com.example.defenders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewPropertyAnimator;

public class SelfThinkingFigure extends Figure implements ValueAnimator.AnimatorUpdateListener {
    protected ViewPropertyAnimator animator;
    protected MainActivity mainActivity;

    public SelfThinkingFigure(Context context,MainActivity mainActivity) {
        super(context);
        this.mainActivity = mainActivity;
    }
    // Stoppt die laufende Animation
    public void freeze(){
        animator.cancel();
    }
    // Selbstzerstörung
    // Aus der Lister der Gegner gelöscht
    public void derFreiTot(){
        mainActivity.selfThinkingFiguresList.remove(this);
        setVisibility(View.GONE);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {

    }
}
