package validation;

import me.nicodax.day1.Day1;
import me.nicodax.day2.Day2;
import me.nicodax.day3.Day3;
import me.nicodax.day3.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static me.nicodax.day2.RPSAction.PAPER;
import static me.nicodax.day2.RPSAction.ROCK;
import static me.nicodax.day2.RPSAction.SCISSORS;
import static me.nicodax.day3.Item.fromName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInputs {
    private final Path PATH_TO_DAY_1_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                                    + "\\src\\main\\resources\\test-input-day1.txt");
    private final Path PATH_TO_DAY_2_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                                    + "\\src\\main\\resources\\test-input-day2.txt");

    @Test
    @DisplayName("It should return the list of total carried calories per elf.")
    public void getTotalCaloriesPerElf() {
        Day1 day1 = new Day1();
        List<Integer> expectedTotalCaloriesPerElfList = asList(6000, 4000, 11000, 24000, 10000);

        day1.readAndParseFile(PATH_TO_DAY_1_TEST_INPUT);

        assertEquals(expectedTotalCaloriesPerElfList, day1.getTotalCaloriesPerElfList());
    }

    @Test
    @DisplayName("It should return the the total amount of calories carried by the elf carrying the most calories.")
    public void getTotalCaloriesForHeaviestElf() {
        Day1 day1 = new Day1();
        Integer expectedTotalCalories = 24000;

        day1.readAndParseFile(PATH_TO_DAY_1_TEST_INPUT);

        assertEquals(expectedTotalCalories, day1.getPart1Solution());
    }

    @Test
    @DisplayName("It should return the the total amount of calories carried by the three elves "
            + "carrying the most calories.")
    public void getTotalCaloriesForHeaviestThreeElves() {
        Day1 day1 = new Day1();
        Integer expectedTotalCalories = 45000;

        day1.readAndParseFile(PATH_TO_DAY_1_TEST_INPUT);

        assertEquals(expectedTotalCalories, day1.getPart2Solution());
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 1.")
    public void getRoundScore_part1Round1() {
        Day2 day2 = new Day2(1);
        Integer expectedScore = 8;

        assertEquals(expectedScore, day2.getRoundScore(PAPER, ROCK));
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 2.")
    public void getRoundScore_part1Round2() {
        Day2 day2 = new Day2(1);
        Integer expectedScore = 1;

        assertEquals(expectedScore, day2.getRoundScore(ROCK, PAPER));
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 1 round 3.")
    public void getRoundScore_part1Round3() {
        Day2 day2 = new Day2(1);
        Integer expectedScore = 6;

        assertEquals(expectedScore, day2.getRoundScore(SCISSORS, SCISSORS));
    }

    @Test
    @DisplayName("It should return the the total score of an RPS game - part 1")
    public void getTotalScore_part1() {
        Day2 day2 = new Day2(1);
        Integer expectedScore = 15;

        day2.readAndParseFile(PATH_TO_DAY_2_TEST_INPUT);

        assertEquals(expectedScore, day2.getTotalScore());
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 1.")
    public void getRoundScore_part2Round1() {
        Day2 day2 = new Day2(2);
        Integer expectedScore = 4;

        assertEquals(expectedScore, day2.getRoundScore(ROCK, ROCK));
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 2.")
    public void getRoundScore_part2Round2() {
        Day2 day2 = new Day2(2);
        Integer expectedScore = 1;

        assertEquals(expectedScore, day2.getRoundScore(ROCK, PAPER));
    }

    @Test
    @DisplayName("It should return the the score of an RPS round - part 2 round 3.")
    public void getRoundScore_part2Round3() {
        Day2 day2 = new Day2(2);
        Integer expectedScore = 7;

        assertEquals(expectedScore, day2.getRoundScore(ROCK, SCISSORS));
    }

    @Test
    @DisplayName("It should return the the total score of an RPS game - part 2")
    public void getTotalScore_part2() {
        Day2 day2 = new Day2(2);
        Integer expectedScore = 12;

        day2.readAndParseFile(PATH_TO_DAY_2_TEST_INPUT);

        assertEquals(expectedScore, day2.getTotalScore());
    }

    @Test
    @DisplayName("It should return the item appearing in both compartments - line 1")
    public void findItemAppearingInBothCompartments_line1() {
        Day3 day3 = new Day3();
        String line = "vJrwpWtwJgWrhcsFMMfFFhFp";
        Item expectedItem = fromName('p');

        assertEquals(expectedItem, day3.findItemAppearingInBothCompartments(line));
    }

    @Test
    @DisplayName("It should return the item appearing in both compartments - line 2")
    public void findItemAppearingInBothCompartments_line2() {
        Day3 day3 = new Day3();
        String line = "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL";
        Item expectedItem = fromName('L');

        assertEquals(expectedItem, day3.findItemAppearingInBothCompartments(line));
    }

    @Test
    @DisplayName("It should return the item appearing in both compartments - line 3")
    public void findItemAppearingInBothCompartments_line3() {
        Day3 day3 = new Day3();
        String line = "PmmdzqPrVvPwwTWBwg";
        Item expectedItem = fromName('P');

        assertEquals(expectedItem, day3.findItemAppearingInBothCompartments(line));
    }

    @Test
    @DisplayName("It should return the item appearing in both compartments - line 4")
    public void findItemAppearingInBothCompartments_line4() {
        Day3 day3 = new Day3();
        String line = "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn";
        Item expectedItem = fromName('v');

        assertEquals(expectedItem, day3.findItemAppearingInBothCompartments(line));
    }

    @Test
    @DisplayName("It should return the item appearing in both compartments - line 5")
    public void findItemAppearingInBothCompartments_line5() {
        Day3 day3 = new Day3();
        String line = "ttgJtRGJQctTZtZT";
        Item expectedItem = fromName('t');

        assertEquals(expectedItem, day3.findItemAppearingInBothCompartments(line));
    }

    @Test
    @DisplayName("It should return the item appearing in both compartments - line 6")
    public void findItemAppearingInBothCompartments_line6() {
        Day3 day3 = new Day3();
        String line = "CrZsJsPPZsGzwwsLwLmpwMDw";
        Item expectedItem = fromName('s');

        assertEquals(expectedItem, day3.findItemAppearingInBothCompartments(line));
    }

    @Test
    @DisplayName("It should return the given item's priority - line 1")
    public void getItemPriority_line1() {
        Item item = fromName('p');
        Integer expectedPriority = 16;

        assertEquals(expectedPriority, item.getPriority());
    }

    @Test
    @DisplayName("It should return the given item's priority - line 2")
    public void getItemPriority_line2() {
        Item item = fromName('L');
        Integer expectedPriority = 38;

        assertEquals(expectedPriority, item.getPriority());
    }

    @Test
    @DisplayName("It should return the given item's priority - line 3")
    public void getItemPriority_line3() {
        Item item = fromName('P');
        Integer expectedPriority = 42;

        assertEquals(expectedPriority, item.getPriority());
    }

    @Test
    @DisplayName("It should return the given item's priority - line 4")
    public void getItemPriority_line4() {
        Item item = fromName('v');
        Integer expectedPriority = 22;

        assertEquals(expectedPriority, item.getPriority());
    }

    @Test
    @DisplayName("It should return the given item's priority - line 5")
    public void getItemPriority_line5() {
        Item item = fromName('t');
        Integer expectedPriority = 20;

        assertEquals(expectedPriority, item.getPriority());
    }

    @Test
    @DisplayName("It should return the group badge item - group 1")
    public void getBadgeItem_group1() {
        Day3 day3 = new Day3();
        List<String> lines = new ArrayList<>();
        lines.add("vJrwpWtwJgWrhcsFMMfFFhFp");
        lines.add("jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL");
        lines.add("PmmdzqPrVvPwwTWBwg");
        Item expectedItem = fromName('r');

        assertEquals(expectedItem, day3.findGroupItem(lines, 0));
    }



    @Test
    @DisplayName("It should return the group badge item - group 2")
    public void getBadgeItem_group2() {
        Day3 day3 = new Day3();
        List<String> lines = new ArrayList<>();
        lines.add("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
        lines.add("ttgJtRGJQctTZtZT");
        lines.add("CrZsJsPPZsGzwwsLwLmpwMDw");
        Item expectedItem = fromName('Z');

        assertEquals(expectedItem, day3.findGroupItem(lines, 0));
    }
}
