package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;

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
	public void test() {
		fail("Not yet implemented");
	}

}
