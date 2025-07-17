package wtf.sinn.poker.model;

public enum Rank {
    FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(9);

    private final int order;

    Rank(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
