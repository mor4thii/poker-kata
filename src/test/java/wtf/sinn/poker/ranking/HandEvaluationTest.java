package wtf.sinn.poker.ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

class HandEvaluationTest {

    private HandEvaluation handEvaluation;

    @BeforeEach
    void setUp() {
        handEvaluation = new HandEvaluation();
    }

    @Nested
    class The_HandEvaluation {
        @Test
        void should_return_the_hand_evaluation() {
            then(handEvaluation).isNotNull();
        }
    }
}
