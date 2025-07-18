package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

final class FlushRanker extends HandRanker {
    @Override
    protected boolean canHandle(Hand hand) {
        return hand.hasOnlyCardsOfSameSuit();
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.FLUSH, hand.cardValuesInDescendingOrder());
    }
}
