import java.util.*;

class SolutionGreedy {

    public static void main(String[] args) {
        var sg = new SolutionGreedy();

        int s[] = { 1, 3, 0, 5, 8, 5 };
        int f[] = { 2, 4, 6, 7, 9, 9 };
        // sg.activitySelection(s, f);

        int a[] = { 1, 5, 1, 2, 5, 1 };
        // sg.minCoinPile(a, 3);

        int K = 50;
        int cost[] = { 1, 12, 5, 111, 200, 1000, 10, 9, 12, 15 };
        // sg.maximumNumberOfToys(cost, K);

        // sg.maxDigitWithSumAsK(2, 9);
        int arr[] = { 3, 16, 12, 9, 20 };
        int k = 3;

        sg.minimizeHeightDifference(arr, k);

    }

    /**
     * You are given n activities with their start and finish times. Select the
     * maximum number of activities that can be performed by a single person,
     * assuming that a person can only work on a single activity at a time.
     * 
     * Approach: Sort the activities according to their end times
     * 
     * Put the first activity in the result sequence. Iterate over the other
     * activities, if the start time of the activity is greater than the end time of
     * the previously selected activity, add this activity to the result sequence.
     * 
     * @param start
     * @param end
     */
    public void activitySelection(int[] start, int[] end) {
        var activities = new ArrayList<Pair<Integer, Integer>>();
        var sequence = new ArrayList<Pair<Integer, Integer>>();
        for (var i = 0; i < start.length; i++) {
            activities.add(Pair.of(start[i], end[i]));
        }

        Collections.sort(activities, (var first, var second) -> {
            return first.getSecond().compareTo(second.getSecond());
        });

        System.out.println(activities.toString());

        sequence.add(activities.get(0));
        var lastActivity = activities.get(0);
        for (var i = 1; i < activities.size(); i++) {
            var activity = activities.get(i);

            if (activity.getFirst().compareTo(lastActivity.getSecond()) >= 1) {
                sequence.add(activity);
                lastActivity = activity;
            }
        }

        System.out.println(sequence.toString());
    }

    /**
     * Find maximum meetings in one room
     * 
     * Approach: Same as activity selection
     * 
     * @param start
     * @param end
     */
    public void numberOfMeetingsInOneRoom(int[] start, int[] end) {

    }

    /**
     * Remove Minimum coins such that absolute difference between any two piles is
     * less than K
     * 
     * Approach: Find the pile with minumum number of coins
     * 
     * Iterate over the array and, for every element, find the difference between
     * the coins and min number of coins. If the diff is greater than k, remove
     * diff-k coins.
     * 
     * @param A
     * @param K
     */
    public void minCoinPile(int[] A, int K) {
        var count = 0;

        var minCoins = Arrays.stream(A).min().getAsInt();
        for (var i : A) {
            var diff = i - minCoins;

            if (diff > K) {
                count += (diff - K);
            }
        }

        System.out.println("Count=" + count);
    }

    /**
     * Maximise the number of toys that can be purchased with amount K
     * 
     * Approach: Sort the cost of toyrs in ascending order.
     * 
     * Ietrate over the sorted cost array and add the cost to current sum till the
     * current sum is less than or equal to K
     * 
     * @param cost
     * @param amount
     */
    public void maximumNumberOfToys(int[] cost, int amount) {
        var currentSum = 0;
        var count = 0;
        Arrays.sort(cost);
        for (var i : cost) {
            currentSum += i;
            if (currentSum <= amount) {
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);
    }

    /**
     * Find the Largest number with given number of digits and sum of digits
     * 
     * Approach: Try to put the max digit possible at the most significant
     * place(leftmost)
     * 
     * If the remainder sum is greater than 9, put 9 at the msb place, else put the
     * remainder sum
     * 
     * @param numberOfDigits
     * @param sumOfDigits
     */
    public void maxDigitWithSumAsK(int numberOfDigits, int sumOfDigits) {

        if (sumOfDigits == 0) {
            // Special case
            if (numberOfDigits == 1) {
                System.out.println(0);
            } else {
                System.out.println("Imposible....");
            }
        }

        if (sumOfDigits > 9 * numberOfDigits) {
            System.out.println("Imposible...");
        }

        var result = new int[numberOfDigits];
        var remainderSum = sumOfDigits;

        for (var i = 0; i < numberOfDigits; i++) {
            if (remainderSum >= 9) {
                result[i] = 9;
                remainderSum -= 9;
            } else {
                result[i] = remainderSum;
                remainderSum = 0;
            }
        }

        System.out.println(Arrays.toString(result));

    }

    /**
     * Minimize the maximum difference between the heights
     * 
     * Approach: Find the min and max of the array. Get the avg of min and max.
     * 
     * Iterate over the array, if the element is greater than the avg then increase
     * by K, else decrese by K
     * 
     * In the end, find the min and max of the modified array
     * 
     * @param A
     * @param K
     */
    public void minimizeHeightDifference(int[] A, int K) {

        var stats = Arrays.stream(A).summaryStatistics();
        var min = stats.getMin();
        var max = stats.getMax();

        var avg = (min + max) / 2;

        for (var i = 0; i < A.length; i++) {
            if (A[i] > avg) {
                A[i] -= K;
            } else {
                A[i] += K;
            }
        }

        var afterStats = Arrays.stream(A).summaryStatistics();
        System.out.println(afterStats.getMax() - afterStats.getMin());
    }

    /**
     * Minimize the sum of product of two arrays with permutations allowed
     * 
     * Approach: sort the array and calculate the product of sum(A[i]*B[i])
     * 
     * @param A
     * @param B
     */
    public void minProductSum(int[] A, int[] B) {

    }

    /**
     * Find the minimum and maximum amount to buy all N candies
     * 
     * Approach: sort the cost array.
     * 
     * Iterate and keep track of amount spent -- amount += cost[i].
     * 
     * Reduce k at every iteration
     * 
     * @param A
     * @param k
     */
    public void minAmountToBuyAllCandies(int[] A, int k) {

    }

}

class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public static <T1, T2> Pair<T1, T2> of(T1 f, T2 s) {
        return new Pair<T1, T2>(f, s);
    }

    private Pair(T1 f, T2 s) {
        this.first = f;
        this.second = s;
    }

    public T1 getFirst() {
        return this.first;
    }

    public T2 getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }
}