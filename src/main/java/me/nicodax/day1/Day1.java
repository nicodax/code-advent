package me.nicodax.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day1 {
    private List<Integer> listOfCaloriesPerElf = new ArrayList<>();

    public void readAndParseFile(Path path) {
        listOfCaloriesPerElf = new ArrayList<>();
        try (Stream<String> lines = Files.lines(path)){
            int currentCalories = 0;
            for (String line : lines.toList()) {
                if (line.isBlank()) {
                    listOfCaloriesPerElf.add(currentCalories);
                    currentCalories = 0;
                }
                else currentCalories += parseInt(line);
            }
            listOfCaloriesPerElf.add(currentCalories);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Integer getPart1Solution() {
        return listOfCaloriesPerElf.stream().mapToInt(v -> v).max().orElse(0);
    }

    public Integer getPart2Solution() {
        List<Integer> workingList = new ArrayList<>(listOfCaloriesPerElf);
        Integer top1 = workingList.stream().mapToInt(v -> v).max().orElse(0);
        workingList.remove(top1);
        Integer top2 = workingList.stream().mapToInt(v -> v).max().orElse(0);
        workingList.remove(top2);
        Integer top3 = workingList.stream().mapToInt(v -> v).max().orElse(0);
        workingList.remove(top3);
        return top1 + top2 + top3;
    }

    public void setListOfCaloriesPerElf(List<Integer> listOfCaloriesPerElf) {
        this.listOfCaloriesPerElf = listOfCaloriesPerElf;
    }

    public List<Integer> getListOfCaloriesPerElf() {
        return listOfCaloriesPerElf;
    }
}
