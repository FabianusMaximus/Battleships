package com.mustache.main;

import com.mustache.gui.MainMenu;
import com.mustache.gui.PlaceField;
import com.mustache.objects.Ship;
import com.mustache.translate.Translate;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Controller {

    private final JFrame window = new JFrame();
    private Container contentPane = window.getContentPane();

    private String language = "german";
    private Translate translate = new Translate("english", "german");

    private PlaceField placeField;

    private Ship[] ships = new Ship[5];
    private int selectedShip = 11;

    private boolean multiplayer;
    private boolean ready = false;

    public Controller() throws IOException {
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
        window.setResizable(false);
        contentPane.setLayout(null);
        window.setContentPane(contentPane);
        window.setVisible(true);
    }

    public void startGame(boolean multiplayer) throws IOException {
        setMultiplayer(multiplayer);
        placeField = new PlaceField(this);
        setupShip();
        window.repaint();
    }

    private void setupShip() {
        ships[0] = new Ship(1,translate.translated("Battleship"), 5);
        ships[1] = new Ship(1,translate.translated("Cruiser"), 4);
        ships[2] = new Ship(1,translate.translated("Frigate"), 3);
        ships[3] = new Ship(1,translate.translated("Frigate"), 3);
        ships[4] = new Ship(1,translate.translated("Minesweeper"), 2);
    }

    public Ship placeShip(int x, int y) {
        if(selectedShip < 5 && selectedShip >= 0) {
            int i = selectedShip;
            ships[i].setStartPositon(x, y);
            System.out.println(ships[i].getSize());
            selectedShip = 11;
            return ships[i];
        }
        return null;
    }

    public void repaint() {
        window.repaint();
    }

    public void setShip(int shipType) {
        selectedShip = shipType;
        System.out.println(selectedShip);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public JFrame getWindow() {
        return window;
    }

    public void setReady() {
        ready = true;
    }

    public boolean isReady() {
        return ready;
    }
}


class Main {
    public static void main(String[] args) throws IOException {
        new Controller();
    }
}
