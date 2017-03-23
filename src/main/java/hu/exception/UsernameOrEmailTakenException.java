package hu.exception;

public class UsernameOrEmailTakenException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsernameOrEmailTakenException() {
		super("Username or email already taken!");
	}
	
	public UsernameOrEmailTakenException(String message) {
		super(message);
	}
}
