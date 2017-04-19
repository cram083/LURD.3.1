package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lurd.game.MainGame;
import com.lurd.game.gui.Backgraund;
import com.lurd.game.gui.Button;

import static com.badlogic.gdx.Gdx.input;

public class HistoryScreen implements Screen {

    MainGame core;
    Stage stage;
    SpriteBatch batch;
    Backgraund backgraund;
    int pag=1;


    public HistoryScreen(MainGame c) {
        core = c;
        batch = new SpriteBatch();
        stage = new Stage();
        stage.addActor(backgraund = new Backgraund("story.png"));
        backgraund.getHeight(4397);
       /* backgraund.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent e, )
        });*/
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
     //   font.draw(batch, "Best: 500", 40+Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/5+40 );
//        font.draw(batch, " "+core.recordInt, 100, 100);

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
