package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

public class TwoPairsRanker extends HandRanker {
    protected TwoPairsRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        final var cardCountByValue = hand.getCardCountByValue();

        return cardCountByValue.values().stream().filter(it -> Long.valueOf(2L).equals(it)).count() == 2;
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.TWO_PAIRS);
    }
}
