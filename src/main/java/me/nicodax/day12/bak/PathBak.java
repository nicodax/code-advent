package me.nicodax.day12.bak;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static me.nicodax.day12.bak.DirectionTypeBak.*;

public class PathBak {

    private final Day12Bak day12Bak;
    private final PathBak parentPathBak;
    @Getter
    private final List<CoordinateBak> coordinateBakList = new ArrayList<>();

    public PathBak(Day12Bak day12Bak, PathBak parentPathBak, DirectionTypeBak firstDirection) {
        this.day12Bak = day12Bak;
        this.parentPathBak = parentPathBak;
        if (firstDirection == null) {
            coordinateBakList.add(day12Bak.getStartCoordinateBak());
            createNewBranches(getDirections());
        } else {
            advance(firstDirection);
        }
    }
    
    private List<DirectionTypeBak> getDirections() {
        CoordinateBak currentCoordinateBak = coordinateBakList.get(coordinateBakList.size() - 1);
        Character startingElevation = mapElevationToCharacter(day12Bak.getGrid()
                .get(currentCoordinateBak.y())
                .get(currentCoordinateBak.x()));
        List<DirectionTypeBak> directions = new ArrayList<>();
        if (currentCoordinateBak.y() - 1 >= 0) {
            CoordinateBak nextCoordinateBak = new CoordinateBak(currentCoordinateBak.x(), currentCoordinateBak.y() - 1);
            if (day12Bak.getMinNumberOfJumpsToCoordinateHashMap().get(nextCoordinateBak) == null
                    || day12Bak.getMinNumberOfJumpsToCoordinateHashMap().get(nextCoordinateBak) > getFullCoordinateList().size()) {
                Character upElevation = mapElevationToCharacter(day12Bak.getGrid().get(currentCoordinateBak.y() - 1).get(currentCoordinateBak.x()));
                if (upElevation <= startingElevation + 1) {
                    directions.add(UP);
                    day12Bak.getMinNumberOfJumpsToCoordinateHashMap().put(nextCoordinateBak, getFullCoordinateList().size());
                }
            }
        }
        if (currentCoordinateBak.x() + 1 < day12Bak.getGrid().get(0).size()) {
            CoordinateBak nextCoordinateBak = new CoordinateBak(currentCoordinateBak.x() + 1, currentCoordinateBak.y());
            if (day12Bak.getMinNumberOfJumpsToCoordinateHashMap().get(nextCoordinateBak) == null
                    || day12Bak.getMinNumberOfJumpsToCoordinateHashMap().get(nextCoordinateBak) > getFullCoordinateList().size()) {
                Character rightElevation = mapElevationToCharacter(day12Bak.getGrid().get(currentCoordinateBak.y()).get(currentCoordinateBak.x() + 1));
                if (rightElevation <= startingElevation + 1) {
                    directions.add(RIGHT);
                    day12Bak.getMinNumberOfJumpsToCoordinateHashMap().put(nextCoordinateBak, getFullCoordinateList().size());
                }
            }
        }
        if (currentCoordinateBak.y() + 1 < day12Bak.getGrid().size()) {
            CoordinateBak nextCoordinateBak = new CoordinateBak(currentCoordinateBak.x(), currentCoordinateBak.y() + 1);
            if (day12Bak.getMinNumberOfJumpsToCoordinateHashMap().get(nextCoordinateBak) == null
                    || day12Bak.getMinNumberOfJumpsToCoordinateHashMap().get(nextCoordinateBak) > getFullCoordinateList().size()) {
                Character downElevation = mapElevationToCharacter(day12Bak.getGrid().get(currentCoordinateBak.y() + 1).get(currentCoordinateBak.x()));
                if (downElevation <= startingElevation + 1) {
                    directions.add(DOWN);
                    day12Bak.getMinNumberOfJumpsToCoordinateHashMap().put(nextCoordinateBak, getFullCoordinateList().size());
                }
            }
        }
        if (currentCoordinateBak.x() - 1 >= 0) {
            CoordinateBak nextCoordinateBak = new CoordinateBak(currentCoordinateBak.x() - 1, currentCoordinateBak.y());
            if (day12Bak.getMinNumberOfJumpsToCoordinateHashMap().get(nextCoordinateBak) == null
                    || day12Bak.getMinNumberOfJumpsToCoordinateHashMap().get(nextCoordinateBak) > getFullCoordinateList().size()) {
                Character leftElevation = mapElevationToCharacter(day12Bak.getGrid().get(currentCoordinateBak.y()).get(currentCoordinateBak.x() - 1));
                if (leftElevation <= startingElevation + 1) {
                    directions.add(LEFT);
                    day12Bak.getMinNumberOfJumpsToCoordinateHashMap().put(nextCoordinateBak, getFullCoordinateList().size());
                }
            }
        }
        return directions;
    }

