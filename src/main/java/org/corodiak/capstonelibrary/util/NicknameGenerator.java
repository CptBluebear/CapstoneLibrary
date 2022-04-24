package org.corodiak.capstonelibrary.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class NicknameGenerator {

	private List<String> adjList;
	private List<String> nounList;
	private Random random;

	public NicknameGenerator() throws IOException {
		String resourceAdj = getClass().getClassLoader().getResource("static/adj.csv").getFile();
		String resourceNoun = getClass().getClassLoader().getResource("static/noun.csv").getFile();
		Path adjPath = new File(resourceAdj).toPath();
		Path nounPath = new File(resourceNoun).toPath();
		adjList = Files.readAllLines(adjPath);
		nounList = Files.readAllLines(nounPath);
		random = new Random();
	}

	public String generate() {
		return adjList.get(random.nextInt(adjList.size())) + nounList.get(random.nextInt(nounList.size()))
			+ String.format("%04d", random.nextInt(10000));
	}
}
