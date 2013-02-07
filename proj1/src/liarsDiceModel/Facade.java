package liarsDiceModel;

import java.util.List;

import programmerTournamentModel.GameFactory;
import programmerTournamentModel.Tournament;

public class Facade {
	Tournament tournament;
	
	public Facade()
	{
		//default to Liar's Dice as the game choice
        tournament = new Tournament(new LiarsDiceGameFactory());
	}

	public List getPlayers() {
		return tournament.getPlayers();
	}

	public void chooseGame(GameFactory factory) {
		tournament = new Tournament(factory);
	}

	public void runTournament(int botsPerGame, int gameRepeats) {
		tournament.runTournament(botsPerGame, gameRepeats);
	}

}
