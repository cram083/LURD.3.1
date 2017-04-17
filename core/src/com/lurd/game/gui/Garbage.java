package com.lurd.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.lurd.game.MainGame;

import java.util.Random;


public class Garbage extends Actor {
   private Texture texture;
   private Vector2 velosity;
   private int randomTex;
   private int randomPos;
   private int randomPos1;
   private float rotation;
    MainGame core;

    public float Size = (Gdx.graphics.getWidth()-80)/3;

    public Garbage(MainGame c) {
        core = c;

        int randomTex = (int) (Math.random() * 2);
        switch (randomTex){
            case(0): texture = new Texture("Apple.png");
                break;
            case(1): texture = new Texture("Cola.png");
                break;

        }

        setWidth(Size);
        setHeight(Size);



    }


    public  void teleport(){
        if(getY()<-Size){
            randomPos = (int) (Math.random() * 3);
            while (randomPos==core.rndPos){
                randomPos = (int) (Math.random() * 3);
            }
                core.rndPos=randomPos;

            randomPos1=randomPos;
              switch (randomPos) {
            case (0):
                setPosition(20, Gdx.graphics.getHeight());
                break;
            case (1):
                setPosition(40 + Size, Gdx.graphics.getHeight());
                break;
            case (2):
                setPosition(60 + Size * 2, Gdx.graphics.getHeight());
                break;
        }

           Gdx.app.log(null, "Teleport");


        if(core.prefs.getInteger("record")<200){
        randomTex = (int) (Math.random() * 2);}
        if((core.prefs.getInteger("record")>=200)&&(core.prefs.getInteger("record")<=400)){
        randomTex = (int) (Math.random() * 4);}
        if((core.prefs.getInteger("record")>400)){
         randomTex = (int) (Math.random() * 4);}
        switch (randomTex) {
            case (0):
                texture = new Texture("Apple.png");
                break;
            case (1):
                texture = new Texture("Cola.png");
                break;
            case (2):
                texture = new Texture("Peper.png");
                break;
            case (3):
                texture = new Texture("Pen.png");
                break;
        }
        }



    }

    @Override
    public void draw(Batch b, float parentAlpha) {
        b.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }


    public void update(){}

    public void dispose() {
        dispose();
    }
}