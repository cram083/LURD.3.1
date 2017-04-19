package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.lurd.game.MainGame;
import com.lurd.game.gui.Button;

public class StartScreen implements Screen {

    MainGame core;
    Stage stage;
    SpriteBatch batch;
    Texture background;

    float indexWeght;
    float indexHight;

    Button Logo;
    Button GerGame;



    public StartScreen(MainGame c) {
        core = c;
        batch = new SpriteBatch();
        stage = new Stage();
        background = new Texture("fone_12.png");

        indexHight = Gdx.graphics.getHeight()/800;
        indexWeght = Gdx.graphics.getWidth()/480;

        stage.addActor(Logo = new Button("Logo0.png", -Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Gdx.graphics.getWidth()*3, Gdx.graphics.getHeight()/2));
        Logo.addAction(Actions.moveTo(Gdx.graphics.getWidth() / 7, Gdx.graphics.getHeight() / 2, 0.5f));
        Logo.addAction(Actions.sizeTo(Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 3.5f), Gdx.graphics.getHeight() / 4, 0.5f));

        stage.addActor(GerGame = new Button("GerrorGame.png", -Gdx.graphics.getWidth() / 2.5f, Gdx.graphics.getHeight()/3, Gdx.graphics.getWidth() / 2.5f, Gdx.graphics.getHeight() / 10));

      /*  stage.addActor(Logo = new Button("Logo.png", Gdx.graphics.getWidth()/5.724f, Gdx.graphics.getHeight()/2.666f, Gdx.graphics.getHeight()/4, Gdx.graphics.getHeight()/4));
        stage.addActor(u = new Button("u.png", Gdx.graphics.getWidth()/4.373f, Gdx.graphics.getHeight()/2.453f, Gdx.graphics.getHeight()/4, Gdx.graphics.getHeight()/4));
        stage.addActor(r = new Button("r.png", Gdx.graphics.getWidth()/2.09f, Gdx.graphics.getHeight()/2.443f, Gdx.graphics.getHeight()/4, Gdx.graphics.getHeight()/4));
        stage.addActor(d = new Button("d.png", Gdx.graphics.getWidth()/1.88f, Gdx.graphics.getHeight()/2.666f, Gdx.graphics.getHeight()/4, Gdx.graphics.getHeight()/4));*/




       /* stage.addActor(Logo = new Button("Logo.png", 84, 300, 200, 200));
        stage.addActor(u = new Button("u.png", 110, 326, 200, 200));
        stage.addActor(r = new Button("r.png", 239, 327, 200, 200));
        stage.addActor(d = new Button("d.png", 268, 300, 200, 200));*/





    }

    @Override
    public void show() {

    }

    private int del = 0;

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();

        if(Logo.getY()==Gdx.graphics.getHeight() / 2){
                GerGame.addAction(Actions.moveTo((Gdx.graphics.getWidth() - (Gdx.graphics.getWidth() / 2.5f)) / 2, Gdx.graphics.getHeight() / 3, 0.5f));

        }


        del++;
        if(del%200==0){
            if(core.prefs.getBoolean("FirstNo")==false){
              core.setScreen(new HistoryScreen(core));
            core.prefs.putBoolean("FirstNo", true);
            core.prefs.flush();}
            if(core.prefs.getBoolean("FirstNo")==true){
                core.setScreen(new MainMenuScreen(core));}

        }
        Gdx.app.log("Del", " "+del);

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
