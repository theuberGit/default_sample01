package uber.common;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 2433212689561578565L;

	public CustomException() {
		super();
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomException(String message) {
		super(message);
	}

	public CustomException(Throwable cause) {
		super(cause);
	}
}
