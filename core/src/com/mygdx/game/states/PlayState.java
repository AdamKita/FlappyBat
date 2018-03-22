package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.FlappyBat;
import com.mygdx.game.sprites.Bat;
import com.mygdx.game.sprites.RockObstacle;

/**
 * Created by Adam on 3/7/2018.
 */

public class PlayState extends State{
    private static final int GroundYOffset = -40;
    private  static final int rockSpacing = 80;
    private static final int rockCount = 4;
    private int score;
    private String points;
    BitmapFont font = new BitmapFont();
    private Bat bat;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private boolean showCoin;

    private Array<RockObstacle> rocksObst;

    public PlayState(GameStateManager gsm){
        super(gsm);

        score = 0;
        points = "Score: 0";
        font = new BitmapFont();
        bat = new Bat(50,300);
        background = new Texture("background.png");
        ground = new Texture("groundCave.png");

        //Zooms in the Camera double
        cam.setToOrtho(false, FlappyBat.width/2, FlappyBat.hight/2);
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth , GroundYOffset);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth) + ground.getWidth(),GroundYOffset);
        //Array that holds Obstacle textures
        rocksObst = new Array<RockObstacle>();

        //Loops through Array
        for(int i = 1; i <= rockCount; i++) {
            //adds object of RockObstacle to the array
            rocksObst.add(new RockObstacle(i * (rockSpacing + RockObstacle.rockWidth)));
        }
    }
    /**
     * Handles users touch input to make bird jump
     */
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bat.jump();
        }
    }

    /**
     * updates the screen
     * @param dt
     */
    @Override
    public void update(float dt) {
        handleInput();
        bat.update(dt);
        updateGround();
        updateScore();
        showCoin = true;
        // bird now follows the camera
        cam.position.x = bat.getPosition().x + 50;
        //Repositions the rocks
        for(int i = 0 ; i < rocksObst.size; i++){
            RockObstacle rock = rocksObst.get(i);
            //check if tube if off the left side of the screen
            if(cam.position.x - (cam.viewportWidth / 2) >= rock.getPosTopRock().x + rock.getTopRock().getWidth()) {
                rock.reposition(rock.getPosTopRock().x + ((RockObstacle.rockWidth + rockSpacing) * rockCount));

            }
            //Bat hits Obsticles start new State
           if(rock.collides(bat.getBounds())) {
               //new state enters stack;
               gsm.set(new EndGameState(gsm));
               cam.update();

            //Bat hits Coin
           }if(rock.touchCoin(bat.getBounds())){
                showCoin = false;
                score++;
                //prints Score to screen
                points = "score: " + score;
            }
        }
        //Bat hits the ground
        if(bat.getPosition().y <= ground.getHeight() + GroundYOffset)
            //new enters the stack
            gsm.set(new EndGameState(gsm));
            cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,cam.position.x - (cam.viewportWidth /2), 0);
        sb.draw(bat.getTexture(), bat.getPosition().x , bat.getPosition().y);
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(sb,points, 25,300);
        //prints the obsticles from array to screen
        for(RockObstacle rock : rocksObst) {
            //prints top rock
            sb.draw(rock.getTopRock(), rock.getPosTopRock().x, rock.getPosTopRock().y);
            //Onces the Bat touches coin the Score should be added and coin disaperas
            ///DOSNT WORK\\\
           if(showCoin == true) {
               //Draws coin object to the screen
               sb.draw(rock.getCoin(), rock.getPosCoin().x, rock.getPosCoin().y);
           }
            //prtinys bottom rock
            sb.draw(rock.getBottomRock(), rock.getPosBottomRock().x, rock.getPosBottomRock().y);
        }
        sb.draw(ground , groundPos1.x,groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();

    }

    /**
     * Disposes the Textures
     */
    @Override
    public void dispose() {
        background.dispose();
        bat.dispose();
        ground.dispose();
        for(RockObstacle rock : rocksObst){
            rock.dispose();
        }

    }

    /**
     * Updates ground
     * Checks if ground is off the screen and repostions it to other side of the screen
     */
    private void updateGround(){
        if(cam.position.x - (cam.viewportWidth / 2) >= groundPos1.x + ground.getWidth())
            groundPos1.add(ground.getWidth() * 2 - 100, 0);
        if(cam.position.x - (cam.viewportWidth / 2) >= groundPos2.x + ground.getWidth())
            groundPos2.add(ground.getWidth() *2  - 100, 0);

    }

    /**
     * updates score to move with the screen
     * THIS DOSNT WORK
     */
    private void updateScore(){
        if(cam.position.x -(cam.viewportWidth / 2) >=font.getRegion().getRegionWidth()){
            System.out.println("Enters loop");
            font.getRegion().setRegion(cam.position.x + 100,cam.position.y + 100,font.getRegion().getRegionWidth(),font.getRegion().getRegionHeight());
        }
    }

}
