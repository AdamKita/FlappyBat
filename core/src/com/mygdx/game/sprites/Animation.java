package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Adam on 3/8/2018.
 */

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int  frameCount;
    private int frame;

    /**
     *  Adds the frames to an array
     *  loops through the array
     *  updates the current frame
     *
     * @param region
     * @param frameCount
     * @param cycleTime
     */
    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        int frameHight = region.getRegionHeight() /12;
        for(int i = 0; i < frameCount; i++)
            frames.add(new TextureRegion(region, i * frameWidth , frameHight ,frameWidth, region.getRegionHeight()));

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    /**
     * update the animations
     * @param dt
     */
    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame ++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;
    }
    //retruns current frame of the animation
    public TextureRegion getFrame(){
        return frames.get(frame);
    }

}
