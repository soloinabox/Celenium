package com.solo.game.input;

import com.solo.game.client.Client;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {

    private static int x = 0;
    private static int y = 0;

    private static float scroll = 0f;

    public static void handle(long window, int key, int scancode, int action, int mods) {

        if(key == GLFW_KEY_D && action == GLFW_PRESS) {
            x += 1;
        } else if(key == GLFW_KEY_D && action == GLFW_RELEASE) {
            x -= 1;
        } else if(key == GLFW_KEY_A && action == GLFW_PRESS) {
            x -= 1;
        } else if(key == GLFW_KEY_A && action == GLFW_RELEASE) {
            x += 1;
        } else if(key == GLFW_KEY_S && action == GLFW_PRESS) {
            y += 1;
        } else if(key == GLFW_KEY_S && action == GLFW_RELEASE) {
            y -= 1;
        } else if(key == GLFW_KEY_W && action == GLFW_PRESS) {
            y -= 1;
        } else if(key == GLFW_KEY_W && action == GLFW_RELEASE) {
            y += 1;
        }

    }

    public static void scrollCallback(long window, double xOffset, double yOffset) {

        scroll = (float)yOffset;

    }

    public static void update() {

        scroll = 0;

    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static float getScroll() {
        return scroll;
    }
}
