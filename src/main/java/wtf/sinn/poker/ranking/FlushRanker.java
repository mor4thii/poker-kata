package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Card;
import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.Comparator;

final class FlushRanker extends HandRanker {
    FlushRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return hand.hasOnlyCardsOfSameSuit();
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        final var cardValuesIsDescendingOrder = hand.cards().stream().map(Card::cardValue).sorted(Comparator.reverseOrder()).toList();
        return new HandRank(Rank.FLUSH, cardValuesIsDescendingOrder);
    }
}
