package com.mustache.gui;

import com.mustache.main.Controller;

import javax.swing.*;
import java.awt.*;

public class PlaceField {

    private JPanel contentPane = new JPanel();

    private JPanel gamePanel = new JPanel(new GridLayout(10,10));
    private JLabel[][] gameComponents = new JLabel[10][10];
    private Controller controller;

    public PlaceField(Controller con) {
        controller = con;
        setupGameField();
        con.setWindow(contentPane);
    }

    private void setupGameField() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                System.out.println(i);
                gameComponents[i][j] = new JLabel();
                gameComponents[i][j].setText(i + ":" + j );
                gamePanel.add(gameComponents[i][j]);
            }
        }
        contentPane.add(gamePanel);
    }

}
