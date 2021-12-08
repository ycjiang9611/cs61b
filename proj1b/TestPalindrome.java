import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator obo = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("acdghjdca"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("aba"));

    }

    @Test
    public void testNewIsPalindrome() {
        assertFalse(palindrome.isPalindrome("dog", obo));
        assertFalse(palindrome.isPalindrome("cate", obo));
        assertTrue(palindrome.isPalindrome("mon", obo));
        assertTrue(palindrome.isPalindrome("spot", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
    }
}
