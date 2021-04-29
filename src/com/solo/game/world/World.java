package com.solo.game.world;

import com.solo.game.world.tiles.Tile;

import java.util.ArrayList;
import java.util.List;

public class World {

    private static final int CHUNKS_TO_LOAD = 3;
    private List<Chunk> chunks;

    public World() {
        chunks = new ArrayList<>();
    }

    public void addChunk(Chunk chunk) {

        chunks.add(chunk);
        chunk.setPosition(chunks.size());

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
