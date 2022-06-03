package org.corodiak.capstonelibrary.util;

import java.util.Random;

import net.bytebuddy.utility.RandomString;

public class CodeGenerator {

	private Random random = new Random();
	private int CODE_LENGTH = 10;

	public String generate(String... str) {
		StringBuilder stringBuilder = new StringBuilder();
		for (String s : str) {
			stringBuilder.append(s).append("-");
		}
		stringBuilder.append(RandomString.make(CODE_LENGTH));
		return stringBuilder.toString();
	}
}
