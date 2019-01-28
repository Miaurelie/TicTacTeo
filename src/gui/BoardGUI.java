package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import config.Configuration;
import ia.Action;
import ia.Board;
import ia.GameState;
import ia.Node;
import ia.Player;

public class BoardGUI extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	private Configuration configuration = Configuration.getConfiguration();
	
    private Board board;
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    private NodeUI[][] grid;

    public BoardGUI(Board board) {
        this.board = board;
        
        Node[][] logGrid = board.getGrid();
        grid = new NodeUI[logGrid.length][logGrid.length];

        gbl = new GridBagLayout();
        setLayout(gbl);
        gbc = new GridBagConstraints();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new NodeUI(new Point(i, j));
                gbc.gridx = j;
                gbc.gridy = i;
                Border border = null;

                if (i < grid.length - 1) {
                    if (j < grid.length - 1) {
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }
                } else {
                    if (j < grid.length - 1) {
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else {
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                grid[i][j].setBorder(border);
                grid[i][j].addMouseListener(this);
                add(grid[i][j], gbc);
            }
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		GameState gameState = board.isGameOver();
        NodeUI n = (NodeUI) e.getSource();

        if (gameState == GameState.IN_PROGRESS) {
        	if (n.isEnabled()) {
                n.setText("X");
                n.setEnabled(false);
                board.move(n.p, Player.PLAYER_X);
                gameState = board.isGameOver();
                if (gameState == GameState.IN_PROGRESS) {
                    Action m = null;
                    if (configuration.getAlgorithm().equals("MiniMax")) {
                        m = board.moveOAIMinMax();
                    }
                    else if (configuration.getAlgorithm().equals("AlphaBeta")) {
                        m = board.moveOAIAlphaBeta();
                    }
                    if (m != null && m.getP() != null) {
                        ConfiGUI.resultsLab.setText(m.toString());
					    grid[m.getP().x][m.getP().y].setText("O");
					    grid[m.getP().x][m.getP().y].setEnabled(false);
					}
                }
                gameState = board.isGameOver();
                if (gameState == GameState.O_WON) {
                	ConfiGUI.resultsLab.setText("<html><body>State : <span style='color: red;'>IA a gagné</span></body></html>");
                } else if (gameState == GameState.X_WON) {
                	ConfiGUI.resultsLab.setText("<html><body>State : <span style='color: green;'>Vous avez gagné</span></body></html>");
                } else if (gameState == GameState.DRAW) {
                	ConfiGUI.resultsLab.setText("<html><body>State : <span style='color: blue;'>Egalité</span></body></html>");
                }
                
            }
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
