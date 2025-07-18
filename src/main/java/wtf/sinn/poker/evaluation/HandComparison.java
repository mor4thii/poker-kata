package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.ranking.HandEvaluation;

import java.util.Optional;

public class HandComparison {
    private final HandEvaluation handEvaluation;

    public HandComparison(HandEvaluation handEvaluation) {
        this.handEvaluation = handEvaluation;
    }

    public Optional<Hand> determineWinner(Hand hand1, Hand hand2) {
        final var handRank1 = handEvaluation.evaluate(hand1);
        final var handRank2 = handEvaluation.evaluate(hand2);

        int comparison = handRank1.compareTo(handRank2);

        if (comparison > 0) return Optional.of(hand1);
        if (comparison < 0) return Optional.of(hand2);

        return Optional.empty();
    }
}
