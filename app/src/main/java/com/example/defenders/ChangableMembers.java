package com.example.defenders;

import android.content.Context;
import android.content.SharedPreferences;

public class ChangableMembers {
    public static final String MISSLE_INTERVALL_GESCHWINDIGKEIT = "missleIntervallGeschwindigkeit";
    public static final String MISSLE_SPEED = "missleSpeed";
    public static final String ENEMY_INTERVALL_GESCHWINDIGKEIT = "enemyIntervallGeschwindigkeit";
    public static final String ENEMY_DURATION = "enemyDuration";
    private static int enemyIntervallGeschwindigkeit = 500;
    private static int enemyDuration = 2500;
    private static int missleSpeed = 1000;
    private static int missleIntervallGeschwindigkeit = 100;
    private static String playerName;

    public static String getPlayerName() {
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        ChangableMembers.playerName = playerName;
    }


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
        editor.putString("Player_Name",playerName);
        editor.commit();
    }
    public static void load(Context context){
        SharedPreferences preferences = context.getSharedPreferences("application_data", Context.MODE_PRIVATE);
        missleSpeed = preferences.getInt(MISSLE_SPEED, 1000);
        missleIntervallGeschwindigkeit = preferences.getInt(MISSLE_INTERVALL_GESCHWINDIGKEIT,100);
        enemyIntervallGeschwindigkeit = preferences.getInt(ENEMY_INTERVALL_GESCHWINDIGKEIT,500);
        enemyDuration = preferences.getInt(ENEMY_DURATION,2500);
        playerName = preferences.getString("Player_Name","Not defined yet!");
    }
}
