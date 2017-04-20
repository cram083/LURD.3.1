package com.lurd.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.lurd.game.MainGame;
import com.lurd.game.gui.Backgraund;
import com.lurd.game.gui.Button;
import com.lurd.game.gui.Garbage;
import com.lurd.game.gui.Ships;


public class GameScreen implements Screen {

    MainGame core;

    float dx, dy, ux, uy;

    int point2;
    int point;
    int speedInt = 1;

    Stage stage;

    SpriteBatch batch;

    Ships ship0;
    Ships ship1;
    Ships ship2;

    Button pause;
    Button exit;


    Backgraund backgraund0;
    Backgraund backgraund1;

    Rectangle rctShip0;
    Rectangle rctShip1;
    Rectangle rctShip2;
    Rectangle rctGarbage0;
    Rectangle rctGarbage1;


    BitmapFont font;

    Boolean touchPause;


    Music Music;

    int del = Gdx.graphics.getHeight() / 200;


    Garbage garbage0;
    Garbage garbage1;
    Color mycolor;

    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    float objectSize = (Gdx.graphics.getWidth() - 80f) / 3;


    public GameScreen(final MainGame c) {

        batch = new SpriteBatch();
        core = c;
        stage = new Stage();
        mycolor = new Color(0, 225, 228, 1);

        core.pause = true;
        touchPause = true;

        generator = new FreeTypeFontGenerator(Gdx.files.internal("1234.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = mycolor;
        parameter.size = Gdx.graphics.getWidth() / 24;
        font = generator.generateFont(parameter);


        Music = Gdx.audio.newMusic(Gdx.files.internal("Fly_Space.ogg"));
        core.MenuMusic.pause();

        if (core.settingsPref.getBoolean("settingsMusic") == true) {
            Music.setLooping(true);
            Music.play();
        }

        stage.addActor(backgraund0 = new Backgraund("fone_12.png"));
        stage.addActor(backgraund1 = new Backgraund("fone_12.png"));


        stage.addActor(garbage0 = new Garbage(core));
        stage.addActor(garbage1 = new Garbage(core));

        stage.addActor(ship0 = new Ships("shipRed.png", 20, -objectSize, objectSize, objectSize));
        stage.addActor(ship1 = new Ships("shipYellow.png", objectSize + 40, -objectSize, objectSize, objectSize));
        stage.addActor(ship2 = new Ships("shipBlue.png", objectSize * 2 + 60, -objectSize, objectSize, objectSize));


        stage.addActor(pause = new Button("pause.png", Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 10 - 20f, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 12, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 12));
        stage.addActor(exit = new Button("back1.png", -1000, -1000, Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 12));


        backgraund0.setPosition(0, 0);
        backgraund1.setPosition(0, Gdx.graphics.getHeight());


        ship0.addAction(Actions.moveTo(ship0.getX(), 20, 2));
        ship1.addAction(Actions.moveTo(ship1.getX(), 20, 1));
        ship2.addAction(Actions.moveTo(ship2.getX(), 20, 2));


        int randomPos = (int) (Math.random() * 3);
        Gdx.app.log(null, " " + randomPos);
        switch (randomPos) {
            case (0):
                garbage0.setPosition(20, Gdx.graphics.getHeight() * 2);
                break;
            case (1):
                garbage0.setPosition(40 + objectSize, Gdx.graphics.getHeight() * 2);
                break;
            case (2):
                garbage0.setPosition(60 + objectSize * 2, Gdx.graphics.getHeight() * 2);
                break;
        }

        randomPos = (int) (Math.random() * 3);
        Gdx.app.log(null, " " + randomPos);
        switch (randomPos) {
            case (0):
                garbage1.setPosition(20, Gdx.graphics.getHeight() * 2 + Gdx.graphics.getHeight() / 2 + objectSize / 2);
                break;
            case (1):
                garbage1.setPosition(40 + objectSize, Gdx.graphics.getHeight() * 2 + Gdx.graphics.getHeight() / 2 + objectSize / 2);
                break;
            case (2):
                garbage1.setPosition(60 + objectSize * 2, Gdx.graphics.getHeight() * 2 + Gdx.graphics.getHeight() / 2 + objectSize / 2);
                break;
        }

        backgraund0.setWidth(Gdx.graphics.getWidth());
        backgraund1.setWidth(Gdx.graphics.getWidth());


        backgraund0.setHeight(Gdx.graphics.getHeight() + 20);
        backgraund1.setHeight(Gdx.graphics.getHeight() + 20);


        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);


        pause.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                if (core.settingsPref.getBoolean("settingsVibro") == true) {
                    Gdx.input.vibrate(50);
                }
                if (core.pause == true) {
                    pause.setTexture("play.png");
                    Music.pause();
                    exit.setPosition(pause.getX(), pause.getY() - 20 - exit.getHeight());
                    core.pause = false;
                } else {
                    if (core.pause == false) {
                        core.pause = true;
                        pause.setTexture("pause.png");
                        if (core.settingsPref.getBoolean("settingsMusic") == true) {
                            Music.setLooping(true);
                            Music.play();
                        }
                        exit.setPosition(Gdx.graphics.getWidth() + 20, pause.getY() - 20 - exit.getHeight());
                    }
                }

                pause();

                return true;
            }
        });

        exit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                Music.stop();
                if (core.settingsPref.getBoolean("settingsVibro") == true) {
                    Gdx.input.vibrate(50);
                }
                core.setScreen(new MainMenuScreen(core));
                if (core.settingsPref.getBoolean("settingsMusic") == true) {
                    core.MenuMusic.setLooping(true);
                    core.MenuMusic.play();
                }
                return true;
            }
        });

        rctShip0 = new Rectangle((int) ship0.getX() + objectSize / 4, (int) ship0.getY() + objectSize / 4, (int) ship0.getWidth() - objectSize / 2, (int) ship0.getHeight() - objectSize / 2);
        rctShip1 = new Rectangle((int) ship1.getX() + objectSize / 4, (int) ship1.getY() + objectSize / 4, (int) ship1.getWidth() - objectSize / 2, (int) ship1.getHeight() - objectSize / 2);
        rctShip2 = new Rectangle((int) ship2.getX() + objectSize / 4, (int) ship2.getY() + objectSize / 4, (int) ship2.getWidth() - objectSize / 2, (int) ship2.getHeight() - objectSize / 2);


        rctGarbage0 = new Rectangle((int) garbage0.getX() + 30, (int) garbage0.getY() + 30, (int) garbage0.getWidth() - 60, (int) garbage0.getHeight() - 60);
        rctGarbage1 = new Rectangle((int) garbage1.getX() + 30, (int) garbage1.getY() + 30, (int) garbage1.getWidth() - 60, (int) garbage1.getHeight() - 60);


        ship0.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                dx = x;
                dy = y;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ux = x;
                uy = y;
                String direction = motion(ux - dx, uy - dy);
                if (direction.equals("Up")) {
                    if (ship0.getY() == 20f) {
                        if ((ship0.getX() != ship1.getX()) && (ship0.getX() != ship2.getX())) {
                            if (core.pause == true) {
                                ship0.addAction(Actions.moveTo(ship0.getX(), ship0.getY() + objectSize + 20, 0.05f));
                            }
                        }
                    }
                } else if (direction.equals("Down")) {
                    if ((ship0.getX() != ship1.getX()) && (ship0.getX() != ship2.getX())) {
                        if (core.pause == true) {
                            ship0.addAction(Actions.moveTo(ship0.getX(), ship0.getY() - objectSize - 20, 0.05f));
                        }
                    }
                } else if (direction.equals("Right")) {
                    if ((((ship1.getX() != ship0.getX() + objectSize + 20f) && (ship0.getY() == ship1.getY())) || (ship0.getY() != ship1.getY())) &&
                            (((ship2.getX() != ship0.getX() + objectSize + 20f) && (ship0.getY() == ship2.getY())) || (ship0.getY() != ship2.getY()))) {
                        if (ship0.getX() < Gdx.graphics.getWidth() - objectSize - 40) {
                            if (core.pause == true) {
                                ship0.addAction(Actions.moveTo(ship0.getX() + objectSize + 20, ship0.getY(), 0.05f));
                            }
                        }
                    }
                } else if (direction.equals("Left")) {
                    if ((((ship1.getX() != ship0.getX() - objectSize - 20f) && (ship0.getY() == ship1.getY())) || (ship0.getY() != ship1.getY())) &&
                            (((ship2.getX() != ship0.getX() - objectSize - 20f) && (ship0.getY() == ship2.getY())) || (ship0.getY() != ship2.getY()))) {
                        if (ship0.getX() > 40) {
                            if (core.pause == true) {
                                ship0.addAction(Actions.moveTo(ship0.getX() - objectSize - 20, ship0.getY(), 0.05f));
                            }
                        }
                    }
                } else if (direction.equals("Null")) {
                    ship0.setPosition(ship0.getX(), ship0.getY());
                }

                super.touchUp(event, x, y, pointer, button);
            }

        });

        ship1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                dx = x;
                dy = y;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ux = x;
                uy = y;
                String direction = motion(ux - dx, uy - dy);

                if (direction.equals("Up")) {
                    if ((ship1.getX() != ship0.getX()) && (ship1.getX() != ship2.getX())) {
                        if (ship1.getY() == 20) {
                            if (core.pause == true) {
                                ship1.addAction(Actions.moveTo(ship1.getX(), ship1.getY() + objectSize + 20, 0.05f));
                            }
                        }
                    }

                } else if (direction.equals("Down")) {
                    if ((ship1.getX() != ship0.getX()) && (ship1.getX() != ship2.getX())) {
                        if (core.pause == true) {
                            ship1.addAction(Actions.moveTo(ship1.getX(), ship1.getY() - objectSize - 20, 0.05f));
                        }
                    }
                } else if (direction.equals("Right")) {
                    if ((((ship2.getX() != ship1.getX() + objectSize + 20f) && (ship2.getY() == ship1.getY())) || (ship1.getY() != ship2.getY())) &&
                            ((((ship0.getX() != ship1.getX() + objectSize + 20f) && (ship1.getY() == ship0.getY())) || (ship1.getY() != ship0.getY())))) {
                        if (ship1.getX() < Gdx.graphics.getWidth() - objectSize - 40) {
                            if (core.pause == true) {
                                ship1.addAction(Actions.moveTo(ship1.getX() + objectSize + 20, ship1.getY(), 0.05f));
                            }
                        }
                    }

                } else if (direction.equals("Left")) {
                    if ((((ship2.getX() != ship1.getX() - objectSize - 20f) && (ship2.getY() == ship1.getY())) || (ship1.getY() != ship2.getY())) &&
                            ((((ship0.getX() != ship1.getX() - objectSize - 20f) && (ship1.getY() == ship0.getY())) || (ship1.getY() != ship0.getY())))) {
                        if (ship1.getX() > 40) {
                            if (core.pause == true) {
                                ship1.addAction(Actions.moveTo(ship1.getX() - objectSize - 20, ship1.getY(), 0.05f));
                            }
                        }
                    }

                } else if (direction.equals("Null")) {
                    ship1.setPosition(ship1.getX(), ship1.getY());
                }


                super.touchUp(event, x, y, pointer, button);
            }
        });

        ship2.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent e, float x, float y, int p, int b) {
                dx = x;
                dy = y;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ux = x;
                uy = y;
                String direction = motion(ux - dx, uy - dy);

                if (direction.equals("Up")) {
                    if ((ship2.getX() != ship0.getX()) && (ship2.getX() != ship1.getX())) {
                        if (ship2.getY() == 20) {
                            if (core.pause == true) {
                                ship2.addAction(Actions.moveTo(ship2.getX(), ship2.getY() + objectSize + 20, 0.05f));
                            }
                        }
                    }

                } else if (direction.equals("Down")) {
                    if ((ship2.getX() != ship0.getX()) && (ship2.getX() != ship1.getX())) {
                        if (core.pause == true) {
                            ship2.addAction(Actions.moveTo(ship2.getX(), ship2.getY() - objectSize - 20, 0.05f));
                        }
                    }
                } else if (direction.equals("Right")) {
                    if ((((ship1.getX() != ship2.getX() + objectSize + 20f) && (ship1.getY() == ship2.getY())) || (ship2.getY() != ship1.getY())) &&
                            ((((ship0.getX() != ship2.getX() + objectSize + 20f) && (ship0.getY() == ship2.getY())) || (ship2.getY() != ship0.getY())))) {
                        if (ship2.getX() < Gdx.graphics.getWidth() - objectSize - 40) {
                            if (core.pause == true) {
                                ship2.addAction(Actions.moveTo(ship2.getX() + objectSize + 20, ship2.getY(), 0.05f));
                            }
                        }
                    }

                } else if (direction.equals("Left")) {
                    if ((((ship1.getX() != ship2.getX() - objectSize - 20f) && (ship1.getY() == ship2.getY())) || (ship2.getY() != ship1.getY())) &&
                            ((((ship0.getX() != ship2.getX() - objectSize - 20f) && (ship0.getY() == ship2.getY())) || (ship2.getY() != ship0.getY())))) {
                        if (ship2.getX() > 40) {
                            if (core.pause == true) {
                                ship2.addAction(Actions.moveTo(ship2.getX() - objectSize - 20, ship2.getY(), 0.05f));
                            }
                        }
                    }


                } else if (direction.equals("Null")) {
                    ship2.setPosition(ship2.getX(), ship2.getY());
                }


                super.touchUp(event, x, y, pointer, button);
            }
        });


    }

    public String motion(float deltaX, float deltaY) {
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                Gdx.app.log("direction", "Right");//Right
                return "Right";
            }
            if (deltaX < 0) {
                Gdx.app.log("direction", "Left"); // Left
                return "Left";
            }
        }
        if (Math.abs(deltaX) < Math.abs(deltaY)) {
            if (deltaY < 0) {
                Gdx.app.log("direction", "Down");// Down
                return "Down";
            }
            if (deltaY > 0) {
                Gdx.app.log("direction", "Up");//Up
                return "Up";
            }
        } else {
            Gdx.app.log("direction", "Null");//Null
            return "Null";
        }
        return null;
    }

    public void update(float dt) {
        if (core.pause == true) {
            garbage0.setY(garbage0.getY() - del);
            garbage1.setY(garbage1.getY() - del);

            backgraund0.update();
            backgraund1.update();

            point2++;
            if (point2 % 10 == 0) {
                point++;
                Gdx.app.log("Point", " " + point);
                if (point % 100 == 0) {
                    del++;
                    speedInt++;
                    Gdx.app.log("Velocity", " " + del);
                }
            }

            rctShip0.set((int) ship0.getX(), (int) ship0.getY(), (int) ship0.getWidth(), (int) ship0.getHeight());
            rctShip1.set((int) ship1.getX(), (int) ship1.getY(), (int) ship1.getWidth(), (int) ship1.getHeight());
            rctShip2.set((int) ship2.getX(), (int) ship2.getY(), (int) ship2.getWidth(), (int) ship2.getHeight());


            rctGarbage0.set((int) garbage0.getX() + 20, (int) garbage0.getY() + 20, (int) garbage0.getWidth() - 40, (int) garbage0.getHeight() - 40);
            rctGarbage1.set((int) garbage1.getX() + 20, (int) garbage1.getY() + 20, (int) garbage1.getWidth() - 40, (int) garbage1.getHeight() - 40);

            if ((rctGarbage0.overlaps(rctShip0)) || (rctGarbage0.overlaps(rctShip1)) || (rctGarbage0.overlaps(rctShip2)) ||
                    (rctGarbage1.overlaps(rctShip0)) || (rctGarbage1.overlaps(rctShip1)) || (rctGarbage1.overlaps(rctShip2))) {
                if (core.settingsPref.getBoolean("settingsVibro") == true) {
                    Gdx.input.vibrate(250);
                }
                core.setScreen(new DeadMenuScreen(core));
                Music.pause();

            }

            core.point = point;
            core.speed = speedInt;
        }
        if (core.point > core.prefs.getInteger("record")) {
            core.prefs.putInteger("record", point);
            core.prefs.flush();
        }
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float dt) {

        stage.act();
        stage.draw();
        update(dt);
        ship0.boudaries();
        ship1.boudaries();
        ship2.boudaries();
        garbage0.update();
        garbage1.update();
        garbage0.teleport();
        garbage1.teleport();
        backgraund0.teleport();
        backgraund1.teleport();
        batch.begin();
        font.draw(batch, "Points: " + point, 20, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Speed: " + speedInt, 40 + objectSize, Gdx.graphics.getHeight() - 10);
        font.draw(batch, "Best: " + core.prefs.getInteger("record"), 60 + objectSize * 2 - objectSize / 6, Gdx.graphics.getHeight() - 10);
        batch.end();


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
        stage.dispose();
        backgraund0.dispose();
        backgraund1.dispose();
        ship0.dispose();
        ship1.dispose();
        ship2.dispose();
        garbage0.dispose();
        garbage1.dispose();
        generator.dispose();

    }


}
