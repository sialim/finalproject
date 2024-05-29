package com.mygdx.game;

import com.badlogic.gdx.ai.utils.Collision;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Bullet {
    private Vector2 position;
    private Vector2 velocity;
    private float speed;
    private CollisionRect collisionRect;

    public Bullet(float x, float y, float angle, CollisionRect collisionRect) {
        position = new Vector2(x, y);
        speed = 700;
        velocity = new Vector2(speed * MathUtils.cosDeg(angle), speed * MathUtils.sinDeg(angle));
    }

    public Bullet(float x, float y, float angle, float speed, CollisionRect collisionRect) {
        position = new Vector2(x, y);
        this.speed = speed;
        velocity = new Vector2(speed * MathUtils.cosDeg(angle), speed * MathUtils.sinDeg(angle));
        this.collisionRect = collisionRect;
    }

    public void update(float deltaTime) {
        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;
        collisionRect.move(position.x, position.y);

        if ((position.y > (MainGame.SCREEN_HEIGHT/2) + 50) || (position.y < 0)) {
            death();
        }
    }

    public void death() {
        position.x = 1000;
        position.y = 1000;
        collisionRect.move(position.x, position.y);
        Array<Bullet> tempGlobalBullets = MainGame.getGlobalBullets();
        tempGlobalBullets.removeValue(this, true);
        MainGame.setGlobalBullets(tempGlobalBullets);
    }

    public CollisionRect getCollisionRect() {
        return collisionRect;
    }

    public boolean isOffScreen(){
        return false;
    }


    public Vector2 getPosition() {
        return position;
    }
}