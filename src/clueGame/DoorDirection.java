package clueGame;

public enum DoorDirection {
	UP("U"), DOWN("D"), LEFT("L"), RIGHT("R"), NONE("N");
	private String value;
	
	DoorDirection (String val) {
		value = val;
	}
	
}
