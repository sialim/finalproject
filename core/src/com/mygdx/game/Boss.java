package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

public class Boss extends Entity {

    private String name;
    private float maxHP;
    private float hp;
    private int atk;
    private int phase;
    private float speed = 10.0f;
    private Bullet bullet;
    private CollisionRect rect;
    private Array<Bullet> bullets;
    private BulletPattern pattern;

    private float fireCooldown = 0.125f;
    private float timeSinceLastShot = 0.0f;

    private float moveCooldown = 0.5f;
    private float timeSinceLastMove = 0.0f;

    public Boss(float maxHealth, float damage, CollisionRect rect, Sprite sprite) {
        super(maxHealth, damage, rect, sprite);
        bullets = new Array<>();
        pattern = new BulletPattern();

        setX(MainGame.SCREEN_WIDTH/2f);
        setPosition(sprite.getX(), sprite.getY());
        getSprite().setSize(sprite.getWidth(), sprite.getHeight());
    }

    public void handleShooting(float deltaTime) {
        timeSinceLastShot += deltaTime;
        if (timeSinceLastShot >= fireCooldown) {
            shoot();
            timeSinceLastShot = 0.0f;
        }
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Array<Bullet> bullets) {
        this.bullets = bullets;
    }

    @Override
    public void move(float deltaTime) {
        entSetX(super.getX());
        entSetY(super.getY());
    }

    @Override public void draw(Batch batch, float parentAlpha) {
        getSprite().draw(batch, parentAlpha);
    }

    public void initiateSequence() {
        float targetY = 500f;
        float duration = 2.0f;
        addAction(Actions.moveTo(getX(), targetY, duration));
    }

    @Override
    public void shoot() {
        System.out.println("Shoot method running");
        int angle = 270;
        Bullet bullet = new Bullet(entGetX(), entGetY(), angle, 100);

        bullets.add(bullet);

        Array<Bullet> tempGlobalBullets = MainGame.getGlobalBullets();
        tempGlobalBullets.add(bullet);
        MainGame.setGlobalBullets(tempGlobalBullets);
    }

    public Boss(float maxHealth, float damage, CollisionRect rect, Sprite sprite, Bullet bullet) {
        super(maxHealth, damage, rect, sprite);
        this.bullet = bullet;
    }


    public void updatePhase() {
        if (hp > maxHP * 2 / 3) {
            phase = 1;
        } else if (hp > maxHP / 3) {
            phase = 2;
        } else if (hp > 0) {
            phase = 3;
        } else {
            phase = 0;
        }
    }

    public void setPhase() { // changes the Bullet Pattern based on the Phase the boss has entered
        // each Pattern is harder than the last.
        int ammo = 100;
        float speedOfBullet = 5;

        if (phase == 3){
            pattern.setRadialPattern(super.getX(), super.getY(), ammo, speedOfBullet);
        } else if (phase == 2) {
            float centerAngle = 180;
            float spreadAngle = 45;
            pattern.setFanPattern(super.getX(), super.getY(), centerAngle, spreadAngle, ammo, speedOfBullet);
        } else {
            float angle = 270;
            pattern.setStraightPattern(super.getX(), super.getY(), angle, ammo, speedOfBullet);
        }
    }
    public int getAtk() {
        return atk;
    }

    public void takeDamage(float dmg) {
        hp -= dmg;
        if (hp < 0) {
            hp = 0;
        }
        updatePhase();
    }

    public void takePlayerDamage(Player p){
        takeDamage(p.getDamage());
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public String toString() {
        updatePhase();
        if (hp == 0) {
            return name + " is dead";
        }
        return name + " has " + hp + "/" + maxHP + " health and " + atk + " attack power and is at phase " + phase;
    }

    @Override public void act(float delta) {
        super.act(delta);

        getSprite().setPosition(getX(), getY());
        getCollisionRect().move(getX(), getY());
    }
}