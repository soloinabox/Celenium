package com.solo.game.client;

import com.solo.game.util.Timing;

public class Camera {

    private float x;
    private float y;

    private float speed;

    public Camera(float speed) {

        x = 0;
        y = 0;

        this.speed = speed;

    }

    public void move(float x, float y) {

        this.x += x * speed * Timing.elapsed;
        this.y += y * speed * Timing.elapsed;

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
