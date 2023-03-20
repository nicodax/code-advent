package me.nicodax.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

public class Day7 {
    private final Integer MAX_DIRECTORY_SIZE = 100000;
    private String presentWorkingDirectory = "";
    private final HashMap<String, Dir> directories = new HashMap<>();

    public void readAndParseFile(Path path) {
        try (Stream<String> linesStream = Files.lines(path)) {
            List<String> lines = linesStream.toList();
            for (String line : lines) {
                if (line.isBlank() || line.contains("$ ls")) continue;
                if (line.contains("$ cd")) changePresentWorkingDirectory(line);
                else saveNewElement(line);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    public void changePresentWorkingDirectory(String line){
        if (line.equals("$ cd /")) {
            presentWorkingDirectory = "/";
            directories.put(presentWorkingDirectory, new Dir());
        }
        else if (line.equals("$ cd ..")) {
            String[] pathAsArray = presentWorkingDirectory.split("/", 0);
            StringBuilder newPresentWorkingDirectory = new StringBuilder();
            for (int i = 1; i < pathAsArray.length - 1; i++) {
                newPresentWorkingDirectory.append("/").append(pathAsArray[i]);
            }
            if (newPresentWorkingDirectory.toString().length() == 0) newPresentWorkingDirectory.append("/");
            presentWorkingDirectory = newPresentWorkingDirectory.toString();
        } else {
            String newDirectory = line.trim().replace("$ cd ", "");
            presentWorkingDirectory = presentWorkingDirectory.equals("/") ?
                                      presentWorkingDirectory + newDirectory :
                                      presentWorkingDirectory + "/" + newDirectory;
        }
    }

    public Integer getDirectoryTotalSize(String directoryFullPath) {
        if (directories.get(directoryFullPath) != null) return directories.get(directoryFullPath).getTotalSize();
        return -1;
    }

    public void saveNewElement(String line) {
        String[] args = line.split(" ");
        if (args[0].equals("dir")) {
            String newDirFullPath = presentWorkingDirectory.equals("/") ? presentWorkingDirectory + args[1] :
                                    presentWorkingDirectory + "/" + args[1];
            Dir newDir = new Dir();
            directories.put(newDirFullPath, newDir);
            directories.get(presentWorkingDirectory).addDirectory(args[1], newDir);
        } else {
            directories.get(presentWorkingDirectory).addFile(args[1], parseInt(args[0]));
        }
    }

    public Integer getPart1Solution() {
        List<Integer> directorySizes = directories.values().stream().map(Dir::getTotalSize).toList();
        return directorySizes.stream().filter(size -> size <= MAX_DIRECTORY_SIZE).reduce(0, Integer::sum);
    }

    public Integer getPart2Solution() {
        Integer DISK_SPACE = 70000000;
        Integer SPACE_REQUIRED_FOR_UPDATE = 30000000;
        Integer availableSpace = DISK_SPACE - directories.get("/").getTotalSize();
        Integer requiredSpace = SPACE_REQUIRED_FOR_UPDATE - availableSpace;
        List<Entry<String, Dir>> candidateList = new ArrayList<>();
        for (Entry<String, Dir> dirEntry : directories.entrySet()) {
            if (dirEntry.getValue().getTotalSize() > requiredSpace) candidateList.add(dirEntry);
        }
        candidateList.sort(Comparator.comparingInt(a -> a.getValue().getTotalSize()));
        return candidateList.get(0).getValue().getTotalSize();
    }
}
