package me.nicodax.day3;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Item {
    Character name;
    Integer priority;

    public static Item fromName(Character name) {
        // create item from character
        return new Item(name, getPriority(name));
    }

    public Integer getPriority() {
        // return this item's priority
        int LAST_PRIORITY_OF_LOWERCASE_ITEMS = 26;
        if ((int) name >= 'A' && (int) name <= 'Z') return (int) name - ('A' - 1) + LAST_PRIORITY_OF_LOWERCASE_ITEMS;
        if ((int) name >= 'a' && (int) name <= 'z') return (int) name - ('a' - 1);
        return 0;
    }

    public static Integer getPriority(Character item) {
        // get priority from a character
        int LAST_PRIORITY_OF_LOWERCASE_ITEMS = 26;
        if ((int) item >= 'A' && (int) item <= 'Z') return (int) item - ('A' - 1) + LAST_PRIORITY_OF_LOWERCASE_ITEMS;
        if ((int) item >= 'a' && (int) item <= 'z') return (int) item - ('a' - 1);
        return 0;
    }
}
