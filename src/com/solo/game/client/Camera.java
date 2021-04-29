package com.solo.game.client;

public class Camera {

    private float x;
    private float y;

    private float speed;

    public Camera(float speed) {

        x = 0;
        y = 0;

        this.speed = speed;

    }

    public void move(int x, int y, float elapsed) {

        this.x += x * speed * elapsed;
        this.y += y * speed * elapsed;

    }

    public void changeSpeed(float s) {
        speed += s;
        if (speed < 0) {
            speed = 0;
        }
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

}
