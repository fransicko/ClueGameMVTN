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
	
	public IntBoard(int ROWS, int COLS) {
		super();
		grid = new BoardCell[ROWS][COLS];
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
		
	}
	
	public void calcTargets(BoardCell startCell, int pathLength) {
		
	}
}
