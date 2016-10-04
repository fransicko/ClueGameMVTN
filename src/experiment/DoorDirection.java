package experiment;

public enum DoorDirection {
	UP("Up"), DOWN("Down"), LEFT("Left"), RIGHT("Right"), NONE("None");
	private String value;
	
	DoorDirection (String val) {
		value = val;
	}
}
