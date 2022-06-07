package org.corodiak.capstonelibrary.Exception;

public class ForbiddenRequestException extends NullPointerException {
	private static final String DEFAULT_MESSAGE = "Forbidden Request!!";

	public ForbiddenRequestException() {
		super(DEFAULT_MESSAGE);
	}

	public ForbiddenRequestException(String s) {
		super(s);
	}
}
