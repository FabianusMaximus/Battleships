package com.mustache.main;

import com.mustache.gui.MainMenu;
import com.mustache.gui.PlaceField;

import javax.swing.*;
import java.io.IOException;

public class Controller {

    private final JFrame window = new JFrame();
    private JPanel contentPane;

    private String language = "german";

    private boolean multiplayer;

    public Controller() throws IOException {
        new MainMenu(this);
        setupWindow();
    }


    public void setWindow(JPanel contentPane) {
        this.contentPane = contentPane;
        window.revalidate();
        window.repaint();
    }

    private void setupWindow() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1000,500);
        window.setResizable(false);
        window.setVisible(true);
        window.add(contentPane);
    }

    public void startGame(boolean multiplayer) {
        setMultiplayer(multiplayer);
        new PlaceField(this);
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
}


class Main {
    public static void main(String[] args) throws IOException {
        new Controller();
    }
}
