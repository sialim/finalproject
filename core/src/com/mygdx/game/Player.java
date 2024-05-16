package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Player {
    private final float BASE_SPEED = 200;

    private Sprite sprite;
    private int health;
    private float speed;
    private float x;
    private float y;

    private boolean autoFireEnabled = false;
    private float fireCooldown = 0.15f;
    private float timeSinceLastShot = 0.0f;

    private Animation<TextureRegion> idleAnimation;
    private Animation<TextureRegion> startLeftAnimation;
    private Animation<TextureRegion> startRightAnimation;
    private Animation<TextureRegion> leftAnimation;
    private Animation<TextureRegion> rightAnimation;

    private float stateTime;

    private Array<Bullet> bullets;

    public Player(int health, Sprite sprite, Animation<TextureRegion> idleAnimation,
                  Animation<TextureRegion> startLeftAnimation, Animation<TextureRegion> startRightAnimation,
                  Animation<TextureRegion> leftAnimation, Animation<TextureRegion> rightAnimation) {
        this.health = health;
        this.sprite = sprite;
        this.idleAnimation = idleAnimation;
        this.startLeftAnimation = startLeftAnimation;
        this.startRightAnimation = startRightAnimation;
        this.leftAnimation = leftAnimation;
        this.rightAnimation = rightAnimation;
        this.bullets = new Array<>();
    }

    public Player(int health, Sprite sprite) {
        this.health = health;
        this.sprite = sprite;
        this.bullets = new Array<>();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
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
        Bullet bullet = new Bullet(x + sprite.getWidth() / 2, y + sprite.getHeight(), 90);
        bullets.add(bullet);

        Array<Bullet> tempGlobalBullets = MainGame.getGlobalBullets();
        tempGlobalBullets.add(bullet);
        MainGame.setGlobalBullets(tempGlobalBullets);
    }

    public void move(float deltaTime) {
        stateTime += deltaTime;

        // Crouch
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            speed = BASE_SPEED / 2.0f;
        } else {
            speed = BASE_SPEED;
        }

        // Autofire
        if(autoFireEnabled) {
            timeSinceLastShot += deltaTime;
            if (timeSinceLastShot >= fireCooldown) {
                shoot();
                timeSinceLastShot = 0.0f;
            }
        }

        // --- Physical player movement
        // Up
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            y += speed * deltaTime;
            if (y + 20 > (MainGame.SCREEN_HEIGHT/2)) {
                y = (MainGame.SCREEN_HEIGHT/2) - 20;
            }
        }
        // Down
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            y -= speed * deltaTime;
            if (y < -(MainGame.SCREEN_HEIGHT/2)) {
                y = -(MainGame.SCREEN_HEIGHT/2);
            }
        }
        // Left
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            x -= speed * deltaTime;
            if (x < -(MainGame.SCREEN_WIDTH/2)) {
                x = -(MainGame.SCREEN_WIDTH/2);
            }
        }
        // Right
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            x += speed * deltaTime;
            if (x + 20 > (MainGame.SCREEN_WIDTH/2)) {
                x = (MainGame.SCREEN_WIDTH/2) - 20;
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
