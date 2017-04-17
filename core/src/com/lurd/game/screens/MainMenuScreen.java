package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
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

import static com.badlogic.gdx.Gdx.*;

public class MainMenuScreen implements Screen {

    MainGame core;

    Stage stage;

    Button settingsButton;
    Button playButton;
    Button infoButton;
    Button historyButton;
    Button exitButton;

    BitmapFont font;

    Texture bg;
    SpriteBatch batch;

    int indicator = 40+Gdx.graphics.getHeight()/15;
    int sizeButton = (Gdx.graphics.getHeight()-6*20)/5-indicator/9;

    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;



    public MainMenuScreen(MainGame c) {
        batch = new SpriteBatch();
        bg = new Texture(files.internal("fone_12.png"));
        core = c;
        stage = new Stage();



        generator = new FreeTypeFontGenerator(Gdx.files.internal("9303.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.YELLOW;
        parameter.size= Gdx.graphics.getWidth() / 17;
        font = generator.generateFont(parameter);


        if (core.settingsPref.getBoolean("settingsMusic") == true) {
            core.MenuMusic.setLooping(true);
            core.MenuMusic.play();
        }else {
            core.MenuMusic.stop();
        }

        Gdx.input.setInputProcessor(stage);
        stage.addActor(settingsButton = new Button("SETTINGS2.png", 100, 80+sizeButton*3 ,Gdx.graphics.getWidth()/2, sizeButton ));
        stage.addActor(playButton = new Button("PLAY2.png", Gdx.graphics.getWidth()-20-(Gdx.graphics.getWidth()-200),60+sizeButton*2, Gdx.graphics.getWidth()/2, sizeButton ));
        stage.addActor(infoButton = new Button("INFO2.png", 20, 100+sizeButton*4, Gdx.graphics.getWidth()/3, sizeButton));
        stage.addActor(historyButton = new Button("HISTORY2.png", 100, 40+sizeButton, Gdx.graphics.getWidth() / 2, sizeButton));
        stage.addActor(exitButton = new Button("EXIT2.png", 20, 20, Gdx.graphics.getWidth() / 3, sizeButton));



        playButton.addAction(Actions.moveTo(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth()/2), playButton.getY(), 1));
        settingsButton.addAction(Actions.moveTo(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2), settingsButton.getY(), 2));
        historyButton.addAction(Actions.moveTo(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 2), historyButton.getY(), 2));
        exitButton.addAction(Actions.moveTo(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/3), exitButton.getY(), 3));
        infoButton.addAction(Actions.moveTo(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 3), infoButton.getY(), 3));


        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if(core.settingsPref.getBoolean("settingsVibro")==true){
                    Gdx.input.vibrate(50);}
                core.setScreen(new SettingsScreen(core));
                return true;
            }
        });

        exitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if(core.settingsPref.getBoolean("settingsVibro")==true){
                    Gdx.input.vibrate(50);}
                Gdx.app.exit();
                return true;
            }
        });



        infoButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if(core.settingsPref.getBoolean("settingsVibro")==true){
                    Gdx.input.vibrate(50);}
                core.setScreen(new InfoScreen(core));
                return true;
            }
        });

        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if(core.settingsPref.getBoolean("settingsVibro")==true){
                    Gdx.input.vibrate(50);}
                core.setScreen(new GameScreen(core));

                return true;
            }
        });
        historyButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if (core.settingsPref.getBoolean("settingsVibro") == true) {
                    Gdx.input.vibrate(50);
                }
                core.setScreen(new HistoryScreen(core));

                return true;
            }
        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(bg, 0, 0, graphics.getWidth(), graphics.getHeight());
        font.draw(batch, "Best: "+core.prefs.getInteger("record"), 20, Gdx.graphics.getHeight()-10 );
        batch.end();
        stage.act();
        stage.draw();


        if(playButton.getX()==Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2)){
            playButton.addAction(Actions.moveTo(0, playButton.getY(), 4));
        }
        if(playButton.getX()==0){
            playButton.addAction(Actions.moveTo(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2), playButton.getY(), 4));
        }

        if(settingsButton.getX()==Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2)){
            settingsButton.addAction(Actions.moveTo(0, settingsButton.getY(), 4));
        }
        if(settingsButton.getX()==0){
            settingsButton.addAction(Actions.moveTo(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2), settingsButton.getY(), 4));
        }

        if(historyButton.getX()==Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2)){
            historyButton.addAction(Actions.moveTo(0, historyButton.getY(), 4));
        }
        if(historyButton.getX()==0){
            historyButton.addAction(Actions.moveTo(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/2), historyButton.getY(), 4));
        }

        if(infoButton.getX()==Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/3)){
            infoButton.addAction(Actions.moveTo(0, infoButton.getY(), 4));
        }
        if(infoButton.getX()==0){
            infoButton.addAction(Actions.moveTo(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/3), infoButton.getY(), 4));
        }

        if(exitButton.getX()==Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/3){
            exitButton.addAction(Actions.moveTo(0, exitButton.getY(), 4));
        }
        if(exitButton.getX()==0){
            exitButton.addAction(Actions.moveTo(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()/3), exitButton.getY(), 4));
        }
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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
