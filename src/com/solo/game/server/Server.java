package com.solo.game.server;

import com.solo.game.client.Client;
import com.solo.game.server.exceptions.ClientNotUniqueException;
import com.solo.game.server.exceptions.InvalidVersionException;
import com.solo.game.server.exceptions.ServerContructionFailedError;
import com.solo.game.server.exceptions.ServerFullException;
import com.solo.game.util.JSONHandler;
import com.solo.game.util.exceptions.JSONException;
import com.solo.game.world.Chunk;
import com.solo.game.world.World;
import com.solo.game.world.tiles.AirTile;
import com.solo.game.world.tiles.Tile;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final String VERSION = "0.0.0";

    private final String version;
    private final List<Client> clients;
    private int max_clients;
    private int tick_rate;

    private World world;

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
            client.setConnectedServer(this);

        }

    }

    public void start() {

        // Generate world
        world = new World();

        //Define blocks
        Tile dirtTile = new Tile("Dirt", new float[] {0.9f, 0.4f, 0.15f});
        Tile grassTile = new Tile("Grass", new float[] {0.2f, 0.9f, 0.2f});
        Tile airTile = new AirTile();

        //Generate chunks
        Chunk c1 = new Chunk();
        Chunk c2 = new Chunk();
        Chunk c3 = new Chunk();

        // 2 rows of air

        for(int i = 0; i < 8; i++) {

            c1.addTile(airTile);
            c2.addTile(airTile);
            c3.addTile(airTile);

        }

        // 1 row of grass

        for(int i = 0; i < 4; i++) {

            c1.addTile(grassTile);
            c2.addTile(grassTile);
            c3.addTile(grassTile);

        }

        // 3 rows of dirt

        for(int i = 0; i < 12; i++) {

            c1.addTile(dirtTile);
            c2.addTile(dirtTile);
            c3.addTile(dirtTile);

        }

        world.addChunkRight(c1);
        world.addChunkLeft(c2);
        world.addChunkRight(c3);

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

    public World getWorld() {
        return world;
    }
}
