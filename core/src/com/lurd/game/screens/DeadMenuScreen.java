package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.lurd.game.MainGame;
import com.lurd.game.gui.Button;

public class DeadMenuScreen implements Screen {

    MainGame core;
    Stage stage;
    SpriteBatch batch;
    Texture backgraund;
    Button menuButton;
    Button restartButton;
    BitmapFont font;
    Button pointImg;
    Button speedImg;
    Button bestImg;
    Color mycolor;

    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;



    int sizeButton = (Gdx.graphics.getHeight  ()-6*20)/5;

    public DeadMenuScreen(MainGame c) {
        core = c;
        batch = new SpriteBatch();
        stage = new Stage();
        mycolor = new Color(0, 225, 228, 1);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("1234.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = mycolor;
        parameter.size= Gdx.graphics.getWidth() / 17;
        font = generator.generateFont(parameter);


        if (core.settingsPref.getBoolean("settingsMusic") == true) {
            core.MenuMusic.setLooping(true);
            core.MenuMusic.play();
        }

        backgraund = new Texture("fone_12.png");
        stage.addActor( menuButton = new Button("MENU2.png", Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth() / 3) / 2, 20, Gdx.graphics.getWidth() / 3, sizeButton));
        stage.addActor(restartButton = new Button("RESTART2.png", Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth() / 2) / 2, sizeButton + 40, Gdx.graphics.getWidth() / 2, sizeButton ));
        stage.addActor(pointImg = new Button("POINTS2.png", 20, Gdx.graphics.getHeight() - 20 - sizeButton / 2, Gdx.graphics.getWidth() / 2, sizeButton / 2));
        stage.addActor( speedImg = new Button("SPEED2.png",20, Gdx.graphics.getHeight() - 40 - sizeButton, Gdx.graphics.getWidth() / 2, sizeButton / 2 ));
        stage.addActor(bestImg = new Button("BEST2.png", 20, Gdx.graphics.getHeight() - 60 - sizeButton - sizeButton / 2, Gdx.graphics.getWidth() / 2, sizeButton / 2));


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

        menuButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if(core.settingsPref.getBoolean("settingsVibro")==true){
                    Gdx.input.vibrate(50);}
                core.setScreen(new MainMenuScreen(core));
                return true;
            }
        });

        restartButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if(core.settingsPref.getBoolean("settingsVibro")==true){
                    Gdx.input.vibrate(50);}
                core.setScreen(new GameScreen(core));
                return true;
            }
        });

       menuButton.addAction(Actions.moveTo(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 3), menuButton.getY(), 1));
       restartButton.addAction(Actions.moveTo(0, restartButton.getY(), 1));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputProcessor() {

            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.BACK) {
                    core.setScreen(new MainMenuScreen(core));
                }
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
        });



    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgraund, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        font.draw(batch, " " + core.point, pointImg.getWidth() + 40, pointImg.getY() + Gdx.graphics.getHeight()/15);
        font.draw(batch, " "+core.speed, speedImg.getWidth()+40 , speedImg.getY()+Gdx.graphics.getHeight()/15);
        font.draw(batch, " "+core.prefs.getInteger("record"), bestImg.getWidth()+40 , bestImg.getY()+Gdx.graphics.getHeight()/15);
        batch.end();
        stage.act();
        stage.draw();



        if(menuButton.getX()==Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/3)){
            menuButton.addAction(Actions.moveTo(0, menuButton.getY(), 3));
        }
        if(menuButton.getX()==0){
            menuButton.addAction(Actions.moveTo(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/3), menuButton.getY(),3));
        }

        if(restartButton.getX()==Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2)){
            restartButton.addAction(Actions.moveTo(0, restartButton.getY(), 3));
        }
        if(restartButton.getX()==0){
            restartButton.addAction(Actions.moveTo(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 2), restartButton.getY(), 3));
        }
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
