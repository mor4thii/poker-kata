package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.Comparator;
import java.util.stream.Stream;

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
        final var cardCounts = hand.getCardCountPerValue();
        final var pairValue = cardCounts.get(2L).getFirst();
        final var kickers = cardCounts.get(1L).stream().sorted(Comparator.reverseOrder());

        return new HandRank(Rank.PAIR, Stream.concat(Stream.of(pairValue), kickers).toList());
    }
}
