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
	
	
	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(board.getCell(0, 5), 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(0, 6)));
		assertTrue(targets.contains(board.getCellAt(1, 5)));	

		board.calcTargets(board.getCell(9, 6), 1);
		targets= board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCellAt(9, 7)));
		assertTrue(targets.contains(board.getCellAt(9, 5)));	
		assertTrue(targets.contains(board.getCellAt(8, 6)));	
		assertTrue(targets.contains(board.getCellAt(10, 6)));			
	}

}