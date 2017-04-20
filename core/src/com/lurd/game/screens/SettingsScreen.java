package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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
    InputMultiplexer im;
    float size = Gdx.graphics.getHeight()/10;

    public SettingsScreen(MainGame c) {
        core = c;
        batch = new SpriteBatch();
        stage = new Stage();
        backgraund = new Texture("fone_12.png");

        if(core.settingsPref.getBoolean("settingsVibro")==true){
        stage.addActor( vibro = new Button("vibro.png", 0, Gdx.graphics.getHeight()-size*2-20, size,size));}
        else {stage.addActor( vibro = new Button("vibroOff.png", 0, Gdx.graphics.getHeight()-size*2-20 , size, size));}

        if(core.settingsPref.getBoolean("settingsMusic")==true){
            stage.addActor( music = new Button("music.png", Gdx.graphics.getWidth()- size, Gdx.graphics.getHeight()-size*3-40,Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10));}
        else {stage.addActor(music = new Button("musicOff.png", Gdx.graphics.getWidth()- size, Gdx.graphics.getHeight()-size*3-40, Gdx.graphics.getHeight()/10, Gdx.graphics.getHeight()/10));}

       // vibro.addAction(Actions.moveTo((int) Gdx.graphics.getWidth() - size, Gdx.graphics.getHeight()-size*2, 3));
        vibro.addAction(Actions.moveTo(Gdx.graphics.getWidth() - size, vibro.getY(), 3));
        music.addAction(Actions.moveTo((int) 0 , music.getY(),  3));


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



        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void show() {
        im = new InputMultiplexer();
        im.addProcessor(stage);
        im.addProcessor(new InputProcessor() {
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
        Gdx.input.setInputProcessor(im);
        Gdx.input.setCatchBackKey(true);


    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(backgraund, 0, 0,Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();

        if(vibro.getX()==(Gdx.graphics.getWidth()-size)){
            vibro.addAction(Actions.moveTo(0, vibro.getY(), 4));
        }
        if(vibro.getX()==0){
            vibro.addAction(Actions.moveTo( Gdx.graphics.getWidth()- size, vibro.getY(), 4));
        }

        if(music.getX()==0){
            music.addAction(Actions.moveTo( Gdx.graphics.getWidth()- size, music.getY(), 4));
        }
        if(music.getX()==(Gdx.graphics.getWidth()-size)){
            music.addAction(Actions.moveTo(0, music.getY(), 4));
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
