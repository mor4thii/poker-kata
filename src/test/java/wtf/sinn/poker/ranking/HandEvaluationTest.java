package wtf.sinn.poker.ranking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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

        @Test
        void allSubclassesOfHandRankerAreChained() {
            // The cast is fine as we know that RANKING_CHAIN classes are of type HandRanker, otherwise this test wouldn't make sense!
            //noinspection unchecked
            final Set<Class<? extends HandRanker>> expected = Arrays.stream(HandRanker.class.getPermittedSubclasses())
                    .map(cls -> (Class<? extends HandRanker>) cls)
                    .collect(Collectors.toSet());

            final Set<Class<? extends HandRanker>> actual = new HashSet<>();
            var current = HandEvaluation.RANKING_CHAIN;
            while (current != null) {
                actual.add(current.getClass());
                current = current.next;
            }

            then(actual).containsAll(expected);
        }
    }
}
