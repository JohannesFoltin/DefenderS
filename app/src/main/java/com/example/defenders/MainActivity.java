package com.example.defenders;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    // Liste für alle Gegner
    public List<Enemy> enemyList = new ArrayList<>();
    private float xZiel,yZiel;
    public int displaywidht;
    public int displayheight;
    private Player player;
    private Handler handler = new Handler();
    private Runnable r = new Runnable() {
        public void run() {
            setEnemy(3000);
            handler.postDelayed(r,new Random().nextInt(Constants.spwanIntervallGeschwindigkeit-100)+100 );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Display Daten bekommen
        displaywidht = getResources().getDisplayMetrics().widthPixels;
        displayheight = getResources().getDisplayMetrics().heightPixels;
        //Player wird erstellt
        player = new Player(this);
        //Player wird zum Contentview hinzugefügt (ins Leben gerufen!)
        addContentView(player, player.initialize());
        player.setBackgroundResource(R.mipmap.startrocketl);
        //Player wird in den Bildschirm geschoben und sichtbar gemacht
        player.startSequenz();
        //Hintergrund wird gesetzt
        ImageView hgrund = findViewById(R.id.hgrund);
        //Hgrund wird mit eienem Gif versehen
        Glide.with(this).load(R.drawable.test).into(hgrund);
        //Gegner Regen wird gestartet. r dabei Runnable.
        handler.postDelayed(r, Constants.startSpwanDelay);
        //PLayer movement wird aktiviert
        player.setCoordssystem(hgrund);


    }
    // Erstellung eines Gegners mit der Initialize methode aus Enemy.
    //startX aufgerufen. Zufällige X Koordinate!
    // Gegner mit move von Oben nach unten gleiten lassen
    //Hintergrund gesetzt
    // Gegner zur gegnerListe hinzugefügt.
    public void setEnemy(int geschwindigkeit){
            final Enemy enemy = new Enemy(this, player, MainActivity.this);
            addContentView(enemy, enemy.initialize());
            enemy.startX();
            enemy.move(displayheight, geschwindigkeit);
            enemy.setBackgroundResource(R.mipmap.meteorid);
            enemyList.add(enemy);
    }
    // bei collision mit etwas alle Meteoriden stoppen und Player unmovable machen
    public void collison(Enemy enemy){
        ImageView gameOver = findViewById(R.id.gameover);
        player.stopPlayer();
        gameOver.setVisibility(View.VISIBLE);
        for (int i = 0; i< enemyList.size();i++){
            enemyList.get(i).freeze();
        }
        handler.removeCallbacksAndMessages(null);
    }
}
