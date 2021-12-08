public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> p = wordToDeque(word);
        return judge(p);
    }

    private boolean judge(Deque<Character> p) {
        if (p.size() == 1 || p.size() == 0) {
            return true;
        }
        char a = p.removeFirst();
        char b = p.removeLast();
        if (a == b) {
            return judge(p);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> p = wordToDeque(word);
        return judge_mod(p, cc);
    }

    private boolean judge_mod(Deque<Character> p, CharacterComparator cc) {
        if (p.size() == 1 || p.size() == 0) {
            return true;
        }
        char a = p.removeFirst();
        char b = p.removeLast();
        if (cc.equalChars(a, b)) {
            return judge_mod(p, cc);
        }
        else {
            return false;
        }
    }
}
