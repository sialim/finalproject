package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 position;
    private Vector2 velocity;
    private float speed;

    public Bullet(float x, float y, float angle) {
        position = new Vector2(x, y);
        speed = 5;
        velocity = new Vector2(speed * MathUtils.cos(angle), speed * MathUtils.sin(angle));
    }

    public void update(float deltaTime) {
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
    }

    public Vector2 getPosition() {
        return position;
    }
}
