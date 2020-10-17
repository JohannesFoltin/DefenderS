package com.example.defenders;

import android.content.Context;
import android.content.SharedPreferences;

public class Score {
    public static final String SCORE_DATA = "score_data";
    public static final String LAST_SCORE = "LastScore";
    public static final String HIGH_SCORE = "HighScore";
    private static int lastScore = 0;
    private static int highScore = 0;

    public static int getLastScore() {
        return lastScore;
    }

    public static int getHighScore() {
        return highScore;
    }
    public static void scoreUeberpruefung(int Scoremitgabe){
        if(Scoremitgabe > highScore){
            highScore = Scoremitgabe;
            lastScore = Scoremitgabe;
        }
        else {
            lastScore  = Scoremitgabe;
        }
    }
    public static void save(Context context){
        SharedPreferences preferences = context.getSharedPreferences(SCORE_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HIGH_SCORE,highScore);
        editor.commit();
    }
    public static void load(Context context){
        SharedPreferences preferences = context.getSharedPreferences(SCORE_DATA, Context.MODE_PRIVATE);
         highScore = preferences.getInt(HIGH_SCORE,0);

    }
}
