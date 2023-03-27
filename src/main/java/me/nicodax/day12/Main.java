package me.nicodax.day12;

import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    private static final String PATH_SEPARATOR = File.separator;
    private static final Path PATH_TO_INPUT = Paths.get(System.getProperty("user.dir")
            + PATH_SEPARATOR + "src"
            + PATH_SEPARATOR + "main"
            + PATH_SEPARATOR + "resources"
            + PATH_SEPARATOR + "input-day12.txt");

    public static void main(String[] args) throws InterruptedException {
        Day12 day12 = new Day12(PATH_TO_INPUT, false);

        JScrollPane scrollPane = new JScrollPane(day12);
        scrollPane.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS);

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(scrollPane);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height - 100;
        window.setSize(new Dimension(screenWidth, screenHeight));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
