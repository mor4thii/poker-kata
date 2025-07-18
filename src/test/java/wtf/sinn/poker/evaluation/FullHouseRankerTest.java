package wtf.sinn.poker.evaluation;

import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class FullHouseRankerTest {

    private final FullHouseRanker fullHouseRanker = new FullHouseRanker(null);

    @Test
    void should_detect_full_house() {
        final var hand = new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.THREE),
                new Card(CardSuit.CLUBS, CardValue.THREE)
        ));

        final var expected = new HandRank(Rank.FULL_HOUSE);

        final var actual = fullHouseRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }

}
