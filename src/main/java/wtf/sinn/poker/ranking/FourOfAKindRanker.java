package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

final class FourOfAKindRanker extends HandRanker {
    FourOfAKindRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return isFourOfAKind(hand);
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.FOUR_OF_A_KIND);
    }

    private static boolean isFourOfAKind(Hand hand) {
        final var cardsGroupedByValue = hand.getCardCountByValue();

        return cardsGroupedByValue.containsValue(4L);
    }
}
