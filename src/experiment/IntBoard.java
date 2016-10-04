package experiment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	// the key is the current cell and the values are the Adjacent cells
	private Map<BoardCell, Set<BoardCell>> adjMtx; 
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	// These values are to be used for comparing the location of the board cell.
	private int rowBounds;	
	private int colBounds;
	
	public IntBoard(int ROWS, int COLS) {
		super();
		grid = new BoardCell[ROWS][COLS];
		rowBounds = ROWS;
		colBounds = COLS;
		for (int i = 0; i < ROWS; ++i) {
			for (int j = 0; j < COLS; ++j) {
				grid[i][j] = new BoardCell(i, j);
			}
		}
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		adjMtx = new HashMap<BoardCell, Set<BoardCell>>();
	}

	public void calcAdjacencies() {
		for (int i = 0; i < rowBounds; ++i) {
			for (int j = 0; j < colBounds; ++j) {
				Set<BoardCell> adj = new HashSet<BoardCell>();
				if (i-1 >= 0) {
					adj.add(grid[i-1][j]);
				}
				if (i+1 < rowBounds) {
					adj.add(grid[i+1][j]);
				}
				if (j+1 < colBounds) {
					adj.add(grid[i][j+1]);
				}
				if (j-1 >= 0) {
					adj.add(grid[i][j-1]);
				}
				
				adjMtx.put(grid[i][j], new HashSet<BoardCell>(adj));
				adj.clear();
			}
		}
		
		return;
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		
		return;
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public Set<BoardCell> getAdjList(BoardCell cell) {
		return adjMtx.get(cell);
	}
	
	public BoardCell getCell(int row, int col) {
		return grid[row][col];
	}
}
