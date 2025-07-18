package wtf.sinn.poker.ranking;

import wtf.sinn.poker.model.Hand;
import wtf.sinn.poker.model.HandRank;
import wtf.sinn.poker.model.Rank;

import java.util.Comparator;
import java.util.stream.Stream;

final class TwoPairsRanker extends HandRanker {
    @Override
    protected boolean canHandle(Hand hand) {
        final var cardCountByValue = hand.getCardCountPerValue();

        return cardCountByValue.containsKey(2L) && cardCountByValue.get(2L).size() == 2;
    }

    @Override
    protected HandRank buildHandRank(Hand hand) {
        final var cardCountByValue = hand.getCardCountPerValue();
        final var pairValuesInDescendingOrder = cardCountByValue.get(2L).stream().sorted(Comparator.reverseOrder());
        final var kicker = cardCountByValue.get(1L).getFirst();

        return new HandRank(Rank.TWO_PAIRS, Stream.concat(pairValuesInDescendingOrder, Stream.of(kicker)).toList());
    }
}
