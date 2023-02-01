package me.nicodax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class Day4 {
    private Integer fullyOverlappedCount;
    private Integer overlappedCount;

    public Integer getFullyOverlappedCount() {
        return fullyOverlappedCount;
    }

    public Integer getOverlappedCount() {
        return overlappedCount;
    }

    public void readAndParseFile(Path path) {
        fullyOverlappedCount = 0;
        overlappedCount = 0;
        try (Stream<String> linesStream = Files.lines(path)){
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank()) continue;
                List<List<Integer>> elfSections = convertLineToListOfListOfIntegers(line);
                if (areFullyOverlapped(elfSections.get(0), elfSections.get(1))) fullyOverlappedCount++;
                if (areAtLeastPartiallyOverlapped(elfSections.get(0), elfSections.get(1))) overlappedCount++;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<List<Integer>> convertLineToListOfListOfIntegers(String line) {
        List<String> pairSectionListAsString = stream(line.split(",")).toList();
        List<Integer> elf1Section = stream(pairSectionListAsString.get(0).split("-"))
                .mapToInt(Integer::parseInt).boxed().toList();
        List<Integer> elf2Section = stream(pairSectionListAsString.get(1).split("-"))
                .mapToInt(Integer::parseInt).boxed().toList();
        return new ArrayList<>(asList(elf1Section, elf2Section));
    }

    public Boolean areFullyOverlapped(List<Integer> elf1Sections, List<Integer> elf2Sections) {
        if (elf1Sections.get(0) <= elf2Sections.get(0) && elf1Sections.get(1) >= elf2Sections.get(1)) return true;
        else return elf2Sections.get(0) <= elf1Sections.get(0) && elf2Sections.get(1) >= elf1Sections.get(1);
    }

    public Boolean areAtLeastPartiallyOverlapped(List<Integer> elf1Sections, List<Integer> elf2Sections) {
        if (elf1Sections.get(0) <= elf2Sections.get(0) && elf1Sections.get(1) >= elf2Sections.get(0)) return true;
        return elf2Sections.get(0) <= elf1Sections.get(0) && elf2Sections.get(1) >= elf1Sections.get(0);
    }
}
