package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

public class StraightFlushRanker extends HandRanker {

    protected StraightFlushRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return isStraightFlush(hand);
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.STRAIGHT_FLUSH);
    }

    private boolean isStraightFlush(Hand hand) {
        return hand.hasOnlyCardsOfSameSuit() && hand.hasSequentialCards();
    }
}
