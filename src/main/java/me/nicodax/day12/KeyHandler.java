package me.nicodax.day12;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class KeyHandler implements KeyListener {

    private final Day12 dp;
    private Integer mode = 0;
    private double scale = 1.0;

    public KeyHandler(Day12 dp) {
        this.dp = dp;
    }

    public void zoomIn() {
        scale += 0.1;
        dp.setScale(scale);
        for (List<Node> nodeLine : dp.getNodes()) {
            for (Node node : nodeLine) {
                node.setScaledCoordinates(scale);
            }
        }
        dp.revalidate();
        dp.repaint();
    }

    public void zoomOut() {
        scale -= 0.1;
        dp.setScale(scale);
        for (List<Node> nodeLine : dp.getNodes()) {
            for (Node node : nodeLine) {
                node.setScaledCoordinates(scale);
            }
        }
        dp.revalidate();
        dp.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == VK_ENTER) {
            if (dp.getPART() == 1 && mode == 0) {
                dp.resolve();
                mode = 1;
                for (Node node : dp.getCheckedList()) {
                    node.startDisplayTimer(dp.getCheckedList());
                }
            }
            else if (dp.getPART() == 1 && mode == 1) {
                dp.trackThePath(dp.getEndNode());
                mode = 2;
                for (Node node : dp.getPath()) {
                    node.startDisplayTimer(dp.getPath());
                }
            }
            else if (dp.getPART() == 1 && mode == 2) {
                dp.reset();
                mode = 0;
            }
            else {
                if (!dp.getFinishedPart2() && dp.getStartNodeList().size() == 0) dp.saveStartNodes();
                else {
                    dp.setStartNode(dp.getStartNodeList().get(0));
                    dp.reset();
                }
                dp.resolvePart2GUI();
            }
        }
        else if (code == VK_SPACE) {
            dp.search();
        }
        else if (code == VK_UP) {
            zoomIn();
        }
        else if (code == VK_DOWN) {
            zoomOut();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
