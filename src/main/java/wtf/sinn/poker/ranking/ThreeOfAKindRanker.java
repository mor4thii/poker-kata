package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

final class ThreeOfAKindRanker extends HandRanker {
    @Override
    protected boolean canHandle(Hand hand) {
        return hand.getCardCountPerValue().containsKey(3L);
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        return new HandRank(Rank.THREE_OF_A_KIND, hand.getCardCountPerValue().get(3L));
    }
}
