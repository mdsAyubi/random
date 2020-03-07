import java.util.*;

class SolutionRec {
    public static void main(String[] args) {

        SolutionRec s = new SolutionRec();
        // System.out.println(s.josephus(7, 3));

        int screen[][] = { { 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0 }, { 1, 0, 0, 1, 1, 0, 1, 1 },
                { 1, 2, 2, 2, 2, 0, 1, 0 }, { 1, 1, 1, 2, 2, 0, 1, 0 }, { 1, 1, 1, 2, 2, 2, 2, 0 },
                { 1, 1, 1, 1, 1, 2, 1, 1 }, { 1, 1, 1, 1, 1, 2, 2, 1 }, };
        int x = 4, y = 4, newC = 3;
        s.printArray(screen);
        s.floodFill(screen, x, y, newC);
        s.printArray(screen);

    }

    public void printArray(int[][] screen) {
        System.out.println("Updated screen ");
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++)
                System.out.print(screen[i][j] + " ");
            System.out.println();
        }
    }

    /**
     * Josephus probelem
     * 
     * Approach: the solution is this recusrions
     * 
     * W(N,K) = (W(N-1, K) + K) % N if N > 1, 0 if N =1
     * 
     * @param N
     * @param K
     * @return
     */
    public int josephus(int N, int K) {
        if (N == 1) {
            return 0;
        }
        return (josephus(N - 1, K) + K) % N;
    }

    int msbPos(int n) {
        int pos = 0;
        while (n != 0) {
            pos++;

            // keeps shifting bits to the right
            // until we are left with 0
            n = n >> 1;
        }
        return pos;
    }

    // function to return at which place Josephus
    // should sit to avoid being killed
    int josephify(int n) {
        /*
         * Getting the position of the Most Significant Bit(MSB). The leftmost '1'. If
         * the number is '41' then its binary is '101001'. So msbPos(41) = 6
         */
        int position = msbPos(n);

        /*
         * 'j' stores the number with which to XOR the number 'n'. Since we need
         * '100000' We will do 1<<6-1 to get '100000'
         */
        int j = 1 << (position - 1);

        /*
         * Toggling the Most Significant Bit. Changing the leftmost '1' to '0'. 101001 ^
         * 100000 = 001001 (9)
         */
        n = n ^ j;

        /*
         * Left-shifting once to add an extra '0' to the right end of the binary number
         * 001001 = 010010 (18)
         */
        n = n << 1;

        /*
         * Toggling the '0' at the end to '1' which is essentially the same as putting
         * the MSB at the rightmost place. 010010 | 1 = 010011 (19)
         */
        n = n | 1;

        return n;
    }

    /**
     * Fill the pixel adjacent to the given pixel with the same
     * color(https://www.geeksforgeeks.org/flood-fill-algorithm-implement-fill-paint/).
     * 
     * Approach: Take the previous color at the pixel and store it. Recurse in all
     * directions
     * 
     * @param arr
     * @param x
     * @param y
     * @param color
     */
    public void floodFill(int[][] arr, int x, int y, int color) {
        int prevColor = arr[x][y];

        floodFillHelper(arr, x, y, prevColor, color);
    }

    private void floodFillHelper(int[][] arr, int x, int y, int prevColor, int color) {

        if (x < 0 || x >= arr.length || y < 0 || y >= arr[0].length) {
            return;
        }
        if (arr[x][y] != prevColor) {
            return;
        }
        arr[x][y] = color;

        floodFillHelper(arr, x - 1, y, prevColor, color);
        floodFillHelper(arr, x + 1, y, prevColor, color);
        floodFillHelper(arr, x, y - 1, prevColor, color);
        floodFillHelper(arr, x, y + 1, prevColor, color);
    }

    /**
     * Reach a cell at row r and column
     * c(https://www.geeksforgeeks.org/count-possible-paths-top-left-bottom-right-nxm-matrix/)
     * 
     * Approach: path(r,c) = path(r-1,c) + path(r, c-1)
     * 
     * @param r
     * @param c
     * @return
     */
    public int countPath(int r, int c) {
        if (r == 1 || c == 1) {
            return 1;
        }
        return countPath(r - 1, c) + countPath(r, c - 1);
    }

    public int numberOfPaths(int m, int n) {
        // Create a 2D table to store results
        // of subproblems
        int count[][] = new int[m][n];

        // Count of paths to reach any cell in
        // first column is 1
        for (int i = 0; i < m; i++)
            count[i][0] = 1;

        // Count of paths to reach any cell in
        // first row is 1
        for (int j = 0; j < n; j++)
            count[0][j] = 1;

        // Calculate count of paths for other
        // cells in bottom-up manner using
        // the recursive solution
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
                count[i][j] = count[i - 1][j] + count[i][j - 1];
        }
        return count[m - 1][n - 1];
    }

    /**
     * Given an array of positive integers arr[] and a sum x, find all unique
     * combinations in arr[] where the sum is equal to
     * x(https://www.geeksforgeeks.org/combinational-sum/).
     * 
     * Approach:
     * 
     * 1. Sort the array(non-decreasing).
     * 
     * 2. First remove all the duplicates from array.
     * 
     * 3. Then use recursion and backtracking to solve the problem.
     * 
     * (A) If at any time sub-problem sum == 0 then add that array to the result
     * 
     * (B) Else if sum if negative then ignore that sub-problem.
     * 
     * (C) Else insert the present array in that index to the current list and call
     * the function with sum = sum-ar[index] and index = index, then pop that
     * element from current index (backtrack) and call the function with sum = sum
     * and index = index+1
     * 
     * @param ar
     * @param sum
     * @param res
     * @param r
     * @param i
     */
    void findNumbers(List<Integer> ar, int sum, List<List<Integer>> res, List<Integer> r, int i) {
        // If current sum becomes negative
        if (sum < 0)
            return;

        // if we get exact answer
        if (sum == 0) {
            res.add(r);
            return;
        }

        // Recur for all remaining elements that
        // have value smaller than sum.
        while (i < ar.size() && sum - ar.get(i) >= 0) {

            // Till every element in the array starting
            // from i which can contribute to the sum
            r.add(ar.get(i)); // add them to list

            // recur for next numbers
            findNumbers(ar, sum - ar.get(i), res, r, i);
            i++;

            // remove number from list (backtracking)
            r.remove(ar.get(i));
        }
    }

    /**
     * How to print maximum number of A’s using given four
     * keys(https://www.geeksforgeeks.org/how-to-print-maximum-number-of-a-using-given-four-keys/)
     * 
     * Approach: a) For N < 7, the output is N itself.
     * 
     * b) Ctrl V can be used multiple times to print current buffer (See last two
     * examples above). The idea is to compute the optimal string length for N
     * keystrokes by using a simple insight. The sequence of N keystrokes which
     * produces an optimal string length will end with a suffix of Ctrl-A, a Ctrl-C,
     * followed by only Ctrl-V’s . (For N > 6)
     * 
     * The task is to find out the break=point after which we get the above suffix
     * of keystrokes. Definition of a breakpoint is that instance after which we
     * need to only press Ctrl-A, Ctrl-C once and the only Ctrl-V’s afterwards to
     * generate the optimal length. If we loop from N-3 to 1 and choose each of
     * these values for the break-point, and compute that optimal string they would
     * produce. Once the loop ends, we will have the maximum of the optimal lengths
     * for various breakpoints, thereby giving us the optimal length for N
     * keystrokes.
     * 
     * @param N
     * @return
     */
    public int findoptimal(int N) {
        // The optimal string length is N
        // when N is smaller than 7
        if (N <= 6)
            return N;

        // Initialize result
        int max = 0;

        // TRY ALL POSSIBLE BREAK-POINTS
        // For any keystroke N, we need to
        // loop from N-3 keystrokes back to
        // 1 keystroke to find a breakpoint
        // 'b' after which we will have Ctrl-A,
        // Ctrl-C and then only Ctrl-V all the way.
        int b;
        for (b = N - 3; b >= 1; b--) {
            // If the breakpoint is s at b'th
            // keystroke then the optimal string
            // would have length
            // (n-b-1)*screen[b-1];
            int curr = (N - b - 1) * findoptimal(b);
            if (curr > max)
                max = curr;
        }
        return max;
    }
}