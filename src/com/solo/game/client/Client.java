package com.solo.game.client;

import com.solo.game.client.exceptions.ClientConstructionFailedError;
import com.solo.game.util.JSONHandler;
import com.solo.game.util.exceptions.JSONException;
import org.json.simple.JSONObject;

import java.util.Objects;
import java.util.UUID;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class Client {

    public static final String VERSION = "0.0.0";

    /* Class Stuff */

    // Version string indicates what version the client is running for server compatibility
    private final String version;
    // The UUID should be unique per client to ensure one client per server
    private final UUID uuid;

    /* GLFW */

    private static int width = 0;
    private static int height = 0;
    private static String title = "";
    private static boolean resizeable = false;
    private static boolean vsync = false;

    /* Renderer */

    public static final int CELL_SIZE = 64;

    private long window;

    // Client constructor, instantiated when the game is run
    private Client(String version, UUID uuid) throws ClientConstructionFailedError {

        this.version = version;
        this.uuid = uuid;

        loadWindowProperties();

    }

    // Auto-generates parameters for constructor call and returns it
    public static Client defaultGame() throws ClientConstructionFailedError {

        return new Client(VERSION, generateUUID());

    }

    // Allows dev to call a custom game version with unique parameters
    public static Client customGame(String version) throws ClientConstructionFailedError {

        return new Client(version, generateUUID());

    }

    private void loadWindowProperties() throws ClientConstructionFailedError {

        try {

            JSONObject windowObject = JSONHandler.parseJson("resources/window.json");

            width = Integer.parseInt(windowObject.get("width").toString());
            height = Integer.parseInt(windowObject.get("height").toString());
            title = windowObject.get("title").toString();
            resizeable = Boolean.parseBoolean(windowObject.get("resizeable").toString());
            vsync = Boolean.parseBoolean(windowObject.get("vsync").toString());

        } catch (JSONException e) {

            throw new ClientConstructionFailedError();

        }

    }

    // TODO use machine-id to generate truly unique UUID
    // Generates a unique id for each client
    private static UUID generateUUID() {

        return UUID.randomUUID();

    }

    /* Game Logic */

    // Called on construction
    // Should sequence all game methods
    public void start() {

        init();
        loop();
        kill();

    }

    // Called when the game starts
    private void init() {

        window = WindowHandler.generateWindow(width, height, title, resizeable, false);

    }

    // Called after the game has started
    private void loop() {

        Renderer.init(width, height);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {

            Renderer.getFrame(window, CELL_SIZE);

        }

    }

    // Called when the game is ending
    private void kill() {

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();

    }

    /* Getters and Setters */

    public String getVersion() {
        return version;
    }

    public UUID getUuid() {
        return uuid;
    }
}
