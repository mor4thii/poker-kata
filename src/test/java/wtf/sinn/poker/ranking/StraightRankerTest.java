package wtf.sinn.poker.ranking;

import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class StraightRankerTest {

    private final StraightRanker fourOfAKindRanker = new StraightRanker(null);

    @Test
    void should_detect_straight() {
        final var hand = new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.THREE),
                new Card(CardSuit.SPADES, CardValue.FOUR),
                new Card(CardSuit.DIAMONDS, CardValue.FIVE),
                new Card(CardSuit.HEARTS, CardValue.SIX)
        ));

        final var expected = new HandRank(Rank.STRAIGHT, List.of(CardValue.SIX));

        final var actual = fourOfAKindRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }
}
