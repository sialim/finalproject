package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

public class BulletPattern {

    public BulletPattern() {
    }

    public Array<Bullet> setStraightPattern(float x, float y, float angle, int count, float speed, CollisionRect collisionRect) {
        Array<Bullet> ammo = new Array<>();
        for (int i = 0; i < count; i++) {
            ammo.add(new Bullet(x, y, angle, speed, collisionRect));
        }
        return ammo;
    }

    public Array<Bullet> setRadialPattern(float x, float y, int count, float speed, CollisionRect collisionRect) {
        Array<Bullet> ammo = new Array<>();
        float steps = 360.0f / count;

        for (int i = 0; i < count; i++) {
            float angle = i * steps;
            ammo.add(new Bullet(x, y, angle, speed, collisionRect));
        }
        return ammo;
    }

    public Array<Bullet> setFanPattern(float x, float y, float midAngle, float fanSpread, int count, float speed, CollisionRect collisionRect) {
        Array<Bullet> ammo = new Array<>();
        float steps = fanSpread / (count - 1);

        for (int i = 0; i < count; i++) {
            float angle = midAngle - (fanSpread / 2) + (i * steps);
            ammo.add(new Bullet(x, y, angle, speed, collisionRect));
        }
        return ammo;
    }
}