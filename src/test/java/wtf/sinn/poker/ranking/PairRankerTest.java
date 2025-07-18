package wtf.sinn.poker.ranking;

import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class PairRankerTest {

    private final PairRanker pairRanker = new PairRanker(null);

    @Test
    void should_detect_pair() {
        final var hand = new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.SPADES, CardValue.THREE),
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.FIVE)
        ));

        final var expected = new HandRank(Rank.PAIR, List.of(CardValue.TWO, CardValue.FIVE, CardValue.FOUR, CardValue.THREE));

        final var actual = pairRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }
}
