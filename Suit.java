public enum Suit {
    SPADES(1),
    HEARTS(2),
    CLUBS(3),
    DIAMONDS(4);


    private int num;

    Suit(int num) {
        this.num = num;
    }

    public int type() {
        return this.num;
    }
}