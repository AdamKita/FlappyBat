package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FlappyBat;
import com.mygdx.game.sprites.Bat;

/**
 * Created by Adam on 3/8/2018.
 */

public class EndGameState extends State {

    private Bat bat;
    private Texture background;
    private Texture ground;
    private Texture endGameLogo;
    private Texture playAgain;

    public EndGameState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FlappyBat.width, FlappyBat.hight);
        background = new Texture("background.png");
        playAgain= new Texture("playbutton.png");
        endGameLogo = new Texture("EndGame.png");
        ground = new Texture("GroundCave.png");

    }
    @Override
    protected void handleInput() {
        //user touches screen
        //game state starts
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            System.out.println("Play state entering");
        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0, FlappyBat.width, FlappyBat.hight);
        sb.draw(endGameLogo, cam.position.x - endGameLogo.getWidth() /2, cam.position.y + 50);
        sb.draw(playAgain, cam.position.x - playAgain.getWidth() / 2, cam.position.y);
       // sb.draw(ground)
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playAgain.dispose();
    }
}