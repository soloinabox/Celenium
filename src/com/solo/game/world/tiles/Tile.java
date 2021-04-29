package com.solo.game.world.tiles;

public class Tile {

    private String name;
    private float[] rgb;

    public Tile(String name, float[] rgb) {

        this.name = name;
        this.rgb = rgb;

    }

    public Tile copy() throws CloneNotSupportedException
    {
        return new Tile(this.name, this.rgb);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float[] getRgb() {
        return rgb;
    }

    public void setRgb(float[] rgb) {
        this.rgb = rgb;
    }
}
