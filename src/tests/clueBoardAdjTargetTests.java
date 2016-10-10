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

}
