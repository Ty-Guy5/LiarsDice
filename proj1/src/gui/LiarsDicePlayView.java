package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.List;

import javax.swing.*;

import model.Facade;
import model.Player;
import model.liarsDice.HumanController;
import model.liarsDice.LiarsDiceGameFactory;
import model.liarsDice.LiarsDiceView;
import model.liarsDice.gameLogic.LiarsDicePlayer;

public class LiarsDicePlayView extends JPanel implements LiarsDiceView {

    private Facade facade;
    private HumanController humanController; 
    private Vector<Player> players;
    private int numPlayers;
    
    private GridLayout layout;
    private PlayerPanel playerPanel1, playerPanel2, playerPanel3, humanPanel;
    private JPanel optionsPanel;
    private JButton startGame, nextRound;
    
	public LiarsDicePlayView(Facade f){
		facade = f;
		humanController = new HumanController();
		humanController.getViewCommunication().registerView(this);

		layout = new GridLayout(3,3);
		setLayout(layout);

		
		
		add(new JPanel(), 0);

		playerPanel2 = new PlayerPanel();
		add(playerPanel2, 1);

		add(new JPanel(), 2);
		
		playerPanel1 = new PlayerPanel();
		add(playerPanel1, 3);
		
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		startGame = new JButton("Start Game");
		startGame.addActionListener(new ButtonListener());
		optionsPanel.add(startGame);
		nextRound = new JButton("Next Round");
		nextRound.addActionListener(new ButtonListener());
		optionsPanel.add(nextRound);
		add(optionsPanel, 4);

		playerPanel3 = new PlayerPanel();
		add(playerPanel3, 5);
		
		add(new JPanel(), 6);

		humanPanel = new PlayerPanel();
		add(humanPanel, 7);

		add(new JPanel(), 8);
		
		

		setupPlayers();

        this.setBackground(Color.black);
	}
	
	private void setupPlayers() {
    	LiarsDiceGameFactory factory = new LiarsDiceGameFactory();
    	List<Player> allPlayers = factory.getPlayers();
		players = new Vector<Player>();
		numPlayers = 4; //TODO this should go elsewhere, I think
		players.setSize(numPlayers);
		
		//TODO this is just the (unstable) default. Change so the user chooses the opponents.
		players.set(0, allPlayers.get(0));
		players.set(1, allPlayers.get(1));
		players.set(2, allPlayers.get(2));
		players.set(3, new LiarsDicePlayer(humanController, allPlayers.size()));
		
		playerPanel1.setPlayer(players.get(0));
		playerPanel2.setPlayer(players.get(1));
		playerPanel3.setPlayer(players.get(2));
		humanPanel.setPlayer(players.get(3));
	}
    
    private class PlayerPanel extends JPanel 
    {
    	private Player player;
    	private JLabel nameLabel;
    	private GridLayout layout;
    	private JLabel[][] diceLabels;
    	private Dimension[] diceIndices;
    	private JPanel dicePanel;
    	
    	public PlayerPanel() {
    		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    		nameLabel = new JLabel("No player selected");
    		add(nameLabel);
    		dicePanel = new JPanel();
    		layout = new GridLayout(6,5);
    		dicePanel.setLayout(layout);
    		diceLabels = new JLabel[6][5];
    		for(int i = 0; i < 6; i++){
    			for(int j = 0; j < 5; j++){
    				diceLabels[i][j] = new JLabel(" " + i + "," + j + " ");
    				dicePanel.add(diceLabels[i][j]);
    			}
    		}
    		diceIndices = new Dimension[5];
    		diceIndices[0] = new Dimension(0,2);
    		diceIndices[1] = new Dimension(2,0);
    		diceIndices[2] = new Dimension(5,1);
    		diceIndices[3] = new Dimension(5,3);
    		diceIndices[4] = new Dimension(2,4);
    		for(Dimension d : diceIndices){
    			diceLabels[d.width][d.height].setText("  1  ");
    		}
    		add(dicePanel);
    	}

		public void setPlayer(Player player) {
			this.player = player;
			reload();
		}
		
		private void reload() {
			nameLabel.setText(player.getName()); //TODO assumes getName will be fast and exception-free
		}
    	
    }
    
    private void runGame() {
    	facade.runGame("LiarsDice", players, Long.MAX_VALUE);
    }

	@Override
	public void decisionRequest() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reportGameResults() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reportInterruption() {
		// TODO Auto-generated method stub
		
	}
	
	private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
        	if (e.getSource() == startGame) {
        		runGame();
        	}
        	else if (e.getSource() == nextRound) {
        	}
        }
    }
}
