package me.nicodax.day12;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Node extends JButton implements ActionListener {

    Node parent;
    Node startNode;
    Node endNode;

    int col;
    int row;
    char elevation;

    boolean start = false;
    boolean end = false;
    boolean open = false;
    boolean checked = false;
    boolean path = false;
    boolean wasDrawnChecked = false;
    private double scale;
    @Getter
    private double scaledX;
    @Getter
    private double scaledY;

    public void setScaledCoordinates(double scale) {
        scaledX = scale * getX();
        scaledY = scale * getY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scaledX, scaledY);
        g2d.translate(scale, scale);
    }


    public Node(int x, int y, char elevation) {
        this.col = x;
        this.row = y;
        this.elevation = elevation;

        setColorFromElevation();
        setText("<html>" + elevation + "</html>");
        addActionListener(this);
    }

    public void setAsStart() {
        start = true;
    }

    public void setAsEnd() {
        end = true;
    }

    public void setAsChecked(List<Node> checkedList) {
        checked = true;
        if (!start && !end) checkedList.add(this);
    }

    public void setAsOpen() { open = true; }

    public void setAsPath() {
        if (endNode.parent.equals(this)) setPathDirection(endNode);
        if (!startNode.equals(parent)) parent.setPathDirection(this);
        path = true;
    }

    public void startDisplayTimer(List<Node> nodeList) {
        int delay = nodeList.indexOf(this);
        Timer timer = new Timer(delay, this);
        timer.setRepeats(false);
        timer.start();
    }

    private void setPathDirection(Node nextNode) {
        if (col > nextNode.col) setText("<html>←</html>");
        else if (col < nextNode.col) setText("<html>→</html>");
        else if (row > nextNode.row) setText("<html>↑</html>");
        else setText("<html>↓</html>");
    }

    public void setColorFromElevation() {
        if (elevation == 'S') {
            setBackground(Color.BLUE);
        } else if (elevation == 'E') {
            setBackground(Color.RED);
        }
        else if (path) {
            if (elevation >= 'a' && elevation <= 'z') {
                float hue = (float) ((26 - (elevation - 'a')) * 11.25);
                setBackground(Color.getHSBColor(hue / 360f, 1f, 0.8f));
            } else {
                setBackground(Color.WHITE);
            }
        } else if (open) {
            if (elevation >= 'a' && elevation <= 'z') {
                float hue = (float) ((elevation - 'a') * 11.25);
                float brightness = (float) (0.8 - (elevation - 'a') * 0.02);
                setBackground(Color.getHSBColor(hue / 360f, 1f, brightness));
            } else {
                float brightness = (float) (0.7 - (elevation - 'A') * 0.01);
                setBackground(new Color(brightness, brightness, brightness));
            }
            wasDrawnChecked = true;
        } else {
            int gray = (int) (255 * (1 - (double)(elevation - 'a') / ('z' - 'a')));
            setBackground(new Color(gray, gray, gray));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Node otherNode)) return false;
        return otherNode.col == col && otherNode.row == row && otherNode.elevation == elevation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (wasDrawnChecked) setAsPath();
        setColorFromElevation();
    }
}
