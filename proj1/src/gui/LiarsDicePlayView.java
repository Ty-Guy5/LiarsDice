package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Facade;
import model.Player;
import model.liarsDice.HumanController;
import model.liarsDice.LiarsDiceGameFactory;
import model.liarsDice.LiarsDiceView;
import model.liarsDice.gameLogic.Die;
import model.liarsDice.gameLogic.LiarsDicePlayer;

public class LiarsDicePlayView extends JPanel implements LiarsDiceView {

    private Facade facade;
    private HumanController humanController; 
    private Vector<Player> players;
    private int numPlayers;
    
    private GridLayout layout;
    private PlayerPanel playerPanel1, playerPanel2, playerPanel3, humanPanel;
    private JPanel optionsPanel, player1InfoPanel, player2InfoPanel, player3InfoPanel, humanInputPanel;
    private JLabel player1InfoLabel, player2InfoLabel, player3InfoLabel, player1Decision, player2Decision, player3Decision;
    private JButton startGame, nextRound, humanBid, humanChallenge;
    private JTextField bidQuantity;
    private JRadioButton rb2, rb3, rb4, rb5, rb6;
    
	public LiarsDicePlayView(Facade f){
		facade = f;
		humanController = new HumanController();
		humanController.getViewCommunication().registerView(this);

		layout = new GridLayout(3,3);
		setLayout(layout);
		
		player2InfoPanel = new JPanel();
		player2InfoPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		player2InfoLabel = new JLabel("Last Decision:  ");
		player2Decision = new JLabel();
		player2InfoPanel.add(player2InfoLabel);
		player2InfoPanel.add(player2Decision);
		add(player2InfoPanel, 0);

		playerPanel2 = new PlayerPanel();
		add(playerPanel2, 1);

		player3InfoPanel = new JPanel();
		player3InfoPanel.setLayout(new BorderLayout());
		player3InfoLabel = new JLabel("Last Decision:  ");
		player3Decision = new JLabel();
		JPanel p3Container = new JPanel();
		p3Container.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3Container.add(player3InfoLabel);
		p3Container.add(player3Decision);
		player3InfoPanel.add(p3Container, BorderLayout.SOUTH);
		add(player3InfoPanel, 2);
		
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
		
		player1InfoPanel = new JPanel();
		player1InfoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		player1InfoLabel = new JLabel("Last Decision:  ");
		player1Decision = new JLabel();
		player1InfoPanel.add(player1InfoLabel);
		player1InfoPanel.add(player1Decision);
		add(player1InfoPanel, 6);

		humanPanel = new PlayerPanel();
		add(humanPanel, 7);

		humanInputPanel = new JPanel();
		GridLayout g = new GridLayout(2,1);
		humanInputPanel.setLayout(g);
		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new BorderLayout());
		JPanel bidPanel = new JPanel();
		bidQuantity = new JTextField("", 4);
		bidPanel.add(bidQuantity);
		//add radio buttons, etc
		rb2 = new JRadioButton("");
		JLabel rbp2 = new JLabel(new ImageIcon("images/small/die2.png"));
		rb3 = new JRadioButton("");
		JLabel rbp3 = new JLabel(new ImageIcon("images/small/die3.png"));
		rb4 = new JRadioButton("");
		JLabel rbp4 = new JLabel(new ImageIcon("images/small/die4.png"));
		rb5 = new JRadioButton("");
		JLabel rbp5 = new JLabel(new ImageIcon("images/small/die5.png"));
		rb6 = new JRadioButton("");
		JLabel rbp6 = new JLabel(new ImageIcon("images/small/die6.png"));
	    //Group the radio buttons.
	    ButtonGroup group = new ButtonGroup();
	    group.add(rb2);
	    group.add(rb3);
	    group.add(rb4);
	    group.add(rb5);
	    group.add(rb6);
	    JPanel radioPanel = new JPanel();
	    radioPanel.setLayout(new GridLayout(1, 10));
	    radioPanel.add(rb2);
	    radioPanel.add(rbp2);
	    radioPanel.add(rb3);
	    radioPanel.add(rbp3);
	    radioPanel.add(rb4);
	    radioPanel.add(rbp4);
	    radioPanel.add(rb5);
	    radioPanel.add(rbp5);
	    radioPanel.add(rb6);
	    radioPanel.add(rbp6);
	    bidPanel.add(radioPanel);
	    wrapperPanel.add(bidPanel, BorderLayout.SOUTH);
		humanInputPanel.add(wrapperPanel, 0);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		humanBid = new JButton(" Submit Bid ");
		humanBid.addActionListener(new ButtonListener());
		humanChallenge = new JButton(" Challenge ");
		humanChallenge.addActionListener(new ButtonListener());
		buttonPanel.add(humanBid);
		buttonPanel.add(humanChallenge);
		humanInputPanel.add(buttonPanel, 1);
		add(humanInputPanel, 8);

