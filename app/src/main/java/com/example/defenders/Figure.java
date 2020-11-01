package com.example.defenders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
//PLayer und Gegner gehören hier zu/leiten sich von dieser Klasse ab
//Klasse leitet sich von dem ImageView ab
public class Figure extends androidx.appcompat.widget.AppCompatImageView {
    public int displayheight;
    public int displaywidht;

    public Figure(Context context) {
        super(context);
    }
    //Generelle Initializierung eines objektes in die Aktivity
    // Wird invisble gesetzt damit Gegner nicht springen
    public FrameLayout.LayoutParams initialize(){
        displaywidht = getResources().getDisplayMetrics().widthPixels;
        displayheight = getResources().getDisplayMetrics().heightPixels;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
                );
        setVisibility(INVISIBLE);
        return params;
    }

    public Bitmap getBitmap(){
        BitmapDrawable drawable = (BitmapDrawable) getBackground();
        return drawable.getBitmap();
    }

    public Rect getRectangle(){
        Rect rectangle = new Rect((int)getX(),(int)getY(),(int)getX()+getWidth(),(int)getY()+getHeight());
       return rectangle;
    }

}
