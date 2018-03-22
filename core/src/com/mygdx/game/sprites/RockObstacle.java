package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by Adam on 3/7/2018.
 */

public class RockObstacle {
    public  static final  int rockWidth = 30;
    private static final int fluctuation = 200;
    private static final int rockGap = 100;
    private static final int lowestOpening = 60;
    private Texture topRock , bottomRock;
    private Texture coin;
    private Vector2 posTopRock, posBottomRock;
    private Vector2 posCoin;
    private Random rand;
    private Rectangle boundsTop, boundsBottom;
    private Rectangle boundsCoin;

    public RockObstacle(float x){
        topRock = new Texture("UpperRock.png");
        bottomRock = new Texture("BottomRock.png");
        coin = new Texture("coin.png");
        rand = new Random();

        posTopRock = new Vector2(x, rand.nextInt(fluctuation) + rockGap + lowestOpening);
        posBottomRock = new Vector2(x, posTopRock.y - rockGap - bottomRock.getHeight());
        posCoin = new Vector2(x + coin.getWidth()/2 - 5, posTopRock.y - rockGap+ 40 - coin.getHeight()/2);


        //AREA OF A RECTANGLE HERE
        boundsTop = new Rectangle(posTopRock.x ,posTopRock.y- 20 , topRock.getWidth()/2,topRock.getHeight());
        boundsBottom = new Rectangle(posBottomRock.x ,posBottomRock.y -20 , bottomRock.getWidth()/2 ,bottomRock.getHeight());

        //COIN BOUNDS
        boundsCoin = new Rectangle(posCoin.x, posCoin.y, coin.getWidth(),coin.getHeight());


    }


    public Texture getTopRock() {return topRock;}

    public Texture getBottomRock() {return bottomRock; }

    public Vector2 getPosTopRock() {return posTopRock;}

    public Vector2 getPosBottomRock() {return posBottomRock;}

    public Vector2 getPosCoin(){return posCoin;}

    public Texture getCoin(){return coin;}

   // public Rectangle getCoinBounds(){return boundsCoin;}

    /**
     * Repositions bounds
     * @param x
     */
    public void reposition(float x){
        posTopRock.set(x, rand.nextInt(fluctuation) + rockGap + lowestOpening);
        posBottomRock.set(x, posTopRock.y - rockGap - bottomRock.getHeight());
        posCoin.set(x + coin.getWidth()/2 -5, posTopRock.y - rockGap+ 40 - coin.getHeight()/2);

        boundsTop.setPosition(posTopRock.x,posTopRock.y);
        boundsBottom.setPosition(posBottomRock.x ,posBottomRock.y);
        boundsCoin.setPosition(posCoin.x,posCoin.y);


    }

    /**
     * checks if played collides with object bounds
     * @param player
     * @return
     */
    public boolean collides(Rectangle player){
        return player.overlaps(boundsBottom) || player.overlaps(boundsTop);
    }

    /**
     * Checks if player touches coin object
     * @param player
     * @return
     */
    public boolean touchCoin(Rectangle player) {
        return player.overlaps(boundsCoin);

    }

    /**
     * Disposes all the Textures
     */
    public void dispose(){
        topRock.dispose();
        bottomRock.dispose();
        coin.dispose();
    }
    //TESTING THIS
    public void CoinDisaprea(){
        getCoin().dispose();
    }
}
