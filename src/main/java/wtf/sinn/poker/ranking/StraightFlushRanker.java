package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;

final class StraightFlushRanker extends HandRanker {
    @Override
    protected boolean canHandle(Hand hand) {
        return isStraightFlush(hand);
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.STRAIGHT_FLUSH, List.of(hand.highestCardValue()));
    }

    private boolean isStraightFlush(Hand hand) {
        return hand.hasOnlyCardsOfSameSuit() && hand.hasSequentialCards();
    }
}
