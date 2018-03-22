package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBat;

/**
 * Created by Adam on 3/7/2018.
 */

public class MenuState extends State{
    private Texture background;
    private Texture playButton;
    private Texture logo;
    private Texture ground;

    /**
     *  inistalizes all Textures
     *  set Orthopidic Camara
     * @param gsm
     */
    public MenuState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, FlappyBat.width, FlappyBat.hight);
        background = new Texture("background.png");
        playButton = new Texture("playbutton.png");
        logo = new Texture("FlappyBatLogo.png");
        ground = new Texture("GroundCave.png");

    }
    @Override
    protected void handleInput() {
        //user touches screen
        //game state starts
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
        }
    }

    /**
     * Handles users input
     * @param dt
     */
    @Override
    public void update(float dt) {
        handleInput();
    }

    /**
     * Draws the Textures to the Game Screen
     * @param sb
     */
    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, FlappyBat.width, FlappyBat.hight);
        sb.draw(logo, cam.position.x - logo.getWidth() /2, cam.position.y + 50);
        sb.draw(playButton, cam.position.x - playButton.getWidth() / 2, cam.position.y);
        sb.end();
    }

    /**
     * Disposes the Textures onces Done
     */
    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }
}
