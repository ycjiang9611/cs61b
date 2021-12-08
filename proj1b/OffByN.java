public class OffByN implements CharacterComparator{

    private final int Num;

    public OffByN(int N) {
        Num = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x == y + Num || y == x + Num) {
            return true;
        }
        return false;
    }
}
