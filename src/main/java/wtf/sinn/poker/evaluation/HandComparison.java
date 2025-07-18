package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.ranking.HandEvaluation;

public class HandComparison {
    private final HandEvaluation handEvaluation;

    public HandComparison(HandEvaluation handEvaluation) {
        this.handEvaluation = handEvaluation;
    }

    public Hand determineWinner(Hand hand1, Hand hand2) {
        return null;
    }
}
