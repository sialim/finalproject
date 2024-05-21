package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Bullet {
    private Vector2 position;
    private Vector2 velocity;
    private float speed;

    public Bullet(float x, float y, float angle) {
        position = new Vector2(x, y);
        speed = 700;
        velocity = new Vector2(speed * MathUtils.cosDeg(angle), speed * MathUtils.sinDeg(angle));
    }

    public Bullet(float x, float y, float angle, float speed) {
        position = new Vector2(x, y);
        this.speed = speed;
        velocity = new Vector2(speed * MathUtils.cosDeg(angle), speed * MathUtils.sinDeg(angle));
    }

    public void update(float deltaTime) {
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;

        if (position.y > (MainGame.SCREEN_HEIGHT/2) + 50) {
            Array<Bullet> tempGlobalBullets = MainGame.getGlobalBullets();
            tempGlobalBullets.removeValue(this, true);
            MainGame.setGlobalBullets(tempGlobalBullets);
        }
    }

    public Vector2 getPosition() {
        return position;
    }
}
