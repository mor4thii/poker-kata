package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Card;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;

public class FullHouseRanker extends HandRanker {
    protected FullHouseRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(List<Card> hand) {
        return isFullHouse(hand);
    }

    @Override
    protected HandRank buildHandRank(List<Card> hand) {
        return new HandRank(Rank.FULL_HOUSE);
    }

    private boolean isFullHouse(List<Card> hand) {
        final var cardsGroupedByValue = getCardsGroupedByValue(hand);

        return cardsGroupedByValue.containsValue(3L) && cardsGroupedByValue.containsValue(2L);
    }
}
