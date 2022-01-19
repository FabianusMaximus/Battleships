package com.mustache.gui;

import com.mustache.main.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PlaceField {

    private Container contentPane;

    private JPanel gamePanel = new JPanel(new GridLayout(10,10));
    private JPanel shipFieldPanel = new JPanel(new GridLayout(10,1));
    private JLabel[][] gameComponents = new JLabel[10][10];
    private Controller controller;

    public PlaceField(Controller con) {
        controller = con;
        contentPane = con.getWindow().getContentPane();
        contentPane.removeAll();
        setupGameField();
        con.setWindow(contentPane);
    }

    private void setupGameField() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                System.out.println();
                gameComponents[i][j] = new JLabel();
                gameComponents[i][j].setText(i + ":" + j );
                gameComponents[i][j].setBorder(new LineBorder(Color.BLACK));
                gamePanel.add(gameComponents[i][j]);
            }
        }
        gamePanel.setBorder(new LineBorder(Color.BLACK));
        gamePanel.setBounds(50,50,400,400);
        contentPane.add(gamePanel);
    }

    private void setupShipField() {
        shipFieldPanel.setBounds(550, 50 , 400, 400);
    }

}
