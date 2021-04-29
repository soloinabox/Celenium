package com.solo.game;

import com.solo.game.client.Client;
import com.solo.game.client.exceptions.ClientConstructionFailedError;
import com.solo.game.server.exceptions.ClientNotUniqueException;
import com.solo.game.server.exceptions.InvalidVersionException;
import com.solo.game.server.Server;
import com.solo.game.server.exceptions.ServerFullException;

public class Main {

    public static void main(String[] args) {

        try {

            Client client = Client.defaultGame();
            Server server = Server.customServer("resources/client_server.json");

            server.connect(client);
            client.start();

        } catch (ClientNotUniqueException | InvalidVersionException | ServerFullException e) {

            System.out.println(e.getMessage());

        } catch (ClientConstructionFailedError e) {

            e.printStackTrace();

        }

    }

}
