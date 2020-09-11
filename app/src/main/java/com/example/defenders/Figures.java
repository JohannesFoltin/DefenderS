package com.example.defenders;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Figures extends androidx.appcompat.widget.AppCompatImageView {
    public int displayheight;
    public int displaywidht;

    public Figures(Context context) {
        super(context);
    }
    public FrameLayout.LayoutParams initialize(){
        displaywidht = getResources().getDisplayMetrics().widthPixels;
        displayheight = getResources().getDisplayMetrics().heightPixels;
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        return params;
    }
}
