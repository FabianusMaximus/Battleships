package com.mustache.gui.controll;

import com.mustache.gui.PlaceField;
import com.mustache.main.Controller;
import com.mustache.objects.GameLabel;
import com.mustache.objects.Ship;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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
        controller.setKeyListener(new PlaceFieldKeyListener(this));
        generateGameField();
        setupShipLabels();
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
                    System.out.println(gameField[finalI].isShip());
                    System.out.println(finalI);
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
        ships = controller.getShips();
        for(int i = 0; i < ships.length; i++) {
            placeField.getShips()[i].setText(ships[i].getName());
            final int finalI = i;
            placeField.getShips()[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedShip = finalI;
                }
            });
        }
    }

    private void setShipStartPositionInController(int id) {
        System.out.println(selectedShip);
        if(selectedShip >= 0 && selectedShip < 5) {
            if (controller.setShipStartPosition(id, selectedShip)) {
                ships = controller.getShips();
                selectedShip = 11;
            }
        }
        updateShipFields();
        controller.getWindow().repaint();
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
        placeField.getReset().addActionListener(e -> {
            controller.setupShip();
            placeField.getGamePanel().removeAll();
            placeField.getGamePanel().revalidate();
            generateGameField();
        });
    }

    public boolean checkIfPositionPlaceable(int id) {
        return placeField.getGameComponents()[id].isPlaceable();
    }
}

class PlaceFieldKeyListener implements KeyListener {

    PlaceFieldController controller;

    public PlaceFieldKeyListener(PlaceFieldController controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}