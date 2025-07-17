package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Card;

import java.util.List;
import java.util.stream.Collectors;

public class HandEvaluation {
    // TODO Change return type to something actually representing an evaluation result
    public boolean evaluate(List<Card> hand) {
        return isStraightFlush(hand) || isFourOfAKind(hand);
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

    private static boolean isFourOfAKind(List<Card> hand) {
        final var cardsGroupedByValue = hand.stream()
                .collect(Collectors.groupingBy(Card::cardValue,
                        Collectors.counting()));

        return cardsGroupedByValue.containsValue(4L);
    }
}
