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
        void should_detect_straight_flush_as_winner_over_four_of_a_kind() {
            final var straightFlush = lowestStraightFlushHand();
            final var fourOfAKind = fourOfAKindHand();

            final var actual = handComparison.determineWinner(straightFlush, fourOfAKind);

            then(actual).hasValue(straightFlush);
        }

        @Test
        void should_detect_four_of_a_kind_as_winner_over_full_house() {
            final var fourOfAKind = fourOfAKindHand();
            final var fullHouse = fullHouseHand();

            final var actual = handComparison.determineWinner(fourOfAKind, fullHouse);

            then(actual).hasValue(fourOfAKind);
        }

        @Test
        void should_detect_full_house_as_winner_over_flush() {
            final var fullHouse = fullHouseHand();
            final var flush = flushHand();

            final var actual = handComparison.determineWinner(fullHouse, flush);

            then(actual).hasValue(fullHouse);
        }

        @Test
        void should_detect_flush_as_winner_over_straight() {
            final var flush = flushHand();
            final var straight = straightHand();

            final var actual = handComparison.determineWinner(flush, straight);

            then(actual).hasValue(flush);
        }

        @Test
        void should_detect_straight_as_winner_over_three_of_a_kind() {
            final var straight = straightHand();
            final var threeOfAKind = threeOfAKindHand();

            final var actual = handComparison.determineWinner(straight, threeOfAKind);

            then(actual).hasValue(straight);
        }

        @Test
        void should_detect_three_of_a_kind_as_winner_over_two_pairs() {
            final var threeOfAKind = threeOfAKindHand();
            final var twoPairs = twoPairsHand();

            final var actual = handComparison.determineWinner(threeOfAKind, twoPairs);

            then(actual).hasValue(threeOfAKind);
        }

        @Test
        void should_detect_two_pairs_as_winner_over_pair() {
            final var twoPairs = twoPairsHand();
            final var pair = pairHand();

            final var actual = handComparison.determineWinner(twoPairs, pair);

            then(actual).hasValue(twoPairs);
        }

        @Test
        void should_detect_pair_as_winner_over_high_card() {
            final var pair = pairHand();
            final var highCard = kingHighCardHand();

            final var actual = handComparison.determineWinner(pair, highCard);

            then(actual).hasValue(pair);
        }

        @Test
        void should_detect_draw_for_same_high_card_hand() {
            final var hand1 = kingHighCardHand();
            final var hand2 = kingHighCardHand();

            final var actual = handComparison.determineWinner(hand1, hand2);

            then(actual).isEmpty();
        }

        @Test
        void should_detect_a_draw_for_two_straight_flushes_with_same_high_card() {
            // This is technically not possible, but suits do not contribute to scoring, so this is fine
            final var straightFlushHand = lowestStraightFlushHand();
            final var otherStraightFlushHand = lowestStraightFlushHand();

            final var actual = handComparison.determineWinner(straightFlushHand, otherStraightFlushHand);

            then(actual).isEmpty();
        }

        @Test
        void should_resolve_tiebreaker_on_straight_flush_by_highest_card() {
            final var straightFlushHand = lowestStraightFlushHand();
            final var highestStraightFlushHand = highestStraightFlushHand();

            final var actual = handComparison.determineWinner(straightFlushHand, highestStraightFlushHand);

            then(actual).hasValue(highestStraightFlushHand);
        }

        @Test
        void should_resolve_tiebreaker_on_same_pair_using_kicker() {
            final var higherKicker = pairWithAceKicker();
            final var lowerKicker = pairWithKingKicker();

            final var actual = handComparison.determineWinner(higherKicker, lowerKicker);

            then(actual).hasValue(higherKicker);
        }

        @Test
        void should_resolve_two_pairs_tiebreaker_by_higher_pair_first() {
            final var higherTwoPair = twoPairsQueensAndEights();
            final var lowerTwoPair = twoPairsJacksAndNines();

            final var actual = handComparison.determineWinner(higherTwoPair, lowerTwoPair);

            then(actual).hasValue(higherTwoPair);
        }

        @Test
        void should_resolve_full_house_by_three_of_a_kind_value() {
            final var higherTrips = fullHouseKingsOverThrees();
            final var lowerTrips = fullHouseJacksOverAces();

            final var actual = handComparison.determineWinner(higherTrips, lowerTrips);

            then(actual).hasValue(higherTrips);
        }

        @Test
        void should_resolve_flush_by_high_card_order() {
            final var highFlush = flushAceHigh();
            final var lowFlush = flushKingHigh();

            final var actual = handComparison.determineWinner(highFlush, lowFlush);

            then(actual).hasValue(highFlush);
        }

        @Test
        void should_resolve_high_card_by_kickers_in_order() {
            final var aceHigh = aceHighHand();
            final var kingHigh = kingHighCardHand();

            final var actual = handComparison.determineWinner(aceHigh, kingHigh);

            then(actual).hasValue(aceHigh);
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

    private Hand fourOfAKindHand() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.SEVEN),
                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                new Card(CardSuit.DIAMONDS, CardValue.SEVEN),
                new Card(CardSuit.SPADES, CardValue.SEVEN),
                new Card(CardSuit.HEARTS, CardValue.THREE)
        ));
    }

    private Hand fullHouseHand() {
        return fullHouseKingsOverThrees();
    }

    private Hand fullHouseKingsOverThrees() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.KING),
                new Card(CardSuit.CLUBS, CardValue.KING),
                new Card(CardSuit.DIAMONDS, CardValue.KING),
                new Card(CardSuit.SPADES, CardValue.THREE),
                new Card(CardSuit.HEARTS, CardValue.THREE)
        ));
    }

    private Hand fullHouseJacksOverAces() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.DIAMONDS, CardValue.JACK),
                new Card(CardSuit.SPADES, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.ACE)
        ));
    }

    private Hand flushHand() {
        return flushKingHigh();
    }

    private Hand flushAceHigh() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.TEN),
                new Card(CardSuit.HEARTS, CardValue.EIGHT),
                new Card(CardSuit.HEARTS, CardValue.FIVE),
                new Card(CardSuit.HEARTS, CardValue.THREE)
        ));
    }

    private Hand flushKingHigh() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.KING),
                new Card(CardSuit.HEARTS, CardValue.NINE),
                new Card(CardSuit.HEARTS, CardValue.SEVEN),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.TWO)
        ));
    }

    private Hand straightHand() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.DIAMONDS, CardValue.FIVE),
                new Card(CardSuit.SPADES, CardValue.SIX),
                new Card(CardSuit.CLUBS, CardValue.SEVEN),
                new Card(CardSuit.HEARTS, CardValue.EIGHT)
        ));
    }

    private Hand threeOfAKindHand() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.DIAMONDS, CardValue.JACK),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.THREE)
        ));
    }

    private Hand twoPairsHand() {
        return twoPairsQueensAndEights();
    }

    private Hand twoPairsQueensAndEights() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.QUEEN),
                new Card(CardSuit.CLUBS, CardValue.QUEEN),
                new Card(CardSuit.DIAMONDS, CardValue.EIGHT),
                new Card(CardSuit.SPADES, CardValue.EIGHT),
                new Card(CardSuit.HEARTS, CardValue.THREE)
        ));
    }

    private Hand twoPairsJacksAndNines() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.JACK),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.DIAMONDS, CardValue.NINE),
                new Card(CardSuit.SPADES, CardValue.NINE),
                new Card(CardSuit.HEARTS, CardValue.ACE)
        ));
    }

    private Hand pairHand() {
        return pairWithKingKicker();
    }

    private Hand pairWithAceKicker() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TEN),
                new Card(CardSuit.CLUBS, CardValue.TEN),
                new Card(CardSuit.DIAMONDS, CardValue.ACE),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.TWO)
        ));
    }

    private Hand pairWithKingKicker() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TEN),
                new Card(CardSuit.CLUBS, CardValue.TEN),
                new Card(CardSuit.DIAMONDS, CardValue.KING),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.TWO)
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

    private Hand aceHighHand() {
        return new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.ACE),
                new Card(CardSuit.CLUBS, CardValue.JACK),
                new Card(CardSuit.SPADES, CardValue.NINE),
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.TWO)
        ));
    }
}
