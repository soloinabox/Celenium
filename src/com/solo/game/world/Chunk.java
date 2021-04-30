package com.solo.game.world;

import com.solo.game.util.RandomUtil;
import com.solo.game.world.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    public static final int WIDTH = 8;
    public static final int HEIGHT = 20;

    private int position;
    private List<Tile> tiles;

    public Chunk(List<Tile> tiles) {

        this.tiles = tiles;

    }

    public Chunk() throws CloneNotSupportedException {

        this.tiles = new ArrayList<>();

        generate();

    }

    private void generate() throws CloneNotSupportedException {

        // 4 rows of air

        for(int i = 0; i < WIDTH * 4; i++) {

            addTile(TileHandler.tiles.get(0));

        }

        // 4 rows of grass and dirt

        for (int i = 0; i < Chunk.WIDTH * 4; i++) {

            if(tiles.get(i + WIDTH * 3).getName().equals("Air")) {
                if (RandomUtil.nextInt(0, 10) <= 3) {
                    addTile(TileHandler.tiles.get(1).copy());
                } else {
                    addTile(TileHandler.tiles.get(0).copy());
                }
            } else {
                addTile(TileHandler.tiles.get(2));
            }

        }

        // 2 guaranteed bottom rows of dirt
        for (int i = 0; i < WIDTH * 2; i++) {

            if(tiles.get(i + WIDTH * 7).getName().equals("Air")) {
                addTile(TileHandler.tiles.get(1));
            } else {
                addTile(TileHandler.tiles.get(2));
            }

        }

        // 10 rows of stone

        for (int i = 0; i < Chunk.WIDTH * 12; i++) {

            addTile(TileHandler.tiles.get(3));

        }

    }

    public void addTile(Tile t) {

        if(tiles.size() < WIDTH * HEIGHT) {

            try {

                tiles.add(t.copy());

            } catch (CloneNotSupportedException e) {

                e.printStackTrace();

            }

        }

    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
