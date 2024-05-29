package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.org.apache.xml.internal.utils.SystemIDResolver;

public abstract class Entity extends Actor {
    private float x;
    private float y;
    private float maxHealth;
    private float health;
    private float damage;
    private int phase;
    private CollisionRect rect;
    private Sprite sprite;

    public Entity(float maxHealth, float damage, CollisionRect rect, Sprite sprite) {
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.damage = damage;
        this.rect = rect;
        this.sprite = sprite;
    }

    public float getDamage() {
        return damage;
    }
    public void moveTowards(Point p){
        if(p.getY() < y){
            y += 0.1;
        }
        if(p.getY() > y){
            y -= 0.1;
        }
        if(p.getX() < x){
            x += 0.1;
        }
        if(p.getX() > x){
            x -= 0.1;
        }
    }

    public void takeDamage(float damage){
        System.out.println("Taking damage: " + damage);
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        System.out.println("Current health: " + health + "/" + maxHealth);
    }

    public void giveHealth(float public_restrooms){
        health += public_restrooms;
    }

    public float getHealth() {
        return health;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public CollisionRect getCollisionRect() {
        return rect;
    }

    public void setCollisionRect(CollisionRect rect) {
        this.rect = rect;
    }

    public boolean isAlive() {
        return health >= 1;
    }

    public float entGetX() {
        return x;
    }

    public float entGetY() {
        return y;
    }

    public void entSetX(float x) {
        this.x = x;
    }

    public void entSetY(float y) {
        this.y = y;
    }

    public abstract void move(float deltaTime);

    public abstract void shoot();

    public Sprite getSprite() {
        return sprite;
    }
}