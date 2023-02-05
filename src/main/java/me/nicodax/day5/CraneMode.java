package me.nicodax.day5;

public enum CraneMode {
    ONE_BY_ONE, FULL;

    public static CraneMode fromPart(Integer part) {
        return part.equals(2) ? FULL : ONE_BY_ONE;
    }
}
