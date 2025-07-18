package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

public abstract class HandRanker {
    protected final HandRanker next;

    protected HandRanker(HandRanker next) {
        this.next = next;
    }

    public HandRank evaluate(Hand hand) {
        if (canHandle(hand)) {
            return buildHandRank(hand);
        }

        if (next != null) {
            return next.evaluate(hand);
        }

        return new HandRank(Rank.NONE);
    }

    protected abstract boolean canHandle(Hand hand);

    protected abstract HandRank buildHandRank(Hand hand);
}
