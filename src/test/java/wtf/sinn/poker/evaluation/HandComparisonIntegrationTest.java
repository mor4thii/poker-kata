package wtf.sinn.poker.evaluation;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.Card;
import wtf.sinn.poker.model.CardSuit;
import wtf.sinn.poker.model.CardValue;
import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.ranking.HandEvaluation;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

public class HandComparisonIntegrationTest {

    private final HandEvaluation handEvaluation = new HandEvaluation();
    private final HandComparison handComparison = new HandComparison(handEvaluation);

    @Nested
    class The_HandComparison {
        @Test
        void should_detect_straight_flushes_as_winner_hand() {
            final var straightFlushHand = lowestStraightFlushHand();
            final var kingHighCardHand = kingHighCardHand();

            final var actual = handComparison.determineWinner(straightFlushHand, kingHighCardHand);

            then(actual).hasValue(straightFlushHand);
        }

        @Test
        void should_detect_the_higher_of_two_straight_flushes_as_winner_hand() {
            final var straightFlushHand = lowestStraightFlushHand();
            final var highestStraightFlushHand = highestStraightFlushHand();

            final var actual = handComparison.determineWinner(straightFlushHand, highestStraightFlushHand);

            then(actual).hasValue(highestStraightFlushHand);
        }

        @Test
        void should_detect_a_draw_for_two_straight_flushes_with_same_high_card() {
            // This is technically not possible, but suits do not contribute to scoring, so this is fine
            final var straightFlushHand = lowestStraightFlushHand();
            final var otherStraightFlushHand = lowestStraightFlushHand();

            final var actual = handComparison.determineWinner(straightFlushHand, otherStraightFlushHand);

            then(actual).isEmpty();
        }
    }

    private Hand lowestStraightFlushHand() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.THREE),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.FIVE),
                new Card(CardSuit.HEARTS, CardValue.SIX)
        ));
    }

    private Hand highestStraightFlushHand() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TEN),
                new Card(CardSuit.HEARTS, CardValue.JACK),
                new Card(CardSuit.HEARTS, CardValue.QUEEN),
                new Card(CardSuit.HEARTS, CardValue.KING),
                new Card(CardSuit.HEARTS, CardValue.ACE)
        ));
    }

    private Hand kingHighCardHand() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.SIX),
                new Card(CardSuit.DIAMONDS, CardValue.KING),
                new Card(CardSuit.CLUBS, CardValue.TEN)
        ));
    }
}
