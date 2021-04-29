package com.solo.game.server.exceptions;

import com.solo.game.client.Client;

public class InvalidVersionException extends Exception {

    private final Client client;

    public InvalidVersionException(Client client) {

        this.client = client;

    }

    @Override
    public String getMessage() {

        return ("Client version " + client.getVersion() + " is not up to date.");

    }
}
