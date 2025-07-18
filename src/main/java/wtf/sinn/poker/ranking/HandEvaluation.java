package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;

public final class HandEvaluation {
    final HandRanker rankingChain = new HandRankerBuilder()
            .with(new HighCardRanker())
            .with(new PairRanker())
            .with(new TwoPairsRanker())
            .with(new ThreeOfAKindRanker())
            .with(new StraightRanker())
            .with(new FlushRanker())
            .with(new FullHouseRanker())
            .with(new FourOfAKindRanker())
            .with(new StraightFlushRanker())
            .build();

    public HandRank evaluate(Hand hand) {
        return rankingChain.evaluate(hand);
    }

    static class HandRankerBuilder {
        private HandRanker current;

        HandRankerBuilder with(HandRanker next) {
            if (current != null) {
                next.setNext(current);
            }
            current = next;
            return this;
        }

        public HandRanker build() {
            return current;
        }
    }
}
