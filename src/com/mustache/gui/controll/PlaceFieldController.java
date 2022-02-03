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
        controller.getWindow().getContentPane().removeAll();
        placeField = new PlaceField(controller.getContentPane());
        controller.getWindow().setContentPane(placeField.getContentPane());
        controller.getWindow().repaint();
    }

    public JPanel generateGameField(JPanel pane) {
        GameLabel[] gameField = new GameLabel[100];
        for(int i = 0; i < 100; i++) {
            gameField[i] = new GameLabel(i);
            final int finalI = i;
            gameField[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }
            });
            pane.add(gameField[i]);
        }
        return pane;
    }
}
