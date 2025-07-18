package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;

// TODO Improve code here. This is not meaningfully testable, and might even be unnecessary
public class HandEvaluation {
    public HandRank evaluate(Hand hand) {
        final var fullHouseRanker = new FullHouseRanker(null);
        final var fourOfAKindRanker = new FourOfAKindRanker(fullHouseRanker);
        final var straightFlushRanker = new StraightFlushRanker(fourOfAKindRanker);

        return straightFlushRanker.evaluate(hand);
    }
}
