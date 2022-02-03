package com.mustache.gui.controll;

import com.mustache.gui.PlaceField;
import com.mustache.main.Controller;
import com.mustache.objects.GameLabel;
import com.mustache.objects.Ship;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

@Getter
@Setter
public class PlaceFieldController {

    private Controller controller;
    private PlaceField placeField;

    public PlaceFieldController(Controller con) {
        this.controller = con;
        placeField = new PlaceField(controller);
    }

    public JPanel generateGameField(JPanel pane) {
        GameLabel[] gameField = new GameLabel[100];
        for(int i = 0; i < 100; i++) {
            gameField[i] = new GameLabel(i);
            final int finalI = i;
            gameField[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(controller.getSelectedShip() < 5 && controller.getSelectedShip() >= 0) {
                        controller.getShips()[controller.getSelectedShip()].setStartPosition(finalI);
                        gameField[finalI].reloadColor();
                        System.out.println(controller.getShips()[controller.getSelectedShip()].getStartPosition());
                    }
                    for(Ship ship : controller.getShips()) {
                        if(ship.getPosition() != null) {
                            for (int i = 0; i < ship.getSize(); i++) {
                                gameField[ship.getPosition()[i]].setShip(true);
                            }
                        }
                    }
                    setupNotPlaceable();
                    System.out.println(gameField[finalI].isShip());
                    System.out.println(gameField[finalI].isPlaceable());
                }
            });
            pane.add(gameField[i]);
        }
        return pane;
    }

    private void setupNotPlaceable() {
        ArrayList<Integer> shipFieldIds = new ArrayList<>();
        for(GameLabel lbl : placeField.getGameComponents()) {

        }
    }
}
