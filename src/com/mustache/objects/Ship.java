package com.mustache.objects;

public class Ship {

    private String name;
    private int size;
    private int startPositon;
    private int endPosition;
    private String side = "right";

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.startPositon = startPositon;
        calculateEndPosition();
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
        calculateEndPosition();
    }

    public int[] getPosition() {
        int[] position = new int[2];
        position[0] = startPositon;
        position[1] = endPosition;
        return position;
    }

    private void calculateEndPosition() {
        switch (side) {
            case "right" :
                endPosition = startPositon+size;
                break;
            case "down" :
                endPosition = startPositon-(size*10);
                break;
            case "up" :
                endPosition = startPositon+(size*10);
            default:
                endPosition = startPositon+size;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStartPositon() {
        return startPositon;
    }

    public void setStartPositon(int startPositon) {
        this.startPositon = startPositon;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
