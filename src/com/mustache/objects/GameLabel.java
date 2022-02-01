package com.mustache.objects;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter
public class GameLabel extends JLabel {

    private int id;
    private boolean ship = false;
    private boolean hit = false;
    private boolean placeable = true;

    public GameLabel(int id) {
        this.setOpaque(true);
        this.setBorder(new LineBorder(Color.BLACK));
        this.id = id;
        reloadColor();
    }

    public boolean setShip(boolean ship){
        if(this.ship == ship) return false;
        if(ship && !placeable) return false;
        this.ship = ship;
        this.placeable = !ship;
        reloadColor();
        return true;
    }

    public boolean checkForShipCanPlaced() {
        if(this.ship || !placeable) return false;
        return true;
    }

    public void reloadColor() {
        if (ship) {
            this.setBackground(Color.DARK_GRAY);
        } else if (!placeable) {
            this.setBackground(Color.lightGray);
        } else {
            this.setBackground(Color.blue);
        }
    }
}
