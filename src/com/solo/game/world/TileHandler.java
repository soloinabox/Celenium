package com.solo.game.world;

import com.solo.game.world.tiles.AirTile;
import com.solo.game.world.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class TileHandler {

    public static List<Tile> tiles;

    public static void init() {

        //register tiles
        tiles = new ArrayList<>();

        tiles.add(new AirTile());
        tiles.add(new Tile("Grass", new float[] {0.2f, 0.9f, 0.2f}));
        tiles.add(new Tile("Dirt", new float[] {0.9f, 0.4f, 0.15f}));
        tiles.add(new Tile("Stone", new float[] {0.3f, 0.3f, 0.3f}));

    }

}
