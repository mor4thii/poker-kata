package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;

final class StraightRanker extends HandRanker {
    @Override
    protected boolean canHandle(Hand hand) {
        return hand.hasSequentialCards();
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.STRAIGHT, List.of(hand.highestCardValue()));
    }
}
