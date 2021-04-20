public final class Tuple {
    private int x;
    private int y;

    Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {return "(" + x + ", " + y + ")";}
}