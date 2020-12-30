package com.example.defenders.Activitys;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.defenders.Activitys.MainActivity;
import com.example.defenders.Activitys.Settings;
import com.example.defenders.ChangableMembers;
import com.example.defenders.R;
import com.example.defenders.Score;

import java.util.HashMap;
import java.util.Map;

public class GameMenu extends AppCompatActivity {
    private TextView highScore;
    private TextView lastScore;
    private TextView displayName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemenu);
        Score.load(this);
        ChangableMembers.load(this);
        VideoView vv = findViewById(R.id.videoView);
        vv.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.backvidbearbeitet);
        vv.requestFocus();
        vv.start();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        Button startButton = (Button) findViewById(R.id.startbutton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             openNewActivity(MainActivity.class);
            }
        });
        Button settingsButton = (Button) findViewById(R.id.settingsbutton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(Settings.class);
            }
        });
        highScore  = findViewById(R.id.highScore);
        lastScore = findViewById(R.id.lastScore);
        highScore.setText("HIGH SCORE: "+ Score.getHighScore());
        lastScore.setText("Last Score: "+Score.getLastScore());
        Button closeButton = findViewById(R.id.spielbeenden);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });
        Button scoreList = findViewById(R.id.scorelist);
        scoreList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(Scorelist.class);
            }
        });
        displayName = findViewById(R.id.displayName);
        displayName.setText("Dein Name lautet zur Zeit: " + ChangableMembers.getPlayerName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Score.load(this);
        highScore.setText("HIGH SCORE: "+ Score.getHighScore());
        lastScore.setText("Last Score: "+Score.getLastScore());
        displayName.setText("Dein Name lautet zur Zeit: " + ChangableMembers.getPlayerName());
        VideoView vv = findViewById(R.id.videoView);
        vv.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.backvidbearbeitet);
        vv.requestFocus();
        vv.start();
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
    }

    public void openNewActivity(Class klasse){
        Intent intent = new Intent(this, klasse);
        startActivity(intent);
    }

}
