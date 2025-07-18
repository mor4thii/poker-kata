package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

public class FullHouseRanker extends HandRanker {
    protected FullHouseRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return isFullHouse(hand);
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.FULL_HOUSE);
    }

    private boolean isFullHouse(Hand hand) {
        final var cardsGroupedByValue = hand.getCardCountByValue();

        return cardsGroupedByValue.containsValue(3L) && cardsGroupedByValue.containsValue(2L);
    }
}
