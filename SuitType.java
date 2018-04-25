public enum SuitType {
    HEARTS(1, "hearts"),
    DIAMONDS(2, "diamonds"),
    SPADES(3, "spades"),
    CLUBS(4, "clubs");
    
    private int suitNum;
    private String suitName;


    public int getSuitNumber() {
        return this.suitNum;
    }

    public String getSuitName() {
        return this.suitName;
    }
}