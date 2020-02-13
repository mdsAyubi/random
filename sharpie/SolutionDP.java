class SolutionDP {
    public static void main(String[] args) {
        var s = new SolutionDP();
        int coins[] = { 9, 6, 5, 1 };
        int V = 11;

        // System.out.println(s.minCoinToMakeChange(coins, V));
        int arr[] = { 1, 2, 3 };
        int m = arr.length;
        System.out.println(s.coinCount(arr, m, 4));
    }

    /**
     * Find minimum number of coins that make a given value
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
     * make the change
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