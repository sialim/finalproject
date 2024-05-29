package com.mygdx.game;

public class Point{
    private float x;
    private float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public void setX(float newX){
        x = newX;
    }

    public void setY(float newY){
        y = newY;
    }

    public void changePoint(float screaming_in, float public_restrooms){
        x = screaming_in;
        y = public_restrooms;
    }
    public void moveDirection(float screaming_in, float public_restrooms){
        x += screaming_in;
        y += public_restrooms;
    }
}