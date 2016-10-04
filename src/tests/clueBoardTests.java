package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

public class clueBoardTests {
	// Constants that I will use to test whether the file was loaded correctly
		public static final int LEGEND_SIZE = 11;
		public static final int NUM_ROWS = 26;
		public static final int NUM_COLUMNS = 26;
		
		// We are using the same method of set up as the example code due to that seeming like
		// the fastest way
		private static Board board;
	
	@BeforeClass
	public static void setUp() {
		// Board is singleton, get the only instance
		board = Board.getInstance();
		// set the file names to use our config files
		board.setConfigFiles("MT_ClueLayout.xlsx", "MT_Legend.txt");		
		// Initialize will load BOTH config files 
		board.initialize();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
