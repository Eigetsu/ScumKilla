package org.eigetsu;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Created by Eigetsu on 2017/10/16.
 */

public class TDS implements InputProcessor, Screen {
	private OrthographicCamera cam;

	private SpriteBatch batch;
	private Texture background;
	private Texture maketa;

	public AssetManager assets;

	private float gameScreenX;
	private float gameScreenY;


	CharMain player1;
	//MapObject block;

	private float dt;
	private float timepassed = 0f;
	private float enemyspawntimer = 0f;
	private float enemyattacktimer = 0f;
	float random;

	int level = 1;
	private int enemieskilled = 0;

	private Array<Bullet> bullets;
	private Array<Bullet> bulletstoremove;
	private Array<Enemy> enemies;
	private Array<Enemy> enemiesToRemove;
	private Array<BloodSplat> bloodDecals;

	private Rectangle scene;

	Pixmap scrn;

	TDSLauncher game;

	private Bullet[] bulletPool =new Bullet[250];


	private Vector2 parking = new Vector2(-100, -100);
	Vector2 mousepos = new Vector2();
	Vector3 mousepos3;

	private BitmapFont font;

	boolean isReleased = true;
	boolean touch0 = false;
	int startTouchX = 0;
	int startTouchY = 0;

	public int drawCountDebug;

	Application.ApplicationType appType;

	public TDS(TDSLauncher game) {
		this.game = game;

	}

	public void create () {
		assets = game.getAssets();
		appType = Gdx.app.getType();
		scrn = ScreenUtils.getFrameBufferPixmap(0, 0, (int)gameScreenX, (int)gameScreenY);

		batch = new SpriteBatch();

		background = assets.getTextureByName("Background");
		maketa = assets.getTextureByName("GameOverScreen");

		player1 = new CharMain(this);
		//block = new MapObject(new Vector2(50, 50), this);
		gameScreenX = 1280f;
		gameScreenY = 720f;

		this.cam = new OrthographicCamera(gameScreenX,gameScreenY);
		cam.setToOrtho(false, gameScreenX, gameScreenY);

		mousepos3 = new Vector3(-200, -200,0);

		bullets = new Array<Bullet>();
		bulletstoremove = new Array<Bullet>();

		enemies = new Array<Enemy>();
		enemiesToRemove = new Array<Enemy>();

		bloodDecals = new Array<BloodSplat>();

		font = new BitmapFont();

		Gdx.input.setInputProcessor(this);

		scene = new Rectangle(0, 0, gameScreenX, gameScreenY);

		for(Bullet bullet : bulletPool) bullet = new Bullet(this, parking);
	}

