package com.solo.game.world;

import com.solo.game.world.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class Chunk {

    public static final int WIDTH = 4;
    public static final int HEIGHT = 6;

    private int position;
    private List<Tile> tiles;

    public Chunk(List<Tile> tiles) {

        this.tiles = tiles;

    }

    public Chunk() {

        this.tiles = new ArrayList<>();

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
