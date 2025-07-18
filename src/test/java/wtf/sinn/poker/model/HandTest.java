package wtf.sinn.poker.model;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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

        @Test
        void should_detect_that_hand_has_less_than_5_cards() {
            assertThatThrownBy(() -> new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE)
            ))).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void should_detect_that_hand_has_more_than_5_cards() {
            assertThatThrownBy(() -> new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.THREE),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX),
                    new Card(CardSuit.HEARTS, CardValue.SEVEN)
            ))).isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        void should_detect_duplicates() {
            assertThatThrownBy(() -> new Hand(List.of(
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.TWO),
                    new Card(CardSuit.HEARTS, CardValue.FOUR),
                    new Card(CardSuit.HEARTS, CardValue.FIVE),
                    new Card(CardSuit.HEARTS, CardValue.SIX)
            ))).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
