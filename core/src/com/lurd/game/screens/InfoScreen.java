package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lurd.game.MainGame;
import com.lurd.game.gui.Button;

public class InfoScreen implements Screen {

    MainGame core;
    Stage stage;
    SpriteBatch batch;
    Texture backgraund;
    Button back;

    public InfoScreen(MainGame c) {
        core = c;
        batch = new SpriteBatch();
        stage = new Stage();
        backgraund = new Texture("foneinfo.png");
        stage.addActor( back = new Button("back.png", Gdx.graphics.getWidth()-Gdx.graphics.getWidth()/10-20, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/12, Gdx.graphics.getWidth()/10, Gdx.graphics.getHeight()/12 ));


        Gdx.input.setInputProcessor(stage);

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
