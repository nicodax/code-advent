package me.nicodax.day7;

import me.nicodax.day7.Day7;
import me.nicodax.day7.Dir;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Day7Test {
    private final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
                                                         + "\\src\\main\\resources\\input-day7.txt");
    private final Path PATH_TO_TEST_INPUT = Paths.get(System.getProperty("user.dir")
                                                              + "\\src\\main\\resources\\test-input-day7.txt");

    @Test
    @DisplayName("Should save a new directory")
    public void saveNewElement_dir() {
        Day7 day7 = new Day7();
        String line = "dir a";
        Dir rootDir = new Dir();
        day7.getDirectories().put("/", rootDir);
        day7.setPresentWorkingDirectory("/");

        day7.saveNewElement(line);

        assertEquals(2, day7.getDirectories().size());
        assertNotNull(day7.getDirectories().get("/"));
        assertNotNull(day7.getDirectories().get("/a"));
        assertEquals(1, day7.getDirectories().get("/").getDirectories().size());
        assertNotNull(day7.getDirectories().get("/").getDirectories().get("a"));
        assertEquals(day7.getDirectories().get("/a"), day7.getDirectories().get("/").getDirectories().get("a"));
    }

    @Test
    @DisplayName("Should save a new file")
    public void saveNewElement_file() {
        Day7 day7 = new Day7();
        String line = "10 file1";
        Dir rootDir = new Dir();
        day7.getDirectories().put("/", rootDir);
        day7.setPresentWorkingDirectory("/");

        day7.saveNewElement(line);

        assertEquals(1, day7.getDirectories().size());
        assertNotNull(day7.getDirectories().get("/"));
        assertEquals(1, day7.getDirectories().get("/").getFiles().size());
        assertNotNull(day7.getDirectories().get("/").getFiles().get("file1"));
    }

    @Test
    @DisplayName("Should change the present working directory given a cd command - cd /")
    public void changePresentWorkingDirectory_rootDir() {
        Day7 day7 = new Day7();
        String line = "$ cd /";

        day7.changePresentWorkingDirectory(line);

        assertEquals("/", day7.getPresentWorkingDirectory());
    }

    @Test
    @DisplayName("Should change the present working directory given a cd command - cd .. to root")
    public void changePresentWorkingDirectory_dotDotToRoot() {
        Day7 day7 = new Day7();
        String line = "$ cd ..";
        day7.setPresentWorkingDirectory("/test1");

        day7.changePresentWorkingDirectory(line);

        assertEquals("/", day7.getPresentWorkingDirectory());
    }

    @Test
    @DisplayName("Should change the present working directory given a cd command - cd ..")
    public void changePresentWorkingDirectory_dotDot() {
        Day7 day7 = new Day7();
        String line = "$ cd ..";
        day7.setPresentWorkingDirectory("/test1/test2");

        day7.changePresentWorkingDirectory(line);

        assertEquals("/test1", day7.getPresentWorkingDirectory());
    }

    @Test
    @DisplayName("Should change the present working directory given a cd command - cd dirName")
    public void changePresentWorkingDirectory() {
        Day7 day7 = new Day7();
        String line = "$ cd test1";
        day7.setPresentWorkingDirectory("/");

        day7.changePresentWorkingDirectory(line);

        assertEquals("/test1", day7.getPresentWorkingDirectory());
    }

    @Test
    @DisplayName("Should read and parse the input file")
    public void readAndParseFile() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);

        // DIRS
        assertEquals(4, day7.getDirectories().size());

        assertNotNull(day7.getDirectories().get("/"));

        assertNotNull(day7.getDirectories().get("/a"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("a"), day7.getDirectories().get("/a"));
        assertEquals(1, day7.getDirectories().get("/a").getDirectories().size());

        assertNotNull(day7.getDirectories().get("/a/e"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("a").getDirectories().get("e"),
                     day7.getDirectories().get("/a/e"));
        assertEquals(day7.getDirectories().get("/a").getDirectories().get("e"), day7.getDirectories().get("/a/e"));
        assertEquals(0, day7.getDirectories().get("/a/e").getDirectories().size());

        assertNotNull(day7.getDirectories().get("/d"));
        assertNotNull(day7.getDirectories().get("/").getDirectories().get("d"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("d"), day7.getDirectories().get("/d"));
        assertEquals(0, day7.getDirectories().get("/d").getDirectories().size());

        // FILES
        assertEquals(2, day7.getDirectories().get("/").getFiles().size());
        assertNotNull(day7.getDirectories().get("/").getFiles().get("b.txt"));
        assertEquals(14848514, day7.getDirectories().get("/").getFiles().get("b.txt"));
        assertNotNull(day7.getDirectories().get("/").getFiles().get("c.dat"));
        assertEquals(8504156, day7.getDirectories().get("/").getFiles().get("c.dat"));

        assertEquals(3, day7.getDirectories().get("/a").getFiles().size());
        assertNotNull(day7.getDirectories().get("/a").getFiles().get("f"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("a").getFiles().get("f"),
                     day7.getDirectories().get("/a").getFiles().get("f"));
        assertEquals(29116, day7.getDirectories().get("/a").getFiles().get("f"));
        assertNotNull(day7.getDirectories().get("/a").getFiles().get("g"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("a").getFiles().get("g"),
                     day7.getDirectories().get("/a").getFiles().get("g"));
        assertEquals(2557, day7.getDirectories().get("/a").getFiles().get("g"));
        assertNotNull(day7.getDirectories().get("/a").getFiles().get("h.lst"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("a").getFiles().get("h.lst"),
                     day7.getDirectories().get("/a").getFiles().get("h.lst"));
        assertEquals(62596, day7.getDirectories().get("/a").getFiles().get("h.lst"));

        assertEquals(4, day7.getDirectories().get("/d").getFiles().size());
        assertNotNull(day7.getDirectories().get("/d").getFiles().get("j"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("d").getFiles().get("j"),
                     day7.getDirectories().get("/d").getFiles().get("j"));
        assertEquals(4060174, day7.getDirectories().get("/d").getFiles().get("j"));
        assertNotNull(day7.getDirectories().get("/d").getFiles().get("d.log"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("d").getFiles().get("d.log"),
                     day7.getDirectories().get("/d").getFiles().get("d.log"));
        assertEquals(8033020, day7.getDirectories().get("/d").getFiles().get("d.log"));
        assertNotNull(day7.getDirectories().get("/d").getFiles().get("d.ext"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("d").getFiles().get("d.ext"),
                     day7.getDirectories().get("/d").getFiles().get("d.ext"));
        assertEquals(5626152, day7.getDirectories().get("/d").getFiles().get("d.ext"));
        assertNotNull(day7.getDirectories().get("/d").getFiles().get("k"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("d").getFiles().get("k"),
                     day7.getDirectories().get("/d").getFiles().get("k"));
        assertEquals(7214296, day7.getDirectories().get("/d").getFiles().get("k"));

        assertEquals(1, day7.getDirectories().get("/a/e").getFiles().size());
        assertNotNull(day7.getDirectories().get("/a/e").getFiles().get("i"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("a").getDirectories().get("e")
                         .getFiles().get("i"),
                     day7.getDirectories().get("/a").getDirectories().get("e").getFiles().get("i"));
        assertEquals(day7.getDirectories().get("/").getDirectories().get("a").getDirectories().get("e")
                         .getFiles().get("i"),
                     day7.getDirectories().get("/a/e").getFiles().get("i"));
        assertEquals(584, day7.getDirectories().get("/a/e").getFiles().get("i"));
    }

    @Test
    public void findTotalSizeForEveryDirectoryWithLessThanMaxSize() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals(95437, day7.findTotalSizeForEveryDirectoryWithLessThanMaxSize());
    }

    @Disabled
    @Test
    public void day7_part1() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 1 SOLUTION: " + day7.findTotalSizeForEveryDirectoryWithLessThanMaxSize());
    }

    @Test
    @DisplayName("Should select the smallest directory with enough disk space to allow upgrading")
    public void findSmallestDirToFreeUpRequiredSpace() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_TEST_INPUT);
        assertEquals(24933642, day7.findSmallestDirSizeToFreeUpRequiredSpace());
    }

    @Disabled
    @Test
    public void day7_part2() {
        Day7 day7 = new Day7();
        day7.readAndParseFile(PATH_TO_INPUT);
        System.out.println("PART 2 SOLUTION: " + day7.findSmallestDirSizeToFreeUpRequiredSpace());
    }
}
