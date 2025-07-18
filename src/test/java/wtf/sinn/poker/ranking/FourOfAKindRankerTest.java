package wtf.sinn.poker.ranking;

import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class FourOfAKindRankerTest {

    private final FourOfAKindRanker fourOfAKindRanker = new FourOfAKindRanker(null);

    @Test
    void should_detect_four_of_a_kind() {
        final var hand = new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.DIAMONDS, CardValue.TWO),
                new Card(CardSuit.HEARTS, CardValue.THREE)
        ));

        final var expected = new HandRank(Rank.FOUR_OF_A_KIND, List.of(CardValue.TWO));

        final var actual = fourOfAKindRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }
}
