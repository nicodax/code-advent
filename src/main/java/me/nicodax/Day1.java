package me.nicodax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day1 {
    List<Integer> listOfCaloriesPerElf = new ArrayList<>();

    public void readAndParseFile(Path path) {
        try (Stream<String> lines = Files.lines(path)){
            int currentCalories = 0;
            for (String line : lines.toList()) {
                if (line.isBlank()) {
                    listOfCaloriesPerElf.add(currentCalories);
                    currentCalories = 0;
                }
                else {
                    currentCalories += parseInt(line);
                }
            }
            listOfCaloriesPerElf.add(currentCalories);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Integer getMaxCalories() {
        return listOfCaloriesPerElf.stream().mapToInt(v -> v).max().orElse(0);
    }

    public void setListOfCaloriesPerElf(List<Integer> listOfCaloriesPerElf) {
        this.listOfCaloriesPerElf = listOfCaloriesPerElf;
    }

    public List<Integer> getListOfCaloriesPerElf() {
        return listOfCaloriesPerElf;
    }
}
