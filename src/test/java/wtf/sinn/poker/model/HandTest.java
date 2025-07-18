package wtf.sinn.poker.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class HandTest {
    @Nested
    class The_Hand {
        @Test
        void should_be_successfully_created() {
            assertThatCode(() -> new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)
            ))).doesNotThrowAnyException();
        }
    }
}
