package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class clueBoardTests {
	// Constants that I will use to test whether the file was loaded correctly
		public static final int LEGEND_SIZE = 11;
		public static final int NUM_ROWS = 26;
		public static final int NUM_COLUMNS = 26;
		public static final int NUM_DOORS = 13;
		
		// We are using the same method of set up as the example code due to that seeming like
		// the fastest way
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
	public void testLegend() {
		Map<Character, String> rooms = board.getLegend();

		// THis will test to see if there are LEGEND_SIZE rooms
		assertEquals(LEGEND_SIZE, rooms.size());
		
		// Checks to make sure the rooms are mapped to the correct character
		assertEquals("Gym", rooms.get('G'));
		assertEquals("Living Room", rooms.get('R'));
		assertEquals("Closet", rooms.get('X'));
		assertEquals("Pool room", rooms.get('P'));
		assertEquals("Library", rooms.get('L'));
	}
	@Test
	public void testSize() {
		// Tests the size of the array
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	// This will test to make sure that there are doors that open in all directions
	@Test
	public void FourDoors() {
		BoardCell door = board.getCellAt(11, 2);
		assertEquals(DoorDirection.RIGHT, door.getDoorDirection());

		door = board.getCellAt(5, 9);
		assertEquals(DoorDirection.UP, door.getDoorDirection());
		
		door = board.getCellAt(2, 10);
		assertEquals(DoorDirection.DOWN, door.getDoorDirection());

		door = board.getCellAt(2, 20);
		assertEquals(DoorDirection.LEFT, door.getDoorDirection());
		
		// This will make sure that a cell is not a board
		door = board.getCellAt(5, 5);
		assertEquals(false, door.isDoorway());
	}
	//Test to see that there are the correct number of doors
	@Test
	public void numDoors() {
		int door = 0;
		
		for (BoardCell[] i: board.getBoard()) {
			for (BoardCell j: i) {
				if (j != null && j.isDoorway()) {
					++door;
				}
			}
		}
		
		assertEquals(NUM_DOORS, door);
	}
	@Test
	public void InitialCheck() {
		assertEquals('X', board.getCellAt(0, 0).getInitial());

		assertEquals('W', board.getCellAt(0, 5).getInitial());
		assertEquals('B', board.getCellAt(1, 9).getInitial());
		assertEquals('C', board.getCellAt(21, 11).getInitial());
		assertEquals('A', board.getCellAt(13, 22).getInitial());
		assertEquals('O', board.getCellAt(23, 23).getInitial());
		assertEquals('P', board.getCellAt(12, 10).getInitial());
		
	}

}
