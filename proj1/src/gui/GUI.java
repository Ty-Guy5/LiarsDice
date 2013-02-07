package gui;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import liarsDiceModel.LiarsDiceGameFactory;

import programmerTournamentModel.Tournament;

public class GUI extends JFrame {
   
    private JButton runButton;
   
    private Tournament tournament;
   
    public GUI()
    {
        tournament = new Tournament(new LiarsDiceGameFactory());
       
        setTitle("The Programmer's Tournament");
        setSize(500,300);
        setLocation(500, 600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container pane = getContentPane();
       
        runButton = new JButton("Run Tournament");
        runButton.setSize(new Dimension(160,20));
        runButton.addActionListener(new ButtonListener());
        pane.add(runButton);
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