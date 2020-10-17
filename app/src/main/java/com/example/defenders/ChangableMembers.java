package com.example.defenders;

import android.content.Context;
import android.content.SharedPreferences;

public class ChangableMembers {
    public static final String MISSLE_INTERVALL_GESCHWINDIGKEIT = "missleIntervallGeschwindigkeit";
    public static final String MISSLE_SPEED = "missleSpeed";
    public static final String ENEMY_INTERVALL_GESCHWINDIGKEIT = "enemyIntervallGeschwindigkeit";
    public static final String ENEMY_DURATION = "enemyDuration";
    private static int enemyIntervallGeschwindigkeit = 250;
    private static int enemyDuration = 2500;
    private static int missleSpeed = 300;
    private static int missleIntervallGeschwindigkeit = 300;


    public static int getEnemyDuration() {
        return enemyDuration;
    }

    public static void setEnemyDuration(int enemyDuration) {
        ChangableMembers.enemyDuration = enemyDuration;
    }


    public static int getEnemyIntervallGeschwindigkeit() {
        return enemyIntervallGeschwindigkeit;
    }

    public static void setEnemyIntervallGeschwindigkeit(int enemyIntervallGeschwindigkeit) {
        ChangableMembers.enemyIntervallGeschwindigkeit = enemyIntervallGeschwindigkeit;
    }

    public static int getMissleSpeed() {
        return missleSpeed;
    }

    public static void setMissleSpeed(int missleSpeed) {
        ChangableMembers.missleSpeed = missleSpeed;
    }

    public static int getMissleIntervallGeschwindigkeit() {
        return missleIntervallGeschwindigkeit;
    }

    public static void setMissleIntervallGeschwindigkeit(int missleIntervallGeschwindigkeit) {
        ChangableMembers.missleIntervallGeschwindigkeit = missleIntervallGeschwindigkeit;
    }
    public static void save(Context context){
        SharedPreferences preferences = context.getSharedPreferences("application_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(MISSLE_INTERVALL_GESCHWINDIGKEIT, missleIntervallGeschwindigkeit);
        editor.putInt(MISSLE_SPEED,missleSpeed);
        editor.putInt(ENEMY_INTERVALL_GESCHWINDIGKEIT,enemyIntervallGeschwindigkeit);
        editor.putInt(ENEMY_DURATION,enemyDuration);
        editor.commit();
    }
    public static void load(Context context){
        SharedPreferences preferences = context.getSharedPreferences("application_data", Context.MODE_PRIVATE);
        missleSpeed = preferences.getInt(MISSLE_SPEED, 300);
        missleIntervallGeschwindigkeit = preferences.getInt(MISSLE_INTERVALL_GESCHWINDIGKEIT,300);
        enemyIntervallGeschwindigkeit = preferences.getInt(ENEMY_INTERVALL_GESCHWINDIGKEIT,250);
        enemyDuration = preferences.getInt(ENEMY_DURATION,2500);

    }
}
