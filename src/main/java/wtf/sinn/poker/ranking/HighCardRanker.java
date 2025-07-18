package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

final class HighCardRanker extends HandRanker {
    HighCardRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return true;
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.HIGH_CARD, hand.cardValuesInDescendingOrder());
    }
}
