import java.util.*;

class SolutionString {

    public void reverseWords(char[] arr) {

        int i = 0;
        int lastChar = 0;
        while (i < arr.length) {
            if (i == arr.length - 1) {
                reverse(arr, lastChar, i);
            }
            if (arr[i] == ' ') {
                reverse(arr, lastChar, i - 1);
                lastChar = i + 1;
            }
            i++;
        }
        reverse(arr, 0, arr.length - 1);
        System.out.println(arr);
    }

    /**
     * Find permutations of a string
     * 
     * Approach: fix a character, permute the rest, then swap back
     * 
     * @param arr
     * @param l
     * @param r
     */
    public void permute(char[] arr, int l, int r) {
        if (l == r) {
            System.out.println(arr);
        } else {
            for (int i = l; i <= r; i++) {
                swap(arr, l, i);
                permute(arr, l + 1, r);
                swap(arr, l, i);
            }
        }
    }

    public void permuteTwo(String str, String answer) {
        if (str.length() == 0) {
            System.out.println(answer);
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            String rest = str.substring(0, i) + str.substring(i + 1, str.length());
            permuteTwo(rest, answer + ch);
        }
    }

    /**
     * Find the longest palindrome in the string
     * 
     * Approach: the longest palindrome in the string[left..right] is the following
     * 
     * if left == right then 1, if right = left + 1, i.e only two characters and
     * both are equal then 2 if left == right then 2 + num(str[left+1..right-1])
     * else max(num(str[left+1, right]), num(str[left..right-1]))
     * 
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public int longestPalindrome(char[] arr, int left, int right) {
        if (left == right)
            return 1;
        else if (right == left + 1 && arr[left] == arr[right])
            return 2;
        else if (arr[left] == arr[right])
            return longestPalindrome(arr, left + 1, right - 1) + 2;
        else {
            return Math.max(longestPalindrome(arr, left + 1, right), longestPalindrome(arr, left, right - 1));
        }

    }

    /**
     * Find the minimum number of insertions to form a palindrom
     * 
     * Approach:
     * 
     * For str[left..right], the min number of insertions is
     * 
     * If str[left] = str[right] then minInsertion(str[left+1, right-1]), Else
     * min(minInsertions(str[left+1, right]), minInsertions(str[left, right-1]))+1
     * 
     * @param str
     * @param left
     * @param right
     */
    public int minInsertionToFormPalindrome(char[] str, int left, int right) {

        if (left > right)
            return Integer.MAX_VALUE;
        if (left == right)
            return 0;
        if (right == left + 1) {
            return str[left] == str[right] ? 0 : 1;
        }

        if (str[left] == str[right]) {
            return minInsertionToFormPalindrome(str, left + 1, right - 1);
        } else {
            return Math.min(minInsertionToFormPalindrome(str, left + 1, right),
                    minInsertionToFormPalindrome(str, left, right - 1)) + 1;
        }

    }

    /**
     * Remove ajacent duplicates from the string
     * 
     * Approach: put the first character in a stack and make it last seen also,
     * start iterating from the second If the current character is the last seen do
     * nothing If the current character is in the stack, pop and it make last seen
     * Else push the character in the stack
     * 
     * @param r
     * @return
     */
    public void removeAdjacentDulicates(char[] str) {
        Stack<Character> st = new Stack<>();

        char lastSeen = str[0];
        st.push(str[0]);
        for (int i = 1; i < str.length; i++) {

            if (!st.isEmpty() && st.peek() == str[i]) {
                st.pop();
                lastSeen = str[i];
            } else {
                if (lastSeen == str[i]) {
                    continue;
                } else {
                    st.push(str[i]);
                }
            }
        }

        System.out.println(st.toString());
    }

    private int value(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        if (r == 'D')
            return 500;
        if (r == 'M')
            return 1000;
        return -1;
    }

    /**
     * COnvert a string in roman numeric form into decimal
     * 
     * Approach: Keep adding the value of the character at ith position If the next
     * character is higher in value add higher - current val instead
     * 
     * @param args
     */
    public void romanToInteger(char[] str) {

        int result = 0;
        for (int i = 0; i < str.length; i++) {
            int val = value(str[i]);
            if (i + 1 < str.length) {
                int nextVal = value(str[i + 1]);
                if (nextVal > val) {
                    result += nextVal - val;
                    i++;
                } else {
                    result += val;
                }
            } else {
                result += val;
            }
        }
        System.out.println(result);

    }

