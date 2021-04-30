package com.solo.game.input;

import com.solo.game.util.Timing;

public class Axis {

    private final String name;

    private float value; // Between -1 and 1
    private int rawValue; //-1, 0, 1
    private final float damping; // Time to move between values from input

    private static final float TOLERANCE = 0.05f;

    public Axis(String name, float damping) {

        this.name = name;
        this.value = 0f;
        this.damping = damping;

    }

    public void rawPlus() {
        rawValue++;
    }

    public void rawMinus() {
        rawValue--;
    }

    public void update() {

        // Interpolate towards rawInput

        if (value < rawValue) {
            value += damping * Timing.elapsed;
        } else if (value > rawValue) {
            value -= damping * Timing.elapsed;
        }

        // Limit

        if (value < -1) value = -1;
        else if (value > 1) value = 1;

        // Snap to 0

        if(rawValue == 0) {
            if (value > 0 && value - TOLERANCE < 0) {
                value = 0;
            } else if (value < 0 && value + TOLERANCE > 0) {
                value = 0;
            }
        }

    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public float getDamping() {
        return damping;
    }

    public int getIntValue() {
        return (int)value;
    }
}
