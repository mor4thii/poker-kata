package wtf.sinn.poker.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

public class HandTest {
    @Nested
    class The_Hand {
        @Test
        void should_be_successfully_created() {
            assertThatCode(() -> new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)
            ))).doesNotThrowAnyException();
        }

        @Test
        void should_detect_that_hand_has_less_than_5_cards() {
            assertThatThrownBy(() -> new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE)
            ))).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void should_detect_that_hand_has_more_than_5_cards() {
            assertThatThrownBy(() -> new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX),
                    new Card(CardSuit.HEARTS, CardValue.SEVEN)
            ))).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void should_detect_duplicates() {
            assertThatThrownBy(() -> new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)
            ))).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void should_group_cards_by_value() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.getCardCountPerValue();

            then(actual).hasSize(1);
            then(actual.get(1L)).containsExactlyInAnyOrderElementsOf(List.of(CardValue.TWO, CardValue.THREE, CardValue.FOUR, CardValue.FIVE, CardValue.SIX));
        }

        @Test
        void should_group_cards_by_value_with_a_value_occuring_two_times() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.CLUBS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.getCardCountPerValue();

            then(actual).hasSize(2);
            then(actual.get(1L)).containsExactlyInAnyOrderElementsOf(List.of(CardValue.FOUR, CardValue.FIVE, CardValue.SIX));
            then(actual.get(2L)).containsExactlyInAnyOrderElementsOf(List.of(CardValue.TWO));
        }

        @Test
        void should_group_cards_by_value_with_a_value_occuring_three_times() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.CLUBS, CardValue.TWO),
                    new Card(CardSuit.SPADES, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.getCardCountPerValue();

            then(actual).hasSize(2);
            then(actual.get(1L)).containsExactlyInAnyOrderElementsOf(List.of(CardValue.FIVE, CardValue.SIX));
            then(actual.get(3L)).containsExactlyInAnyOrderElementsOf(List.of(CardValue.TWO));
        }

        @Test
        void should_group_cards_by_value_with_a_value_occuring_four_times() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.CLUBS, CardValue.TWO),
                    new Card(CardSuit.SPADES, CardValue.TWO),
                    new Card(CardSuit.DIAMONDS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.getCardCountPerValue();

            then(actual).hasSize(2);
            then(actual.get(1L)).containsExactlyInAnyOrderElementsOf(List.of(CardValue.SIX));
            then(actual.get(4L)).containsExactlyInAnyOrderElementsOf(List.of(CardValue.TWO));
        }

        @Test
        void should_detect_hands_with_cards_of_the_same_suit() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.hasOnlyCardsOfSameSuit();

            then(actual).isTrue();
        }

        @Test
        void should_detect_hands_with_cards_of_different_suit() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.DIAMONDS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.hasOnlyCardsOfSameSuit();

            then(actual).isFalse();
        }

        @Test
        void should_detect_hands_that_are_sequential_in_values() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.DIAMONDS, CardValue.THREE),
                    new Card(CardSuit.SPADES, CardValue.FOUR),
                    new Card(CardSuit.CLUBS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.hasSequentialCards();

            then(actual).isTrue();
        }

        @Test
        void should_detect_hands_that_are_not_sequential_in_values() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.DIAMONDS, CardValue.THREE),
                    new Card(CardSuit.SPADES, CardValue.FOUR),
                    new Card(CardSuit.CLUBS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SEVEN)));

            final var actual = hand.hasSequentialCards();

            then(actual).isFalse();
        }

        @Test
        void should_sort_card_values_in_descending_order() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.DIAMONDS, CardValue.THREE),
                    new Card(CardSuit.SPADES, CardValue.FOUR),
                    new Card(CardSuit.CLUBS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SEVEN)));

            final var actual = hand.cardValuesInDescendingOrder();

            then(actual).containsExactly(CardValue.SEVEN, CardValue.FIVE, CardValue.FOUR, CardValue.THREE, CardValue.TWO);
        }
    }
}
