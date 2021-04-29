package com.solo.game.client;

import com.solo.game.world.Chunk;
import com.solo.game.world.World;
import com.solo.game.world.tiles.Tile;
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

    public static void getFrame(long window, World world, Camera camera) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

        // This is where shit be drawn
        // Lets draw the server world

        int chunk_width = Chunk.getWIDTH();
        int chunk_height = Chunk.getHEIGHT();

        // i = x + width*y;

        for(int chunk = 0; chunk < world.getChunks().size(); chunk++) {

            Chunk currentChunk = world.getChunks().get(chunk);
            for(int x = 0; x < chunk_width; x ++) {
                for(int y = 0; y < chunk_height; y++) {

                    Tile t = currentChunk.getTiles().get(x + chunk_width * y);
                    float[] rgb = t.getRgb();
                    drawCell(x + currentChunk.getPosition() * chunk_width, y, rgb[0], rgb[1], rgb[2], camera);

                }
            }

        }

        glfwSwapBuffers(window); // swap the color buffers

        // Poll for window events. The key callback above will only be
        // invoked during this call.
        glfwPollEvents();

    }

    public static void drawCell(int x, int y, float r, float g, float b, Camera camera) {

        x *= Client.CELL_SIZE;
        y *= Client.CELL_SIZE;

        x += Client.getWidth()/2;
        y += Client.getHeight()/2;

        x -= camera.getX();
        y -= camera.getY();

        glBegin(GL_QUADS);

        glColor4f(r, g, b, 1f);
        glVertex2d(x, y);
        glVertex2d(x+Client.CELL_SIZE, y);
        glVertex2d(x+Client.CELL_SIZE, y+Client.CELL_SIZE);
        glVertex2d(x, y+Client.CELL_SIZE);

        glEnd();

    }

}
