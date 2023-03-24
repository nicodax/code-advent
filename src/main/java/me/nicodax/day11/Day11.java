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
import static java.lang.Long.valueOf;
import static java.util.Arrays.stream;
import static java.util.Collections.reverse;
import static me.nicodax.day11.Monkey.OPERATION_NUMBER_IS_ITSELF_MARKER;
import static me.nicodax.day11.OperationTypes.of;

public class Day11 {
    private final Integer RELIEF_DIVIDER;
    private Long SUPER_MODULO = Long.MAX_VALUE;
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
            if (RELIEF_DIVIDER == 1) {
                List<Long> conditionDividerList = monkeyList.stream()
                        .map(Monkey::getConditionDivider)
                        .map(Long::valueOf)
                        .toList();
                SUPER_MODULO = lcm(conditionDividerList);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private Long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private Long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }

    private Long lcm(List<Long> input) {
        long result = input.get(0);
        for(int i = 1; i < input.size(); i++) result = lcm(result, input.get(i));
        return result;
    }

    public void processRounds(Integer numberOfRoundsToProcess) {
        for (int round = 0; round < numberOfRoundsToProcess; round++) {
            for (Monkey monkey : monkeyList) {
                List<Long> preRoundItemWorryLevelList = new ArrayList<>(monkey.getItemWorryLevelList());
                for (int item = 0; item < preRoundItemWorryLevelList.size(); item++) {
                    monkey.inspectItem(SUPER_MODULO);
                    monkey.applyReliefTo();
                    Monkey targetMonkey = monkeyList.get(monkey.throwItemTo());
                    Long thrownItemWorryLevel = monkey.throwItem();
                    targetMonkey.catchItem(thrownItemWorryLevel);
                }
            }
        }
    }

    public Long getSolution(Integer numberOfRoundsToProcess) {
        processRounds(numberOfRoundsToProcess);
        List<Integer> totalInspectedItemsList =
                monkeyList.stream().map(Monkey::getTotalInspectedItems).sorted().collect(Collectors.toList());
        reverse(totalInspectedItemsList);
        return valueOf(totalInspectedItemsList.get(0)) * valueOf(totalInspectedItemsList.get(1));
    }
}
