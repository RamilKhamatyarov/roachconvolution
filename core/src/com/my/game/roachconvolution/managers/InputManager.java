package com.my.game.roachconvolution.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dersu on 17.03.16.
 */
public class InputManager extends InputAdapter {
    private OrthographicCamera camera;
    private static Vector3 temp = new Vector3(); //temporary vector

    public InputManager (OrthographicCamera camera) {
        this.camera = camera;
    }

    public boolean isLeftPuddleTouched (float touchX, float touchY) {
        //handle touch input on the left paddle
        if ((touchX >= GameManager.leftPaddleSprite.getX()) && touchX <= (GameManager.leftPaddleSprite.getX()
                + GameManager.leftPaddleSprite.getWidth()) && (touchY >= GameManager.leftPaddleSprite.getY()) &&
                touchY >= GameManager.leftPaddleSprite.getY() + GameManager.leftPaddleSprite.getHeight()){
            return true;
        }

        return false;
    }

    public boolean isRightPuddleTouched (float touchX, float touchY) {
        //handle touch input on the right paddle
        if ((touchX >= GameManager.rightPaddleSprite.getX()) && touchX <= (GameManager.rightPaddleSprite.getX()
                + GameManager.rightPaddleSprite.getWidth()) && (touchY >= GameManager.rightPaddleSprite.getY()) &&
                touchY >= GameManager.rightPaddleSprite.getY() + GameManager.rightPaddleSprite.getHeight()){
            return true;
        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        //set the left key status to pressed
        if (keycode == Input.Keys.LEFT) {
            GameManager.rochelle.setLeftPressed(true);
        }
        //set the right key status to pressed
        else if (keycode == Input.Keys.RIGHT){
            GameManager.rochelle.setRightPressed(true);
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        //set the left key status to pressed
        if (keycode == Input.Keys.LEFT) {
            GameManager.rochelle.setLeftPressed(false);
        }
        //set the right key status to pressed
        else if (keycode == Input.Keys.RIGHT) {
            GameManager.rochelle.setRightPressed(false);
        }

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX, screenY, 0); //get the touch coordinates with respect to the camera's viewpoint
        camera.unproject(temp);

        float touchX = temp.x;
        float touchY = temp.y;

        if (isLeftPuddleTouched(touchX, touchY)) {
            GameManager.rochelle.setLeftPuddleTouched(true);
        }

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        temp.set(screenX, screenY, 0); //get the touch coordinates with respect to the camera's viewpoint
        camera.unproject(temp);

        float touchX = temp.x;
        float touchY = temp.y;

        if (isRightPuddleTouched(touchX, touchY)) {
            GameManager.rochelle.setRightPuddleTouched(true);
        }
        return false;
    }
}
