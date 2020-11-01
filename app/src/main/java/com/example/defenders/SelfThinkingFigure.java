package com.example.defenders;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;

import com.example.defenders.Activitys.MainActivity;

public class SelfThinkingFigure extends Figure implements ValueAnimator.AnimatorUpdateListener {
    protected ViewPropertyAnimator animator;
    protected MainActivity mainActivity;

    public SelfThinkingFigure(Context context, MainActivity mainActivity) {
        super(context);
        this.mainActivity = mainActivity;
    }

    // Stoppt die laufende Animation
    public void freeze() {
        animator.cancel();
    }

    // Selbstzerstörung
    // Aus der Lister der Gegner gelöscht
    public void derFreiTot() {
        mainActivity.selfThinkingFiguresList.remove(this);
        animator.cancel();
        setVisibility(View.GONE);
    }

    //Die grobe Kollision Dection die auf Rectangles basiert.
    //Schneiden sich zwei Rectangels und die Figure ist im Positiven Bereich (Soll Enemys die sofort nach dem erscheinen sterben verhindern. Remeinder: Enemys starten ueber null!)
    public boolean kollisionDectionLvl1(Figure objekt) {
        if (getRectangle().intersect(objekt.getRectangle()) && getY() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //Die feine Kollision Dection die mit den Pixeln der Bitmap arbeitet (Siehe KollisionErkennung Klasse!).
    public boolean kollisionDectionLvl2(Figure objekt) {
        if (KollisionsErkennung.isCollisionDetected(getBitmap(), (int) getX(), (int) getY(), objekt.getBitmap(), (int) objekt.getX(), (int) objekt.getY())) {
            return true;
        } else {
            return false;
        }
    }
    //Nicht löschen!!
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
    }
}
