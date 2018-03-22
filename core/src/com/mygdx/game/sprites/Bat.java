package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.FlappyBat;

/**
 * Created by Adam on 3/7/2018.
 */

public class Bat {
    private static final int gravity= -15;
    private static final int movement = 60;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture bat;
    private Animation batAnimation;

    public Bat(int x , int y){
        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bat = new Texture("bats.png");
        // bounds = new Rectangle(x,y,bat.getWidth(),bat.getHeight());

        batAnimation = new Animation(new TextureRegion(bat),4,0.5f);
        bounds = new Rectangle(x,y,bat.getWidth()/ 4, bat.getHeight() / 4);
    }

    public void update(float dt){

        batAnimation.update(dt);
        //add gravity to bat if its still on the screen
        if(position.y > 0){
            velocity.add(0,gravity,0);
        }

        velocity.scl(dt);
        position.add( movement * dt , velocity.y, 0); //  change 0 to momevment
        //if bat falls to the ground then stop gravitiy
        if(position.y < 20){
            position.y = 20;
        }
        // dont let bat jump off the screen
        if(position.y >= FlappyBat.width -70){
            position.y = FlappyBat.width -70;
        }
        bounds.setPosition(position.x, position.y);
        velocity.scl(1/dt);
    }
    public void jump(){velocity.y = 210;}

    public Vector3 getPosition() {return position;}

    public Rectangle getBounds(){return bounds;}

    public TextureRegion getTexture() {return batAnimation.getFrame();}

    public void dispose(){bat.dispose();}


}
