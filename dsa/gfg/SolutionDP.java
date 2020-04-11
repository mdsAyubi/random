import java.util.Arrays;

class SolutionDP {
    public static void main(String[] args) {
        var s = new SolutionDP();
        // int coins[] = { 9, 6, 5, 1 };
        // int V = 11;

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

        // int set[] = { 3, 34, 4, 12, 5, 2 };
        // int sum = 9;
        // System.out.println(s.isSubsetSum(set, sum, set.length));

        // int l = 11, p = 2, q = 3, r = 5;

        // // Calling Function
        // s.rodCutting(l, p, q, r);

        // int mat[][] = { { 1, 2, 9 }, { 5, 3, 8 }, { 4, 6, 7 } };
        // s.longestPathInGrid(mat);

        // int arr[] = { 3, 1, 4, 2, 2, 1 };
        // s.partitionWithMinSubsetSum(arr);

        // System.out.println(s.waysToCoverADistanceMemoized(4));
        // System.out.println(s.eggDrop(2, 10));

        // int V[] = { 8, 15, 3, 7 };
        // System.out.println(s.optimalStrategyInGame(V, 0, V.length - 1));

        // String X = "AGGTAB";
        // String Y = "GXTXAYB";

        // System.out.println(s.shortestSuperSequence(X.toCharArray(),
        // Y.toCharArray()));

        int arr[] = { 1, 3, 6, 3, 2, 3, 6, 8, 9, 5 };
        System.out.println(s.minJumps(arr, 0, arr.length - 1));
    }

    public int minJumps(int[] A, int l, int h) {
        if (l == h)
            return 0;

        if (A[l] == 0)
            return Integer.MAX_VALUE;

        var min = Integer.MAX_VALUE;

        for (int i = l + 1; i <= h && i <= l + A[l]; i++) {
            var jmps = minJumps(A, i, h);
            if (jmps != Integer.MAX_VALUE && jmps + 1 < min) {
                min = jmps + 1;
            }
        }
        return min;
    }

    /**
     * 
     * @param X
     * @param Y
     * @return
     */
    public int shortestSuperSequence(char[] X, char[] Y) {
        int m = X.length;
        int n = Y.length;

        int l = lcs(X, Y, m, n);
        return (m + n - l);
    }

    /**
     * 
     * @param V
     * @param i
     * @param j
     * @return
     */
    public int optimalStrategyInGame(int[] V, int i, int j) {

        if (i == j)
            return V[i];
        if (j == i + 1)
            return Math.max(V[i], V[j]);

        return Math.max(V[i] + Math.min(optimalStrategyInGame(V, i + 2, j), optimalStrategyInGame(V, i + 1, j - 1)),
                V[j] + Math.min(optimalStrategyInGame(V, i + 1, j - 1), optimalStrategyInGame(V, i, j - 2)));

    }

    /**
     * 
     * @param n
     * @param k
     * @return
     */
    public int eggDrop(int n, int k) {

        // For one egg,all the floors
        if (n == 1) {
            return k;
        }

        // For 1 or 0 floors, as many trials
        if (k == 1 || k == 0)
            return k;

        var res = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            res = Math.min(res, 1 + Math.max(eggDrop(n - 1, i - 1), eggDrop(n, k - i)));
        }

        return res;

    }

    public int waysToCoverADistance(int dist) {
        if (dist < 0)
            return 0;

        if (dist == 0)
            return 1;

        return waysToCoverADistance(dist - 1) + waysToCoverADistance(dist - 2) + waysToCoverADistance(dist - 3);
    }

    public int waysToCoverADistanceMemoized(int dist) {
        var dp = new int[dist + 1];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[dist];

    }

    /**
     * 
     * @param A
     */
    public void partitionWithMinSubsetSum(int[] A) {
        var sum = Arrays.stream(A).summaryStatistics().getSum();

        System.out.println(partitionWithMinSubsetSumHelper(A, A.length - 1, 0, sum));
    }

    private long partitionWithMinSubsetSumHelper(int[] A, int N, long currentSum, long totalSum) {
        if (N == 0) {
            return Math.abs(currentSum - (totalSum - currentSum));
        }

        return Math.min(partitionWithMinSubsetSumHelper(A, N - 1, currentSum + A[N - 1], totalSum),
                partitionWithMinSubsetSumHelper(A, N - 1, currentSum, totalSum));
    }

    /**
     * 
     */
    public void longestPathInGrid(int[][] mat) {
        var dp = new int[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        var result = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (dp[i][j] == -1) {
                    result = Math.max(result, longestPathInGridHelper(mat, dp, i, j));
                }
            }
        }
        System.out.println(result);
    }

    private int longestPathInGridHelper(int[][] mat, int[][] dp, int i, int j) {
        if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length) {
            return 0;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        int x = Integer.MIN_VALUE, y = Integer.MIN_VALUE, z = Integer.MIN_VALUE, w = Integer.MIN_VALUE;
        if (j < mat[i].length - 1 && mat[i][j + 1] == mat[i][j] + 1) {
            x = 1 + longestPathInGridHelper(mat, dp, i, j + 1);
        }

        if (i > 0 && mat[i - 1][j] == mat[i][j] + 1) {
            y = 1 + longestPathInGridHelper(mat, dp, i - 1, j);
        }

        if (i < mat.length - 1 && mat[i + 1][j] == mat[i][j] + 1) {
            z = 1 + longestPathInGridHelper(mat, dp, i + 1, j);
        }

        if (j > 0 && mat[i][j - 1] == mat[i][j] + 1) {
            w = 1 + longestPathInGridHelper(mat, dp, i, j - 1);
        }

        dp[i][j] = Math.max(x, Math.max(y, Math.max(z, Math.max(w, 1))));
        return dp[i][j];
    }

    /**
     * 
     * @param l
     * @param p
     * @param q
     * @param r
     */
    public void rodCutting(int l, int p, int q, int r) {
        var t = new int[l + 1];

        Arrays.fill(t, -1);

        t[0] = 0;

        for (int i = 0; i <= l; i++) {
            if (t[i] == -1) {
                continue;
            }

            if (i + p <= l) {
                t[i + p] = Math.max(t[i] + 1, t[i + p]);
            }

            if (i + q <= l) {
                t[i + q] = Math.max(t[i] + 1, t[i + q]);
            }

            if (i + r <= l) {
                t[i + r] = Math.max(t[i] + 1, t[i + r]);
            }
        }

        System.out.println(t[l]);
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