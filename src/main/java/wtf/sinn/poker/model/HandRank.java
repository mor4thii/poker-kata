package wtf.sinn.poker.model;

import java.util.List;

public record HandRank(Rank rank, List<CardValue> tiebreakers) implements Comparable<HandRank> {

    @Override
    public int compareTo(HandRank other) {
        int rankComparison = Integer.compare(this.rank.getOrder(), other.rank.getOrder());
        if (rankComparison != 0) {
            return rankComparison;
        }

        for (int i = 0; i < Math.min(this.tiebreakers.size(), other.tiebreakers.size()); i++) {
            int tiebreakerComparison = Integer.compare(this.tiebreakers.get(i).getValue(), other.tiebreakers.get(i).getValue());
            if (tiebreakerComparison != 0) {
                return tiebreakerComparison;
            }
        }

        return 0;
    }
}
