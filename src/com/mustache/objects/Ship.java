package com.mustache.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship {

    private int id;
    private String name;
    private int size;
    private int[] position;
    private String side = "right";

    public Ship(int id, String name, int size) {
        this.name = name;
        this.size = size;
        this.id = id;
        position = new int[size];
        calculatePosition();
    }

    public void rotate(){
        switch (side) {
            case "right" :
                side = "down";
                break;
            case "down" :
                side = "up";
                break;
            default:
                side = "right";
                break;
        }
        calculatePosition();
    }

    private void calculatePosition() {

    }
}
