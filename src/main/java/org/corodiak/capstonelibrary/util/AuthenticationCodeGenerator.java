package org.corodiak.capstonelibrary.util;

import java.util.Random;

public class AuthenticationCodeGenerator {

	private Random random = new Random();
	private int AUTHCODE_LENGTH = 5;
	private char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
		'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
		'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

	public String generate() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < AUTHCODE_LENGTH; i++) {
			stringBuilder.append(alphabet[random.nextInt(alphabet.length)]);
		}
		return stringBuilder.toString();
	}
}
