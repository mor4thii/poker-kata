package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.List;

final class FlushRanker extends HandRanker {
    FlushRanker(HandRanker next) {
        super(next);
    }

    @Override
    protected boolean canHandle(Hand hand) {
        return hand.hasOnlyCardsOfSameSuit();
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.FLUSH, List.of());
    }
}
