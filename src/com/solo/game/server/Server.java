package com.solo.game.server;

import com.solo.game.client.Client;
import com.solo.game.server.exceptions.ClientNotUniqueException;
import com.solo.game.server.exceptions.InvalidVersionException;
import com.solo.game.server.exceptions.ServerContructionFailedError;
import com.solo.game.server.exceptions.ServerFullException;
import com.solo.game.util.JSONHandler;
import com.solo.game.util.exceptions.JSONException;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final String VERSION = "0.0.0";

    private final String version;
    private final List<Client> clients;
    private int max_clients;
    private int tick_rate;

    private Server(int max_clients, int tick_rate, String version) {

        clients = new ArrayList<>();

        this.max_clients = max_clients;
        this.tick_rate = tick_rate;
        this.version = version;

    }

    public static Server defaultServer(int max_clients, int tick_rate) {

        return new Server(max_clients, tick_rate, VERSION);

    }

    public static Server customServer(int max_clients, int tick_rate, String version) {

        return new Server(max_clients, tick_rate, version);

    }

    public static Server customServer(String file) throws ServerContructionFailedError {

        try {

            JSONObject serverObject = JSONHandler.parseJson(file);
            return new Server(
                    Integer.parseInt(serverObject.get("max_clients").toString()),
                    Integer.parseInt(serverObject.get("tick_rate").toString()),
                    serverObject.get("version").toString()
            );

        } catch (JSONException e) {

            throw new ServerContructionFailedError();

        }


    }

    private boolean isUUIDUnique(Client client) {

        for (Client c : clients) {

            if (c.getUuid().equals(client.getUuid())) {
                return (false);
            }

        }

        return true;

    }

    private boolean isVersionValid(Client client) {

        return (client.getVersion().equals(version));

    }

    public void connect(Client client) throws ClientNotUniqueException, InvalidVersionException, ServerFullException {

        if (!isUUIDUnique(client)) {

            throw new ClientNotUniqueException(client);

        } else if (!isVersionValid(client)) {

            throw new InvalidVersionException(client);

        } else if (clients.size() == max_clients) {

            throw new ServerFullException();

        } else {

            clients.add(client);

        }

    }

    public int getMax_clients() {
        return max_clients;
    }

    public void setMax_clients(int max_clients) {
        this.max_clients = max_clients;
    }

    public int getTick_rate() {
        return tick_rate;
    }

    public void setTick_rate(int tick_rate) {
        this.tick_rate = tick_rate;
    }
}
