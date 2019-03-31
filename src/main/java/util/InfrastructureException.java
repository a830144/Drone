package util;

public class InfrastructureException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2426011759475099851L;

	public InfrastructureException() {
	}

	public InfrastructureException(String s) {
		super(s);
	}

	public InfrastructureException(Throwable throwable) {
		super(throwable);
	}

	public InfrastructureException(String s, Throwable throwable) {
		super(s, throwable);
	}
}
