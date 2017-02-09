//Exception used to alert the user about the format needed for inputting Grade Files
public class GradeFileFormatException extends Exception {

	public GradeFileFormatException() {
		super();
	}
	
	public GradeFileFormatException(String message) {
		super(message);
	}

}
