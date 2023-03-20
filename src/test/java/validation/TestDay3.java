package validation;

import me.nicodax.day3.Day3;
import me.nicodax.day3.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static me.nicodax.day3.Item.fromName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay3 {

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
