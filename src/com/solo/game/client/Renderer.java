package com.solo.game.client;

import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void init(int width, int height) {

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity(); // Resets any previous projection matrices
        glOrtho(0, width, height, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);

    }

    public static void getFrame(long window, int cell_size) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        // This is where shit be drawn
        drawCell(0, 0, cell_size, 1f, 1f, 1f);
        drawCell(1, 0, cell_size, 1f, 0f, 0f);
        drawCell(2, 0, cell_size, 0f, 1f, 0f);
        drawCell(3, 1, cell_size, 0f, 0f, 1f);
        drawCell(3, 2, cell_size, 0.8f, 0.1f, 0.7f);

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();

    }

    public static void drawCell(int x, int y, int cell_size, float r, float g, float b) {

        x *= cell_size;
        y *= cell_size;

        glBegin(GL_QUADS);

        glColor4f(r, g, b, 1f);
        glVertex2d(x, y);
        glVertex2d(x+cell_size, y);
        glVertex2d(x+cell_size, y+cell_size);
        glVertex2d(x, y+cell_size);

        glEnd();

    }

}
