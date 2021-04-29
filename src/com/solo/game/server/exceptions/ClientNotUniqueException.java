package com.solo.game.server.exceptions;

import com.solo.game.client.Client;

public class ClientNotUniqueException extends Exception {

    private Client client;

    public ClientNotUniqueException(Client client) {

        this.client = client;

    }

    @Override
    public String getMessage() {

        return ("Client UUID " + client.getUuid().toString() + " is not unique.");

    }
}
