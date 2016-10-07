package clueGame;

public class BadConfigFormatException extends Exception{
	
	public BadConfigFormatException() {
		super("Not consistent columns");
	}
	
	public BadConfigFormatException(String string) {
		super(string + " is not in your legend.");
	}

}
