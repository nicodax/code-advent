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
}
