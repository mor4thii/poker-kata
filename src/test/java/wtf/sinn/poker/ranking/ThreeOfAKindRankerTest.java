package wtf.sinn.poker.ranking;

import org.junit.jupiter.api.Test;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class ThreeOfAKindRankerTest {

    private final ThreeOfAKindRanker threeOfAKindRanker = new ThreeOfAKindRanker(null);

    @Test
    void should_detect_three_of_a_kind() {
        final var hand = new Hand(List.of(
                new Card(CardSuit.HEARTS, CardValue.TWO),
                new Card(CardSuit.CLUBS, CardValue.TWO),
                new Card(CardSuit.SPADES, CardValue.TWO),
                new Card(CardSuit.DIAMONDS, CardValue.FOUR),
                new Card(CardSuit.HEARTS, CardValue.THREE)
        ));

        final var expected = new HandRank(Rank.THREE_OF_A_KIND, List.of(CardValue.TWO));

        final var actual = threeOfAKindRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }
}
