package me.nicodax.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class Day3 {
    private List<Integer> misplacedItemPriorityList = new ArrayList<>();
    private List<Integer> groupBadgeItemPriorityList = new ArrayList<>();
    private final Integer LAST_PRIORITY_OF_LOWERCASE_ITEMS = 26;

    public void readAndParseFile(Path path) {
        List<Character> misplacedItemList = new ArrayList<>();
        try (Stream<String> linesStream = Files.lines(path)){
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank()) continue;
                if (lines.indexOf(line) == 0 || lines.indexOf(line) % 3 == 0) {
                    Character groupBadge = findGroupBadge(lines, lines.indexOf(line));
                    groupBadgeItemPriorityList.add(mapItemToPriority(groupBadge));
                }
                List<String> rucksackCompartmentInventory = splitRucksackInventoryIntoCompartments(line);
                misplacedItemList.add(findItemSharedBetweenCompartments(rucksackCompartmentInventory.get(0),
                                                                        rucksackCompartmentInventory.get(1)));
                misplacedItemPriorityList = misplacedItemList.stream().map(this::mapItemToPriority)
                                                             .collect(Collectors.toList());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Character findGroupBadge(List<String> lines, Integer firstElfIndex) {
        List<Character> firstGroupLineCharacters = lines.get(firstElfIndex).chars().mapToObj(s -> (char) s).toList();
        List<Character> secondGroupLineCharacters = lines.get(firstElfIndex + 1)
                                                         .chars().mapToObj(s -> (char) s).toList();
        List<Character> thirdGroupLineCharacters = lines.get(firstElfIndex + 2)
                                                        .chars().mapToObj(s -> (char) s).toList();
        for (Character character : firstGroupLineCharacters) {
            if (secondGroupLineCharacters.contains(character) && thirdGroupLineCharacters.contains(character))
                return character;
        }
        return '0';
    }

    public List<String> splitRucksackInventoryIntoCompartments(String rucksackInventory) {
        int firstCompartment2ItemIndex = rucksackInventory.length() / 2;
        return asList(rucksackInventory.substring(0, firstCompartment2ItemIndex),
                      rucksackInventory.substring(firstCompartment2ItemIndex));
    }

    public Character findItemSharedBetweenCompartments(String compartment1, String compartment2) {
        List<Character> compartment1Items = compartment1.chars().mapToObj(s -> (char) s).toList();
        List<Character> compartment2Items = compartment2.chars().mapToObj(s -> (char) s).toList();
        for (Character character : compartment1Items) {
            if (compartment2Items.contains(character)) return character;
        }
        return '0';
    }

    public Integer mapItemToPriority(Character item) {
        if ((int) item >= 'A' && (int) item <= 'Z') return (int) item - ('A' - 1) + LAST_PRIORITY_OF_LOWERCASE_ITEMS;
        if ((int) item >= 'a' && (int) item <= 'z') return (int) item - ('a' - 1);
        return 0;
    }

    public Integer getPrioritySum() {
        return misplacedItemPriorityList.stream().mapToInt(v -> v).sum();
    }

    public Integer getGroupBadgePrioritySum() {
        return groupBadgeItemPriorityList.stream().mapToInt(v -> v).sum();
    }

    public void setMisplacedItemPriorityList(List<Integer> misplacedItemPriorityList) {
        this.misplacedItemPriorityList = misplacedItemPriorityList;
    }

    public void setGroupBadgeItemPriorityList(List<Integer> groupBadgeItemPriorityList) {
        this.groupBadgeItemPriorityList = groupBadgeItemPriorityList;
    }

    public List<Integer> getMisplacedItemPriorityList() {
        return misplacedItemPriorityList;
    }
}
