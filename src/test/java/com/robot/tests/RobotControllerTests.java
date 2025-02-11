package com.robot.tests;
//import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
}