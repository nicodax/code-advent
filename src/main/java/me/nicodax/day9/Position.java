package me.nicodax.day9;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Position {
    private Integer x;
    private Integer y;

    public static Position copyOf(Position position) {
        return new Position(position.getX(), position.getY());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Position otherPosition)) return false;
        return otherPosition.getX().equals(x) && otherPosition.getY().equals(y);
    }

    @Override
    public String toString() {
        return "[x=" + x + ", y=" + y + "]";
    }
}
