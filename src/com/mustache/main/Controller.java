package com.mustache.main;

import com.mustache.gui.MainMenu;
import com.mustache.gui.PlaceField;
import com.mustache.objects.Ship;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Controller {

    private final JFrame window = new JFrame();
    private Container contentPane = window.getContentPane();

    private String language = "german";

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
        PlaceField placeField = new PlaceField(this);
        placeField.setGameFieldToShipField(5,3);
        placeField.setGameFieldToShipField(1,1);
        window.repaint();
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
