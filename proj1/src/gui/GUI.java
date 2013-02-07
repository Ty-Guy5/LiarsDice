package gui;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import liarsDiceModel.LiarsDiceGameFactory;

import programmerTournamentModel.Tournament;

public class GUI extends JFrame {
   
    private Tournament tournament;
    
    private JButton runButton;
    private JTable statsTable;
   
    public GUI()
    {
        tournament = new Tournament(new LiarsDiceGameFactory());
        Container pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
       
        runButton = new JButton("Run Tournament");
        runButton.setPreferredSize(new Dimension(160,20));
        runButton.addActionListener(new ButtonListener());
        pane.add(runButton);
        
        StatsTableModel statsTableModel = new StatsTableModel();
        statsTable = new JTable(statsTableModel);
        statsTable.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        statsTable.setFillsViewportHeight(true);
        JScrollPane statsTableScrollPane = new JScrollPane(statsTable);
        pane.add(statsTableScrollPane);

        
        setTitle("The Programmer's Tournament");
        setLocation(500, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }
   
   
    private Object[][] getStatsTableData() {
		// TODO Stub
    	
    	
		return new Object[][] {{new Integer(1), "bot1", new Integer(14), new Integer(6)}};
	}
    
    private class StatsTableModel extends AbstractTableModel {
        private String[] columnNames = {"playerID", "botName", "wins", "losses"};
        private Object[][] data = new Object[][] {};

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
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }
         */

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }


	private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            tournament.runTournament(2, 1);
            runButton.setEnabled(false);
        }
    }
    public static void main(String[] args)
    {
        GUI gui = new GUI();
    }
}
