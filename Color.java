public enum Color {
    RED(1),
    BLACK(2);
    private int num;

    Color(int num) {
        this.num = num;
    }

    public int type() {
        return this.num;
    }
}