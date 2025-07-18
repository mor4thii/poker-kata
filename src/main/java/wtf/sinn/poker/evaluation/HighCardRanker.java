package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

public class HighCardRanker extends HandRanker {
    protected HighCardRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return true;
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.HIGH_CARD);
    }
}
