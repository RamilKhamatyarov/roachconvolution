package com.my.game.roachconvolution.managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.my.game.roachconvolution.gameobjects.Rochelle;

/**
 * Created by dersu on 16.03.16.
 */
public class GameManager {
    public static Rochelle rochelle;
    private static Texture rochelleSpriteSheet;
    private static Sprite backgroundSprite;
    private static Texture backgroundTexture;

    static Texture leftPaddleTexture;
    static Texture rightPaddleTexture;
    static Sprite leftPaddleSprite;
    static Sprite rightPaddleSprite;
    public static final float PADDLE_RESIZE_FACTOR = 9000f;
    public static final float PADDLE_ALFA = 0.5f;
    public static final float PADDLE_HORIZ_POSITION_FACTOR = 0.02f;
    public static final float PADDLE_VERT_POSITION_FACTOR = 0.01f;

    public static final float ROCHELLE_RESIZE_FACTOR = 1000f;

    public static void initialize(float width, float height) {
        rochelle = new Rochelle();
        //load the Rochelle texture with image from file
        rochelleSpriteSheet = new Texture("Rochelle-2.png");
        //initialize Rochelle
        rochelle.initialize(width, height/rochelle.ANIMATION_FRAME_SIZE_VERT, rochelleSpriteSheet);
        //instantiate Rochelle sprite
        rochelle.rochelleSprite = new Sprite();
        //set the size of the Rochelle
        rochelle.rochelleSprite.setSize((rochelle.walkSheet.getWidth()/rochelle.ANIMATION_FRAME_SIZE_HORIZ)
                * (width / ROCHELLE_RESIZE_FACTOR), (rochelle.walkSheet.getHeight()/rochelle.ANIMATION_FRAME_SIZE_VERT)
                * (width / ROCHELLE_RESIZE_FACTOR)); //error on width!!
        //set the position of Rochelle to bottom - center
        rochelle.setPosition(width / 2f, 0);
        //set background texture
        backgroundTexture = new Texture("brain-3.1.png");
        //set background sprite with the texture
        backgroundSprite = new Sprite(backgroundTexture);
        //set the background to completely fill the screen
        backgroundSprite.setSize(width, height);

        initializeLeftPaddle(width, height);
        initializeRightPaddle(width, height);
    }

    public static void renderGame(SpriteBatch batch) {
        backgroundSprite.draw(batch);
        leftPaddleSprite.draw(batch);
        rightPaddleSprite.draw(batch);

        rochelle.update();
        //render (draw) the Rochelle
        rochelle.render(batch);
    }

    public static void initializeLeftPaddle(float width, float height) {
        //load background texture
        leftPaddleTexture = new Texture("LeftPaddle.png");
        //set left paddle sprite with the texture
        leftPaddleSprite = new Sprite(leftPaddleTexture);
        //resize the sprite
        leftPaddleSprite.setSize(leftPaddleSprite.getWidth() * width /
                PADDLE_RESIZE_FACTOR, leftPaddleSprite.getHeight() * width /
                PADDLE_RESIZE_FACTOR);
        //set the position of bottom left corner with offset
        leftPaddleSprite.setPosition(width *
                PADDLE_HORIZ_POSITION_FACTOR, height * PADDLE_VERT_POSITION_FACTOR);
        //make the paddle semi transparent
        leftPaddleSprite.setAlpha(PADDLE_ALFA);

    }
    public static void initializeRightPaddle(float width, float height) {
        //load background texture
        rightPaddleTexture = new Texture("RightPaddle.png");
        //set left paddle sprite with the texture
        rightPaddleSprite = new Sprite(rightPaddleTexture);
        //resize the sprite
        rightPaddleSprite.setSize(rightPaddleSprite.getWidth() * width /
                PADDLE_RESIZE_FACTOR, rightPaddleSprite.getHeight() * width /
                PADDLE_RESIZE_FACTOR);
        //set the position of bottom left corner with offset
        rightPaddleSprite.setPosition(leftPaddleSprite.getX() + leftPaddleSprite.getWidth() + width *
                PADDLE_HORIZ_POSITION_FACTOR, height * PADDLE_VERT_POSITION_FACTOR);
        //make the paddle semi transparent
        rightPaddleSprite.setAlpha(PADDLE_ALFA);

    }

    public static void dispose() {
        leftPaddleTexture.dispose();
        rightPaddleTexture.dispose();
        backgroundTexture.dispose();
        rochelleSpriteSheet.dispose();
    }
}
