package com.example.defenders.Activitys;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.defenders.ChangableMembers;
import com.example.defenders.R;

public class Settings extends AppCompatActivity{
    private static final String TAG = Settings.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Button fertigButton = findViewById(R.id.buttonFertig);
        fertigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangableMembers.save(Settings.this);
                openNewActivity(GameMenu.class);
            }
        });

        final EditText editTextEnemySeed = findViewById(R.id.editTextSpwanSpeed);
        final TextView textViewEnemySpwanSpedd = findViewById(R.id.textSpwanSpeed);
        textViewEnemySpwanSpedd.setText("EnemySpwanIntervall(minimal 50!): "+ ChangableMembers.getEnemyIntervallGeschwindigkeit());
        editTextEnemySeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ChangableMembers.setEnemyIntervallGeschwindigkeit(Integer.parseInt(editTextEnemySeed.getText().toString()));
 //               textViewEnemySpwanSpedd.setText("EnemySpwanIntervall: "+ ChangableMembers.getEnemyIntervallGeschwindigkeit());
            }
        });

        final EditText editEnemyDuration = findViewById(R.id.editEnemyDuration);
        final TextView textEnemyDuration = findViewById(R.id.textEnemyDuration);
        textEnemyDuration.setText("EnemyDuration: "+ ChangableMembers.getEnemyDuration());
        editEnemyDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ChangableMembers.setEnemyDuration(Integer.parseInt(editEnemyDuration.getText().toString()));
            }
        });

        final EditText editMissleSpeed = findViewById(R.id.editMissleSpeed);
        final TextView textMissleSpeed = findViewById(R.id.textMissleSpeed);
        textMissleSpeed.setText("MissleSpeed: "+ ChangableMembers.getMissleSpeed());
        editMissleSpeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ChangableMembers.setMissleSpeed(Integer.parseInt(editMissleSpeed.getText().toString()));
            }
        });

        final EditText editMissleIntervallGeschwindigkeit = findViewById(R.id.editMissleIntervallGeschwindigkeit);
        final TextView textMissleIntervallGeschwindigkeit = findViewById(R.id.textViewMissleIntervallGeschwindigkeit);
        textMissleIntervallGeschwindigkeit.setText("MissleIntervallGeschwindigkeit: "+ ChangableMembers.getEnemyIntervallGeschwindigkeit());
        editMissleIntervallGeschwindigkeit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ChangableMembers.setEnemyIntervallGeschwindigkeit(Integer.parseInt(editMissleIntervallGeschwindigkeit.getText().toString()));
            }
        });
    }
    public void openNewActivity(Class klasse){
        Intent intent = new Intent(this, klasse);
        startActivity(intent);
    }

}
