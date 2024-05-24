package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.audio.Sound;

public class Player extends Entity {
    private final float BASE_SPEED = 200;
    private float speed;

    private boolean autoFireEnabled = false;
    private float fireCooldown = 0.05f;
    private float timeSinceLastShot = 0.0f;
    private Sound laserSound;


    private float stateTime;

    private Array<Bullet> bullets;

    public Player(float maxHealth, float damage, CollisionRect rect, Sprite sprite) {
        super(maxHealth, damage, rect, sprite);
        this.bullets = new Array<>();
        laserSound = Gdx.audio.newSound(Gdx.files.internal("playerbulletsoundeffect.wav"));

    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void toggleAutoFire() {
        autoFireEnabled = !autoFireEnabled;
    }

    public void shoot() {
        int angle = Utilities.generateRandomNum(87, 93);
        Bullet bullet = new Bullet(super.entGetX() + (super.getSprite().getWidth() / 2)-8f /*center*/, super.entGetY() + super.getSprite().getHeight()/*top*/, angle);
        bullets.add(bullet);
        laserSound.play();

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
            super.entSetY(super.entGetY() + (speed * deltaTime));
            if (super.entGetY() + 20 > (MainGame.SCREEN_HEIGHT/2)) {
                super.entSetY((MainGame.SCREEN_HEIGHT/2) - 20);
            }
        }
        // Down
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            super.entSetY(super.entGetY() - (speed * deltaTime));
            if (super.entGetY() < -(MainGame.SCREEN_HEIGHT/2)) {
                super.entSetY(-(MainGame.SCREEN_HEIGHT/2));
            }
        }
        // Left
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            super.entSetX(super.entGetX() - (speed * deltaTime));
            if (super.entGetX() < -(MainGame.SCREEN_WIDTH/2)) {
                super.entSetX(-(MainGame.SCREEN_WIDTH/2));
            }
        }
        // Right
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            super.entSetX(super.entGetX() + (speed * deltaTime));
            if (super.entGetX() + 20 > (MainGame.SCREEN_WIDTH/2)) {
                super.entSetX((MainGame.SCREEN_WIDTH/2) - 20);
            }
        }
    }

    public void updateBullets(float deltaTime) {
        for (Bullet bullet : bullets) {
            bullet.update(deltaTime);
        }
    }
}