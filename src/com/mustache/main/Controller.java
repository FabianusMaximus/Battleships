package com.mustache.main;

import com.mustache.gui.MainMenu;

import javax.swing.*;

public class Controller {

    private JFrame window = new JFrame();

    public Controller() {
        new MainMenu(window);
    }

    private void setupWindow() {
        window.setSize(1000,500);
        window.setResizable(false);
    }


}


class Main {
    public static void main(String[] args) {
        new Controller();
    }
}
