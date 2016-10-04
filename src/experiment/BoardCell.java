package experiment;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private enum DoorDirection {UP, DOWN, LEFT, RIGHT, NONE}
	
	public BoardCell(int row, int column, char initial) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public boolean isWalkway() {
		if (initial == 'W') {
			return true;
		}
		return false;
	}
	
	public boolean isRoom() {
		if (initial != 'W' && initial != 'X') {
			return true;
		}
		return false;
	}
	
	public boolean isDoorway() {
		
	}
	
}
