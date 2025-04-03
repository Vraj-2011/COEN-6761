package com.robot.tests;
//import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RobotControllerTests {
    private RobotController robot;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        robot = new RobotController();
        System.setOut(new PrintStream(outContent)); // Redirect console output for testing
    }

    @Test
    public void testInitializeCommand() {
        robot.initialize(10);
        assertTrue(outContent.toString().contains("Initialized 10x10 grid."));
    }

    @Test
    public void testCheckStatusCommand() {
        robot.initialize(10);
        robot.printStatus();
        assertTrue(outContent.toString().contains("Position: [0, 0] - Pen: up - Facing: NORTH"));
    }

    @Test
    public void testPenDownCommand() {
        robot.penDown();
        assertTrue(outContent.toString().contains("Pen is now down."));
        assertTrue(robot.penDown);
    }

    @Test
    public void testMoveCommand() {
        robot.initialize(10);
        robot.penDown();
        robot.move(4);
        assertTrue(outContent.toString().contains("Moved 4 steps."));
        assertEquals(4, robot.y); // Check final position
    }

    @Test
    public void testTurnRightCommand() {
        robot.turnRight();
        assertTrue(outContent.toString().contains("Turned right. Now facing EAST."));
        assertEquals(RobotController.Direction.EAST, robot.direction);
    }

    @Test
    public void testMoveEastCommand() {
        robot.initialize(10);
        robot.turnRight(); // Facing EAST
        robot.move(3);
        assertTrue(outContent.toString().contains("Moved 3 steps."));
        assertEquals(3, robot.x); // Check final position
    }

    @Test
    public void testPrintFloorCommand() {
        robot.initialize(10);
        robot.penDown();
        robot.move(2);
        robot.printFloor();
        assertTrue(outContent.toString().contains("Floor:")); // Ensuring floor prints
    }

    @Test
    public void testHistoryCommand() {
        robot.initialize(10);
        robot.penDown();
        robot.move(2);
        robot.turnRight();
        robot.printHistory();
        assertTrue(outContent.toString().contains("History:"));
        assertTrue(outContent.toString().contains("Initialized 10x10 grid."));
        assertTrue(outContent.toString().contains("Pen is now down."));
        assertTrue(outContent.toString().contains("Moved 2 steps."));
        assertTrue(outContent.toString().contains("Turned right. Now facing EAST."));
    }

    @ParameterizedTest
    @CsvSource({
            "0, NORTH, 5, 0, 0, true, 0, 0",
            "2, NORTH, 5, 0, 0, true, 2, 0",
            "2, SOUTH, 5, 3, 3, true, 1, 0",
            "2, EAST, 5, 0, 0, true, 0, 2",
            "2, WEST, 5, 3, 3, true, 0, 1",
            "2, NORTH, 5, 0, 0, false, 2, 0",
    })
    public void dataFlowTesting(int steps, RobotController.Direction direction, int floorSize,
                                int initX, int initY, boolean isPenDown,
                                int expectedX, int expectedY) {
        // Prepare
        robot.initialize(floorSize);
        robot.direction = direction;
        robot.x = initX;
        robot.y = initY;
        robot.penDown = isPenDown;

        // Act
        robot.move(steps);

        int actualX = initX;
        int actualY = initY;
        int[][] expectedFloor = new int[floorSize][floorSize];
        for (int i = 0; i < steps; i++) {
            switch (direction) {
                case NORTH -> actualY = Math.min(actualY + 1, floorSize - 1);
                case SOUTH -> actualY = Math.max(actualY - 1, 0);
                case EAST -> actualX = Math.min(actualX + 1, floorSize - 1);
                case WEST -> actualX = Math.max(actualX - 1, 0);
            }
            if (isPenDown) {
                expectedFloor[actualY][actualX] = 1;
            }
        }

        // Assert
        assertEquals(actualX, robot.x, "X failed");
        assertEquals(actualY, robot.y, "Y failed");

        int[][] actualFloor = robot.getFloor();
        for (int y = 0; y < floorSize; y++) {
            for (int x = 0; x < floorSize; x++) {
                assertEquals(expectedFloor[y][x], actualFloor[y][x],
                        "marking failed");
            }
        }
    }
}