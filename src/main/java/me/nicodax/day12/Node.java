package me.nicodax.day12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

public class Node extends JButton implements ActionListener {

    Node parent;

    int col;
    int row;
    char elevation;

    boolean start = false;
    boolean end = false;
    boolean open = false;
    boolean checked = false;

    public Node(int x, int y, char elevation) {
        this.col = x;
        this.row = y;
        this.elevation = elevation;

        setBackground(setColorFromElevation());
        setForeground(black);
        setText("<html>" + elevation + "</html>");
        addActionListener(this);
    }

    public void setAsStart() {
        start = true;
    }

    public void setAsEnd() {
        end = true;
    }

    public void setAsChecked() {
        if (!start && !end) {
            setBackground(orange);
            setForeground(black);
        }
        checked = true;
    }

    public void setAsOpen() { open = true; }

    public void setAsPath() {
        setBackground(green);
        setForeground(black);
    }
    public Color setColorFromElevation() {
        if (elevation >= 'a' && elevation <= 'z') {
            float hue = (float) ((elevation - 'a') * 11.25);
            return Color.getHSBColor(hue / 360f, 1f, 0.8f);
        } else if (elevation == 'S') {
            return Color.BLUE;
        } else if (elevation == 'E') {
            return Color.RED;
        } else {
            return Color.WHITE;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof Node otherNode)) return false;
        return otherNode.col == col && otherNode.row == row && otherNode.elevation == elevation;
    }

    @Override
    public void actionPerformed(ActionEvent e) { setBackground(orange); }
}
