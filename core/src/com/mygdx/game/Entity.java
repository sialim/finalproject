package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity {
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
