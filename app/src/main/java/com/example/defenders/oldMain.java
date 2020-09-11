package com.example.defenders;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

//public class oldMain {
//    private int xZiel,yZiel;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final ImageView Stern = findViewById(R.id.imageView);
//        final ImageView hgrund = findViewById(R.id.hgrund);
//        TextView ausgabe = findViewById(R.id.Ausgabe);
//        final int displaywidht = getResources().getDisplayMetrics().widthPixels;
//        final int displayheight = getResources().getDisplayMetrics().heightPixels;
//        ausgabe.setText("Höhe: "+ displayheight + "breite: "+displaywidht);
//        //for (int i = 0; i <= 3 ; i++){
//        setEnemy(new Random().nextInt(displaywidht),0);
//        //}
//        hgrund.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                xZiel = (int)event.getX();
//                yZiel = (int)event.getY();
//                Stern.animate().y(yZiel).x(xZiel).setDuration(30);
//                return false;
//            }
//        });
//
//    }
//    public void setEnemy(final int ortx, int orty){
//        final ImageView Name= new ImageView (this);
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
//                FrameLayout.LayoutParams.WRAP_CONTENT,
//                FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.topMargin = 0;
//        params.leftMargin = 50;
//        params.gravity = Gravity.TOP;
//        Name.setBackgroundResource(R.mipmap.meteorid);
//        addContentView(Name, params);
//        Name.animate().x(50).y(2500).setDuration(1000);
//        Name.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Name.setVisibility(View.INVISIBLE);
//                return false;
//            }
//        });
//    }
//}
