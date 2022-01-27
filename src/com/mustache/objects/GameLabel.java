package com.mustache.objects;

import javax.swing.*;

public class GameLabel extends JLabel {

    private boolean ship;
    private boolean hit;
    private boolean placeable = true;

    public GameLabel(boolean ship, boolean hit) {
        this.ship = ship;
        this.hit = hit;
    }

    public boolean isShip() {
        return ship;
    }

    public void setShip(boolean ship) {
        this.ship = ship;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isPlaceable() {
        return placeable;
    }

    public void setPlaceable(boolean placeable) {
        this.placeable = placeable;
    }

}
