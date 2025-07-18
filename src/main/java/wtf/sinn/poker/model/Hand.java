package wtf.sinn.poker.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public record Hand(List<Card> cards) {
    public Hand {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("A hand must consist of 5 cards");
        }

        if (hasDuplicates(cards)) {
            throw new IllegalArgumentException("A hand must not have duplicates");
        }
    }

    public Map<CardValue, Long> getCardsGroupedByValue() {
        return this.cards.stream()
                .collect(Collectors.groupingBy(Card::cardValue,
                        Collectors.collectingAndThen(
                                Collectors.mapping(Card::cardSuit, Collectors.toSet()),
                                set -> (long) set.size()
                        )));
    }

    public boolean hasOnlyCardsOfSameSuit() {
        final var suitAtHand = this.cards.getFirst().cardSuit();

        return this.cards.stream().allMatch(it -> it.cardSuit().equals(suitAtHand));
    }

    public boolean isUniqueAndSequential() {
        final var sortedValues = this.cards.stream()
                .map(it -> it.cardValue().getValue())
                .sorted()
                .toList();

        int min = sortedValues.getFirst();
        int max = sortedValues.getLast();

        boolean allUnique = !hasDuplicates(this.cards);
        boolean isSequential = (max - min + 1) == sortedValues.size();

        return allUnique && isSequential;
    }

    private static boolean hasDuplicates(List<Card> cards) {
        return cards.stream().distinct().count() != cards.size();
    }
}
