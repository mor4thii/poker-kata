package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

final class FullHouseRanker extends HandRanker {
    FullHouseRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return isFullHouse(hand);
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.FULL_HOUSE, hand.getCardCountPerValue().get(3L));
    }

    private boolean isFullHouse(Hand hand) {
        final var cardsGroupedByValue = hand.getCardCountPerValue();

        return cardsGroupedByValue.containsKey(3L) && cardsGroupedByValue.containsKey(2L);
    }
}
