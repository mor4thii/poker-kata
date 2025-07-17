package wtf.sinn.poker.evaluation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.Card;
import wtf.sinn.poker.model.CardSuit;
import wtf.sinn.poker.model.CardValue;

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
        @Test
        void should_detect_a_hearts_straight_flush() {
            final var hand = List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)
            );

            final var actual = handEvaluation.evaluate(hand);

            then(actual).isTrue();
        }

        @Test
        void should_detect__that_hand_is_not_a_hearts_straight_flush() {
            final var hand = List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SEVEN)
            );

            final var actual = handEvaluation.evaluate(hand);

            then(actual).isFalse();
        }
    }
}
