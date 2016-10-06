package clueGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class Board {
	public static final int MAX_BOARD_SIZE = 50;
	private int numRows;
	private int numColumns;
	private BoardCell[][] board;
	private Map<Character, String> rooms; // map for legend
	private Map<BoardCell, Set<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private Set<BoardCell> visited;
	private String boardConfigFile;
	private String roomConfigFile;
	
	// variable used for singleton pattern
	public static Board theInstance = new Board();
	// ctor is private to ensure only one can be created
	private Board() {}
	// this method returns the only Board
	public static Board getInstance() {
		return theInstance;
	}
	
	// This will setup the board/ csv
	public void initialize() throws FileNotFoundException {
		rooms = new HashMap<>();
		targets = new HashSet<>();
		visited = new HashSet<>();
		adjMatrix = new HashMap<>();
		
		FileReader csv = new FileReader(boardConfigFile);
		Scanner line = new Scanner(csv);
		
		int i = 0; //This will be what we use to store our max row values as
		while (line.hasNextLine()) {
			int j = 0;	//This is what we will use to store our max column
			String ln = line.nextLine();
			String[] a = ln.split(",");
			
			// We will go through our array of strings and place them into our board
			for (String k :a) {
				// NOTE: we have to see if the string has two characters in it
				if (k.length() == 2) {
					board[i][j] = new BoardCell(i, j, k.charAt(0), DoorDirection.valueOf(String.valueOf(k.charAt(1))));
				}
				else {
					board[i][j] = new BoardCell(i, j, k.charAt(0), DoorDirection.NONE);
				}
				++j;
			}
			numColumns = j;
		}
		numRows = i;
		line.close();
		
		// This will set up the adjmatrix
		board = new BoardCell[numRows][numColumns];
		calcAdjacencies();
		
		return;
	}
	
	
	// load in the legend
	public void loadRoomConfig() throws FileNotFoundException {
		FileReader reader = new FileReader(roomConfigFile);
		Scanner legend = new Scanner(reader);
		legend.useDelimiter(" ");
		while (legend.hasNextLine()) {
			String ln = legend.nextLine();
			String[] a = ln.split(",");
			rooms.put(a[0].charAt(0), a[1]);
		}
		
		return;
	}
	
	public void calcAdjacencies() {
		for (int i = 0; i < numRows; ++i) {
			for (int j = 0; j < numColumns; ++j) {
				Set<BoardCell> adj = new HashSet<BoardCell>();
				if (i-1 >= 0) {
					adj.add(board[i-1][j]);
				}
				if (i+1 < numRows) {
					adj.add(board[i+1][j]);
				}
				if (j+1 < numColumns) {
					adj.add(board[i][j+1]);
				}
				if (j-1 >= 0) {
					adj.add(board[i][j-1]);
				}
				
				adjMatrix.put(board[i][j], new HashSet<BoardCell>(adj));
				adj.clear();
			}
		}
		
		return;
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		visited.add(startCell);
		for (BoardCell i: adjMatrix.get(startCell)) {
			if (!visited.contains(i)) {
				visited.add(i);
				if (pathLength == 1) {
					targets.add(i);
					visited.remove(i);
				}
				else {
					calcTargets(i, pathLength-1);
				}
			}
		}
		visited.remove(startCell);
		return;
	}
	
	public BoardCell getCellAt(int i, int j) {
		return board[i][j];
	}
	public void setConfigFiles(String string, String string2) {
		boardConfigFile = string;
		roomConfigFile = string2;
		
	}
	
	// These are the getters which will be used for the tests and mabye other things down the line
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public Map<Character, String> getLegend() {
		return rooms;
	}
	public Map<BoardCell, Set<BoardCell>> getAdjMatrix() {
		return adjMatrix;
	}
	public Set<BoardCell> getTargets() {
		return targets;
	}
	public BoardCell[][] getBoard() {
		return board;
	}
	public void numRows() {
		return;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMatrix.get(cell);
	}
	public BoardCell getCell(int row, int col) {
		return board[row][col];
	}

}
