import java.util.Arrays;

class SolutionDP {
    public static void main(String[] args) {
        var s = new SolutionDP();
        int coins[] = { 9, 6, 5, 1 };
        int V = 11;

        // System.out.println(s.minCoinToMakeChange(coins, V));
        // int arr[] = { 1, 2, 3 };
        // int m = arr.length;
        // System.out.println(s.coinCount(arr, m, 4));
        // System.out.println(s.minCostToReachN(9, 5, 1));
        // Pair arr[] = new Pair[] { new Pair(5, 24), new Pair(15, 25), new Pair(27,
        // 40), new Pair(50, 60) };
        // s.maxChainLength(arr);

        // int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        // s.lis(arr);

        // String X = "abcdxyz";
        // String Y = "xyzabcd";
        // System.out.println(s.lcs(X.toCharArray(), Y.toCharArray(), X.length(),
        // Y.length()));

        // int val[] = { 60, 100, 120 };
        // int wt[] = { 10, 20, 30 };
        // int W = 50;
        // int n = 3;
        // System.out.println(s.zeroOneKnapsack(W, wt, val, n));

        // int arr[] = new int[] { 1, 101, 2, 3, 100, 4, 5 };
        // System.out.println(s.maximumSumIncresingSubsequence(arr));

        // String str1 = "sunday";
        // String str2 = "saturday";

        // System.out.println(s.editDistance(str1.toCharArray(), str2.toCharArray(),
        // str1.length(), str2.length()));

        int set[] = { 3, 34, 4, 12, 5, 2 };
        int sum = 9;
        System.out.println(s.isSubsetSum(set, sum, set.length));
    }

    public boolean isSubsetSum(int[] set, int sum, int n) {
        if (sum == 0) {
            return true;
        }

        if (sum != 0 && n == 0) {
            return false;
        }

        if (set[n - 1] > sum) {
            return isSubsetSum(set, sum, n - 1);
        }

        return isSubsetSum(set, sum - set[n - 1], n - 1) || isSubsetSum(set, sum, n - 1);
    }

    public int editDistance(char[] X, char[] Y, int m, int n) {
        if (m == 0) {
            return n;
        }

        if (n == 0) {
            return m;
        }

        if (X[m - 1] == Y[n - 1]) {
            return editDistance(X, Y, m - 1, n - 1);
        } else {
            return 1 + min(editDistance(X, Y, m - 1, n), editDistance(X, Y, m, n - 1),
                    editDistance(X, Y, m - 1, n - 1));
        }
    }

    private int min(int x, int y, int z) {
        return Math.min(Math.min(x, y), z);
    }

    /**
     * 
     * @param A
     * @return
     */
    public int maximumSumIncresingSubsequence(int[] A) {
        var msis = Arrays.copyOf(A, A.length);

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j] && msis[i] < msis[j] + A[i]) {
                    msis[i] = msis[j] + A[i];
                }
            }
        }

        return Arrays.stream(msis).summaryStatistics().getMax();
    }

    /**
     * 
     * @param W
     * @param wt
     * @param val
     * @param n
     * @return
     */
    public int zeroOneKnapsack(int W, int[] wt, int[] val, int n) {
        if (n == 0 || W == 0)
            return 0;

        if (wt[n - 1] > W)
            return zeroOneKnapsack(W, wt, val, n - 1);
        else
            return Math.max(val[n - 1] + zeroOneKnapsack(W - wt[n - 1], wt, val, n - 1),
                    zeroOneKnapsack(W, wt, val, n - 1));
    }

    /**
     * 
     * @param x
     * @param y
     * @param m
     * @param n
     * @return
     */
    public int lcs(char[] x, char[] y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;

        if (x[m - 1] == y[n - 1]) {
            return 1 + lcs(x, y, m - 1, n - 1);
        } else {
            return Math.max(lcs(x, y, m - 1, n), lcs(x, y, m, n - 1));
        }
    }

    /**
     * 
     * @param A
     */
    public void lis(int[] A) {
        var lis = new int[A.length];
        Arrays.fill(lis, 1);

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j] && lis[i] < lis[j] + 1) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        System.out.println(Arrays.stream(lis).summaryStatistics().getMax());
    }

    /**
     * 
     * @param pairs
     */
    public void maxChainLength(Pair[] pairs) {
        var mcl = new int[pairs.length];

        Arrays.fill(mcl, 1);

        for (int i = 0; i < pairs.length; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i].first > pairs[j].second && mcl[i] < mcl[j] + 1) {
                    mcl[i] = mcl[j] + 1;
                }
            }
        }

        var max = Arrays.stream(mcl).summaryStatistics().getMax();
        System.out.println(max);

    }

    /**
     * Minimum cost to reach N with cost as P and
     * Q(https://www.geeksforgeeks.org/minimum-cost-to-reach-a-point-n-from-0-with-two-different-operations-allowed/)
     * 
     * @param N
     * @param P
     * @param Q
     * @return
     */
    public int minCostToReachN(int N, int P, int Q) {
        int cost = 0;

        while (N > 0) {
            if (N % 2 == 1) {
                cost += P;
                N -= 1;
            } else {
                int half = N / 2;
                cost += Math.min(half * P, Q);
                N /= 2;
            }
        }

        return cost;
    }

    /**
     * Find minimum number of coins that make a given
     * value(https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/)
     * 
     * Approach: Bascially minCoins(C[0..m-1], V) = min(1 + minCoins(C[0..m-2],
     * V-C[i]), minCoins(C[0..m-2], V))
     * 
     * @param C
     * @param value
     */
    public int minCoinToMakeChange(int[] C, int value) {

        if (value == 0)
            return 0;

        var result = Integer.MAX_VALUE;
        for (var i = 0; i < C.length; i++) {
            if (C[i] <= value) {
                int sRes = minCoinToMakeChange(C, value - C[i]);

                if (sRes != Integer.MAX_VALUE && sRes + 1 < result) {
                    result = sRes + 1;
                }
            }
        }

        return result;

    }

    /**
     * Given a value N, if we want to make change for N cents, and we have infinite
     * supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we
     * make the change(https://www.geeksforgeeks.org/coin-change-dp-7/)
     * 
     * Approach: the count(C, m, V) = count(C, m-1, V) + count(C, m, V - S[m-1])
     * 
     * @param C
     * @param V
     * @return
     */
    public int coinCount(int C[], int m, int V) {

        if (V == 0)
            return 1; // one solution

        if (V < 0)
            return 0;

        if (m <= 0 && V >= 1)
            return 0;

        return coinCount(C, m - 1, V) + coinCount(C, m, V - C[m - 1]);

    }
}

class Pair {
    int first;
    int second;

    Pair(int a, int b) {
        this.first = a;
        this.second = b;
    }
}