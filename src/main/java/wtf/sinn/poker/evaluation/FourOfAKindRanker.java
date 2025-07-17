package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Card;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;

public class FourOfAKindRanker extends HandRanker {
    protected FourOfAKindRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(List<Card> hand) {
        return isFourOfAKind(hand);
    }

    @Override
    protected HandRank buildHandRank(List<Card> hand) {
        return new HandRank(Rank.FOUR_OF_A_KIND);
    }

    private static boolean isFourOfAKind(List<Card> hand) {
        final var cardsGroupedByValue = getCardsGroupedByValue(hand);

        return cardsGroupedByValue.containsValue(4L);
    }
}