	@Override
	public void render (float delta) {

		dt = Gdx.graphics.getDeltaTime();
		random = (float)Math.random();
		mousepos.x=getMousePosInGameWorld().x;
		mousepos.y=getMousePosInGameWorld().y;
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//PROCESS CONTROLS
		timepassed += dt;
		if (appType == Application.ApplicationType.Android) proccessAndroidControls();
		else processDesktopKeys();

//CAMERA
		batch.setProjectionMatrix(cam.combined);
		cam.update();

//SPRITE BATCH
		batch.begin();
		batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		batch.draw(assets.getTextureRegionByName("Backgroundr"),0, 0,gameScreenX,gameScreenY);
		batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

//DRAW BLOOD
		drawCountDebug = 0;
		for(BloodSplat blood : bloodDecals){
			blood.update(dt,batch);
		}
		/*if (bloodDecals.size > 20) {
			assets.updateBackground();
			bloodDecals.clear();

		}*/



//SPAWN ENEMIES
		//TODO:make pooling
		if (enemies.size < level*4){
			spawnEnemies();
			}

//DETECT BULLETS COLLISIONS
		for (Bullet bullet : bullets){
			for (Enemy enemy : enemies){
				if (enemy.rec.contains(bullet.pos)){
					enemy.recieveDamage(bullet.getDamage());
					bulletstoremove.add(bullet);
					if(enemy.isDead) bloodDecals.add(new BloodSplat(enemy.pos.cpy(), this));
					//Gdx.app.log("Enemies", enemy +" is DEAD @ enemy:" + enemy.pos +"Bullet:" +bullet.pos);
				}
			}
			//if (block.rec.contains(bullet.pos)) bulletstoremove.add(bullet);

			if (bullet.isexploding || !scene.contains(bullet.pos)) bulletstoremove.add(bullet);
		}
		bullets.removeAll(bulletstoremove,true);


		bulletstoremove.clear();

//DRAW ENEMIES
		for (Enemy enemy : enemies){
			enemy.update(dt,batch);
			if (player1.rec.contains(enemy.pos)) {

				enemyattacktimer += dt;
				if (enemyattacktimer > 1f) {
					player1.setHealth(player1.getHealth() - 20);
					System.out.println(player1.getHealth());
					enemyattacktimer = 0f;
				}
			}
			if (enemy.isDead) enemiesToRemove.add(enemy);
			//if (enemy.rec.contains( getMousePosInGameWorld().x, getMousePosInGameWorld().y)) enemy.isDead = true;

		}
		enemies.removeAll(enemiesToRemove,true);
		enemiesToRemove.clear();

//DRAW PLAYER
		player1.update(dt,batch);


//DRAW BULLETS
		for (Bullet bullet : bullets){
			bullet.update(dt,batch);
		}
		batch.draw(assets.getTextureByName("UIHealth"),10, 680,100,40);
		for (int n=0; n < player1.getHealth();n+=10) batch.draw(assets.getTextureByName("UIHealthBar"),120+n*1, 690,2,20);



/* PLAYER CONDITIONS */
		if (player1.getHealth() <= 0){
			//batch.draw(maketa, 0f, 0f,gameScreenX,gameScreenY);
			assets.updateBackground();
			enemies.clear();
			bloodDecals.clear();
			System.gc();
			Gdx.input.setInputProcessor(null);
			game.setScreen(new GameOverMenu(game));
		}


		batch.end();

//LEVEL UP
		if (enemieskilled > 20){
			level++;
			enemieskilled = 0;
			//assets.updateBackground(scrn);
			//bloodDecals.clear();
			Gdx.app.log("main", "Leveled UP! Level:" + level );
		}

//PLAYER OUT OF SCENE
		if (player1.pos.x <0) player1.setPos(1f,player1.getPos().y);
		if (player1.pos.y <0) player1.setPos(player1.getPos().x,1f);
		if (player1.pos.x >gameScreenX) player1.setPos(gameScreenX-1f,player1.getPos().y);
		if (player1.pos.y >gameScreenY) player1.setPos(player1.getPos().x,gameScreenY-1f);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	//DESKTOP CONTROL
	private void processDesktopKeys(){

		getMousePosInGameWorld();

		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			player1.setPos(player1.getPos().add(0,50f*dt));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.S)){
			player1.setPos(player1.getPos().add(0,-50f*dt));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			player1.setPos(player1.getPos().add(-50f*dt,0));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.D)){
			player1.setPos(player1.getPos().add(50f*dt,0));
		}
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE) || Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			if (timepassed > 0.2f){

				bullets.add(new Bullet(this,player1.pos.cpy()));
				timepassed = 0f;
			}
		}
		if(Gdx.input.isKeyPressed(Input.Keys.NUM_2) || Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){

			if (timepassed > 0.03f){
				bullets.add(new MachineGunBullet(this, player1.pos.cpy()));
				timepassed = 0f;
			}
		}

		//SPAWN ENEMIES BY KEY
		if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)){
			spawnEnemies();
		}



		/*if (Gdx.input.getDeltaX() > 0 && touch0) player1.setPos(player1.getPos().add(50f*dt,0));
		if (Gdx.input.getDeltaX() < 0 && touch0) player1.setPos(player1.getPos().add(-50f*dt,0));
		if (Gdx.input.getDeltaY() > 0 && touch0) player1.setPos(player1.getPos().add(0,50f*dt));
		if (Gdx.input.getDeltaY() < 0 && touch0) player1.setPos(player1.getPos().add(0,-50f*dt));*/

	}
//ANDROID CONTROLL
	private void proccessAndroidControls(){
		touch0 = Gdx.input.isTouched(0);

		mousepos3.x = Gdx.input.getX(1);
		mousepos3.y = Gdx.input.getY(1);
		cam.unproject(mousepos3);

		if (Gdx.input.getGyroscopeX() > 0) player1.setPos(player1.getPos().add(50f*dt,0)); //R
		if (Gdx.input.getGyroscopeX() < 0) player1.setPos(player1.getPos().add(-50f*dt,0));//L
		if (Gdx.input.getGyroscopeY() > 0) player1.setPos(player1.getPos().add(0,50f*dt));//U
		if (Gdx.input.getGyroscopeY() < 0) player1.setPos(player1.getPos().add(0,-50f*dt));//D

	}

	private void spawnEnemies(){
		enemies.add(new Enemy(this, random*gameScreenX,random*-100f));//TOP
		enemies.add(new Enemy(this,random*100f+gameScreenX,random*gameScreenY));//RIGHT
		enemies.add(new Enemy(this, random*gameScreenX,random*100f+gameScreenY));//BOTTOM
		enemies.add(new Enemy(this, random*-100f,random*gameScreenY));//LEFT
		//Gdx.app.log("Enemies", "Enemies spawned");
	}

	public Vector3 getMousePosInGameWorld() {

		mousepos3.x = Gdx.input.getX();
		mousepos3.y = Gdx.input.getY();
		cam.unproject(mousepos3);
		return mousepos3;
	}

	public void enemyDeadEvent(){
		enemieskilled++;
	}


	@Override
	public void show() {
		create();

	}



	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();

		bullets.clear();
		bulletstoremove.clear();
		enemies.clear();
		enemiesToRemove.clear();
		assets.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
