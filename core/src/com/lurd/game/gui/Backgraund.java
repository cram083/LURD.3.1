package com.lurd.game.gui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Backgraund extends Actor {
    Texture texture;
    Vector2 velosity;

    public Backgraund(String i) {
        texture = new Texture(Gdx.files.internal(i));
        velosity = new Vector2(0, 0);

    }

    public void setTexture(String texture) {
        this.texture = new Texture(Gdx.files.internal(texture));

    }

    @Override
    public void draw(Batch b, float parentAlpha) {
        b.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        setSize(Gdx.graphics.getWidth() + 20, Gdx.graphics.getHeight() + 20);
    }

    public void update() {
        setPosition(0, getY() - Gdx.graphics.getHeight() / 150);
    }

    public void teleport() {
        if (getY() < -Gdx.graphics.getHeight()) {
            setPosition(0, Gdx.graphics.getHeight());
        }
    }

    public void getHeight(int i) {
    }

    public void dispose() {
    }


}