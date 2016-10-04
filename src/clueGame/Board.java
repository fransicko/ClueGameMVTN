package clueGame;

import java.util.Map;
import java.util.Set;

public class Board {
	public static final int MAX_BOARD_SIZE = 50;
	private int numRows;
	private int numColumns;
	private BoardCell[][] board;
	private Map<Character, String> rooms;
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	
	// variable used for singleton pattern
	private static Board theInstance = new Board();
	// ctor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	public void initialize() {
		return;
	}
	
	public void loadRoomConfig() {
		return;
	}
	public void loadBoardConfig() {
		return;
	}
	public void calcAdjacencies() {
		return;
	}
	public void calcTargets(BoardCell cell, int pathLength) {
		return;
	}
	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}

}
