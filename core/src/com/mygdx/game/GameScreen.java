package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {

    private static Array<Bullet> globalBullets;

    final MainGame mainGame;

    private Texture playerBulletTexture;
    private Sprite playerBulletSprite;

    private float deltaTime = 0;
    private float distance = 0;

    OrthographicCamera camera;
    SpriteBatch batch;
    Sprite sprite;
    Sprite background;
    Player player;

    public GameScreen(final MainGame mainGame) {
        this.mainGame = mainGame;
    }
    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();

        sprite = new Sprite(new Texture("player.png"));
        sprite.setPosition(0, 0);
        sprite.setSize(20, 20);

        playerBulletTexture = new Texture("playerbullet.jpg");
        playerBulletSprite = new Sprite(playerBulletTexture);

        globalBullets = new Array<>();

        background = new Sprite(new Texture("background.jpg"));
        background.setPosition(0, 0);

        player = new Player(200, 50, new CollisionRect(0, 0, 20, 20), sprite);

    }

    @Override
    public void render(float v) {
        deltaTime = Gdx.graphics.getDeltaTime();

        player.handleShooting(deltaTime);
        player.move(deltaTime);
        player.updateBullets(deltaTime);



        player.getCollisionRect().move(player.getX(), player.getY());

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScreenUtils.clear(1, 0, 0, 1);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background, -650, -350);

        for (Bullet bullet : player.getBullets()) {
            batch.draw(playerBulletTexture, bullet.getPosition().x, bullet.getPosition().y, 10, 20);
        }

        batch.draw(player.getSprite(), player.getX(), player.getY());

        batch.end();
    }

    @Override
    public void resize(int x, int y) {
        //super.resize(x, y);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
