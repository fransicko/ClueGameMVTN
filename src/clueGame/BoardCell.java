package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection door;
	
	public BoardCell(int row, int column, char initial, DoorDirection door) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.door = door;
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
		if (door == DoorDirection.NONE) {
			return false;
		}
		return true;
	}

	public DoorDirection getDoorDirection() {
		return door;
	}
	
}