		setupPlayers();

        this.setMinimumSize(new Dimension(600,400));
	}
	
	public void setDice(Player p){
		if(p == null){
			return;
		}
		if(p == playerPanel1.player){
			playerPanel1.updateDicePanel(false);
		}
		else if(p == playerPanel2.player){
			playerPanel2.updateDicePanel(false);
		}
		else if(p == playerPanel1.player){
			playerPanel3.updateDicePanel(false);
		}
		else if(p == humanPanel.player){
			humanPanel.updateDicePanel(true);
		}
		else{
			System.out.println("non-existent player: " + p.getID() + " " + p.getName());
		}
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
		
		playerPanel1.setPlayer((LiarsDicePlayer)players.get(0));
		playerPanel1.setBorder(new TitledBorder(players.get(0).getName()));
		playerPanel2.setPlayer((LiarsDicePlayer)players.get(1));
		playerPanel2.setBorder(new TitledBorder(players.get(1).getName()));
		playerPanel3.setPlayer((LiarsDicePlayer)players.get(2));
		playerPanel3.setBorder(new TitledBorder(players.get(2).getName()));
		humanPanel.setPlayer((LiarsDicePlayer)players.get(3));
		humanPanel.setBorder(new TitledBorder(players.get(3).getName()));
		
		playerPanel1.updateDicePanel(false);
		playerPanel2.updateDicePanel(false);
		playerPanel3.updateDicePanel(false);
		humanPanel.updateDicePanel(true);
		
	}
    
    private class PlayerPanel extends JPanel 
    {
    	public LiarsDicePlayer player;
    	public GridLayout layout;
    	public JLabel[][] diceLabels;
    	public Dimension[] diceIndices;
    	public JPanel dicePanel;
    	public ImageIcon die1, die2, die3, die4, die5, die6, dieq, blank;
    	
    	public PlayerPanel() {
    		blank = new ImageIcon("images/blank.png");
    		die1 = new ImageIcon("images/die1.png");
    		die2 = new ImageIcon("images/die2.png");
    		die3 = new ImageIcon("images/die3.png");
    		die4 = new ImageIcon("images/die4.png");
    		die5 = new ImageIcon("images/die5.png");
    		die6 = new ImageIcon("images/die6.png");
    		dieq = new ImageIcon("images/diequestion.png");
    		
    		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    		dicePanel = new JPanel();
    		layout = new GridLayout(5,5);
    		dicePanel.setLayout(layout);
    		diceLabels = new JLabel[5][5];
    		for(int i = 0; i < 5; i++){
    			for(int j = 0; j < 5; j++){
    				diceLabels[i][j] = new JLabel(blank/*" " + i + "," + j + " "*/);
    				dicePanel.add(diceLabels[i][j]);
    			}
    		}
    		diceIndices = new Dimension[5];
    		diceIndices[0] = new Dimension(0,2);
    		diceIndices[1] = new Dimension(2,0);
    		diceIndices[2] = new Dimension(4,1);
    		diceIndices[3] = new Dimension(4,3);
    		diceIndices[4] = new Dimension(2,4);
//    		for(Dimension d : diceIndices){
//    			//diceLabels[d.width][d.height].setText("  1  ");
//    			diceLabels[d.width][d.height].setIcon(dieq);
//    		}
    		add(dicePanel);
    	}

		public void updateDicePanel(boolean show) {
			Dimension d;
			int i = 0;
			while(i < player.getDice().size()){
				d = diceIndices[i];
				if(show){
					diceLabels[d.width][d.height].setIcon(chooseDie(player.getDice().get(i)));
				}
				else{
					diceLabels[d.width][d.height].setIcon(dieq);
				}
				i++;
			}
			while(i < 5){
				d = diceIndices[i];
    			diceLabels[d.width][d.height].setIcon(blank);
    			i++;
			}
		}

		private Icon chooseDie(Die die) {
			switch(die.getValue()){
				case 1:
					return die1;
				case 2:
					return die2;
				case 3:
					return die3;
				case 4:
					return die4;
				case 5:
					return die5;
				case 6:
					return die6;
				default: //shouldn't happen
					return dieq;
			}
		}

		public void setPlayer(LiarsDicePlayer player) {
			this.player = player;
			reload();
		}
		
		private void reload() {
			//nameLabel.setText(player.getName()); //TODO assumes getName will be fast and exception-free
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
