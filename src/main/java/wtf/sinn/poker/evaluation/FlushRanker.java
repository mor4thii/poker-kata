package wtf.sinn.poker.evaluation;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;

public class FlushRanker extends HandRanker {
    protected FlushRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return false;
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return null;
    }
}
