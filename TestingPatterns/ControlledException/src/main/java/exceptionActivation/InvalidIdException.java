package exceptionActivation;
public class InvalidIdException extends Exception {

	public InvalidIdException() {
		super();
	}

	public InvalidIdException(String arg0) {
		super(arg0);
	}

	public InvalidIdException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidIdException(Throwable arg0) {
		super(arg0);
	}

}
