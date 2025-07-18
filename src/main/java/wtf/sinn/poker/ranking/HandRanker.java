package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;

abstract sealed class HandRanker
        permits FlushRanker, FourOfAKindRanker, FullHouseRanker, HighCardRanker, PairRanker, StraightFlushRanker, StraightRanker, ThreeOfAKindRanker, TwoPairsRanker {
    protected final HandRanker next;

    protected HandRanker(HandRanker next) {
        this.next = next;
    }

    public HandRank evaluate(Hand hand) {
        if (canHandle(hand)) {
            return buildHandRank(hand);
        }

        if (next != null) {
            return next.evaluate(hand);
        }

        return new HandRank(Rank.NONE, List.of());
    }

    protected abstract boolean canHandle(Hand hand);

    protected abstract HandRank buildHandRank(Hand hand);
}
