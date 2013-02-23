package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import liarsDiceModel.Facade;
import liarsDiceModel.Player;
import liarsDiceModel.Statistics;

public class TournamentView extends JPanel {
    
    private JLabel botsPerGameLabel , repeatTimesLabel, messageLabel;
    private JTextField botsPerGame, repeatTimes;
    private JPanel tournamentOptionsPanel, tablePanel;
    private JTable statsTable;
    private JButton runButton;
    
    private StatsTableModel statsTableModel;
    private int numPlayersPerGame;
    private int numGameRepeatsPerTournament;
    
    private Facade facade;

	public TournamentView(Facade f){
		
		facade = f;
		
    	numPlayersPerGame = 4;
    	numGameRepeatsPerTournament = 1;

        this.setLayout(new BorderLayout());
        
        //tournament view
        tournamentOptionsPanel = new JPanel();
        tournamentOptionsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        tournamentOptionsPanel.setPreferredSize(new Dimension(180, 400));
        tournamentOptionsPanel.setMaximumSize(new Dimension(180, 2000));
        botsPerGameLabel = new JLabel("Bots per game:");
        tournamentOptionsPanel.add(botsPerGameLabel);
        botsPerGame = new JTextField("4", 2);
        tournamentOptionsPanel.add(botsPerGame);
        repeatTimesLabel = new JLabel("Repeat each game:");
        tournamentOptionsPanel.add(repeatTimesLabel);
        repeatTimes = new JTextField("1", 2);
        tournamentOptionsPanel.add(repeatTimes);
        runButton = new JButton("Run Tournament");
        runButton.addActionListener(new ButtonListener());
        tournamentOptionsPanel.add(runButton);
        messageLabel = new JLabel();
        tournamentOptionsPanel.add(messageLabel);
        this.add(tournamentOptionsPanel, BorderLayout.WEST);
        
        statsTableModel = new StatsTableModel();
        statsTable = new JTable(statsTableModel);
        statsTable.setPreferredScrollableViewportSize(new Dimension(800, 500));
        statsTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        statsTable.setFillsViewportHeight(true);
        JScrollPane statsTableScrollPane = new JScrollPane(statsTable);
        statsTableScrollPane.setPreferredSize(new Dimension(800,400));
        statsTableScrollPane.setMinimumSize(new Dimension(400, 200));
        
        this.add(statsTableScrollPane, BorderLayout.CENTER);
        this.setBackground(Color.black);
	}
	
	public void loadTable(){
		if(facade != null && statsTableModel != null){
			statsTableModel.loadTable(facade);
		}
	}
	
    private class StatsTableModel extends AbstractTableModel {
        private String[] columnNames = {
        		"Include",
        		"Place",
        		"Player ID", 
        		"Bot Name", 
        		"Wins", 
        		"Losses", 
        		"Exceptions", 
        		"Invalid Decisions",
        		"Timeouts"};
        private Object[][] data = new Object[][] {};
        
        public void loadTable(Facade f) {
        	List<Player> players = f.getPlayers();
        	List<Player> participants = f.getParticipants();
        	Collections.sort(players); //sorts by number of wins
        	data = new Object[players.size()][];
        	for (int p=0; p<players.size(); p++)
        	{
            	data[p] = new Object[9];
        		Statistics stats = players.get(p).getStatistics();
        		System.out.println(stats);
        		
        		setValueAt(participants.contains(players.get(p)), p, 0); //refresh check boxes
        		
        		setValueAt(getPlace(p+1), p, 1);
        		setValueAt(players.get(p).getID(), p, 2);
        		setValueAt(players.get(p).getName(), p, 3);
        		setValueAt(stats.getWins(), p, 4);
        		setValueAt(stats.getLosses(), p, 5);
        		setValueAt(stats.getExceptions(), p, 6);
        		setValueAt(stats.getInvalidDecisions(), p, 7);
        		setValueAt(stats.getTimeouts(), p, 8);
        	}
        }

        private String getPlace(int place) {
        	int lastDigit = place % 10;
        	int penultimateDigit = (place / 10) % 10;
        	if(penultimateDigit == 1){
        		return place + "th"; //teens
        	}
			if(lastDigit == 1){
				return place + "st";
			}
			else if(lastDigit == 2){
				return place + "nd";
			}
			else if(lastDigit == 3){
				return place + "rd";
			}
			return place + "th";
		}

		public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        public Class getColumnClass(int c) {
        	if(c == 0){
        		return Boolean.class;
        	}
            return getValueAt(0, c).getClass();
        }
        

        ///*
        // * Don't need to implement this method unless your table's
        // * editable.
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col == 0){
                return true;
            } 
            else{
                return true;
            }
        }
        //*/

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            if(col == 0){ //including/excluding a player
            	facade.addOrRemovePlayer(((Boolean)value), row);
            }
            fireTableCellUpdated(row, col);
        }
    }


	private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
        	try{
        		facade.resetPlayerStats();
        		messageLabel.setText("");
        		numPlayersPerGame = Integer.parseInt(botsPerGame.getText());
        		numGameRepeatsPerTournament = Integer.parseInt(repeatTimes.getText());
				facade.runTournament(numPlayersPerGame, numGameRepeatsPerTournament);
				statsTableModel.loadTable(facade);
        	}catch(NumberFormatException ex){
        		messageLabel.setText("Please only input positive integers.");
        	}
        }
    }
}
