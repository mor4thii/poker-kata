package wtf.sinn.poker.ranking;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import wtf.sinn.poker.model.*;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

class StraightFlushRankerTest {

    private final StraightFlushRanker straightFlushRanker = new StraightFlushRanker(null);

    @ParameterizedTest
    @EnumSource(CardSuit.class)
    void should_detect_a_hearts_straight_flush(CardSuit cardSuit) {
        final var hand = new Hand(List.of(
                new Card(cardSuit, CardValue.TWO),
                new Card(cardSuit, CardValue.THREE),
                new Card(cardSuit, CardValue.FOUR),
                new Card(cardSuit, CardValue.FIVE),
                new Card(cardSuit, CardValue.SIX)
        ));

        final var expected = new HandRank(Rank.STRAIGHT_FLUSH, List.of(CardValue.SIX));

        final var actual = straightFlushRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @EnumSource(CardSuit.class)
    void should_detect_that_hand_is_not_a_hearts_straight_flush(CardSuit cardSuit) {
        final var hand = new Hand(List.of(
                new Card(cardSuit, CardValue.TWO),
                new Card(cardSuit, CardValue.THREE),
                new Card(cardSuit, CardValue.FOUR),
                new Card(cardSuit, CardValue.FIVE),
                new Card(cardSuit, CardValue.SEVEN)
        ));

        final var expected = new HandRank(Rank.NONE, List.of());

        final var actual = straightFlushRanker.evaluate(hand);

        then(actual).isEqualTo(expected);
    }

}
