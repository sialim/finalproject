package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Boss extends Entity {

    private String name;
    private double maxHP;
    private double hp;
    private int atk;
    private int phase;
    private Bullet bullet;
    private CollisionRect rect;
    public Boss(float maxHealth, float damage, CollisionRect rect, Sprite sprite) {
        super(maxHealth, damage, rect, sprite);
    }
}