package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*DO NOT LOOK AT OUR CODE PLEASE
DO NOT LOOK AT OUR CODE PLEASE
DO NOT LOOK AT OUR CODE PLEASE
DO NOT LOOK AT OUR CODE PLEASE
DO NOT LOOK AT OUR CODE PLEASE
DO NOT LOOK AT OUR CODE PLEASE
DO NOT LOOK AT OUR CODE PLEASE
DO NOT LOOK AT OUR CODE PLEASE*/
public class MenuScreen implements Screen {
    private final MainGame mainGame;
    private final BulletPattern bulletPattern;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture titleTexture;
    private BitmapFont font;
    private String[] menuOptions;
    private int selectedOption;

    public MenuScreen(MainGame mainGame, BulletPattern bulletPattern) {
        this.mainGame = mainGame;
        this.bulletPattern = bulletPattern;
        this.menuOptions = new String[] {"Start Game", "Options", "Exit"};
        this.selectedOption = 0;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();
        //titleTexture = new Texture("title.png");
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(2);
    }

    @Override
    public void render(float v) {
        handleInput();


        Gdx.gl.glClearColor(0, 0, 0, 1);

        // Render the screen
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        //batch.draw(titleTexture, Gdx.graphics.getWidth() / 2f - titleTexture.getWidth() / 2f, Gdx.graphics.getHeight() - 200);

        // Draw menu options
        for (int i = 0; i < menuOptions.length; i++) {
            if (i == selectedOption) {
                font.setColor(Color.GOLD);
            } else {
                font.setColor(Color.WHITE);
            }
            font.draw(batch, menuOptions[i], Gdx.graphics.getWidth() / 2f - 475, (Gdx.graphics.getHeight() / 2f - i * 40) - 300);
        }

        //font.draw(batch, "Hello\nscreaming in public\nrestrooms prank", 0, 200);

        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedOption = (selectedOption - 1 + menuOptions.length) % menuOptions.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % menuOptions.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            selectOption();
        }
    }

    private void selectOption() {
        switch (selectedOption) {
            case 0:
                mainGame.setScreen(new GameScreen(mainGame, bulletPattern));
                break;
            case 1:
                mainGame.setScreen(new OptionsScreen(mainGame));
                break;
            case 2:
                Gdx.app.exit();
                break;
        }
    }

    @Override
    public void resize(int i, int i1) {

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
        batch.dispose();
        //titleTexture.dispose();
        font.dispose();
    }
}