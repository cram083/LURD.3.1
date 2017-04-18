package com.lurd.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.lurd.game.screens.StartScreen;


public class MainGame extends Game {


    public int point;
    public int speed;
    public boolean pause;
    public Preferences prefs;
    public Preferences settingsPref;
    public int rndPos;
    public Music MenuMusic;
    public Preferences complectShipperf;
    public static final int WIDHT = 480;
    public static final int HEIGHT = 800;



    @Override
    public void create () {

        this.setScreen(new StartScreen(this));
        prefs = Gdx.app.getPreferences("BESTPOINTs");
        settingsPref = Gdx.app.getPreferences("SETTINGS");
        MenuMusic = Gdx.audio.newMusic(Gdx.files.internal("Menu_sound.ogg"));
        complectShipperf = Gdx.app.getPreferences("ShipComplect");
        Gdx.app.log("ИСХОДНЫЙ", " "+prefs.getBoolean("FirstNo"));





    }

    @Override
    public void render () {
        super.render();
    }
}
