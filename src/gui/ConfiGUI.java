package gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import config.Configuration;
import ia.Board;

public class ConfiGUI extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
    private Configuration configuration = Configuration.getConfiguration();
    private String[] tauxSeconde = {"1","2","3","5","10","30"};

	private JRadioButton JRB3x3, JRB4x4, JRB5x5, JRB6x6;
    private JPanel results, team, taux, level, board, generate, algorithm, buttons, config;
    private JRadioButton minimax, alphabeta, expert, normal;
    private JButton JBSave, JBLoad, JBStart;
    private ButtonGroup dim;
    private JComboBox<String> tauxList;
    
    static JLabel resultsLab;

    
	public ConfiGUI(JPanel board) {
        this.setSize(100, 100);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.board = board;
		
		config = new JPanel();
		config.setLayout(new GridLayout(20,1));
		config.setBorder(BorderFactory.createTitledBorder("Configuration"));

		generate = new JPanel();
        generate.setLayout(new FlowLayout());
        generate.setBorder(BorderFactory.createTitledBorder("Taille du tableau"));        
        dim = new ButtonGroup();
        JRB3x3 = new JRadioButton("3x3");
        JRB4x4 = new JRadioButton("4x4");
        JRB5x5 = new JRadioButton("5x5");
        JRB6x6 = new JRadioButton("6x6");
        dim.add(JRB3x3);
        dim.add(JRB4x4);
        dim.add(JRB5x5);
        dim.add(JRB6x6);
        JRB3x3.setSelected(true);
        generate.add(JRB3x3);
        generate.add(JRB4x4);
        generate.add(JRB5x5);
        generate.add(JRB6x6);
        
        taux = new JPanel();
        taux.setBorder(BorderFactory.createTitledBorder("Taux Repondre"));
        tauxList = new JComboBox<String>(tauxSeconde);
        tauxList.setSelectedIndex(1);
        taux.add(new Label("Taux Max : "));
        taux.add(tauxList);
        taux.add(new Label("Secondes"));
        
        level = new JPanel();
        level.setBorder(BorderFactory.createTitledBorder("Niveau"));
        ButtonGroup levelgroup = new ButtonGroup();
        expert = new JRadioButton("Expert");
        normal = new JRadioButton("Normal");
        expert.setSelected(true);
        levelgroup.add(expert);
        levelgroup.add(normal);
        level.add(expert);
        level.add(normal);  
        
        algorithm = new JPanel();
        algorithm.setBorder(BorderFactory.createTitledBorder("Algorithm"));
        ButtonGroup algogroup = new ButtonGroup();
        minimax = new JRadioButton("MiniMax");
        alphabeta = new JRadioButton("AlphaBeta");
        minimax.setSelected(true);
        algogroup.add(minimax);
        algogroup.add(alphabeta);
        algorithm.add(minimax);
        algorithm.add(alphabeta);

        results = new JPanel();
        results.setBorder(BorderFactory.createTitledBorder("Statistiques"));
        resultsLab = new JLabel("");
        results.add(resultsLab);
 
		buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.setBorder(BorderFactory.createEmptyBorder());
        JBLoad = new JButton("Import");
        JBSave = new JButton("Savegarder");
        JBStart = new JButton("Demarer");
        JBLoad.addActionListener(this);
        JBSave.addActionListener(this);
        JBStart.addActionListener(this);
        buttons.add(JBLoad);
        buttons.add(JBSave);
        buttons.add(JBStart);
        
        team = new JPanel();
        team.setBorder(BorderFactory.createTitledBorder("Equipe"));
        team.setLayout(new GridLayout(2,2));
        team.add(new JLabel("Yucef Hamlili"));
        team.add(new JLabel("Afafe Berroukech"));
        team.add(new JLabel("Saâd Mimouni"));
        team.add(new JLabel("Soumia Youbi"));
        
        config.add(generate);
        config.add(level);
        config.add(taux);
        config.add(algorithm);
        config.add(results);
        config.add(buttons);
        config.add(team);
        
        this.add(config);
        
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource() == JBStart )
    		actionPerformedStart(e);
    	else if(e.getSource() == JBSave )
    		actionPerformedSave(e);
        else if(e.getSource() == JBLoad )
    		actionPerformedLoad(e);

    }
    private void actionPerformedSave(ActionEvent e) {
    	updateConfiguration();
    	configuration.saveConfig();
	}

	private void actionPerformedLoad(ActionEvent e) {		
		configuration = Configuration.loadConfig();
    	tauxList.setSelectedItem(new String(configuration.getTaux()+""));
    	if(configuration.getSize() == 3)
    		JRB3x3.setSelected(true);
    	else if(configuration.getSize() == 4)
    		JRB4x4.setSelected(true);
    	else if(configuration.getSize() == 5)
    		JRB5x5.setSelected(true);
    	else if(configuration.getSize() == 6)
    		JRB6x6.setSelected(true);
    	if(configuration.getAlgorithm().equals("MiniMax"))
    		minimax.setSelected(true);
    	else if(configuration.getAlgorithm().equals("AlphaBeta"))
    		alphabeta.setSelected(true);
    	if(configuration.getLevel() == 1)
    		expert.setSelected(true);
    	else if(configuration.getLevel() == 10)
    		normal.setSelected(true);
	}

	private void actionPerformedStart(ActionEvent e) {
		updateConfiguration();
        BoardGUI bb = new BoardGUI(new Board());
        board.removeAll();
        board.revalidate();
        board.repaint();
        board.add(bb);
        board.repaint();
    }
	
	private void updateConfiguration() {
    	if (JRB3x3.isSelected()) {
        	configuration.setSize(3);
        }
    	else if (JRB4x4.isSelected()) {
        	configuration.setSize(4);
        }
        else if (JRB5x5.isSelected()) {
        	configuration.setSize(5);
        }
        else if (JRB6x6.isSelected()) {
        	configuration.setSize(6);
        }        
        if (minimax.isSelected()) {
        	configuration.setAlgorithm("MiniMax");
        }
        else if (alphabeta.isSelected()) {
        	configuration.setAlgorithm("AlphaBeta");
        }
        if (expert.isSelected()) {
        	configuration.setLevel(1);
        }
        else if (normal.isSelected()) {
        	configuration.setLevel(10);
        }
        configuration.setTaux(Integer.parseInt((String) tauxList.getSelectedItem()));
	}
    
}
