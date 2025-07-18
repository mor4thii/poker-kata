package wtf.sinn.poker.evaluation;

import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class TwoPairsRankerTest {

    private final TwoPairsRanker twoPairsRanker = new TwoPairsRanker(null);

    @Test
    void should_detect_two_pairs() {
        final var hand = new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.SPADES, CardValue.THREE),
                new Card(CardSuit.DIAMONDS, CardValue.THREE),
                new Card(CardSuit.HEARTS, CardValue.FOUR)
        ));

        final var expected = new HandRank(Rank.TWO_PAIRS);

        final var actual = twoPairsRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }
}
