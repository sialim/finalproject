package com.mygdx.game;

import com.badlogic.gdx.utils.Timer;

public class Utilities {
    public static int generateRandomNum(int min, int max){
        //min is the smallest number this method can return
        //max is the biggest number this method can return
        return (int)(Math.random() * (max - min + 1) + min);
    }

    public static void wait(float seconds, Runnable task) {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                task.run();
            }
        }, seconds);
    }
}