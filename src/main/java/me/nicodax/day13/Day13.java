package me.nicodax.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Day13 {
    
    public void readAndParseFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                if (line.isBlank()) continue;
                // TO DO
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public Boolean valuesAreInRightOrder(Integer left, Integer right) {
        // TO DO
        return false;
    }

    public Boolean valuesAreInRightOrder(List<Integer> left, List<Integer> right) {
        // TO DO
        return false;
    }

	public Integer getPart1Solution() {
        // TO DO
		return -1;
	}
}
