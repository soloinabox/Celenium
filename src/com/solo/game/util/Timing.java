package com.solo.game.util;

public class Timing {

    public static float elapsed;
    private static long currentFrame;

    public static void init() {

        currentFrame = System.currentTimeMillis();

    }

    public static void next() {

        long lastFrame = currentFrame;
        currentFrame = System.currentTimeMillis();
        elapsed = (float) (currentFrame - lastFrame) / 1000;

    }

}
