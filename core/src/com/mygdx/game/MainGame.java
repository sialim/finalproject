package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainGame extends Game {
	public static final int SCREEN_WIDTH = 500;
	public static final int SCREEN_HEIGHT = 700;

	private GameScreen gameScreen;
	private MenuScreen menuScreen;
	private OptionsScreen optionsScreen;
	private BulletPattern bulletPattern;
	private Music backgroundMusic;

	private static Array<Bullet> globalBullets;

	private static final float FRAME_DURATION = 0.1f;

	// Textures for player
	private Texture idleTexture;
	private Texture startLeftTexture;
	private Texture startRightTexture;
	private Texture leftTexture;
	private Texture rightTexture;

	// Textures for bullets
	private Texture playerBulletTexture;

	// Animations for player
	private Animation<TextureRegion> idleAnimation;
	private Animation<TextureRegion> startLeftAnimation;
	private Animation<TextureRegion> startRightAnimation;
	private Animation<TextureRegion> leftAnimation;
	private Animation<TextureRegion> rightAnimation;

	private float deltaTime = 0;
	private float distance = 0;

	OrthographicCamera camera;
	SpriteBatch batch;
	Sprite sprite;
	Sprite background;
	Player player;

	public static Array<Bullet> getGlobalBullets() {
		return globalBullets;
	}

	public static void setGlobalBullets(Array<Bullet> newGlobalBullets) {
		globalBullets = newGlobalBullets;
	}

	@Override
	public void create () {
		// Register player textures
		/*idleTexture = new Texture("reimuidle.gif");
		startLeftTexture = new Texture("reimuleft.gif");
		startRightTexture = new Texture("reimuright.gif");
		leftTexture = new Texture("reimuleftcontinuous.gif");
		rightTexture = new Texture("reimurightcontinuous.gif");*/

		/*idleAnimation = createAnimation(idleTexture, 8);
		startLeftAnimation = createAnimation(startLeftTexture, 8);
		startRightAnimation = createAnimation(startRightTexture, 8);
		leftAnimation = createAnimation(leftTexture, 3);
		rightAnimation = createAnimation(rightTexture, 3);*/



		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch = new SpriteBatch();

		sprite = new Sprite(new Texture("player.png"));
		sprite.setPosition(0, 0);
		sprite.setSize(20, 20);

		playerBulletTexture = new Texture("playerbullet.jpg");

		globalBullets = new Array<>();

		background = new Sprite(new Texture("background.jpg"));
		background.setPosition(0, 0);

		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("meilingtheme.mp3"));
		backgroundMusic.setLooping(true);
		backgroundMusic.setVolume(0.5f);
		backgroundMusic.play();



		player = new Player(200, 50, new CollisionRect(0, 0, 20, 20), sprite);

		this.setScreen(new MenuScreen(this, bulletPattern));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
		playerBulletTexture.dispose();
		/*idleTexture.dispose();
		startLeftTexture.dispose();
		startRightTexture.dispose();
		leftTexture.dispose();
		rightTexture.dispose();*/
	}

	/*private Animation<TextureRegion> createAnimation(Texture texture, int frameCount) {
		TextureRegion[][] regions = TextureRegion.split(texture, texture.getWidth() / frameCount, texture.getHeight());
		TextureRegion[] frames = new TextureRegion[frameCount * regions[0].length];
		int index = 0;
		for (int i = 0; i < regions.length; i++) {
			for (int j = 0; j < frameCount; j++) {
				frames[index++] = regions[i][j];
			}
		}
		return new Animation<>(FRAME_DURATION, frames);
	}*/
}