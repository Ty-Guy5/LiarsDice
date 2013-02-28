package gui;

import java.awt.*;

import javax.swing.*;

import liarsDiceModel.Facade;
import liarsDiceModel.Player;

public class LiarsDiceView extends JPanel {

    private Facade facade;
    private GridLayout layout;
    private PlayerPanel playerPanel1, playerPanel2, playerPanel3;
    private JPanel optionsPanel, humanPanel;
    private JButton startGame, nextRound;
    
	public LiarsDiceView(Facade f){
		facade = f;

		layout = new GridLayout(3,3);
		setLayout(layout);

		add(new JPanel(), 0);

		playerPanel1 = new PlayerPanel();
		add(playerPanel1, 1);
		
		add(new JPanel(), 2);

		playerPanel2 = new PlayerPanel();
		add(playerPanel2, 3);
		
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
		startGame = new JButton("Start Game");
		optionsPanel.add(startGame);
		nextRound = new JButton("Next Round");
		optionsPanel.add(nextRound);
		add(optionsPanel, 4);

		humanPanel = new JPanel();
		add(humanPanel, 5);
		
		add(new JPanel(), 6);
		
		playerPanel3 = new PlayerPanel();
		add(playerPanel3, 7);

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
