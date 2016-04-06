package com.my.game.roachconvolution.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by dersu on 16.03.16.
 */
public class Rochelle {
    public Sprite rochelleSprite;

    private boolean isLeftPressed; // indicates if left key is pressed
    private boolean isRightPressed; //indicates if right key is pressed
    private boolean isLeftPuddleTouched;  //indicates if left paddle is touched
    private boolean isRightPuddleTouched; //indicates if right paddle is touched
    private static final float X_MOVE_UNITS = 3f; //units roach will move in x direction

    public Animation walkAnimation;

    public Texture walkSheet; //sprite sheet
    public TextureRegion currentFrame;
    public float stateTime; //elapsed time

    public static int ANIMATION_FRAME_SIZE_HORIZ = 6; //this specifies the number of images that we are using for animation in horizontal direction
    public static int ANIMATION_FRAME_SIZE_VERT = 2; //this specifies the number of images that we are using for animation in vertical direction
    public static float ANIMATION_TIME_PERIOD = 0.08f; //this specifies the time between two consecutive frame animation

    boolean updateAnimationStateTime = false; //keep track of when to update Rochelle's state time

    enum Direction {LEFT, RIGHT};
    Direction direction = Direction.RIGHT; //denotes player's direction. defaulted to right

    public void update() {
        updateAnimationStateTime = false;

        //move specified units to left if left key is pressed
        if (isLeftPressed) {
            updateAnimationStateTime = true;
            direction = Direction.LEFT;
            move(-X_MOVE_UNITS, 0);
        }
        //move to right
        else if (isRightPressed){
            updateAnimationStateTime = true;
            direction = Direction.RIGHT;
            move(X_MOVE_UNITS, 0);
        }

        //move specified units to left if left puddle is touched
        if (isLeftPuddleTouched) {
            updateAnimationStateTime = true;
            direction = Direction.LEFT;
            move(-X_MOVE_UNITS, 0);
        }
        //move to right
        else if (isRightPuddleTouched) {
            updateAnimationStateTime = true;
            direction = Direction.RIGHT;
            move(X_MOVE_UNITS, 0);
        }

        //if Rochelle is moving, only he update his state time
        if (updateAnimationStateTime) {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        }
    }

    public void render(SpriteBatch batch){
        rochelleSprite.setRegion(currentFrame); //set the rochelle's texture to the current frame

        if (direction == Direction.LEFT){
            rochelleSprite.setFlip(true, false);
        } else {
            rochelleSprite.setFlip(false, false);
        }

        rochelleSprite.draw(batch);
    }

    public void setPosition(float x, float y) {
        rochelleSprite.setPosition(x, y);

    }

    public void initialize (float width, float height, Texture walkSheet) {
        this.walkSheet = walkSheet;
        //split the sprite sheet into different textures
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / ANIMATION_FRAME_SIZE_HORIZ,
                walkSheet.getHeight() / ANIMATION_FRAME_SIZE_VERT);
        //convert 2D array to 1D
        TextureRegion[] walkFrames = new TextureRegion[tmp.length * tmp[0].length]; int k = 0;
        for (int i = 0; i < tmp.length; i++) {
            for ( int j = 0; j < tmp[0].length; j++) {
                if (tmp[i][j] != null) walkFrames[k] = tmp[i][j];
                k++;
            }
        }
        //create new animation sequence with the walk frames and time period of specified seconds
        walkAnimation = new Animation(ANIMATION_TIME_PERIOD, walkFrames);
        //set the animation to loop
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);
        //get initial frame
        currentFrame = walkAnimation.getKeyFrame(stateTime, true);
    }

    // move roach's with the specified amount
    public void move (float x, float y) {
        setPosition(rochelleSprite.getX() + x, rochelleSprite.getY() + y);
    }

    public void setLeftPuddleTouched(boolean isTouched) {
        //to restrict motion in only one direction if both are touched
        if (isRightPuddleTouched && isTouched) {
            isRightPuddleTouched = false;
        }

        isLeftPuddleTouched = isTouched;
    }

    public void setRightPuddleTouched(boolean isTouched) {
        //to restrict motion if both are touched
        if (isLeftPuddleTouched && isTouched) {
            isLeftPuddleTouched = false;
        }

        isRightPuddleTouched = isTouched;
    }

    public void setLeftPressed(boolean isPressed) {
        //to restrict motion if both are pressed
        if (isRightPressed && isPressed) {
            isRightPressed = false;
        }
        isLeftPressed = isPressed;
    }

    public void setRightPressed(boolean isPressed) {
        //to restrict motion if both are pressed
        if (isLeftPressed && isPressed) {
            isLeftPressed = false;
        }
        isRightPressed = isPressed;
    }

    public Texture getWalkSheet() {
        return walkSheet;
    }
}
