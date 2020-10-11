package com.example.defenders;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    // Liste für alle Gegner
    public List<SelfThinkingFigure> selfThinkingFiguresList = new ArrayList<>();


    public int displaywidht;
    public int displayheight;
    private int score = 0;

    private Player player;
    private TextView scoreView;

    private Handler handler = new Handler();
    private Runnable r = new Runnable() {
        public void run() {
            setEnemy(ChangableMembers.getEnemyDuration());
            handler.postDelayed(r,new Random().nextInt(ChangableMembers.getEnemyIntervallGeschwindigkeit()-50)+50 );
        }
    };
    private Handler handler2 = new Handler();
    private Runnable r2 = new Runnable() {
        public void run() {
            spwanGunShot(ChangableMembers.getMissleSpeed());
            handler2.postDelayed(r2,ChangableMembers.getMissleIntervallGeschwindigkeit());
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
        player = new Player(this,MainActivity.this);
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
        handler2.postDelayed(r2,100);
        scoreView = findViewById(R.id.score);
        scoreView.setText("" + score);

    }
    // Erstellung eines Gegners mit der Initialize methode aus Enemy.
    //startX aufgerufen. Zufällige X Koordinate!
    // Gegner mit move von Oben nach unten gleiten lassen
    //Hintergrund gesetzt
    // Gegner zur gegnerListe hinzugefügt.
    public void setEnemy(int geschwindigkeit){
            final Enemy enemy = new Enemy(this, player, MainActivity.this,8);
            addContentView(enemy, enemy.initialize());
            enemy.startX();
            enemy.move(displayheight, geschwindigkeit);
            enemy.setBackgroundResource(R.mipmap.meteorid);
            selfThinkingFiguresList.add(enemy);
    }
    public void spwanGunShot(int speed){
        final GunShot gunShot = new GunShot(this,player,MainActivity.this);
        addContentView(gunShot, gunShot.initialize());
        gunShot.startGunShot(speed);
        gunShot.setBackgroundResource(R.mipmap.gunshot);
        selfThinkingFiguresList.add(gunShot);
    }
    // bei collision mit etwas, alle Meteoriden stoppen und Player unmovable machen
    public void collison(Enemy enemy){
        ImageView gameOver = findViewById(R.id.gameover);
        player.stopPlayer();
        gameOver.setVisibility(View.VISIBLE);
        for (int i = 0; i< selfThinkingFiguresList.size(); i++){
            selfThinkingFiguresList.get(i).freeze();
        }
        handler.removeCallbacksAndMessages(null);
        handler2.removeCallbacksAndMessages(null);
    }
    //Ein Highscore wird hochgezählt
    public void setScore(){
    score++;
    scoreView.setText(""+score);
    }
}
