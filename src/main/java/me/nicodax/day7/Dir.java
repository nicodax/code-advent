package me.nicodax.day7;

import java.util.HashMap;

public class Dir {
    private final HashMap<String, Integer> files = new HashMap<>();
    private final HashMap<String, Dir> directories = new HashMap<>();
    private Integer filesTotalSize = 0;
    private Integer directoriesTotalSize = 0;

    public Integer getTotalSize() {
        if (!files.isEmpty()) filesTotalSize = files.values().stream().reduce(0, Integer::sum);
        if (!directories.isEmpty()) directoriesTotalSize = directories.values()
                                                                      .stream()
                                                                      .map(Dir::getTotalSize)
                                                                      .reduce(0, Integer::sum);
        return filesTotalSize + directoriesTotalSize;
    }

    public void addFile(String name, Integer size) {
        files.put(name, size);
    }

    public void addDirectory(String name, Dir dir) {
        directories.put(name, dir);
    }
}
