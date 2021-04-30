package com.solo.game.world;

import com.solo.game.client.Client;
import com.solo.game.world.tiles.AirTile;
import com.solo.game.world.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class World {

    private static final int CHUNKS_TO_LOAD = 3;
    private List<Chunk> chunks;

    private int leftBound = -1;
    private int rightBound = 0;

    public World() {
        chunks = new ArrayList<>();
    }

    public void addChunkRight(Chunk chunk) {

        chunks.add(chunk);
        chunk.setPosition(rightBound);
        rightBound += 1;

    }

    public void addChunkLeft(Chunk chunk) {

        chunks.add(0, chunk);
        chunk.setPosition(leftBound);
        leftBound -= 1;

    }

    public static int getChunksToLoad() {
        return CHUNKS_TO_LOAD;
    }

    public List<Chunk> getChunks() {
        return chunks;
    }

    public void setChunks(List<Chunk> chunks) {
        this.chunks = chunks;
    }

    public void generate() {

        TileHandler.init();

        try {
            // Add 3 chunks left and right
            addChunkRight(new Chunk());
            addChunkRight(new Chunk());
            addChunkRight(new Chunk());
            addChunkLeft(new Chunk());
            addChunkLeft(new Chunk());
            addChunkLeft(new Chunk());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    public void requestUpdate(int x) {

        if(x / Client.CELL_SIZE > chunks.get(chunks.size()-1).getPosition() * Chunk.WIDTH) {

            try {
                addChunkRight(new Chunk());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        } else if(x / Client.CELL_SIZE < chunks.get(0).getPosition() * Chunk.WIDTH) {

            try {
                addChunkLeft(new Chunk());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        }

    }

}
