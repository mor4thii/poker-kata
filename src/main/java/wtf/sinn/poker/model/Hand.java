package wtf.sinn.poker.model;

import java.util.Comparator;
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

    public List<CardValue> cardValuesInDescendingOrder() {
        return cards.stream().map(Card::cardValue).sorted(Comparator.reverseOrder()).toList();
    }

    public CardValue highestCardValue() {
        return cards.stream()
                .map(Card::cardValue)
                .max(Comparator.comparingInt(CardValue::getValue))
                .orElseThrow();
    }

    public Map<Long, List<CardValue>> getCardCountPerValue() {
        final var counts = this.cards.stream()
                .collect(Collectors.groupingBy(
                        Card::cardValue,
                        Collectors.counting())
                );

        return counts.entrySet().stream()
                .collect(Collectors.groupingBy(
                        Map.Entry::getValue,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                ));
    }

    public boolean hasOnlyCardsOfSameSuit() {
        final var suitAtHand = this.cards.getFirst().cardSuit();

        return this.cards.stream().allMatch(it -> it.cardSuit().equals(suitAtHand));
    }

    public boolean hasSequentialCards() {
        final var sortedValues = this.cards.stream()
                .map(it -> it.cardValue().getValue())
                .sorted()
                .toList();

        int min = sortedValues.getFirst();
        int max = sortedValues.getLast();

        return (max - min + 1) == sortedValues.size();
    }

    private static boolean hasDuplicates(List<Card> cards) {
        return cards.stream().distinct().count() != cards.size();
    }
}