    /**
     * Write your owb atoi
     * 
     * Approach 1. Ignore the white characters 2. Check for negative sign 3. Check
     * for overflow 4. Check for non numeric characters
     * 
     * @param args
     */

    public int atoi(char[] str) {
        int i = 0, result = 0, sign = 1;

        while (str[i] == ' ' || str[i] == '\t')
            i++;

        if (str[i] == '-') {
            sign = -1;
            i++;
        }

        while (i < str.length) {
            if (str[i] >= '0' && str[i] <= '9') {
                result = result * 10 + (str[i] - '0');
            } else {
                break;
            }
        }

        return sign * result;

    }

    /**
     * Longest common prefix of a list of strings
     * 
     * Approach: Create a trie and walk the trie till theere is birfurcation, i.e. 2
     * children
     * 
     * @param args
     */
    public void commonPrefix(List<String> strings) {

        Trie tri = Trie.newInstance();
        for (String str : strings) {
            tri.insert(str);
        }

        TrieNode p = tri.root;
        String prefix = "";

        for (;;) {
            if (p.isLeaf || p.childrenCount() > 1) {
                System.out.println(prefix);
                break;
            } else {
                prefix += p.ch;
                p = p.firstChild();

            }
        }

    }

    public static void main(String[] args) {
        SolutionString s = new SolutionString();

        // String str = "my name is khan";
        // s.reverseWords(str.toCharArray());

        // String str = "ABC";
        // s.permute(str.toCharArray(), 0, str.length() - 1);
        // // s.permuteTwo(str, "");

        // String str = "forgeeksskeegfor";
        // System.out.println(s.longestPalindrome(str.toCharArray(), 0, str.length() -
        // 1));

        // String str = "geeksforgeeg";
        // s.removeAdjacentDulicates(str.toCharArray());

        // String str = "MCMIV";
        // s.romanToInteger(str.toCharArray());

        // String str = "geeks";
        // System.out.println(s.minInsertionToFormPalindrome(str.toCharArray(), 0,
        // str.length() - 1));

        List<String> strs = List.of("geeksforgeeks", "geeks", "geek", "geezer");
        s.commonPrefix(strs);

    }

    private void reverse(char[] arr, int from, int to) {
        for (int i = from, j = to; i <= j; i++, j--) {
            swap(arr, i, j);
        }
    }

    private void swap(char[] A, int i, int j) {
        char temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public void printArray(char[] a) {
        for (char i : a) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isLeaf;
    char ch = '-';

    private TrieNode() {
        for (int i = 0; i < 26; i++) {
            children[i] = null;
        }
    }

    public static TrieNode newInstance() {
        return new TrieNode();
    }

    public int childrenCount() {
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (children[i] != null) {
                count++;
            }
        }
        return count;
    }

    public TrieNode firstChild() {
        for (int i = 0; i < 26; i++) {
            if (children[i] != null) {
                return children[i];
            }
        }
        return null;
    }
}

class Trie {
    TrieNode root;

    private Trie() {
        this.root = TrieNode.newInstance();
    }

    public static Trie newInstance() {
        return new Trie();
    }

    public void insert(String key) {
        TrieNode p = this.root;

        for (int level = 0; level < key.length(); level++) {
            char data = key.charAt(level);
            if (p.ch != '-') {
                System.out.println(p.ch + "level=" + level);
                if (p.children[data - 'a'] == null) {
                    System.out.println("Found null" + p);
                    p.children[data - 'a'] = TrieNode.newInstance();
                    p.children[data - 'a'].ch = data;
                }
                p = p.children[data - 'a'];
            }
        }
        p.isLeaf = true;
    }

    public void print(TrieNode node) {

        if (node.isLeaf) {
            return;
        }
        System.out.println(node.ch);
        for (int i = 0; i < node.children.length; i++) {
            if (node.children[i] != null)
                print(node.children[i]);
        }
    }

}