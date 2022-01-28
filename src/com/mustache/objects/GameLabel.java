package com.mustache.objects;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Getter
@Setter
public class GameLabel extends JLabel {

    private int id;
    private boolean ship;
    private boolean hit;
    private boolean placeable = true;

    public GameLabel(int id, boolean ship, boolean hit) {
        this.id = id;
        this.ship = ship;
        this.hit = hit;
    }



}
