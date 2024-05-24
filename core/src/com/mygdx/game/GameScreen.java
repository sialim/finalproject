package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import sun.jvm.hotspot.utilities.BitMap;

public class GameScreen implements Screen {

    private static Array<Bullet> globalBullets;

    private Stage stage;

    final MainGame mainGame;
    final BulletPattern bulletPattern;

    private Texture playerBulletTexture;

    private Texture bossBulletTexture;

    private float deltaTime = 0;
    private float distance = 0;

    OrthographicCamera camera;
    SpriteBatch batch;
    Sprite sprite;
    Sprite background;
    Player player;

    float timeSinceTextDisplayed;

    BitmapFont font;

    Sprite bossSprite;
    Boss hongMeiling;

    public GameScreen(final MainGame mainGame, final BulletPattern bulletPattern) {
        this.mainGame = mainGame;
        this.bulletPattern = bulletPattern;
    }
    @Override
    public void show() {
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(1.5f);
        font.setColor(0, 0, 0, 0);

        timeSinceTextDisplayed = 0.0f;

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        batch = new SpriteBatch();

        // Player sprite
        sprite = new Sprite(new Texture("player.png"));
        sprite.setPosition(0, 0);
        sprite.setSize(20, 20);

        // Player bullet sprite
        playerBulletTexture = new Texture("playerbullet.jpg");

        // Boss sprite
        bossSprite = new Sprite(new Texture("hongmeiling.png"));
        bossSprite.setPosition(MainGame.SCREEN_WIDTH/2f, 200);
        sprite.setSize(30, 30);

        // Boss bullet sprite
        bossBulletTexture = new Texture("bossbullet.png");

        globalBullets = new Array<>();

        background = new Sprite(new Texture("background.jpg"));
        background.setPosition(0, 0);

        player = new Player(200, 50, new CollisionRect(0, 0, 20, 20), sprite);
        hongMeiling = new Boss(50000.0f, 1.0f, new CollisionRect(0, 0, 30, 30), bossSprite);
        hongMeiling.setX(MainGame.SCREEN_WIDTH/2f);
        hongMeiling.setY(1000.0f);

        stage = new Stage(new ScreenViewport());
        stage.addActor(hongMeiling);

        hongMeiling.initiateSequence();
    }

    @Override
    public void render(float v) {
        deltaTime = Gdx.graphics.getDeltaTime();

        player.handleShooting(deltaTime);
        player.move(deltaTime);
        player.updateBullets(deltaTime);

        timeSinceTextDisplayed += deltaTime;

        player.getCollisionRect().move(player.getX(), player.getY());

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ScreenUtils.clear(1, 0, 0, 1);

        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        batch.draw(background, -650, -350);

        for (Bullet bullet : hongMeiling.getBullets()) {
            batch.draw(bossBulletTexture, bullet.getPosition().x, bullet.getPosition().y, 15, 15);
            bullet.update(deltaTime);
            //System.out.println("Rendering bullet at x: " + bullet.getPosition().x + ", y: " + bullet.getPosition().y);
            //if ((bullet.getPosition().y < 0 || bullet.getPosition().y > MainGame.SCREEN_HEIGHT) || (bullet.getPosition().x < 0 || bullet.getPosition().x > MainGame.SCREEN_WIDTH)) {
            //    hongMeiling.getBullets().removeValue(bullet, true);
            //}
        }

        for (Bullet bullet : player.getBullets()) { /*Rendering all the bullets gathered from the player's personal bullet list
        Fuck ChatGPT*/
            batch.draw(playerBulletTexture, bullet.getPosition().x, bullet.getPosition().y, 10, 20);
        }

        batch.draw(player.getSprite(), player.entGetX(), player.entGetY());

        batch.end();

        //hongMeiling.draw(batch, 1f);

        stage.act(deltaTime);
        stage.draw();

        batch.begin();

        if (timeSinceTextDisplayed >= 1.8f && timeSinceTextDisplayed < 7.0f) {
            font.setColor(1, 1, 1, 1);
        } else if (timeSinceTextDisplayed >= 7.0f) {
            font.setColor(0, 0, 0, 0);
        }

        if (timeSinceTextDisplayed >= 7.2f && timeSinceTextDisplayed <= 8.2f) {
            hongMeiling.handleShooting(deltaTime);
        }
        // note to self later: keep using time and use modulo operators to determine when to move the boss and fire attacks

        font.draw(batch, "Fair is foul, and foul is fair.\nHover through the fog and filthy air.\nAUGUST 12 2036\nTHE HEAT DEATH OF THE UNIVERSE.", -150, -200);


        batch.end();
    }

    @Override
    public void resize(int x, int y) {
        stage.getViewport().update(x, y, true);
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
        playerBulletTexture.dispose();
        bossBulletTexture.dispose();
        bossSprite.getTexture().dispose();
        stage.dispose();
        batch.dispose();
        background.getTexture().dispose();
        font.dispose();
    }

    /*String message = "screaming in public restrooms prank\nAAAAAAAAAAAAAA";
        layout.setText(font, message);
		float width = layout.width;
		float height = layout.height;
        font.draw(mainGame.getBatch(), message, (Gdx.graphics.getWidth() - width) / 2, (Gdx.graphics.getHeight() + height) / 2);
        batch.begin();
        font.draw(batch, message, 200, 200);
		batch.draw(img, x, y);
		batch.end();
     */
}