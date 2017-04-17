package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.lurd.game.MainGame;
import com.lurd.game.gui.Button;

public class SettingsScreen implements Screen {

    MainGame core;
    Stage stage;
    SpriteBatch batch;
    Texture backgraund;
    Button back;
    Button vibro;
    Button music;
    float size = Gdx.graphics.getHeight()/10;

    public SettingsScreen(MainGame c) {
        core = c;
        batch = new SpriteBatch();
        stage = new Stage();
        backgraund = new Texture("fone_12.png");
        stage.addActor( back = new Button("back1.png",20, Gdx.graphics.getHeight() - 20-size, size,size ));

        if(core.settingsPref.getBoolean("settingsVibro")==true){
        stage.addActor( vibro = new Button("vibro.png", 100, 100, size,size));}
        else {stage.addActor( vibro = new Button("vibroOff.png", 100, 100, 100, 100));}

        if(core.settingsPref.getBoolean("settingsMusic")==true){
            stage.addActor( music = new Button("music.png", 100, 300,Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10));}
        else {stage.addActor(music = new Button("musicOff.png", 100, 300, Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10));}

        vibro.addAction(Actions.moveTo((int) (Math.random() * Gdx.graphics.getWidth()-size),0, 3));
        music.addAction(Actions.moveTo((int) (Math.random() * Gdx.graphics.getWidth()-size),Gdx.graphics.getHeight()-size, 3));


        Gdx.input.setInputProcessor(stage);



        vibro.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if (core.settingsPref.getBoolean("settingsVibro") == true) {
                    core.settingsPref.putBoolean("settingsVibro", false);
                    core.settingsPref.flush();
                    vibro.setTexture("vibroOff.png");
                } else {
                    core.settingsPref.putBoolean("settingsVibro", true);
                    core.settingsPref.putBoolean("settingsVibro", true);
                    core.settingsPref.flush();
                    vibro.setTexture("vibro.png");
                }
                return true;
            }
        });

        music.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if (core.settingsPref.getBoolean("settingsMusic") == true) {
                    core.settingsPref.putBoolean("settingsMusic", false);
                    core.settingsPref.flush();
                    music.setTexture("musicOff.png");
                    core.MenuMusic.stop();
                } else {
                    core.settingsPref.putBoolean("settingsMusic", true);
                    core.settingsPref.flush();
                    music.setTexture("music.png");
                    core.MenuMusic.setLooping(true);
                    core.MenuMusic.play();


                }
                return true;
            }
        });


        back.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if(core.settingsPref.getBoolean("settingsVibro")==true){
                    Gdx.input.vibrate(50);}
                core.setScreen(new MainMenuScreen(core));
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
        batch.draw(backgraund, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();

        if(vibro.getY()==0){
            vibro.addAction(Actions.moveTo(Gdx.graphics.getWidth()-size, Gdx.graphics.getHeight()/2, 4));
        }
        if(vibro.getX()==Gdx.graphics.getWidth()-size){
            vibro.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()-size ,4));
        }
        if(vibro.getX()==0){
            vibro.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2, 0, 4));
        }
        if(vibro.getY()==Gdx.graphics.getHeight()-size){
            vibro.addAction(Actions.moveTo(0, Gdx.graphics.getHeight()/2, 4));
        }


        if(music.getY()==0){
            music.addAction(Actions.moveTo(Gdx.graphics.getWidth()-size,Gdx.graphics.getHeight()/2, 4));
        }
        if(music.getX()==Gdx.graphics.getWidth()-size){
            music.addAction(Actions.moveTo( Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()-size ,4));
        }
        if(music.getX()==0){
            music.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2, 0, 4));
        }
        if(music.getY()==Gdx.graphics.getHeight()-size){
            music.addAction(Actions.moveTo(0, Gdx.graphics.getHeight()/2, 4));
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

    }
}
