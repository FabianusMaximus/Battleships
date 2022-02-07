package com.mustache.gui.controll;

import com.mustache.gui.PlaceField;
import com.mustache.main.Controller;
import com.mustache.objects.GameLabel;
import com.mustache.objects.Ship;
import com.mustache.objects.ShipPlaceLabel;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;


@Getter
@Setter
public class PlaceFieldController {

    private Controller controller;
    private PlaceField placeField;
    private int selectedShip = 11;
    private Ship[] ships;

    public PlaceFieldController(Controller con) {
        this.controller = con;
        controller.getWindow().getContentPane().removeAll();
        placeField = new PlaceField(controller.getContentPane());
        //placeField.getContentPane().addKeyListener(th);
        ships = controller.getShips();
        setupShipLabels();
        generateGameField();
        setupResetListener();
        controller.getWindow().setContentPane(placeField.getContentPane());
        controller.getWindow().repaint();
    }

    private void generateGameField() {
        GameLabel[] gameField = new GameLabel[100];
        for(int i = 0; i < 100; i++) {
            gameField[i] = new GameLabel(i);
            final int finalI = i;
            gameField[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setShipStartPositionInController(gameField[finalI].getId());
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    if(selectedShip >= 0 && selectedShip < 5 && ships[selectedShip].getPosition() == null) {
                        int[] positions = ships[selectedShip].getHoveredPositions(gameField[finalI].getId());
                        boolean possible = true;
                        for(int pos : positions) {
                            if(pos >= 100) {
                                possible = false;
                                break;
                            }
                            if(!gameField[pos].isPlaceable()) possible = false;
                            if(Math.round(pos/10)*10 >= (Math.round(finalI/10)*10)+10) possible = false;
                        }
                        for(int pos : positions) {
                            if (pos < 100) {
                                if (possible) gameField[pos].setBackground(Color.GREEN);
                                if (!possible) gameField[pos].setBackground(Color.RED);
                            }
                        }
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    for(GameLabel lbl : gameField) {
                        lbl.reloadColor();
                    }
                }
            });
        }
        placeField.setGameComponents(gameField);
    }

    private void setupShipLabels() {
        ShipPlaceLabel[] shipPlaceLabel = new ShipPlaceLabel[5];
        for(int i = 0; i < 5; i++) {
            shipPlaceLabel[i] = new ShipPlaceLabel(ships[i].getName(), i);
            int finalI = i;
            shipPlaceLabel[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedShip = finalI;
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    shipPlaceLabel[finalI].setBackground(Color.GRAY);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    shipPlaceLabel[finalI].setBackground(Color.lightGray);
                }
            });
        }
        placeField.setShipComponents(shipPlaceLabel);
    }

    private void setShipStartPositionInController(int id) {
        System.out.println(selectedShip);
        if(selectedShip >= 0 && selectedShip < 5) {
            if (controller.setShipStartPosition(id, selectedShip)) {
                ships = controller.getShips();
                if(ships[selectedShip].getPosition() != null) {
                    removeShipLabel(selectedShip);
                    selectedShip = 11;
                }
            }
        }
        updateShipFields();
        controller.getWindow().repaint();
    }

    private void removeShipLabel(int id) {
        for(ShipPlaceLabel spl : placeField.getShipPlaceLabels()) {
            if(spl.getId() == id) placeField.getShipPanel().remove(spl);
        }
        JLabel placeholder = new JLabel();
        placeholder.setOpaque(true);
        placeholder.setBackground(Color.lightGray);
        placeholder.setBorder(new LineBorder(Color.BLACK));
        placeField.getShipPanel().add(placeholder);
        placeField.getShipPanel().revalidate();
    }

    private void updateShipFields() {
        for(Ship ship : ships) {
            if(ship.getPosition() != null) {
                for (int position : ship.getPosition()) {
                    placeField.getGameComponents()[position].setShip(true);
                }
            }
        }
    }

    private void setupResetListener() {
        placeField.getReset().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.setupShip();
                placeField.getShipPanel().removeAll();
                placeField.getShipPanel().revalidate();
                setupShipLabels();
                placeField.getGamePanel().removeAll();
                placeField.getGamePanel().revalidate();
                generateGameField();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                placeField.getReset().setBackground(Color.GRAY);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                placeField.getReset().setBackground(Color.lightGray);
            }
        });
    }

    public boolean checkIfPositionPlaceable(int id) {
        return placeField.getGameComponents()[id].isPlaceable();
    }
}