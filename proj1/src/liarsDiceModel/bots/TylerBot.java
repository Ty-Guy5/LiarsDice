package liarsDiceModel.bots;
import liarsDiceModel.*;

/**
 * Tyler's bot so far.
 */
public class TylerBot extends LiarsDiceBot {

	public String getName() {
		return "TylerColemanBot";
	}

	public Decision getDecision(GameInfo gi) {
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

}
