package wtf.sinn.poker.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class CardTest {
    @Nested
    class The_Card {
        @Test
        void should_build() {
            final var actual = new Card(CardSuit.CLUBS, CardValue.TWO);

            then(actual).isNotNull();
        }
    }
}
