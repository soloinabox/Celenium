package com.solo.game.input;

import com.solo.game.client.Client;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class InputHandler {

    private static List<Axis> axes;
    private static List<Button> buttons;

    private static float scroll = 0f;

    public static void init() {

        axes = new ArrayList<>();
        buttons = new ArrayList<>();

        // Register axes and buttons
        axes.add(new Axis("x", 5f));
        axes.add(new Axis("y", 5f));

    }

    private static Axis getAxis(String name) {

        for(Axis a : axes) {

            if (a.getName().equals(name)) {
                return a;
            }

        }

        return null;

    }

    public static float getAxisFloat(String name) {

        Axis a = getAxis(name);
        if (a != null) {

            return a.getValue();

        } else return 0f;

    }

    public static int getAxisInt(String name) {

        Axis a = getAxis(name);
        if (a != null) {

            return a.getIntValue();

        } else return 0;

    }

    public static void handle(long window, int key, int scancode, int action, int mods) {

        Axis x = getAxis("x");
        Axis y = getAxis("y");

        assert x != null;
        assert y != null;

        // Close

        if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
            glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop

        // Axis Stuff

        if(key == GLFW_KEY_D && action == GLFW_PRESS) {
            x.rawPlus();
        } else if(key == GLFW_KEY_D && action == GLFW_RELEASE) {
            x.rawMinus();
        } else if(key == GLFW_KEY_A && action == GLFW_PRESS) {
            x.rawMinus();
        } else if(key == GLFW_KEY_A && action == GLFW_RELEASE) {
            x.rawPlus();
        } else if(key == GLFW_KEY_S && action == GLFW_PRESS) {
            y.rawPlus();
        } else if(key == GLFW_KEY_S && action == GLFW_RELEASE) {
            y.rawMinus();
        } else if(key == GLFW_KEY_W && action == GLFW_PRESS) {
            y.rawMinus();
        } else if(key == GLFW_KEY_W && action == GLFW_RELEASE) {
            y.rawPlus();
        }

        // Button Stuff

    }

    public static void scrollCallback(long window, double xOffset, double yOffset) {

        scroll = (float)yOffset;

    }

    public static void update() {

        scroll = 0;
        for(Axis a : axes) {
            a.update();
        }

    }

    public static float getScroll() {
        return scroll;
    }
}
