package me.nicodax.day4;

public enum RelativeSectionPosition {
    // HEAD represents the highest id of a given section
    // TAIL represents the lowest id of a given section
    FULLY_BEFORE, FULLY_AFTER, PARTIALLY_COVERED_AT_TAIL, PARTIALLY_COVERED_AT_HEAD,
}
