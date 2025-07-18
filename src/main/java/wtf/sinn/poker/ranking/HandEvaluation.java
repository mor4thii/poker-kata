package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;

public final class HandEvaluation {
    // TODO: Replace by builder
    static final HandRanker RANKING_CHAIN = new StraightFlushRanker(new FourOfAKindRanker(new FullHouseRanker(new FlushRanker(new StraightRanker(new ThreeOfAKindRanker(new TwoPairsRanker(new PairRanker(new HighCardRanker(null)))))))));

    public HandRank evaluate(Hand hand) {
        return RANKING_CHAIN.evaluate(hand);
    }
}
