package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Card;

import java.util.List;

public class HandEvaluation {
    // TODO Change return type to something actually representing an evaluation result
    public boolean evaluate(List<Card> hand) {
        final var suitAtHand = hand.getFirst().cardSuit();

        final var sortedValues = hand.stream()
                .map(it -> it.cardValue().getValue())
                .sorted()
                .toList();

        int min = sortedValues.getFirst();
        int max = sortedValues.getLast();

        // Check for uniqueness and strict consecutiveness
        boolean allUnique = sortedValues.stream().distinct().count() == sortedValues.size();
        boolean isSequential = (max - min + 1) == sortedValues.size();

        return allUnique && isSequential && hand.stream().allMatch(it -> it.cardSuit().equals(suitAtHand));
    }
}
