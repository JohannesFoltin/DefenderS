package com.example.defenders.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.defenders.ChangableMembers;
import com.example.defenders.Constants;
import com.example.defenders.Enemy;
import com.example.defenders.GunShot;
import com.example.defenders.Player;
import com.example.defenders.R;
import com.example.defenders.Score;
import com.example.defenders.SelfThinkingFigure;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();


    // Liste für alle Gegner
    public List<SelfThinkingFigure> selfThinkingFiguresList = new ArrayList<>();
    private int[] meteoridenBilderListe = new int[]{R.mipmap.smallmeteorid,R.mipmap.meteorid,R.mipmap.bigmeteorid};

    public int displaywidht;
    public int displayheight;
    private int score = 0;

    private Player player;
    private TextView scoreView;

    private Handler handler = new Handler();
    private Runnable r = new Runnable() {
        public void run() {
            setEnemy(ChangableMembers.getEnemyDuration());
            handler.postDelayed(r, new Random().nextInt(ChangableMembers.getEnemyIntervallGeschwindigkeit() - 50) + 50);
        }
    };
    private Handler handler2 = new Handler();
    private Runnable r2 = new Runnable() {
        public void run() {
            spwanGunShot(ChangableMembers.getMissleSpeed());
            handler2.postDelayed(r2, ChangableMembers.getMissleIntervallGeschwindigkeit());
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
        player = new Player(this, MainActivity.this);
        //Player wird zum Contentview hinzugefügt (ins Leben gerufen!)
        addContentView(player, player.initialize());
        player.setBackgroundResource(R.mipmap.startrocketl);
        //Player wird in den Bildschirm geschoben und sichtbar gemacht
        player.startSequenz();
        //Hintergrund wird gesetzt
        ImageView hgrund = findViewById(R.id.hgrund);
        //Gegner Regen wird gestartet. r dabei Runnable.
        handler.postDelayed(r, Constants.startSpwanDelay);
        //PLayer movement wird aktiviert
        player.setCoordssystem(hgrund);
        //Schüsse werden gestartet
        handler2.postDelayed(r2, 100);
        //Score wird gefunden. Es wird keine Postioin gesetzt da er automatisch auf 0 0 geht.
        scoreView = findViewById(R.id.score);
        scoreView.setText("Score: " + score);

    }

    // Erstellung eines Gegners mit der Initialize methode aus Enemy.
    //startX aufgerufen. Zufällige X Koordinate!
    // Gegner mit move von Oben nach unten gleiten lassen
    //Hintergrund gesetzt
    // Gegner zur gegnerListe hinzugefügt.
    public void setEnemy(int geschwindigkeit) {
        final Enemy enemy = new Enemy(this, player, MainActivity.this, 8);
        addContentView(enemy, enemy.initialize());
        enemy.startX();
        enemy.move(displayheight, geschwindigkeit);
        enemy.setBackgroundResource(meteoridenBilderListe[(int)(Math.random()*3f)]);
        selfThinkingFiguresList.add(enemy);
    }

    public void spwanGunShot(int speed) {
        final GunShot gunShot = new GunShot(this, player, MainActivity.this);
        addContentView(gunShot, gunShot.initialize());
        gunShot.startGunShot(speed);
        gunShot.setBackgroundResource(R.mipmap.gunshot);
    }

    // bei collision mit etwas, alle Meteoriden stoppen und Player unmovable machen
    public void collison() {
        ImageView gameOver = findViewById(R.id.gameover);
        player.stopPlayer();
        gameOver.setVisibility(View.VISIBLE);
        for (int i = 0; i < selfThinkingFiguresList.size(); i++) {
            selfThinkingFiguresList.get(i).freeze();
            selfThinkingFiguresList.get(i).setVisibility(View.INVISIBLE);
        }
        handler.removeCallbacksAndMessages(null);
        handler2.removeCallbacksAndMessages(null);
        Score.scoreUeberpruefung(score);
        Score.save(this);
        Button returnButton = findViewById(R.id.returnbuttonwithSave);
        returnButton.setVisibility(View.VISIBLE);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreSenden(Score.getLastScore(),ChangableMembers.getPlayerName());
                openNewActivity(GameMenu.class);
            }
        });
        Button returnButtonwithoutSave = findViewById(R.id.returnwithoutSave);
        returnButtonwithoutSave.setVisibility(View.VISIBLE);
        returnButtonwithoutSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(GameMenu.class);
            }
        });
        TextView scoreAfterFinishing = findViewById(R.id.scoreAfterFinishing);
        scoreAfterFinishing.setText("Dein erreichter Score lautet: " + score);
        scoreAfterFinishing.setVisibility(View.VISIBLE);
        TextView onlineName  = findViewById(R.id.playerNameTextView);
        onlineName.setText("Dein derzeitiger Name lautet: " + ChangableMembers.getPlayerName());
        onlineName.setVisibility(View.VISIBLE);
    }
    //Ein Highscore wird hochgezählt
    public void setScore() {
        score++;
        scoreView.setText("Score: " + score);
    }

    public void scoreSenden(final int score, final String name){
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://docfoltin.de:8899/store"; // <----enter your post url here
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                error.printStackTrace();
            }
        })
        {
            protected Map<String, String> getParams() {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("player",name);
                hashMap.put("score","" + score);
                return hashMap;
            }
        };
        MyRequestQueue.add(MyStringRequest);
    }
    public void openNewActivity(Class klasse){
        Intent intent = new Intent(this, klasse);
        startActivity(intent);
    }
}
