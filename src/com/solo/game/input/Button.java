package com.solo.game.input;

public class Button {

    private String name;
    private boolean active;

    public void update(boolean active) {

        this.active = active;

    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }
}
