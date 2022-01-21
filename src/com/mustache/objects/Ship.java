package com.mustache.objects;

public class Ship {

    private String name;
    private int size;
    private int[][] position;
    private String side = "right";

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        position = new int[2][size];
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

    public int[][] getPosition() {
        return position;
    }

    private void calculatePosition() {
        switch (side) {
            case "left" :
                for(int i = 1; i < size; i++) {
                    position[0][i] = position[0][0]-i;
                    position[1][i] = position[1][0];
                }
                break;
            case "up" :
                for(int i = 1; i < size; i++) {
                    position[0][i] = position[0][0];
                    position[1][i] = position[1][0]-i;
                }
                break;
            case "down" :
                for(int i = 1; i < size; i++) {
                    position[0][i] = position[0][0];
                    position[1][i] = position[1][0]+i;
                }
                break;
            case "right" :
                for(int i = 1; i < size; i++) {
                    position[0][i] = position[0][0]+i;
                    position[1][i] = position[1][0];
                }
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

    public void setStartPositon(int x, int y) {
        position[0][0] = x;
        position[1][0] = y;
        calculatePosition();
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
