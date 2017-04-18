package com.lurd.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Button extends Actor {

    Texture texture;

    public Button(String i , float x, float y, float  width, float height) {
        texture = new Texture(Gdx.files.internal(i));
        setPosition(x, y);
        setSize(width, height);
    }

    public void setTexture(String texture) {
        this.texture = new Texture(Gdx.files.internal(texture));

    }

    @Override
    public void draw(Batch b, float parentAlpha) {
        b.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
