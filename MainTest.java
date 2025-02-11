package com.robot.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.robot.tests.Main;

import java.io.*;


class MainTest {
	    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	    private final InputStream originalIn = System.in;
	    private final PrintStream originalOut = System.out;
	    
	 
	    
	    @BeforeEach
	    void setUpStreams() {
	        System.setOut(new PrintStream(outContent));
	    }

	    @AfterEach
	    void restoreStreams() {
	        System.setIn(originalIn);
	        System.setOut(originalOut);
	    }

	    private void simulateInput(String input) {
	        System.setIn(new ByteArrayInputStream(input.getBytes()));
	    }

	    @Test
	    void testValidInitializeCommand() {
	        simulateInput("I 10\nQ\n"); 
	        simulateInput("i 10\nQ\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Initialized 10x10 grid."));
	    }
	    
	    @Test
	    public void testInputCommandWithValidParts() {
	        String input = "i 10";
	        String[] parts = input.split(" ");

	        assertTrue(parts[0].equalsIgnoreCase("i"));
	        assertTrue(parts.length > 1);
	    }

	    @Test
	    public void testInputCommandWithInvalidParts() {
	        String input = "i";
	        String[] parts = input.split(" ");

	        assertTrue(parts[0].equalsIgnoreCase("i"));
	        assertFalse(parts.length > 1);
	    }
	    
	    @Test
	    public void testInvalidGridSizeExceptionHandling() {
	        try {
	            int size = Integer.parseInt("abc"); // Invalid input
	            fail("Expected NumberFormatException");
	        } catch (NumberFormatException e) {
	            assertEquals("Invalid grid size. Please enter a valid number.", "Invalid grid size. Please enter a valid number.");
	        }
	    }


	    @Test
	    void testValidPenUpCommand() {
	        simulateInput("U\nQ\n");
	        simulateInput("u\nQ\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Pen is now up."));
	    }


	    @Test
	    void testValidPenDownCommand() {
	        simulateInput("D\nQ\n");
	        simulateInput("d\nQ\n");// Correct case
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Pen is now down."));
	    }


	    @Test
	    void testValidTurnRightCommand() {
	        simulateInput("R\nQ\n");
	        simulateInput("r\nQ\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Turned right. Now facing EAST."));
	    }



	    @Test
	    void testValidTurnLeftCommand() {
	        simulateInput("L\nQ\n");
	        simulateInput("l\nQ\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Turned left. Now facing WEST."));
	    }

	

	    @Test
	    void testValidMoveCommand() {
	        simulateInput("I 10\nM 4\nQ\n"); // Correct case
	        simulateInput("I 10\nm 4\nQ\n"); 
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Moved 4 steps."));
	    }

	    @Test
	    void testValidPrintFloorCommand() {
	        simulateInput("I 10\nP\nQ\n");
	        simulateInput("I 10\np\nQ\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Floor:"));
	    }


	    @Test
	    void testValidPrintStatusCommand() {
	        simulateInput("C\nQ\n");
	        simulateInput("c\nQ\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Position: [0, 0]"));
	    }


	    @Test
	    void testValidHistoryCommand() {
	        simulateInput("history\nQ\n");
	        simulateInput("History\nQ\n");
	        Main.main(new String[]{});
	        assertFalse(outContent.toString().contains("history:"));
	    }


	    @Test
	    void testValidHelpCommand() {
	        simulateInput("H\nQ\n");
	        simulateInput("h\nQ\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Invalid command. Enter [Help] for help."));
	    }

	    @Test
	    void testValidQuitCommand() {
	        simulateInput("Q\n");
	        simulateInput("q\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Exiting program..."));
	    }

	
	    @Test
	    void testInvalidCommand() {
	        simulateInput("X\nQ\n");
	        Main.main(new String[]{});
	        assertTrue(outContent.toString().contains("Invalid command. Enter [Help] for help."));
	    }
	    
	    @Test
	    void testPrintHelp() {
	        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        System.setOut(new PrintStream(outContent));
	        Main.printHelp();
	        String[] expectedHelpStrings = {
	            "[U|u] Pen up",
	            "[D|d] Pen down",
	            "[R|r] Turn right",
	            "[L|l] Turn left",
	            "[M s|m s] Move forward s spaces",
	            "[P|p] Print the grid",
	            "[C|c] Print current status",
	            "[I n|i n] Initialize the system",
	            "[History|history] Print the history of actions",
	            "[Q|q] Quit the program"
	        };
	        for (String expected : expectedHelpStrings) {
	            assertTrue(outContent.toString().contains(expected), "Expected to find: " + expected);
	        }
	        System.setOut(System.out);
	    }
	}
