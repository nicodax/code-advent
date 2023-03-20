package me.nicodax.day9;

import lombok.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

@Value
public class Day9 {
    Rope rope;

    public Integer getSolution() {
        return rope.getNumberOfPositionsTailVisitedAtLeastOnce();
    }

    public Day9(Integer ropeLength) {
        this.rope = new Rope(ropeLength);
    }
    public void readAndParseFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                if (line.isBlank()) continue;
                List<String> args = asList(line.trim().split(" "));
                MovementType action = MovementType.of(args.get(0));
                Integer numberOfRepetitions = parseInt(args.get(1));
                switch (action) {
                    case UP -> rope.moveHead(numberOfRepetitions, 0, 1);
                    case RIGHT -> rope.moveHead(numberOfRepetitions, 1, 0);
                    case DOWN -> rope.moveHead(numberOfRepetitions, 0, -1);
                    case LEFT -> rope.moveHead(numberOfRepetitions, -1, 0);
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
