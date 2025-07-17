package wtf.sinn.poker.evaluation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class HandEvaluationTest {

    private HandEvaluation handEvaluation;

    @BeforeEach
    void setUp() {
        handEvaluation = new HandEvaluation();
    }

    @Nested
    class The_HandEvaluation {
        @ParameterizedTest
        @EnumSource(CardSuit.class)
        void should_detect_a_hearts_straight_flush(CardSuit cardSuit) {
            final var hand = List.of(
                    new Card(cardSuit, CardValue.TWO),
                    new Card(cardSuit, CardValue.THREE),
                    new Card(cardSuit, CardValue.FOUR),
                    new Card(cardSuit, CardValue.FIVE),
                    new Card(cardSuit, CardValue.SIX)
            );

            final var expected = new HandRank(Rank.STRAIGHT_FLUSH);

            final var actual = handEvaluation.evaluate(hand);

            then(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @EnumSource(CardSuit.class)
        void should_detect_that_hand_is_not_a_hearts_straight_flush(CardSuit cardSuit) {
            final var hand = List.of(
                    new Card(cardSuit, CardValue.TWO),
                    new Card(cardSuit, CardValue.THREE),
                    new Card(cardSuit, CardValue.FOUR),
                    new Card(cardSuit, CardValue.FIVE),
                    new Card(cardSuit, CardValue.SEVEN)
            );

            final var actual = handEvaluation.evaluate(hand);

            then(actual).isNull();
        }

        @Test
        void should_detect_four_of_a_kind() {
            final var hand = List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.CLUBS, CardValue.TWO),
                    new Card(CardSuit.SPADES, CardValue.TWO),
                    new Card(CardSuit.DIAMONDS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE)
            );

            final var expected = new HandRank(Rank.FOUR_OF_A_KIND);

            final var actual = handEvaluation.evaluate(hand);

            then(actual).isEqualTo(expected);
        }

        @Test
        void should_detect_four_of_a_kind_cheaters() {
            final var hand = List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.SPADES, CardValue.TWO),
                    new Card(CardSuit.DIAMONDS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE)
            );

            final var actual = handEvaluation.evaluate(hand);

            then(actual).isNull();
        }
    }
}
