package com.example.defenders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int startSpwanDelay = 1000;
    private float xZiel,yZiel;
    public int displaywidht;
    public int displayheight;
    private Player player;
    private int spwanIntervallGeschwindigkeit = 250;
    private Handler handler = new Handler();
    private Runnable r = new Runnable() {
        public void run() {
            setEnemy(3000);
            handler.postDelayed(r,new Random().nextInt(spwanIntervallGeschwindigkeit-100)+100 );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displaywidht = getResources().getDisplayMetrics().widthPixels;
        displayheight = getResources().getDisplayMetrics().heightPixels;
        player = new Player(this);
        addContentView(player, player.initialize());
        player.setBackgroundResource(R.mipmap.playerraumschiff);
        player.startSequenz();
        ImageView hgrund = findViewById(R.id.hgrund);
        handler.postDelayed(r, startSpwanDelay);
        player.setCoordssystem(hgrund);


    }

    public void setEnemy(int geschwindigkeit){
            final Enemy enemy = new Enemy(this, player, MainActivity.this);
            addContentView(enemy, enemy.initialize());
            enemy.startX();
            enemy.move(displayheight, geschwindigkeit);
            enemy.setBackgroundResource(R.mipmap.meteorid);

    }
    public void collison(Enemy enemy){
        ImageView gameOver = findViewById(R.id.gameover);
        player.stopPlayer();
        gameOver.setVisibility(View.VISIBLE);
        handler.removeCallbacksAndMessages(null);
        enemy.freeze();
    }
}
