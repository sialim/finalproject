package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Player extends Entity {
    private final float BASE_SPEED = 200;

    private Sprite sprite;
    private float speed;

    private boolean autoFireEnabled = false;
    private float fireCooldown = 0.05f;
    private float timeSinceLastShot = 0.0f;

    private float stateTime;

    private Array<Bullet> bullets;

    public Player(float maxHealth, float damage, CollisionRect rect, Sprite sprite) {
        super(maxHealth, damage, rect, sprite);
        this.sprite = sprite;
        this.bullets = new Array<>();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void toggleAutoFire() {
        autoFireEnabled = !autoFireEnabled;
    }

    public void shoot() {
        int angle = Utilities.generateRandomNum(87, 93);
        Bullet bullet = new Bullet(super.getX() + sprite.getWidth() / 2 /*center*/, super.getY() + sprite.getHeight()/*top*/, angle);
        bullets.add(bullet);

        Array<Bullet> tempGlobalBullets = MainGame.getGlobalBullets();
        tempGlobalBullets.add(bullet);
        MainGame.setGlobalBullets(tempGlobalBullets);
    }

    public void handleShooting(float deltaTime) {
        if(autoFireEnabled) {
            timeSinceLastShot += deltaTime;
            if (timeSinceLastShot >= fireCooldown) {
                shoot();
                timeSinceLastShot = 0.0f;
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.E)) {
            toggleAutoFire();
        }
    }

    public void move(float deltaTime) {
        stateTime += deltaTime;

        // Crouch
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speed = BASE_SPEED / 2.0f;
        } else {
            speed = BASE_SPEED;
        }

        // --- Physical player movement
        // Up
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            super.setY(super.getY() + (speed * deltaTime));
            if (super.getY() + 20 > (MainGame.SCREEN_HEIGHT/2)) {
                super.setY((MainGame.SCREEN_HEIGHT/2) - 20);
            }
        }
        // Down
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            super.setY(super.getY() - (speed * deltaTime));
            if (super.getY() < -(MainGame.SCREEN_HEIGHT/2)) {
                super.setY(-(MainGame.SCREEN_HEIGHT/2));
            }
        }
        // Left
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            super.setX(super.getX() - (speed * deltaTime));
            if (super.getX() < -(MainGame.SCREEN_WIDTH/2)) {
                super.setX(-(MainGame.SCREEN_WIDTH/2));
            }
        }
        // Right
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            super.setX(super.getX() + (speed * deltaTime));
            if (super.getX() + 20 > (MainGame.SCREEN_WIDTH/2)) {
                super.setX((MainGame.SCREEN_WIDTH/2) - 20);
            }
        }
    }

    public void updateBullets(float deltaTime) {
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
        }
    }

    /*public TextureRegion getCurrentFrame() {
        // Check which animation to play based on player movement
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            return leftAnimation.getKeyFrame(stateTime, true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            return rightAnimation.getKeyFrame(stateTime, true);
        } else {
            return idleAnimation.getKeyFrame(stateTime, true);
        }
    }*/
}
