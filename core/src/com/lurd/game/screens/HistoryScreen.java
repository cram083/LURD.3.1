package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.lurd.game.MainGame;
import com.lurd.game.gui.Backgraund;

public class HistoryScreen implements Screen {

    MainGame core;
    Stage stage;
    SpriteBatch batch;
    Backgraund backgraund;
    int pag = 1;
    boolean catchBackKey;


    public HistoryScreen(MainGame c) {
        core = c;
        batch = new SpriteBatch();
        stage = new Stage();
        stage.addActor(backgraund = new Backgraund("story.png"));
        backgraund.setHeight(Gdx.graphics.getHeight());
        backgraund.setPosition(0, 0);
        Gdx.input.getInputProcessor();
        Gdx.input.isCatchBackKey();

       /* backgraund.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent e, )
        });*/

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);

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
        //   font.draw(batch, "Best: 500", 40+Gdx.graphics.getWidth()/3, Gdx.graphics.getHeight()/5+40 );
//        font.draw(batch, " "+core.recordInt, 100, 100);
        //if(catchBackKey==true){
        //     core.setScreen(new MainMenuScreen(core));
        // }

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
