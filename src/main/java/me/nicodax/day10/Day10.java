package me.nicodax.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Integer.valueOf;
import static java.util.Arrays.asList;

public class Day10 {
    private final List<Integer> xRegisterValueList = new ArrayList<>();
    private final Integer SCREEN_LINE_WIDTH = 40;

    public void readAndParseFile(Path path) {
        Integer FIRST_X_VALUE = 1;
        xRegisterValueList.add(FIRST_X_VALUE);
        try (Stream<String> lines = Files.lines(path)) {
            for (String line : lines.toList()) {
                if (line.isBlank()) continue;
                List<String> args = asList(line.trim().split(" "));
                if (args.get(0).equals("noop")) {
                    xRegisterValueList.add(xRegisterValueList.get(xRegisterValueList.size() - 1));
                    continue;
                }
                executeAddxInstruction(valueOf(args.get(1)));
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    private void executeAddxInstruction(Integer v) {
        xRegisterValueList.add(xRegisterValueList.get(xRegisterValueList.size() - 1));
        xRegisterValueList.add(xRegisterValueList.get(xRegisterValueList.size() - 1) + v);
    }

    public Integer getXRegisterValueAfterCycle(Integer cycleNumber) {
        return xRegisterValueList.get(cycleNumber);
    }

    public Integer getXRegisterValueDuringCycle(Integer cycleNumber) {
        return xRegisterValueList.get(cycleNumber - 1);
    }

    public Integer getSignalStrengthDuringCycle(Integer cycleNumber) {
        return cycleNumber * getXRegisterValueDuringCycle(cycleNumber);
    }

    private Integer findMaxCycleNumber(Integer n, Integer signalStrengthCurrentSum) {
        int cycleNumber = 20 + n * 40;
        if (cycleNumber >= xRegisterValueList.size()) return signalStrengthCurrentSum;
        signalStrengthCurrentSum += xRegisterValueList.get(cycleNumber - 1) * cycleNumber;
        return findMaxCycleNumber(n + 1, signalStrengthCurrentSum);
    }

    public Integer getPart1Solution() {
        return findMaxCycleNumber(0, 0);
    }

    public List<String> getPart2Solution() {
        List<String> lines = new ArrayList<>();
        for (int lineIndex = 0; lineIndex < (getTotalNumberOfCycle() / SCREEN_LINE_WIDTH); lineIndex++) {
            lines.add(drawScreenLine(lineIndex));
        }
        return lines;
    }

    private Integer getTotalNumberOfCycle() {
        return xRegisterValueList.size() - 1;
    }

    private String drawScreenLine(Integer lineIndex) {
        String LIT_PIXEL = "#";
        String DARK_PIXEL = ".";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < SCREEN_LINE_WIDTH; i++) {
            Integer cycleNumber = lineIndex * SCREEN_LINE_WIDTH + i + 1;
            stringBuilder.append(isDrawingSpritePixel(i, cycleNumber) ? LIT_PIXEL : DARK_PIXEL);
        }
        return stringBuilder.toString();
    }

    private Boolean isDrawingSpritePixel(Integer screenIndex, Integer cycleNumber) {
        Integer centerSpritePixel = getXRegisterValueDuringCycle(cycleNumber);
        return screenIndex >= centerSpritePixel - 1 && screenIndex <= centerSpritePixel + 1;
    }
}
