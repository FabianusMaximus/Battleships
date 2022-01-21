package com.mustache.gui;

import com.mustache.main.Controller;
import com.mustache.objects.GameLabel;
import com.mustache.objects.Ship;
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
    private JPanel shipFieldPanel = new JPanel(new GridLayout(5,1));
    private GameLabel[][] gameComponents = new GameLabel[10][10];

    private JLabel[] ships = new JLabel[5];
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
                gameComponents[i][j].setOpaque(true);
                gameComponents[i][j].setBackground(Color.blue);
                gameComponents[i][j].setBorder(new LineBorder(Color.BLACK));
                int finalI = i+1;
                int finalJ = j+1;
                gameComponents[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                            System.out.println((finalJ) + ":" + (finalI));
                            System.out.println("Is ship: " + gameComponents[finalI][finalJ].isShip());
                            System.out.println("Is hit: " + gameComponents[finalI][finalJ].isHit());
                            Ship ship = controller.placeShip(finalJ, finalI);
                            if(ship.getPosition() != null) {
                                int[] x = ship.getPosition()[0];
                                int[] y = ship.getPosition()[1];
                                for (int i = 0; i < ship.getSize(); i++) {
                                    if (!gameComponents[y[i]][x[i]].isShip()) gameComponents[y[i]][x[i]].setShip(true);
                                }
                            }
                            setupShipFields();
                            controller.repaint();
                            ships[ship.getId()].removeAll();
                    }
                });
                gamePanel.add(gameComponents[i][j]);
            }
        }
        gamePanel.setOpaque(true);
        gamePanel.setBackground(Color.BLACK);
        gamePanel.setBorder(new LineBorder(Color.BLACK));
        gamePanel.setBounds(50,50,400,400);
        contentPane.add(gamePanel);
    }

    private void setupShipField() {
        shipFieldPanel.setBounds(550, 50 , 200, 200);
        shipFieldPanel.setBorder(new LineBorder(Color.BLACK));
        setupShipFieldPanel();
        readyButton.setBounds(550, 400, 200, 50);
        readyButton.addActionListener(e -> controller.setReady());
        contentPane.add(readyButton);
        contentPane.add(shipFieldPanel);
    }

    private void setupShipFieldPanel() {
        ships[0] = addShipAsLabel("Battleship", 0);
        ships[1] = addShipAsLabel("Cruiser", 1);
        ships[2] = addShipAsLabel("Frigate", 2);
        ships[3] = addShipAsLabel("Frigate", 3);
        ships[4] = addShipAsLabel("Minesweeper", 4);

        for(JLabel lbl : ships) {
            shipFieldPanel.add(lbl);
        }
    }

    private JLabel addShipAsLabel(String name, int id) {
        JLabel lbl = new JLabel(translater.translated(name));
        lbl.setBorder(new LineBorder(Color.BLACK));
        lbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                controller.setShip(id);
            }
        });
        return lbl;
    }

}
