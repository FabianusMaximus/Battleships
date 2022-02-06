package com.mustache.objects;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

@Setter
@Getter
public class ShipPlaceLabel extends JLabel {

    private String name;
    private int id;

    public ShipPlaceLabel(String name, int id) {
        this.name = name;
        this.setText(name);
        this.id = id;
        this.setOpaque(true);
        this.setBackground(Color.lightGray);
        this.setBorder(new LineBorder(Color.BLACK));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
    }

}
