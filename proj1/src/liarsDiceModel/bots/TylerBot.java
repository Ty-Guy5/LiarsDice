package liarsDiceModel.bots;
import java.util.ArrayList;
import java.util.List;

import programmerTournamentModel.GameHistory;
import liarsDiceModel.*;

/**
 * Tyler's bot so far.
 */
public class TylerBot extends LiarsDiceBot 
{
	boolean verbose = true;
	public String getName() 
	{
		return "Tyler's Bot";
	}

	public Decision getDecision(GameInfo gi) 
	{
		Bid myBid = new Bid(gi.getTotalDice() / 3, 6);
		if(gi.getCurrentBid() == null)
		{
			return myBid;
		}
		else if (checkValidDecision(myBid, gi))
		{
			return myBid;
		}
		else
		{
			return new Challenge();
		}
	}

	int numGames = 0;
	public void reportGameResults(GameHistory hist)
	{
		say("Game " + ++numGames + ": ");
		for (Round r : hist.getRounds())
		{
			displayRound(r);
		}
	}

	int numRounds = 0;
	private void displayRound(Round r) 
	{
		say("Round " + ++numRounds + ": ");
		for (Turn t : r.getTurns())
		{
			say("\t" + t.getPlayerID() + ": " + decisionToString(t.getDecision()));
		}
		say("Result: " + r.getResult());
	}

	private void say(String string) 
	{
		if (verbose)
			System.out.println(string);
	}

	private String decisionToString(Decision decision) 
	{
		if (decision instanceof Challenge)
			return "Challenge";
		else
		{
			Bid bid = (Bid)decision;
			return "Bid " + bid.getFrequency() + " " + bid.getFaceValue() + "s";
		}
	}
}
