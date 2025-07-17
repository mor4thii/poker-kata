package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Card;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;

public class StraightFlushRanker extends HandRanker {

    protected StraightFlushRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(List<Card> hand) {
        return isStraightFlush(hand);
    }

    @Override
    protected HandRank buildHandRank(List<Card> hand) {
        return new HandRank(Rank.STRAIGHT_FLUSH);
    }

    private boolean isStraightFlush(List<Card> hand) {
        return hasOnlyCardsOfSameSuit(hand) && isUniqueAndSequential(hand);
    }

    private static boolean hasOnlyCardsOfSameSuit(List<Card> hand) {
        final var suitAtHand = hand.getFirst().cardSuit();

        return hand.stream().allMatch(it -> it.cardSuit().equals(suitAtHand));
    }

    private boolean isUniqueAndSequential(List<Card> hand) {
        final var sortedValues = hand.stream()
                .map(it -> it.cardValue().getValue())
                .sorted()
                .toList();

        int min = sortedValues.getFirst();
        int max = sortedValues.getLast();

        boolean allUnique = sortedValues.stream().distinct().count() == sortedValues.size();
        boolean isSequential = (max - min + 1) == sortedValues.size();

        return allUnique && isSequential;
    }
}
