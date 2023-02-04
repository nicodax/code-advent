package me.nicodax.day7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirTest {

    @Test
    @DisplayName("Should return the total size of a given directory - empty dir")
    public void getTotalSize_emptyDir() {
        Dir dir = new Dir();
        Integer expectedTotalSize = 0;
        assertEquals(expectedTotalSize, dir.getTotalSize());
    }

    @Test
    @DisplayName("Should return the total size of a given directory - dir contains only files")
    public void getTotalSize_onlyFiles() {
        Dir dir = new Dir();

        dir.addFile("file1", 10);
        dir.addFile("file2", 20);
        dir.addFile("file3", 30);
        dir.addFile("file4", 40);

        Integer expectedTotalSize = 100;
        assertEquals(expectedTotalSize, dir.getTotalSize());
    }

    @Test
    @DisplayName("Should return the total size of a given directory - dir contains only directories")
    public void getTotalSize_onlyDirs() {
        Dir parentDir = new Dir();
        Dir childDir1 = new Dir();
        Dir childDir2 = new Dir();
        Dir childDir3 = new Dir();

        parentDir.addDirectory("dir1", childDir1);
        parentDir.addDirectory("dir2", childDir2);
        parentDir.addDirectory("dir3", childDir3);

        childDir1.addFile("file1", 10);
        childDir2.addFile("file2", 20);
        childDir2.addFile("file3", 30);
        childDir3.addFile("file4", 40);

        Integer expectedTotalSize = 100;
        assertEquals(expectedTotalSize, parentDir.getTotalSize());
    }

    @Test
    @DisplayName("Should return the total size of a given directory")
    public void getTotalSize() {
        Dir parentDir = new Dir();
        Dir childDir1 = new Dir();
        Dir childDir2 = new Dir();
        Dir childDir3 = new Dir();

        parentDir.addDirectory("dir1", childDir1);
        parentDir.addDirectory("dir2", childDir2);
        parentDir.addDirectory("dir3", childDir3);

        parentDir.addFile("file1", 10);
        parentDir.addFile("file2", 20);
        childDir1.addFile("file3", 30);
        childDir2.addFile("file4", 40);
        childDir2.addFile("file5", 50);
        childDir3.addFile("file6", 60);

        Integer expectedTotalSize = 210;
        assertEquals(expectedTotalSize, parentDir.getTotalSize());
    }
}
