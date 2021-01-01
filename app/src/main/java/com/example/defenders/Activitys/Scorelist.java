package com.example.defenders.Activitys;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.*;
import com.android.volley.toolbox.Volley;
import com.example.defenders.R;
import com.example.defenders.Score_Online;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Scorelist extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorelist);
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        final Gson gson = new Gson();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://docfoltin.de:8899/scores";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Score_Online[] scoreOnlineArray = gson.fromJson(response , Score_Online[].class);
                        scoreOnlineArraytoDisplay(scoreOnlineArray);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView errorTextView = findViewById(R.id.errorTextView);
                errorTextView.setText("Failed to load data. Please check your internet connection! And restart the App");
                errorTextView.setVisibility(View.VISIBLE);
            }
        });
        queue.add(stringRequest);
        Button exitButton = findViewById(R.id.returnToMenuButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(GameMenu.class);
            }
        });
    }
    public void scoreOnlineArraytoDisplay(Score_Online[] scoreOnlines){
        String rueckgabestring = "";
        TextView place1 = findViewById(R.id.place1);
        TextView place2 = findViewById(R.id.place2);
        TextView place3 = findViewById(R.id.place3);
        TextView place4 = findViewById(R.id.place4);
        TextView place5 = findViewById(R.id.place5);
        TextView place6 = findViewById(R.id.place6);
        TextView place7 = findViewById(R.id.place7);
        TextView place8 = findViewById(R.id.place8);
        TextView place9 = findViewById(R.id.place9);
        TextView place10 = findViewById(R.id.place10);
        TextView[] platzierungen = new TextView[10];
        platzierungen[0] = (place1);
        platzierungen[1] =(place2);
        platzierungen[2] =(place3);
        platzierungen[3] =(place4);
        platzierungen[4] =(place5);
        platzierungen[5] =(place6);
        platzierungen[6] =(place7);
        platzierungen[7] =(place8);
        platzierungen[8] =(place9);
        platzierungen[9] =(place10);
        for(int i = 0; i<scoreOnlines.length;i++) {
            //convert seconds to milliseconds
            Date date = new Date(scoreOnlines[i].getDate());
            // format of the date
            SimpleDateFormat jdf = new SimpleDateFormat("HH:mm dd/MM/yyyy");
            String java_date = jdf.format(date);
            platzierungen[i].setText((i+1) + ".Platz: " + "\n" + "Name: " + scoreOnlines[i].getPlayer() + ", Punktzahl: "+ scoreOnlines[i].getScore() +  "\n"+ "Datum: " + java_date);
        }
    }
    public void openNewActivity(Class klasse){
        Intent intent = new Intent(this, klasse);
        startActivity(intent);
    }

}
