package com.solo.game.server.exceptions;

public class ServerFullException extends Exception {

    @Override
    public String getMessage() {
        return "The server is full.";
    }
}
