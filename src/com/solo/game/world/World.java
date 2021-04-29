package com.solo.game.world;

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
}
