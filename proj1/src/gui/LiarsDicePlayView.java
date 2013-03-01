package gui;

import genericModel.Facade;
import genericModel.Player;

import java.awt.*;

import javax.swing.*;


import liarsDiceModel.interfaceToFrontEnd.LiarsDiceView;

public class LiarsDicePlayView extends JPanel implements LiarsDiceView {

    private Facade facade;
    private GridLayout layout;
    private PlayerPanel playerPanel1, playerPanel2, playerPanel3;
    private JPanel optionsPanel, humanPanel;
    private JButton startGame, nextRound;
    
	public LiarsDicePlayView(Facade f){
		facade = f;

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
		optionsPanel.add(startGame);
		nextRound = new JButton("Next Round");
		optionsPanel.add(nextRound);
		add(optionsPanel, 4);

		playerPanel3 = new PlayerPanel();
		add(playerPanel3, 5);
		
		add(new JPanel(), 6);

		humanPanel = new JPanel();
		add(humanPanel, 7);

		add(new JPanel(), 8);
		
		

		setupPlayers();

        this.setBackground(Color.black);
	}
	
	private void setupPlayers() {
		//TODO this is just a method to do the default. Instead, the user should fill the player slots.
		playerPanel1.setPlayer(facade.getPlayers().get(0));
		playerPanel2.setPlayer(facade.getPlayers().get(1));
		playerPanel3.setPlayer(facade.getPlayers().get(2));
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
}
