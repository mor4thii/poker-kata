package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Card;

import java.util.List;

public class HandEvaluation {
    // TODO Change return type to something actually representing an evaluation result
    public boolean evaluate(List<Card> hand) {
        final var suitAtHand = hand.getFirst().cardSuit();

        return hand.stream().allMatch(it -> it.cardSuit().equals(suitAtHand));
    }
}
