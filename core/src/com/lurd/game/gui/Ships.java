package com.lurd.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Ships extends Actor {

    Texture texture;
    Vector2 velosity;



    public float shipSize = (Gdx.graphics.getWidth()-80)/3;



    public Ships(String i, float x, float y, float width, float height) {
        texture = new Texture(Gdx.files.internal(i));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
        velosity = new Vector2(0, 0);
        setPosition(x, y);
        setSize(width, height);


    }

    public Vector2 getVelosity() {
        return velosity;
    }

    public void setVelosity(Vector2 velosity) {
        this.velosity = velosity;
    }

    @Override
    public void draw(Batch b, float parentAlpha) {
        b.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void boudaries(){
       if(getX()<20){
           // addAction(Actions.moveTo(20, getY(), 0.2f));
           setPosition(20, getY());
        }
        if (getX()+shipSize>Gdx.graphics.getWidth()){
           // addAction(Actions.moveTo(Gdx.graphics.getWidth() - shipSize - 20, getY(), 0.2f));
           setPosition(Gdx.graphics.getWidth() - shipSize - 20, getY());
        }
        if(getY()<20){
           // addAction(Actions.moveTo(getX(), 20, 0.2f));
          setPosition(getX(), 20);
        }
        if(getY()>shipSize+40){
           // addAction(Actions.moveTo(getX(), shipSize + 40));
            setPosition(getX(), shipSize + 40);
        }
    }


    public void update(){}
    public void dispose() {}
}
