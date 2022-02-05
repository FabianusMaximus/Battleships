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
    private int sideMultiplier = 1;

    public Ship(int id, String name, int size) {
        this.name = name;
        this.size = size;
        this.id = id;
        position = new int[size];
    }

    public void rotate(){
        switch (side) {
            case "right" :
                side = "down";
                sideMultiplier = 10;
                break;
            default:
                side = "right";
                sideMultiplier = 1;
                break;
        }
        calculatePosition();
    }

    private void calculatePosition() {
        int[] prognosesPosition = new int[size];
        boolean possible = true;
        for(int i = 0; i < size; i++) {
            prognosesPosition[i] = startPosition+(i*sideMultiplier);
        }
        for(int pos : prognosesPosition) {
            if(pos > 99) possible = false;
        }
        if(possible) position = prognosesPosition;
    }

    public void setStartPosition(int startPosition) {
        if(getHoveredPositions(startPosition)[size-1] > 99) return;
        this.startPosition = startPosition;
        this.placed = true;
        calculatePosition();
    }

    public int[] getPosition() {
        if(!placed) return null;
        return position;
    }

    public int[] getHoveredPositions(int id) {
        int[] shipPosition = new int[size];
        for(int i = 0; i < size; i++) {
            shipPosition[i] = id+(i*sideMultiplier);
        }
        return shipPosition;
    }
}
