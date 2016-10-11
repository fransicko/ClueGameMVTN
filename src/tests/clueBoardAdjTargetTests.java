package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class clueBoardAdjTargetTests {
	private static Board board;
	
	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use our config files
		board.setConfigFiles("MT_ClueLayout.csv", "MT_Legend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	// Ensure that player does not move around within room
	// These cells are ORANGE on the planning spreadsheet
	@Test
	public void testAdjacenciesInsideRooms() {
		//Test side
		Set<BoardCell> test = board.getAdjList(board.getCell(9, 0));
		assertEquals(0, test.size());
		//Test one that has a hallway underneath
		test = board.getAdjList(board.getCell(13, 0));
		assertEquals(0, test.size());
		// Test one that has walkway above
		test = board.getAdjList(board.getCell(8, 9));
		assertEquals(0, test.size());
		// Test one that is in middle of room
		test = board.getAdjList(board.getCell(21, 11));
		assertEquals(0, test.size());
		// Test one beside a door
		test = board.getAdjList(board.getCell(2, 15));
		assertEquals(0, test.size());
		// Test one in a corner of room
		test = board.getAdjList(board.getCell(25, 20));
		assertEquals(0, test.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway.
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		Set<BoardCell> test = board.getAdjList(board.getCell(11, 2));
		assertEquals(1, test.size());
		assertTrue(test.contains(board.getCellAt(11, 3)));
		// TEST DOORWAY LEFT 
		test = board.getAdjList(board.getCell(10, 9));
		assertEquals(1, test.size());
		assertTrue(test.contains(board.getCellAt(10, 8)));
		//TEST DOORWAY DOWN
		test = board.getAdjList(board.getCell(16, 10));
		assertEquals(1, test.size());
		assertTrue(test.contains(board.getCellAt(17, 10)));
		//TEST DOORWAY UP
		test = board.getAdjList(board.getCell(5, 9));
		assertEquals(1, test.size());
		assertTrue(test.contains(board.getCellAt(4, 9)));
		
	}
	
	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		Set<BoardCell> testList = board.getAdjList(board.getCell(24, 13));
		assertTrue(testList.contains(board.getCellAt(24, 12)));
		assertTrue(testList.contains(board.getCellAt(24, 14)));
		assertTrue(testList.contains(board.getCellAt(25, 13)));
		assertEquals(3, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(board.getCell(17, 10));
		assertTrue(testList.contains(board.getCellAt(17, 9)));
		assertTrue(testList.contains(board.getCellAt(17, 11)));
		assertTrue(testList.contains(board.getCellAt(16, 10)));
		assertTrue(testList.contains(board.getCellAt(18, 10)));
		assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(board.getCell(11, 3));
		assertTrue(testList.contains(board.getCellAt(11, 2)));
		assertTrue(testList.contains(board.getCellAt(11, 4)));
		assertEquals(2, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(board.getCell(4, 9));
		assertTrue(testList.contains(board.getCellAt(4, 10)));
		assertTrue(testList.contains(board.getCellAt(4, 8)));
		assertTrue(testList.contains(board.getCellAt(5, 9)));
		assertTrue(testList.contains(board.getCellAt(3, 9)));
		assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are LIGHT PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board
		Set<BoardCell> test = board.getAdjList(board.getCellAt(0, 6));
		assertTrue(test.contains(board.getCellAt(0, 5)));
		assertTrue(test.contains(board.getCellAt(0, 7)));
		assertTrue(test.contains(board.getCellAt(1, 6)));
		assertEquals(3, test.size());
		
		// Test on left edge of board
		test = board.getAdjList(board.getCellAt(6, 0));
		assertTrue(test.contains(board.getCellAt(5, 0)));
		assertTrue(test.contains(board.getCellAt(7, 0)));
		assertTrue(test.contains(board.getCellAt(6, 1)));
		assertEquals(3, test.size());
		
		// Test on bottom edge of board
		test = board.getAdjList(board.getCellAt(25, 6));
		assertTrue(test.contains(board.getCellAt(25, 5)));
		assertTrue(test.contains(board.getCellAt(25, 7)));
		assertTrue(test.contains(board.getCellAt(24, 6)));
		assertEquals(3, test.size());
		
		// Test on right edge of board
		test = board.getAdjList(board.getCellAt(18, 25));
		assertTrue(test.contains(board.getCellAt(17, 25)));
		assertTrue(test.contains(board.getCellAt(19, 25)));
		assertTrue(test.contains(board.getCellAt(18, 24)));
		assertEquals(3, test.size());
		
		// Test one inside corner of library
		test = board.getAdjList(board.getCellAt(19, 2));
		assertTrue(test.contains(board.getCellAt(19, 1)));
		assertTrue(test.contains(board.getCellAt(18, 2)));
		assertEquals(2, test.size());
		
		// Test a walkway with 4 adj
		test = board.getAdjList(board.getCellAt(19, 18));
		assertTrue(test.contains(board.getCellAt(18, 18)));
		assertTrue(test.contains(board.getCellAt(19, 19)));
		assertTrue(test.contains(board.getCellAt(20, 18)));
		assertTrue(test.contains(board.getCellAt(19, 17)));
		assertEquals(3, test.size());
		
		
	}
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(0, 5, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 6)));
		assertTrue(targets.contains(board.getCellAt(1, 5)));	

		board.calcTargets(9, 6, 1);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(9, 7)));
		assertTrue(targets.contains(board.getCellAt(9, 5)));	
		assertTrue(targets.contains(board.getCellAt(8, 6)));	
		assertTrue(targets.contains(board.getCellAt(10, 6)));			
	}
	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(0, 5, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 7)));
		assertTrue(targets.contains(board.getCellAt(1, 6)));
		assertTrue(targets.contains(board.getCellAt(2, 5)));

		board.calcTargets(9, 6, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertEquals(8, targets.size());
		assertTrue(targets.contains(board.getCellAt(7, 6)));
		assertTrue(targets.contains(board.getCellAt(8, 7)));	
		assertTrue(targets.contains(board.getCellAt(8, 5)));	
		assertTrue(targets.contains(board.getCellAt(9, 4)));	
		assertTrue(targets.contains(board.getCellAt(10, 5)));	
		assertTrue(targets.contains(board.getCellAt(11, 6)));	
		assertTrue(targets.contains(board.getCellAt(10, 7)));	
		assertTrue(targets.contains(board.getCellAt(9, 8)));
	}

	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsThreeSteps() {
		// Includes a path that doesn't have enough length
		board.calcTargets(15, 0, 3);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(16, 0)));
		assertTrue(targets.contains(board.getCellAt(15, 1)));
		assertTrue(targets.contains(board.getCellAt(15, 3)));
		assertTrue(targets.contains(board.getCellAt(17, 1)));
		assertTrue(targets.contains(board.getCellAt(16, 2)));
		
	}

	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(25, 12, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(23, 14)));
		assertTrue(targets.contains(board.getCellAt(24, 15)));
		assertTrue(targets.contains(board.getCellAt(25, 16)));
		assertTrue(targets.contains(board.getCellAt(24, 9)));
		assertTrue(targets.contains(board.getCellAt(25, 8)));
	}	

	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet
	
	@Test
	public void testTargetsFiveSteps() {
		board.calcTargets(16, 0, 5);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(13, targets.size());
		assertTrue(targets.contains(board.getCellAt(16, 5)));
		assertTrue(targets.contains(board.getCellAt(14, 3)));
		assertTrue(targets.contains(board.getCellAt(15, 4)));
		assertTrue(targets.contains(board.getCellAt(17, 4)));
		assertTrue(targets.contains(board.getCellAt(15, 2)));
		assertTrue(targets.contains(board.getCellAt(15, 0)));
		assertTrue(targets.contains(board.getCellAt(17, 4)));
		assertTrue(targets.contains(board.getCellAt(16, 3)));
		assertTrue(targets.contains(board.getCellAt(19, 2)));
		assertTrue(targets.contains(board.getCellAt(17, 2)));
		assertTrue(targets.contains(board.getCellAt(18, 1)));
		assertTrue(targets.contains(board.getCellAt(16, 1)));
		assertTrue(targets.contains(board.getCellAt(17, 0)));
		// path with not enough length
		assertTrue(targets.contains(board.getCellAt(14, 0)));
		
		
	}

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(8, 13, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(14, 13)));
		assertTrue(targets.contains(board.getCellAt(7, 8)));
		assertTrue(targets.contains(board.getCellAt(8, 7)));
		assertTrue(targets.contains(board.getCellAt(9, 8)));	
	}	
	
	//random comment
}

