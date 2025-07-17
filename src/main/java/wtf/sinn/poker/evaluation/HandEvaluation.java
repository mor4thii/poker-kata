package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Card;
import wtf.sinn.poker.model.HandRank;

import java.util.List;

public class HandEvaluation {
    public HandRank evaluate(List<Card> hand) {
        final var fullHouseRanker = new FullHouseRanker(null);
        final var fourOfAKindRanker = new FourOfAKindRanker(fullHouseRanker);
        final var straightFlushRanker = new StraightFlushRanker(fourOfAKindRanker);

        return straightFlushRanker.evaluate(hand);
    }
}
