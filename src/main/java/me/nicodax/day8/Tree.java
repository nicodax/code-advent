package me.nicodax.day8;

public record Tree(Integer x, Integer y, Integer height) {

    public String getNextTreeId(Integer xModifier, Integer yModifier) {
        return (x + xModifier) + "-" + (y + yModifier);
    }
}
