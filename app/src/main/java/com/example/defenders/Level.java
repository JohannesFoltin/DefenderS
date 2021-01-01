package com.example.defenders;

import com.example.defenders.Activitys.MainActivity;

public class Level {
   private int enemys;

   public boolean hasEnemies(){
       if(enemys <= 0)
           return false;
       enemys--;
       return true;
   }
   
    public int getEnemyduration() {
        return enemyduration;
    }
    private int enemyduration;


    public Level(int lvl){
        enemys = (10 + (lvl*10)) + (int) (Math.random()*10);
        enemyduration = 2000 - lvl * 5;
        if(enemyduration <1000){
            enemyduration = 1000;
        }
    }

}
