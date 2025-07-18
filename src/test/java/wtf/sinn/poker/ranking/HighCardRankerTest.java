package wtf.sinn.poker.ranking;

import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class HighCardRankerTest {

    private final HighCardRanker highCardRanker = new HighCardRanker(null);

    @Test
    void should_detect_high_card() {
        final var hand = new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.THREE)
        ));

        final var expected = new HandRank(Rank.HIGH_CARD, List.of(CardValue.THREE, CardValue.TWO));

        final var actual = highCardRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }
}
