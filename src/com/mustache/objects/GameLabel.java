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

    /**
     *
     * Custom JLabel, das das Platzieren von Schiffen möglich macht
     *
     * @param id Nummer des Panels, auf dem Spielfeld
     */
    public GameLabel(int id) {
        this.setOpaque(true);
        this.setBorder(new LineBorder(Color.BLACK));
        this.id = id;
        reloadColor();
    }

    /**
     * überprüft, ob das Schiff an dieser Stelle platziert werden kann,
     * wenn ja, wird das Schiff platziert und true zurückgegeben
     * @param ship zu setzender boolescher Wert
     * @return ob das Schiff plaziert wurde
     */
    public boolean setShip(boolean ship){
        if(this.ship == ship) return false;
        if(ship && !placeable) return false;
        this.ship = ship;
        this.placeable = !ship;
        reloadColor();
        return true;
    }

    /**
     * färbt das Panel entsprechend seines Zustands ein
     */
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
