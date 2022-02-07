package com.mustache.main;

import com.mustache.gui.MainMenu;
import com.mustache.gui.controll.PlaceFieldController;
import com.mustache.objects.Ship;
import com.mustache.translate.Translate;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

@Setter
@Getter
public class Controller {

    private final JFrame window = new JFrame();
    private Container contentPane = window.getContentPane();

    private String language = "german";
    private Translate translate = new Translate("english", "german");

    private PlaceFieldController placeField;

    private Ship[] ships = new Ship[5];
    private int selectedShip = 11;

    private boolean multiplayer;
    private boolean ready = false;

    public Controller() {
        new MainMenu(this);
        setupWindow();
    }


    public void setWindow(Container contentPane) {
        window.setContentPane(contentPane);
        window.repaint();
    }

    private void setupWindow() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentPane.setSize(1000,535);
        window.setSize(contentPane.getSize());
        contentPane.setBackground(Color.lightGray);
        window.setResizable(false);
        contentPane.setLayout(null);
        window.setContentPane(contentPane);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void startGame(boolean multiplayer) {
        setupShip();
        placeField = new PlaceFieldController(this);
        window.repaint();
    }

    public void setupShip() {
        ships[0] = new Ship(1,translate.translated("Battleship"), 5);
        ships[1] = new Ship(1,translate.translated("Cruiser"), 4);
        ships[2] = new Ship(1,translate.translated("Frigate"), 3);
        ships[3] = new Ship(1,translate.translated("Frigate"), 3);
        ships[4] = new Ship(1,translate.translated("Minesweeper"), 2);
    }

    public boolean setShipStartPosition(int positionId, int shipId) {
        boolean placeable = true;
        if(ships[shipId].getPosition() == null) {
            for(int id : ships[shipId].getHoveredPositions(positionId)) {
                if(id < 100 && !placeField.checkIfPositionPlaceable(id)) placeable = false;
                if(Math.round(id/10)*10 >= (Math.round(positionId/10)*10)+10) placeable = false;
            }
            if(placeable) ships[shipId].setStartPosition(positionId);
            return true;
        }
        return false;
    }
}


class Main {
    public static void main(String[] args) {
        new Controller();
    }
}
