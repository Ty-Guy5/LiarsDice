package gui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.*;

import liarsDiceModel.Facade;
import liarsDiceModel.Player;

public class LiarsDiceView extends JPanel {

    private Facade facade;
    private SpringLayout layout;
    private PlayerPanel playerPanel1, playerPanel2, playerPanel3;
    private JPanel optionsPanel, humanPanel;
    private JButton startGame, nextRound;
    
	public LiarsDiceView(Facade f){
		facade = f;
		
		layout = new SpringLayout();
		setLayout(layout);

		playerPanel1 = new PlayerPanel();
		add(playerPanel1);
		layout.putConstraint(SpringLayout.WEST, playerPanel1, 100, SpringLayout.WEST, this);
		
		playerPanel2 = new PlayerPanel();
		add(playerPanel2);
		layout.putConstraint(SpringLayout.WEST, playerPanel2, 100, SpringLayout.WEST, this);
		
		playerPanel3 = new PlayerPanel();
		add(playerPanel3);
		layout.putConstraint(SpringLayout.WEST, playerPanel3, 100, SpringLayout.WEST, this);
		
		setupPlayers();
		
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		startGame = new JButton("Start Game");
		optionsPanel.add(startGame);
		nextRound = new JButton("Next Round");
		optionsPanel.add(nextRound);
		add(optionsPanel);
		
		humanPanel = new JPanel();
		add(humanPanel);

        this.setBackground(Color.black);
	}
	
	private void setupPlayers() {
		playerPanel1.setPlayer(facade.getPlayers().get(0));
		playerPanel1.setPlayer(facade.getPlayers().get(1));
		playerPanel1.setPlayer(facade.getPlayers().get(2));
	}
    
    private class PlayerPanel extends JPanel 
    {
    	private Player player;
    	private JLabel nameLabel;
    	
    	public PlayerPanel() {
    		nameLabel = new JLabel("No player selected");
    		add(nameLabel);
    	}

		public void setPlayer(Player player) {
			this.player = player;
			reload();
		}
		
		private void reload() {
			nameLabel.setText(player.getName()); //TODO assumes getName will be fast and exception-free
		}
    	
    }
}
