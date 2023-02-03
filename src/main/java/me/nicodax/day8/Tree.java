package me.nicodax.day8;

import lombok.Getter;

public class Tree {
    @Getter
    private final Integer x;
    @Getter
    private final Integer y;

    public Tree(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}