    private Character mapElevationToCharacter(Character c) {
        if (c == 'S') return 'a';
        if (c == 'E') return 'z';
        return c;
    }
    
    private void advance(DirectionTypeBak directionTypeBak) {
        CoordinateBak nextCoordinateBak = getNextCoordinate(directionTypeBak);
        coordinateBakList.add(nextCoordinateBak);
        if (day12Bak.getGrid().get(nextCoordinateBak.y()).get(nextCoordinateBak.x()) == 'E') foundPathToBestSignal();
        else {
            List<DirectionTypeBak> nextDirectionTypeBakList = getDirections();
            nextDirectionTypeBakList = filterInvalidDirection(nextDirectionTypeBakList);
            if (nextDirectionTypeBakList.size() == 0) {
//                drawFullPath();
                return;
            }
            if (nextDirectionTypeBakList.size() > 1) createNewBranches(nextDirectionTypeBakList);
            else advance(nextDirectionTypeBakList.get(0));
        }
    }

    private List<DirectionTypeBak> filterInvalidDirection(List<DirectionTypeBak> nextDirectionTypeBakList) {
        List<CoordinateBak> fullCoordinateListBak = getFullCoordinateList();
        List<DirectionTypeBak> filteredNextDirectionTypeListBak = new ArrayList<>();
        for (DirectionTypeBak directionTypeBak : nextDirectionTypeBakList) {
            CoordinateBak nextCoordinateBak = getNextCoordinate(directionTypeBak);
            if (!fullCoordinateListBak.contains(nextCoordinateBak)) filteredNextDirectionTypeListBak.add(directionTypeBak);
        }
        return filteredNextDirectionTypeListBak;
    }

    public List<CoordinateBak> getFullCoordinateList() {
        List<CoordinateBak> result = new ArrayList<>();
        if (parentPathBak != null) result.addAll(parentPathBak.getFullCoordinateList());
        result.addAll(coordinateBakList);
        return result;
    }

    private CoordinateBak getNextCoordinate(DirectionTypeBak directionTypeBak) {
        CoordinateBak currentCoordinateBak;
        if (coordinateBakList.size() > 0) currentCoordinateBak = coordinateBakList.get(coordinateBakList.size() - 1);
        else currentCoordinateBak = parentPathBak.getCoordinateBakList().get(parentPathBak.getCoordinateBakList().size() - 1);
        return switch (directionTypeBak) {
            case UP -> new CoordinateBak(currentCoordinateBak.x(), currentCoordinateBak.y() - 1);
            case RIGHT -> new CoordinateBak(currentCoordinateBak.x() + 1, currentCoordinateBak.y());
            case DOWN -> new CoordinateBak(currentCoordinateBak.x(), currentCoordinateBak.y() + 1);
            default -> new CoordinateBak(currentCoordinateBak.x() - 1, currentCoordinateBak.y());
        };
    }
    
    private void createNewBranches(List<DirectionTypeBak> directionTypeBakList) {
        for (DirectionTypeBak directionTypeBak : directionTypeBakList) {
            new PathBak(day12Bak, this, directionTypeBak);
        }
    }
    
    public void foundPathToBestSignal() {
//        drawFullPath();
        day12Bak.getPathToBestSignalList().add(getFullCoordinateList());
    }

    /**
     * Not necessary for the end result.
     */
    private void drawFullPath() {
        System.out.println();
        System.out.println("------------------------------------------------------------");
        System.out.println();
        List<List<Character>> pathDisplay = new ArrayList<>();
        for (int y = 0; y < day12Bak.getGrid().size(); y++) {
            List<Character> line = new ArrayList<>();
            for (int x = 0; x < day12Bak.getGrid().get(0).size(); x++) {
                if (day12Bak.getStartCoordinateBak().x() == x && day12Bak.getStartCoordinateBak().y() == y) line.add('S');
                else if (day12Bak.getEndCoordinateBak().x() == x && day12Bak.getEndCoordinateBak().y() == y) line.add('E');
                else line.add(' ');
            }
            pathDisplay.add(line);
        }
        for (CoordinateBak coordinateBak : getFullCoordinateList()) {
            pathDisplay.get(coordinateBak.y()).set(coordinateBak.x(), 'X');
        }
        for (List<Character> line : pathDisplay) {
            System.out.println(line);
        }
    }
}
