package validation;

import me.nicodax.day7.Day7;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay7 {
    private final String PATH_SEPARATOR = File.separator;
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "test-input-day7.txt");

    @Test
    @DisplayName("It should return the total size of directory e")
    public void getDirectoryEFullSize() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);
        String directoryEFullPath = "/a/e";
        assertEquals(584, day7.getDirectoryTotalSize(directoryEFullPath));
    }

    @Test
    @DisplayName("It should return the total size of directory a")
    public void getDirectoryAFullSize() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);
        String directoryEFullPath = "/a";
        assertEquals(94853, day7.getDirectoryTotalSize(directoryEFullPath));
    }

    @Test
    @DisplayName("It should return the total size of directory d")
    public void getDirectoryDFullSize() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);
        String directoryEFullPath = "/d";
        assertEquals(24933642, day7.getDirectoryTotalSize(directoryEFullPath));
    }

    @Test
    @DisplayName("It should return the total size of directory d")
    public void getRootDirectoryFullSize() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);
        String directoryEFullPath = "/";
        assertEquals(48381165, day7.getDirectoryTotalSize(directoryEFullPath));
    }

    @Test
    @DisplayName("It should return the sum of the total size of every directory with a total size of at most 100000")
    public void day7Part1Solution() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals(95437, day7.getPart1Solution());
    }

    @Test
    @DisplayName("It should return the total size of the smallest directory with a size big enough to free up the "
            + "space needed by the update")
    public void day7Part2Solution() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals(24933642, day7.getPart2Solution());
    }
}
