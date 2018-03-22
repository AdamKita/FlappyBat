package com.mygdx.game.states;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Adam on 3/7/2018.
 */

public class GameStateManager {
    //Stack that holds the Game States
    private Stack<State> states;

    public GameStateManager(){ states = new Stack<State>();}
    //Removes the last stack
    public void push(State state ){ states.push(state);}
    //is used to see the last state added to the stack
    public void pop(){states.pop().dispose();}

    //sets the methods to deal with stack
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }
    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch sb){states.peek().render(sb);}

}
