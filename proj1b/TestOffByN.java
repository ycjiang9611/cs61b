import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offBy5 = new OffByN(5);

    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertFalse(offBy5.equalChars('a', 'a'));
        assertFalse(offBy5.equalChars('z', 'B'));
        assertTrue(offBy5.equalChars('a', 'f'));
        assertTrue(offBy5.equalChars('f', 'a'));
    }
}
