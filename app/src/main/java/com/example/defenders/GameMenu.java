package com.example.defenders;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemenu);
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

    }
    public void openNewActivity(Class klasse){
        Intent intent = new Intent(this, klasse);
        startActivity(intent);
    }
}
