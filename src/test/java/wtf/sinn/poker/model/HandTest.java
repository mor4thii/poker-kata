package wtf.sinn.poker.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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

            final var actual = hand.getCardCountByValue();

            then(actual).containsExactlyInAnyOrderEntriesOf(
                    Map.of(
                            CardValue.TWO, 1L,
                            CardValue.THREE, 1L,
                            CardValue.FOUR, 1L,
                            CardValue.FIVE, 1L,
                            CardValue.SIX, 1L
                    )
            );
        }

        @Test
        void should_group_cards_by_value_with_a_value_occuring_two_times() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.CLUBS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.getCardCountByValue();

            then(actual).containsExactlyInAnyOrderEntriesOf(
                    Map.of(
                            CardValue.TWO, 2L,
                            CardValue.FOUR, 1L,
                            CardValue.FIVE, 1L,
                            CardValue.SIX, 1L
                    )
            );
        }

        @Test
        void should_group_cards_by_value_with_a_value_occuring_three_times() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.CLUBS, CardValue.TWO),
                    new Card(CardSuit.SPADES, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.getCardCountByValue();

            then(actual).containsExactlyInAnyOrderEntriesOf(
                    Map.of(
                            CardValue.TWO, 3L,
                            CardValue.FIVE, 1L,
                            CardValue.SIX, 1L
                    )
            );
        }

        @Test
        void should_group_cards_by_value_with_a_value_occuring_four_times() {
            final var hand = new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.CLUBS, CardValue.TWO),
                    new Card(CardSuit.SPADES, CardValue.TWO),
                    new Card(CardSuit.DIAMONDS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.SIX)));

            final var actual = hand.getCardCountByValue();

            then(actual).containsExactlyInAnyOrderEntriesOf(
                    Map.of(
                            CardValue.TWO, 4L,
                            CardValue.SIX, 1L
                    )
            );
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
    }
}
