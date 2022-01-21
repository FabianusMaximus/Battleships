package com.mustache.gui;

import com.mustache.main.Controller;
import com.mustache.objects.GameLabel;
import com.mustache.translate.Translate;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class PlaceField {

    private Container contentPane;
    private Translate translater = new Translate("english", "german");

    private JPanel gamePanel = new JPanel(new GridLayout(10,10));
    private JPanel shipFieldPanel = new JPanel(new GridLayout(10,1));
    private GameLabel[][] gameComponents = new GameLabel[10][10];

    private JButton readyButton = new JButton(translater.translated("Ready"));

    private Controller controller;

    public PlaceField(Controller con) throws IOException {
        controller = con;
        contentPane = con.getWindow().getContentPane();
        contentPane.removeAll();
        setupGameField();
        setupShipField();
        con.setWindow(contentPane);
    }

    public void setGameFieldToShipField(int x, int y) {
        gameComponents[y][x].setShip(true);
        setupShipFields();
    }

    private void setupShipFields() {
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(gameComponents[j][i].isShip()) {
                    gameComponents[j-1][i-1].setOpaque(true);
                    gameComponents[j-1][i-1].setBackground(Color.darkGray);
                }
            }
        }
    }

    private void setupGameField() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                System.out.println();
                gameComponents[i][j] = new GameLabel(false, false);
                gameComponents[i][j].setText((j+1) + ":" + (i+1));
                gameComponents[i][j].setBorder(new LineBorder(Color.BLACK));
                int finalI = i;
                int finalJ = j;
                gameComponents[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        System.out.println((finalJ+1) + ":" + (finalI+1));
                        System.out.println(gameComponents[finalI+1][finalJ+1].isShip());
                        System.out.println(gameComponents[finalI+1][finalJ+1].isHit());
                        setupShipField();
                    }
                });
                gamePanel.add(gameComponents[i][j]);
            }
        }
        gamePanel.setBorder(new LineBorder(Color.BLACK));
        gamePanel.setBounds(50,50,400,400);
        contentPane.add(gamePanel);
    }

    private void setupShipField() {
        shipFieldPanel.setBounds(550, 50 , 200, 400);
        shipFieldPanel.setBorder(new LineBorder(Color.BLACK));
        readyButton.setBounds(800, 50, 150, 30);
        readyButton.addActionListener(e -> controller.setReady());
        contentPane.add(readyButton);
        contentPane.add(shipFieldPanel);
    }

}
