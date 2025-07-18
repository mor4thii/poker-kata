package wtf.sinn.poker.model;

import java.util.List;

public record Hand(List<Card> cards) {
    public Hand {
        if (cards.size() != 5) {
            throw new IllegalArgumentException("A hand must consist of 5 cards");
        }
    }
}
