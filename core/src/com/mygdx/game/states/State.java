package com.mygdx.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Adam on 3/7/2018.
 */

public abstract class State {
    //Camrea to locate a postition in the world
    protected OrthographicCamera cam;
    //pointer
    protected Vector3 mouse;
    //Game states to pop up ahead of the current game state
    protected GameStateManager gsm;

    //Constructor for Game State
    protected State(GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    //set methods
    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);//contains everthing that needs to be rendered
    public abstract void dispose();

}
