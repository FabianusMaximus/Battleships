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
    private int startPosition;
    private String side = "right";
    private boolean placed = false;

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
        for(int i = 0; i < size; i++) {
            position[i] = startPosition+i;
        }
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
        this.placed = true;
        calculatePosition();
    }

    public int[] getPosition() {
        if(!placed) return null;
        return position;
    }
}
