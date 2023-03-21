package me.nicodax.day9;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static me.nicodax.day9.MovementType.NONE;

public class Rope {
    @Setter
    @Getter
    List<Position> sectionPositionList = new ArrayList<>();
    @Setter
    @Getter
    List<Position> tailPositionList = new ArrayList<>();
    Integer ropeLength;
    Integer HEAD_INDEX = 0;
    Integer TAIL_INDEX;

    public Rope(Integer ropeLength) {
        this.ropeLength = ropeLength;
        TAIL_INDEX = ropeLength - 1;
        for (int i = 0; i < ropeLength; i++) sectionPositionList.add(new Position(0, 0));
        tailPositionList.add(new Position(0, 0));
    }

    public void moveHead(Integer numberOfRepetitions, Integer xModifier, Integer yModifier) {
        int counter = 0;
        while (counter < numberOfRepetitions) {
            Position headPosition = sectionPositionList.get(HEAD_INDEX);
            headPosition.setX(headPosition.getX() + xModifier);
            headPosition.setY(headPosition.getY() + yModifier);
            followPreviousSection(HEAD_INDEX + 1);
            counter++;
        }
    }

    public void followPreviousSection(Integer sectionIndex) {
        if (sectionIndex.equals(ropeLength)) return;
        Position previousSectionPosition = sectionPositionList.get(sectionIndex - 1);
        Integer relativeX = sectionPositionList.get(sectionIndex).getX() - previousSectionPosition.getX();
        Integer relativeY = sectionPositionList.get(sectionIndex).getY() - previousSectionPosition.getY();
        Position relativePositionToPreviousSection = new Position(relativeX, relativeY);
        MovementType action = MovementType.fromRelativePosition(relativePositionToPreviousSection);
        if (action.equals(NONE)) return;
        switch (action) {
            case UP -> moveSection(sectionIndex, 0, 1);
            case UP_RIGHT -> moveSection(sectionIndex, 1, 1);
            case RIGHT -> moveSection(sectionIndex, 1, 0);
            case DOWN_RIGHT -> moveSection(sectionIndex, 1, -1);
            case DOWN -> moveSection(sectionIndex, 0, -1);
            case DOWN_LEFT -> moveSection(sectionIndex, -1, -1);
            case LEFT -> moveSection(sectionIndex, -1, 0);
            default -> moveSection(sectionIndex, -1, 1);
        }
        followPreviousSection(sectionIndex + 1);
    }

    public void moveSection(Integer sectionIndex, Integer xModifier, Integer yModifier) {
        Position position = sectionPositionList.get(sectionIndex);
        position.setX(position.getX() + xModifier);
        position.setY(position.getY() + yModifier);
        if (sectionIndex.equals(TAIL_INDEX)) tailPositionList.add(Position.copyOf(position));
    }

    public Integer getNumberOfPositionsTailVisitedAtLeastOnce() {
        return tailPositionList.stream()
                               .map(p -> asList(p.getX(), p.getY()))
                               .collect(Collectors.toSet())
                               .size();
    }
}
