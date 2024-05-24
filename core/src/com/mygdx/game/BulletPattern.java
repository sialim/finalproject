package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

public class BulletPattern {

    public BulletPattern() {
    }

    public Array<Bullet> setStraightPattern(float startX, float startY, float angle, int count, float speed) {
        Array<Bullet> bullets = new Array<>();
        for (int i = 0; i < count; i++) {
            bullets.add(new Bullet(startX, startY, angle, speed));
        }
        return bullets;
    }

    public Array<Bullet> setRadialPattern(float startX, float startY, int count, float speed) {
        Array<Bullet> bullets = new Array<>();
        float steps = 360.0f / count;

        for (int i = 0; i < count; i++) {
            float angle = i * steps;
            bullets.add(new Bullet(startX, startY, angle, speed));
        }
        return bullets;
    }

    public Array<Bullet> setFanPattern(float startX, float startY, float centerAngle, float spreadAngle, int count, float speed) {
        Array<Bullet> bullets = new Array<>();
        float steps = spreadAngle / (count - 1);

        for (int i = 0; i < count; i++) {
            float angle = centerAngle - (spreadAngle / 2) + (i * steps);
            bullets.add(new Bullet(startX, startY, angle, speed));
        }
        return bullets;
    }
}