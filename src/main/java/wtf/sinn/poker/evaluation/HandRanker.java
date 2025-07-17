package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Card;
import wtf.sinn.poker.model.CardValue;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class HandRanker {
    protected final HandRanker next;

    protected HandRanker(HandRanker next) {
        this.next = next;
    }

    public HandRank evaluate(List<Card> hand) {
        if (canHandle(hand)) {
            return buildHandRank(hand);
        }

        if (next != null) {
            return next.evaluate(hand);
        }

        return new HandRank(Rank.NONE);
    }

    protected static Map<CardValue, Long> getCardsGroupedByValue(List<Card> hand) {
        return hand.stream()
                .collect(Collectors.groupingBy(Card::cardValue,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Card::cardSuit, Collectors.toSet()),
                                set -> (long) set.size()
                        )));
    }

    protected abstract boolean canHandle(List<Card> hand);

    protected abstract HandRank buildHandRank(List<Card> hand);
}
