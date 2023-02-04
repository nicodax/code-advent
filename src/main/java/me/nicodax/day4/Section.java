package me.nicodax.day4;

import static me.nicodax.day4.OverlapType.CONTAINED;
import static me.nicodax.day4.OverlapType.FULL;
import static me.nicodax.day4.OverlapType.NONE;
import static me.nicodax.day4.OverlapType.PARTIAL;

public record Section(Integer firstId, Integer lastId) {
    public OverlapType contains(Section other) {
        if (firstId <= other.firstId && lastId >= other.lastId) return FULL;
        else if (firstId >= other.firstId && lastId <= other.lastId) return CONTAINED;
        else if (firstId <= other.firstId && lastId >= other.firstId ||
                firstId <= other.lastId && lastId >= other.lastId) return PARTIAL;
        else return NONE;
    }
}
