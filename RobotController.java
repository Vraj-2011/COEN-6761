package com.robot;

public class RobotController {
    private int[][] floor;
    private int x, y;
    private boolean penDown;
    private String direction;

    public RobotController() {
        // Default initialization
        x = 0;
        y = 0;
        penDown = false;
        direction = "NORTH";
    }

    public void initialize(int n) {
        floor = new int[n][n];
        x = 0;
        y = 0;
        penDown = false;
        direction = "NORTH";
        System.out.println("Initialized " + n + "x" + n + " grid.");
    }

    public void penUp() {
        penDown = false;
        System.out.println("Pen is now up.");
    }

    public void penDown() {
        penDown = true;
        System.out.println("Pen is now down.");
    }

    public void turnRight() {
        switch (direction) {
            case "NORTH" -> direction = "EAST";
            case "EAST" -> direction = "SOUTH";
            case "SOUTH" -> direction = "WEST";
            case "WEST" -> direction = "NORTH";
        }
        System.out.println("Turned right. Now facing " + direction + ".");
    }

    public void turnLeft() {
        switch (direction) {
            case "NORTH" -> direction = "WEST";
            case "WEST" -> direction = "SOUTH";
            case "SOUTH" -> direction = "EAST";
            case "EAST" -> direction = "NORTH";
        }
        System.out.println("Turned left. Now facing " + direction + ".");
    }

    public void move(int steps) {
        for (int i = 0; i < steps; i++) {
            switch (direction) {
                case "NORTH" -> y = Math.min(y + 1, floor.length - 1);
                case "SOUTH" -> y = Math.max(y - 1, 0);
                case "EAST" -> x = Math.min(x + 1, floor[0].length - 1);
                case "WEST" -> x = Math.max(x - 1, 0);
            }
            if (penDown) {
                floor[y][x] = 1;
            }
        }
        System.out.println("Moved " + steps + " steps.");
    }

    public void printFloor() {
        System.out.println("Floor:");
        for (int i = floor.length - 1; i >= 0; i--) {
            for (int j = 0; j < floor[i].length; j++) {
                System.out.print(floor[i][j] == 1 ? "*" : " ");
            }
            System.out.println();
        }
    }

    public void printStatus() {
        System.out.println("Position: [" + x + ", " + y + "] - Pen: " + (penDown ? "down" : "up") + " - Facing: " + direction);
    }
}
