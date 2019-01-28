package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ia.Board;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
    private static final int WIDTH = 840;
    private static final int HEIGHT = 560;
    
    private JPanel board, configuration;

	public Game() throws HeadlessException {
        this.setLayout(new BorderLayout());
        this.setTitle("TicTacToe - Projet GI5 ENSAO");
        Board b = new Board();
        
        this.board = new BoardGUI(b);
        this.configuration = new ConfiGUI(this.board);
        
        this.add(board, BorderLayout.CENTER);
        this.add(configuration, BorderLayout.EAST);
        
        this.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

}
