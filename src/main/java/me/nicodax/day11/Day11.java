package me.nicodax.day11;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Arrays.stream;
import static java.util.Collections.reverse;
import static me.nicodax.day11.Monkey.OPERATION_NUMBER_IS_ITSELF_MARKER;
import static me.nicodax.day11.OperationTypes.of;

public class Day11 {
    private final Integer RELIEF_DIVIDER;
    @Getter
    private final List<Monkey> monkeyList = new ArrayList<>();

    public Day11(Integer reliefDivider) {
        RELIEF_DIVIDER = reliefDivider;
    }

    public void readAndParseFile(Path path) {
        String STARTING_ITEMS_MARKER = "Starting items: ";
        String OPERATION_MARKER = "Operation: ";
        int OPERATION_ARG_INDEX = 3;
        int OPERATION_NUMBER_ARG_INDEX = 4;
        String TEST_MARKER = "Test: divisible by ";
        String TRUE_TEST_MARKER = "If true: throw to monkey ";
        String FALSE_TEST_MARKER = "If false: throw to monkey ";
        try (Stream<String> lines = Files.lines(path)) {
            List<Long> currentMonkeyStartingItems = new ArrayList<>();
            OperationTypes currentMonkeyOperation = null;
            int currentMonkeyOperationNumber = 0;
            int currentMonkeyConditionDivider = 0;
            int currentMonkeyTrueTestMonkeyTarget = 0;
            int currentMonkeyFalseTestMonkeyTarget = 0;
            for (String line : lines.toList()) {
                if (line.contains(STARTING_ITEMS_MARKER))
                    currentMonkeyStartingItems = stream(line.trim()
                            .replace(STARTING_ITEMS_MARKER, "")
                            .split(", "))
                            .map(Long::valueOf)
                            .toList();
                else if (line.contains(OPERATION_MARKER)) {
                    List<String> operationArgs = stream(line.trim()
                            .replace(OPERATION_MARKER, "")
                            .split(" "))
                            .toList();
                    currentMonkeyOperation = of(operationArgs.get(OPERATION_ARG_INDEX));
                    currentMonkeyOperationNumber = operationArgs.get(OPERATION_NUMBER_ARG_INDEX).equals("old") ?
                            OPERATION_NUMBER_IS_ITSELF_MARKER : parseInt(operationArgs.get(OPERATION_NUMBER_ARG_INDEX));
                } else if (line.contains(TEST_MARKER))
                    currentMonkeyConditionDivider = parseInt(line.trim().replace(TEST_MARKER, ""));
                else if (line.contains(TRUE_TEST_MARKER))
                    currentMonkeyTrueTestMonkeyTarget = parseInt(line.trim().replace(TRUE_TEST_MARKER, ""));
                else if (line.contains(FALSE_TEST_MARKER))
                    currentMonkeyFalseTestMonkeyTarget = parseInt(line.trim().replace(FALSE_TEST_MARKER, ""));
                else if (line.isBlank()) {
                    monkeyList.add(new Monkey(currentMonkeyStartingItems,
                            currentMonkeyOperation,
                            currentMonkeyOperationNumber,
                            currentMonkeyConditionDivider,
                            currentMonkeyTrueTestMonkeyTarget,
                            currentMonkeyFalseTestMonkeyTarget,
                            RELIEF_DIVIDER
                            ));
                }
            }
            monkeyList.add(new Monkey(currentMonkeyStartingItems,
                    currentMonkeyOperation,
                    currentMonkeyOperationNumber,
                    currentMonkeyConditionDivider,
                    currentMonkeyTrueTestMonkeyTarget,
                    currentMonkeyFalseTestMonkeyTarget,
                    RELIEF_DIVIDER));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void processRounds(Integer numberOfRoundsToProcess) {
        for (int round = 0; round < numberOfRoundsToProcess; round++) {
            for (Monkey monkey : monkeyList) {
                List<Long> preRoundItemWorryLevelList = new ArrayList<>(monkey.getItemWorryLevelList());
                for (int item = 0; item < preRoundItemWorryLevelList.size(); item++) {
                    monkey.inspectItem();
                    monkey.applyReliefTo();
                    Monkey targetMonkey = monkeyList.get(monkey.throwItemTo());
                    Long thrownItemWorryLevel = monkey.throwItem();
                    targetMonkey.catchItem(thrownItemWorryLevel);
                }
            }
        }
    }

    public Integer getPart1Solution() {
        processRounds(20);
        List<Integer> totalInspectedItemsList =
                monkeyList.stream().map(Monkey::getTotalInspectedItems).sorted().collect(Collectors.toList());
        reverse(totalInspectedItemsList);
        return totalInspectedItemsList.get(0) * totalInspectedItemsList.get(1);
    }
}
