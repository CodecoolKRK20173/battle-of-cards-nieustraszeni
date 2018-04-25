public enum Suit {
    HEARTS(1),
    DIAMONDS(2),
    SPADES(3),
    CLUBS(4);

    private int num;

    Suit(int num) {
        this.num = num;
    }

    public int type() {
        return this.num;
    }
}