package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;

final class PairRanker extends HandRanker {
    PairRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return hand.getCardCountPerValue().containsKey(2L);
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.PAIR, List.of());
    }
}
