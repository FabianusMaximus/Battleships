package com.mustache.gui.controll;

import com.mustache.gui.PlaceField;
import com.mustache.main.Controller;
import com.mustache.objects.GameLabel;
import com.mustache.objects.Ship;
import com.mustache.translate.Translate;
import lombok.Getter;
import lombok.Setter;

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
        generateGameField();
        setupShipLabels();






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
            });
            placeField.getGamePanel().add(gameField[i]);
        }
    }

    private void setupShipLabels() {
        ships = controller.getShips();
        for(int i = 0; i < ships.length; i++) {
            placeField.getShips()[i].setText(ships[i].getName());
        }
    }

    private void setShipStartPositionInController(int id) {
        if(selectedShip <= 0 && selectedShip > 5) {
            if (controller.setShipStartPosition(id, selectedShip)) {
                ships = controller.getShips();
            }
        }
    }
}
