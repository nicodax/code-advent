package me.nicodax.day5;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;

@Getter
public class Day5 {
    private final CraneMode CRANE_MODE;
    private final List<CrateStack> crateStackList = new ArrayList<>();

    public Day5(Integer part) {
        this.CRANE_MODE = CraneMode.fromPart(part);
    }

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank()) continue;
                if (line.contains(" 1   ")) {
                    createCraneStacks(lines, lines.indexOf(line) - 1);
                } else if (line.contains(("move "))) {
                    List<String> args = stream(line.trim().split(" ")).toList();
                    Integer numberOfCrates = parseInt(args.get(1));
                    CrateStack origin = crateStackList.get(parseInt(args.get(3)) - 1);
                    CrateStack destination = crateStackList.get(parseInt(args.get(5)) - 1);
                    origin.moveTo(CRANE_MODE, destination, numberOfCrates);
                }
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void createCraneStacks(List<String> lines, Integer baseIndex) {
        for (int i = 0; i <= baseIndex; i++) {
            String line = lines.get(baseIndex - i);
            // 3 character per crate ([X]) and 1 space character between each crate
            int numberOfStacks = (line.length() + 1) / 4;
            for (int j = 0; j < numberOfStacks; j++) {
                int startIndex = j * 4;
                int endIndex = j * 4 + 3;
                String crate = line.substring(startIndex, endIndex);
                if (crate.equals("   ")) continue;
                if (crateStackList.size() == 0 || crateStackList.size() <= j) crateStackList.add(new CrateStack());
                CrateStack crateStack = crateStackList.get(j);
                crateStack.addCrates(List.of(Crate.from(crate)));
            }
        }
    }

    public String getSolution() {
        StringBuilder solution = new StringBuilder();
        List<String> topCrateLabelList =
                crateStackList.stream()
                              .map(crateStack -> crateStack.getStack()
                                                           .get(crateStack.getStack().size() - 1)
                                                           .label()
                                                           .toString())
                              .toList();
        topCrateLabelList.forEach(solution::append);
        return solution.toString();
    }
}
