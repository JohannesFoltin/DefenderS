package com.example.defenders.Activitys;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.defenders.Activitys.MainActivity;
import com.example.defenders.Activitys.Settings;
import com.example.defenders.ChangableMembers;
import com.example.defenders.R;
import com.example.defenders.Score;

public class GameMenu extends AppCompatActivity {
    private TextView highScore;
    private TextView lastScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemenu);
        Score.load(this);
        ChangableMembers.load(this);
        Button startButton = (Button) findViewById(R.id.startbutton);
        TextView ausgabe = findViewById(R.id.Ausgabe);
        ausgabe.setText("Höhe: "+ getResources().getDisplayMetrics().heightPixels + "breite: "+ getResources().getDisplayMetrics().widthPixels);
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


    }

    @Override
    protected void onResume() {
        super.onResume();
        Score.load(this);
        highScore.setText("HIGH SCORE: "+ Score.getHighScore());
        lastScore.setText("Last Score: "+Score.getLastScore());
    }

    public void openNewActivity(Class klasse){
        Intent intent = new Intent(this, klasse);
        startActivity(intent);
    }

}
