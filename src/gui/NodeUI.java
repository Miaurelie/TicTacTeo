package gui;

import javax.swing.*;

import config.Configuration;

import java.awt.*;

public class NodeUI extends JButton {
    private static final long serialVersionUID = 1L;
	public Point p;
    private Configuration configuration = Configuration.getConfiguration();

    public NodeUI(Point p) {
        this.p = p;
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(new Font("Sans Serif", Font.BOLD, 80));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(450/configuration.getSize(), 450/configuration.getSize());
    }
}
