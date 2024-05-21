package com.mygdx.game;

public class Utilities {
    public static int generateRandomNum(int min, int max){
        //min is the smallest number this method can return
        //max is the biggest number this method can return
        return (int)(Math.random() * (max - min + 1) + min);
    }
}
