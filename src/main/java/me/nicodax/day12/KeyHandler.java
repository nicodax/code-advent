package me.nicodax.day12;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class KeyHandler implements KeyListener {

    private final Day12 dp;

    public KeyHandler(Day12 dp) {
        this.dp = dp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == VK_ENTER) dp.resolve();
        if (code == VK_R) dp.reset();
        if (code == VK_SPACE) dp.search();
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
