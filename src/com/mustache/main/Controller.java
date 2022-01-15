package com.mustache.main;

import com.mustache.gui.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Controller {

    private final JFrame window = new JFrame();
    private JPanel contentPane;

    public Controller() throws IOException {
        new MainMenu(this);
        setupWindow();
        window.repaint();
    }


    public void setWindow(JPanel contentPane) {
        this.contentPane = contentPane;
    }

    private void setupWindow() {
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1000,500);
        window.setResizable(false);
        window.setVisible(true);
        window.add(contentPane);
    }


}


class Main {
    public static void main(String[] args) throws IOException {
        new Controller();
    }
}
