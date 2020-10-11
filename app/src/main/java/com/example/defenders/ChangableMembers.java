package com.example.defenders;

public class ChangableMembers {
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
}
