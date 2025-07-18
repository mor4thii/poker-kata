package wtf.sinn.poker.ranking;

import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class FlushRankerTest {

    private final FlushRanker flushRanker = new FlushRanker(null);

    @Test
    void should_detect_flush() {
        final var hand = new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.KING),
                new Card(CardSuit.HEARTS, CardValue.ACE),
                new Card(CardSuit.HEARTS, CardValue.SIX)
        ));

        final var expected = new HandRank(Rank.FLUSH);

        final var actual = flushRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }
}
