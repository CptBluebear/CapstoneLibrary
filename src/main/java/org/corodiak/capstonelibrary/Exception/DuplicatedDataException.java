package org.corodiak.capstonelibrary.Exception;

public class DuplicatedDataException extends Exception {
	private static final String DEFAULT_MESSAGE = "Already Joined!";

	public DuplicatedDataException() {
		super(DEFAULT_MESSAGE);
	}

	public DuplicatedDataException(String s) {
		super(s);
	}
}
