import java.util.*;

class SolutionArray {
    /**
     * Subarry with the given
     * sum(https://www.geeksforgeeks.org/find-subarray-with-given-sum/)
     * 
     * Approach: take two pointers, init at 0 and 1 Start adding elements to the
     * current sum If the cureent_sum exceeds the given sum, then start removing
     * from the tail till current_sum becomes less than sum
     * 
     * Only handles postive numbers
     */

    public void subarrayWithGivenSum(int[] arr, int sum) {
        int currentSum = arr[0];
        // int i=0;
        int start = 0;
        for (int i = 1; i < arr.length; i++) {
            currentSum += arr[i];
            // System.out.println(currentSum);
            if (currentSum > sum) {
                // start = i;
                while (currentSum > sum && start < i) {
                    currentSum -= arr[start];
                    start++;
                    // System.out.println("...");
                }
            }
            if (currentSum == sum) {
                System.out.println(start);
                System.out.println(i);
                break;
            }

        }

    }

    /**
     * Given an unsorted array of integers, find a subarray which adds to a given
     * number. If there are more than one subarrays with the sum as the given
     * number, print any of
     * them(https://www.geeksforgeeks.org/find-subarray-with-given-sum-in-array-of-integers/).
     * 
     * Approach: make a map of integer to integer, and traverse over the array
     * 
     * For every element, add the currentSum to the map. Query the map for a number
     * which currentSum - sum, if the number exists, print the number from location
     * of the difference to the current location
     */

    public void subarrayWithGivenSumWithNegativeNumbers(int[] arr, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        int currentSum = 0;
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];

            if (currentSum - sum == 0) {
                print(0);
                print(i);
                break;
            }

            if (map.containsKey(currentSum - sum)) {
                print(map.get(currentSum - sum));
                print(i);
                break;
            }

            map.put(currentSum, i);
        }
    }

    /**
     * Given an array of distinct integers. The task is to print all the triplets
     * such that sum of two elements equals the third
     * element(https://www.geeksforgeeks.org/count-triplets-such-that-one-of-the-numbers-can-be-written-as-sum-of-the-other-two/)
     * 
     * Approach: Put all the elements into a map
     * 
     * Take one element, traverse from next to last and try to find first-second in
     * the map
     */
    public void findTriplets(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int lookUp = arr[i] - arr[j];
                if (map.containsKey(lookUp) && map.get(lookUp) != i) {
                    print(arr[i], arr[j], lookUp);
                    break;
                }
            }
        }
    }

    /**
     * Find the sum of contiguous subarray within a one-dimensional array of numbers
     * which has the largest
     * sum(https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/).
     * 
     * Approach:
     * 
     * find the max ending at every index and then keep track of the max so far
     * which is the max of all the max ending at indices
     * 
     * @param a
     */

    public void maxSumContiguousSubarray(int[] arr) {
        int maxEndingAtIndex = 0;
        int maxSoFar = 0;

        for (int i = 0; i < arr.length; i++) {
            maxEndingAtIndex = maxEndingAtIndex + arr[i];
            if (maxEndingAtIndex < 0) {
                maxEndingAtIndex = 0;
            }

            if (maxSoFar < maxEndingAtIndex) {
                maxSoFar = maxEndingAtIndex;
            }
        }
        print(maxSoFar);
    }

    /**
     * Find missing number from 1 to
     * n(https://www.geeksforgeeks.org/find-the-missing-number/).
     * 
     * Approach: sum the numbers 1 to n using n*(n+1)/2, then subtract the sum of
     * arr
     * 
     * OR, XOR the numbers 1 to n, then XOR the numbers in the array, then XOR the
     * two results
     * 
     * @param a
     */

    public void findMissing(int[] arr, int n) {

        int r1 = 1;
        int r2 = arr[0];
        for (int i = 2; i <= n + 1; i++) {
            r1 = r1 ^ i;
        }

        for (int i = 1; i < arr.length; i++) {
            r2 = r2 ^ arr[i];
        }

        print(r1 ^ r2);
    }

    /**
     * Merge two sorted
     * arrays(https://www.geeksforgeeks.org/merge-two-sorted-arrays/).
     * 
     * Apparoach: Take a third arrays with size n1+n2 Traverse the two arrays
     * simultaneously, put the smaller of the two in the third array Put the
     * remaining of the two in the third when one list is exhausted
     * 
     * @param a
     */

    public void mergeSortedArrays(int[] arr1, int arr2[]) {
        int arr3[] = new int[arr1.length + arr2.length];

        int i = 0, j = 0, k = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                arr3[k++] = arr1[i++];
            } else {
                arr3[k++] = arr2[j++];
            }
        }

        while (j < arr2.length) {
            arr3[k++] = arr2[j++];
        }

        while (i < arr1.length) {
            arr3[k++] = arr2[i++];
        }

        printArray(arr3);
    }

    /**
     * Merge two arrays with constant space such that initial elements are in the
     * first array and the rest in the
     * second(https://www.geeksforgeeks.org/merge-two-sorted-arrays-o1-extra-space/).
     * 
     * Approach: Take the elements from the second array and try to find the right
     * place for it in the first
     * 
     * Use insertion sort style to make way in the first array, if you find a place,
     * Shift all the elements in the first array to put this element. Move the last
     * element of the first array to the second array
     * 
     * @param arr1
     * @param arr2
     */
    public void mergeSortedWitConstantSpace(int[] arr1, int arr2[]) {

        for (int i = arr2.length - 1; i >= 0; i--) {

            int last = arr1[arr1.length - 1];
            int j = arr1.length - 2;
            for (; arr1[j] > arr2[i] && j > 0; j--) {
                arr1[j + 1] = arr1[j];
            }

            if (j != arr1.length - 2 || last > arr2[i]) {
                arr1[j + 1] = arr2[i];
                arr2[i] = last;
            }

        }
        printArray(arr1);
        printArray(arr2);

    }

    /**
     * Given a sorted array of positive integers, rearrange the array alternately
     * i.e first element should be the maximum value, second minimum value,
     * third-second max, fourth-second min and so
     * on(https://www.geeksforgeeks.org/rearrange-array-maximum-minimum-form-set-2-o1-extra-space/).
     * 
     * Approach: take the max_element as max of array + 1. Start max_index from n-1
     * and min_index from 0
     * 
     * For every element in the array i arr[i] += arr[max_index] % max_element *
     * max_element, max_index-- for even i arr[i] += arr[min_index] % max_element *
     * max_element, min_index++ for odd i
     * 
     * basically two number at one place using modulo and multiplication
     * 
     * @param a
     */

    public void rearrangeMaxMin(int[] arr) {
        int maxElement = arr[arr.length - 1] + 1;

        int maxIndex = arr.length - 1;
        int minIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                arr[i] += arr[maxIndex] % maxElement * maxElement;
                maxIndex--;
            } else {
                arr[i] += arr[minIndex] % maxElement * maxElement;
                minIndex++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] /= maxElement;
        }

        printArray(arr);
    }

    // Function to return count of pairs with x as one element
    // of the pair. It mainly looks for all values in Y[] where
    // x ^ Y[i] > Y[i] ^ x
    private int count(int x, int Y[], int n, int NoOfY[]) {
        // If x is 0, then there cannot be any value in Y such that
        // x^Y[i] > Y[i]^x
        if (x == 0)
            return 0;

        // If x is 1, then the number of pais is equal to number of
        // zeroes in Y[]
        if (x == 1)
            return NoOfY[0];

        // Find number of elements in Y[] with values greater than x
        // getting upperbound of x with binary search
        int idx = Arrays.binarySearch(Y, x);
        int ans;
        if (idx < 0) {
            idx = Math.abs(idx + 1);
            ans = Y.length - idx;
        } else {
            while (idx < n && Y[idx] == x) {
                idx++;
            }
            ans = Y.length - idx;
        }

        // If we have reached here, then x must be greater than 1,
        // increase number of pairs for y=0 and y=1
        ans += (NoOfY[0] + NoOfY[1]);

        // Decrease number of pairs for x=2 and (y=4 or y=3)
        if (x == 2)
            ans -= (NoOfY[3] + NoOfY[4]);

        // Increase number of pairs for x=3 and y=2
        if (x == 3)
            ans += NoOfY[2];

        return ans;
    }

    /**
     * Given two arrays X[] and Y[] of positive integers, find number of pairs such
     * that x^y > y^x where x is an element from X[] and y is an element from Y[].
     * 
     * Approach: The trick here is, if y > x then x^y > y^x with some exceptions.
     * 
     * Following are simple steps based on this trick.
     * 
     * Sort array Y[]. For every x in X[], find the index idx of smallest number
     * greater than x (also called ceil of x) in Y[] using binary search in
     * algorithm library. All the numbers after idx satisfy the relation so just add
     * (n-idx) to the count. Base Cases and Exceptions: Following are exceptions for
     * x from X[] and y from Y[]
     * 
     * If x = 0, then the count of pairs for this x is 0. If x = 1, then the count
     * of pairs for this x is equal to count of 0s in Y[]. x smaller than y means
     * x^y is greater than y^x. x = 2, y = 3 or 4 x = 3, y = 2
     * 
     * @param X
     * @param Y
     * @param m
     * @param n
     * @return
     */
    public int countPairs(int X[], int Y[], int m, int n) {
        // To store counts of 0, 1, 2, 3 and 4 in array Y
        int NoOfY[] = new int[5];
        for (int i = 0; i < n; i++)
            if (Y[i] < 5)
                NoOfY[Y[i]]++;

        // Sort Y[] so that we can do binary search in it
        Arrays.sort(Y);

        int total_pairs = 0; // Initialize result

        // Take every element of X and count pairs with it
        for (int i = 0; i < m; i++)
            total_pairs += count(X[i], Y, n, NoOfY);

        return total_pairs;
    }

    private int mergeAndCount(int[] arr, int l, int m, int r) {
        int[] left = Arrays.copyOfRange(arr, l, m + 1);
        int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

        int count = 0;
        int i = 0, j = 0, k = l;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
                count += (m + 1) - (l + i);
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }

        return count;

    }

    private int mergeSortAndCount(int arr[], int l, int r) {
        int count = 0;

        if (l < r) {
            int mid = (l + r) / 2;
            count += mergeSortAndCount(arr, l, mid);
            count += mergeSortAndCount(arr, mid + 1, r);
            count += mergeAndCount(arr, l, mid, r);
        }
        return count;
    }

    /**
     * Count the number of inversions in an array which is 1 is arr[i] > arr[j] if
     * i<j(https://www.geeksforgeeks.org/counting-inversions/).
     * 
     * Approach: modified merge sort
     * 
     * The number of inversions is the sum of inversions on left and the right plus
     * the inversions during the merge operation If a[i] < a[j] during the merge
     * operation, then inversion is m-i
     * 
     * @param a
     */
    public void countInversions(int[] arr) {
        print(mergeSortAndCount(arr, 0, arr.length - 1));
    }

    /**
     * Sort the elements 0,1,2 in ascending order in an
     * array(https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/).
     * 
     * Approach: take low=0, mid=0, high=n as three pointers For every element,
     * check if arr[mid] = 0, swap arr[low++] and arr[mid++] if arr[mid] = 1, no op
     * if arr[mid] = 2, swap arr[mid] and arr[high--]
     * 
     * @param arr
     */
    public void dutchNationalFlag(int[] arr) {
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid < high) {
            if (arr[mid] == 0) {
                swap(arr, low++, mid++);
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                swap(arr, mid, high--);
            }
        }
        printArray(arr);
    }

    /**
     * Equilibrium index of an array is an index such that the sum of elements at
     * lower indexes is equal to the sum of elements at higher
     * indexes(https://www.geeksforgeeks.org/equilibrium-index-of-an-array/).
     * 
     * Approach: Find the total sum of the array. For every element in the array,
     * total_sum - current_element gives the right sum. If left_sum = right_sum, i
     * is the index
     * 
     * @param a
     */
    public void findEquilibirium(int[] arr) {
        int leftSum = 0;
        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            totalSum = totalSum - arr[i];
            if (leftSum == totalSum) {
                print(i);
                return;
            }
            leftSum += arr[i];
        }
        print(-1);
    }

    /**
     * Write a program to print all the LEADERS in the array. An element is leader
     * if it is greater than all the elements to its right side. And the rightmost
     * element is always a
     * leader(https://www.geeksforgeeks.org/leaders-in-an-array/).
     * 
     * Approach: Scan from right, keep track of maximum element. Print when element
     * in question is greater then the max so far
     * 
     * @param a
     */

    public void findLeader(int[] arr) {
        int max = arr[arr.length - 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] >= max) {
                print(arr[i]);
                max = arr[i];
            }
        }
    }

    /**
     * Given arrival and departure times of all trains that reach a railway station,
     * the task is to find the minimum number of platforms required for the railway
     * station so that no train
     * waits(https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/).
     * 
     * Approach: Sort the arrival and departure times in ascending order. Iterate on
     * both the arrays simultaneously. If the arrival time is less than depature
     * time, platform needed++. Keep count of the maximum number of platforms
     * required at any time
     * 
     * @param a
     */
    public void numberOfPlatformsNeeded(int[] arrival, int[] departure) {
        int platform = 0;
        int maxPlatforms = 0;

        Arrays.sort(arrival);
        Arrays.sort(departure);

        int i = 0, j = 0;
        while (i < arrival.length && j < departure.length) {
            if (arrival[i] <= departure[j]) {
                platform++;
                i++;
                if (platform > maxPlatforms) {
                    maxPlatforms = platform;
                }
            } else {
                platform--;
                j++;
            }
        }

        print(maxPlatforms);

    }

    /**
     * Swap elements in an array in groups of
     * k(https://www.geeksforgeeks.org/reverse-an-array-in-groups-of-given-size/).
     * 
     * Approach: Take elements in group of k and reverse it, if the number of
     * elments left is less than k rever only those
     * 
     * @param a
     */
    public void reverInKGroups(int[] arr, int k) {

        for (int i = 0; i < arr.length; i += k) {
            int left = i;
            int right = Math.min(i + k - 1, arr.length - 1);

            while (left < right) {
                swap(arr, left++, right--);
            }
        }
        printArray(arr);
    }

    private int partition(int[] arr, int l, int r) {
        int x = arr[r], i = l;
        for (int j = l; j <= r - 1; j++) {
            if (arr[j] <= x) {
                // Swapping arr[i] and arr[j]
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }

    /**
     * Given an array and a number k where k is smaller than size of array, we need
     * to find the k’th smallest element in the given array. It is given that all
     * array elements are
     * distinct(https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/).
     * 
     * Approach: We pick a pivot element, then move the pivot element to its correct
     * position and partition the array around it. The idea is, not to do complete
     * quicksort, but stop at the point where pivot itself is k’th smallest element.
     * 
     * @param arr
     * @param l
     * @param r
     * @param k
     * @return
     */
    public int kthSmallest(int[] arr, int l, int r, int k) {
        // If k is smaller than number of elements
        // in array
        if (k > 0 && k <= r - l + 1) {
            // Partition the array around last
            // element and get position of pivot
            // element in sorted array
            int pos = partition(arr, l, r);

            // If position is same as k
            if (pos - l == k - 1)
                return arr[pos];

            // If position is more, recur for
            // left subarray
            if (pos - l > k - 1)
                return kthSmallest(arr, l, pos - 1, k);

            // Else recur for right subarray
            return kthSmallest(arr, pos + 1, r, k - pos + l - 1);
        }

        // If k is more than number of elements
        // in array
        return Integer.MAX_VALUE;
    }

    /**
     * Trap water in building given an array of
     * heights(https://www.geeksforgeeks.org/trapping-rain-water/).
     * 
     * Approach: For every building, figure out the left max height and right max
     * height. Water at that level = min(left_height,right_height) - height of the
     * building
     * 
     * @param a
     */
    public void findTrappedWater(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        left[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            left[i] = Math.max(left[i - 1], arr[i]);
        }

        right[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], arr[i]);
        }

        int water = 0;
        for (int i = 0; i < arr.length; i++) {
            water += Math.min(left[i], right[i]) - arr[i];
        }

        print(water);

    }

    /**
     * Find rain water trapped(https://www.geeksforgeeks.org/trapping-rain-water/).
     * 
     * Approach: Keep the left max for a point and right max for a point. Water is
     * min of left and right minus the building height.
     * 
     * @param arr
     */
    public void findTrappedWaterO1(int[] arr) {

        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];

        int low = 0, high = arr.length - 1;
        int water = 0;
        while (low <= high) {

            if (arr[low] < arr[high]) {

                if (arr[low] > leftMax) {
                    // Found new high
                    leftMax = arr[low];
                } else
                    water += leftMax - arr[low];
                low++;

            } else {
                if (arr[high] > rightMax) {
                    rightMax = arr[high];
                } else
                    water += rightMax - arr[high];
                high--;

            }

        }

        print(water);

    }

    private void findTwoElementsWithSum(int[] arr, int index) {
        int sum = arr[index];

        for (int i = 0, j = index - 1; i <= j;) {
            if (arr[i] + arr[j] == sum) {
                // print(arr[index], arr[i], arr[j]);
                print("Yes");
                return;
            } else if (arr[i] + arr[j] < sum) {
                i++;
            } else {
                j--;
            }
        }
    }

    /**
     * Find pythogorian triplets in an array
     * (https://www.geeksforgeeks.org/find-pythagorean-triplet-in-an-unsorted-array/).
     * 
     * Approach: Sqaure every element in the array and sort them. Fix one element
     * from the right, find two elements summing to the fixed element using meet in
     * the middle
     * 
     * @param arr
     */
    public void pythogoreanTriplet(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * arr[i];
        }
        // printArray(arr);
        Arrays.sort(arr);
        // printArray(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            findTwoElementsWithSum(arr, i);
        }

    }

    /**
     * Given an array of n integers where each value represents number of chocolates
     * in a packet. Each packet can have variable number of chocolates. There are m
     * students, the task is to distribute chocolate packets such that, each student
     * gets one packet. The difference between the number of chocolates in packet
     * with maximum chocolates and packet with minimum chocolates given to the
     * students is
     * minimum(https://www.geeksforgeeks.org/chocolate-distribution-problem/).
     * 
     * Approach: Sort the array and find the consecutive elements of size n which
     * gives min diff
     * 
     * @param arr
     * @param n
     */
    public void chocolateDistribution(int[] arr, int n) {
        Arrays.sort(arr);
        // printArray(arr);
        int j = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i + n - 1 < arr.length; i++) {
            j = i + n - 1;
            int diff = arr[j] - arr[i];
            if (diff < minDiff) {
                // print(i, j);
                minDiff = diff;
            }
        }
        print(minDiff);
    }

    /**
     * Buy and sell stock to maximize the profits, can be bought and sold multiple
     * times(https://www.geeksforgeeks.org/stock-buy-sell/).
     * 
     * Approach: Find the local minima which is a number less than its next number
     * Find the local maxima which is a number greater than its next number. Maxima
     * - minima gives the profit
     * 
     * @param arr
     */
    public void buySellStocks(int[] arr) {

        for (int i = 0; i < arr.length - 1;) {
            while (i < arr.length - 1 && arr[i] >= arr[i + 1]) {
                i++;
            }

            if (i == arr.length - 1) {
                break;
            }

            int buy = i++;
            while (i < arr.length && arr[i] >= arr[i - 1]) {
                i++;
            }
            int sell = i - 1;
            print(buy, sell);
        }

    }

    /**
     * Find the element before which all the elements are smaller than it, and after
     * which all are
     * greater(https://www.geeksforgeeks.org/find-the-element-before-which-all-the-elements-are-smaller-than-it-and-after-which-all-are-greater-than-it/).
     * 
     * Approach: Keep track of running max towards the left of the array. Keep track
     * of running min towards to right of the array.
     * 
     * Find an element which is greater than left max at i and less than right min
     * at i
     * 
     * @param arr
     */
    public void findPivotInUnsortedArray(int[] arr) {
        int[] leftMax = new int[arr.length];
        int[] rightMin = new int[arr.length];

        printArray(arr);
        leftMax[0] = arr[0];// Integer.MIN_VALUE;
        rightMin[arr.length - 1] = arr[arr.length - 1];// Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]);
        }
        printArray(leftMax);

        for (int i = arr.length - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }
        printArray(rightMin);
        for (int i = 0; i < arr.length; i++) {
            print(i);
            if ((i == 0 && arr[i] < rightMin[i + 1]) || (i == arr.length - 1 && arr[i] > leftMax[i - 1])
                    || (arr[i] < rightMin[i + 1] && arr[i] > leftMax[i - 1])) {
                print(arr[i]);
                break;
            }

        }
    }

    /**
     * Convert array into Zig-Zag
     * fashion(https://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion/).
     * 
     * Approach: Keep a flag for relation type which is < or > Compare each element
     * with its next element, if they are not in desirec state according to the flag
     * the swap
     * 
     * @param arr
     */
    public void rearrangeZigzag(int[] arr) {

        boolean flag = true;

        for (int i = 0; i < arr.length - 1; i++) {
            if (flag) { // true is <
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            } else {
                if (arr[i] < arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
            flag = !flag;
        }
        printArray(arr);

    }

    /**
     * Given a sorted array consisting 0’s and 1’s. The problem is to find the index
     * of first ‘1’ in the sorted array. It could be possible that the array
     * consists of only 0’s or only 1’s. If 1’s are not present in the array then
     * print
     * “-1”(https://www.geeksforgeeks.org/find-index-first-1-sorted-array-0s-1s/).
     * 
     * @param arr
     */
    public void findLastIndexOfOne(int[] arr) {

        int low = 0, high = arr.length - 1;
        int index = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if ((arr[mid] == 1) && (mid == 0 || arr[mid - 1] == 0)) {
                index = mid;
                break;
            } else if (arr[mid] == 1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }

        }

        print(index);

    }

    /**
     * Given a 2D array, print it in spiral
     * form(https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/).
     * 
     * @param arr
     * @param i   start index of row
     * @param j   start index of column
     * @param m   the number of rows
     * @param n   the number of columns
     */
    public void print(int arr[][], int i, int j, int m, int n) {
        // If i or j lies outside the matrix
        if (i >= m || j >= n) {
            return;
        }

        // Print First Row
        for (int p = i; p < n; p++) {
            System.out.print(arr[i][p] + " ");
        }

        // Print Last Column
        for (int p = i + 1; p < m; p++) {
            System.out.print(arr[p][n - 1] + " ");
        }

        // Print Last Row, if Last and
        // First Row are not same
        if ((m - 1) != i) {
            for (int p = n - 2; p >= j; p--) {
                System.out.print(arr[m - 1][p] + " ");
            }
        }

        // Print First Column, if Last and
        // First Column are not same
        if ((n - 1) != j) {
            for (int p = m - 2; p > i; p--) {
                System.out.print(arr[p][j] + " ");
            }
        }
        print(arr, i + 1, j + 1, m - 1, n - 1);
    }

    /**
     * Given an array of numbers, arrange them in a way that yields the largest
     * value(https://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/).
     * 
     * @param numbers
     */
    public void largestNumberFromArray(List<String> numbers) {

        Collections.sort(numbers, (x, y) -> {
            var xy = x + y;
            var yx = y + x;
            return xy.compareTo(yx) > 0 ? -1 : 1;
        });

        print(String.join("", numbers));
    }

    public static void main(String... a) {
        SolutionArray s = new SolutionArray();
        // int arr[] = { 15, 2, 4, 8, 9, 5, 10, 23 };
        // int sum = 23;

        // int[] arr2 = { 10, 2, -2, -20, 10 };
        // int sum2 = -10;
        // s.subarrayWithGivenSumWithNegativeNumbers(arr2, sum2);
        // s.subarrayWithGivenSum(arr, sum);

        // int arr3[] = { 5, 32, 1, 7, 10, 50, 19, 21, 2 };
        // s.findTriplets(arr3);

        // int[] arr4 = { -2, -3, 4, -1, -2, 1, 5, -3 };
        // s.maxSumContiguousSubarray(arr4);

        // int arr5[] = { 1, 2, 4, 5, 6 };
        // s.findMissing(arr5, arr5.length);

        // int[] arr6_1 = { 1, 3, 5, 7 };
        // int[] arr6_2 = { 2, 4, 6, 8 };
        // s.mergeSortedArrays(arr6_1, arr6_2);

        // int arr7_1[] = { 1, 5, 9, 10, 15, 20 };
        // int arr7_2[] = { 2, 3, 8, 13 };
        // s.mergeSortedWitConstantSpace(arr7_1, arr7_2);

        // int arr8[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        // s.rearrangeMaxMin(arr8);

        // int[] arr9 = { 1, 20, 6, 4, 5 };
        // s.countInversions(arr9);

        // int[] arr10 = { 0, 1, 2, 0, 2, 0, 1, 2, 0, 0 };
        // s.dutchNationalFlag(arr10);

        // int arr11[] = { -7, 1, 5, 2, -4, 3, 0 };
        // s.findEquilibirium(arr11);

        // int arr12[] = { 16, 17, 4, 3, 5, 2 };
        // s.findLeader(arr12);

        // int arr13_1[] = { 900, 940, 950, 1100, 1500, 1800 };
        // int arr13_2[] = { 910, 1200, 1120, 1130, 1900, 2000 };
        // s.numberOfPlatformsNeeded(arr13_1, arr13_2);

        // int arr14[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
        // s.reverInKGroups(arr14, 3);

        // int arr15[] = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        // s.findTrappedWater(arr15);
        // s.findTrappedWaterO1(arr15);

        // int arr16[] = { 3, 1, 4, 6, 5 };
        // s.pythogoreanTriplet(arr16);

        // int arr17[] = { 12, 4, 7, 9, 2, 23, 25, 41, 30, 40, 28, 42, 30, 44, 48, 43,
        // 50 };
        // s.chocolateDistribution(arr17, 7);

        // int arr18[] = { 100, 180, 260, 310, 40, 535, 695 };
        // s.buySellStocks(arr18);

        // int arr19[] = { 5, 1, 4, 3, 6, 8, 10, 7, 9 };
        // s.findPivotInUnsortedArray(arr19);

        // int arr20[] = { 4, 3, 7, 8, 6, 2, 1 };
        // s.rearrangeZigzag(arr20);

        // int arr21[] = { 0, 0, 0, 0, 0, 0, 1, 1, 1, 1 };
        // s.findLastIndexOfOne(arr21);

        List<String> arr21 = new ArrayList<>(List.of("54", "546", "548", "60"));
        s.largestNumberFromArray(arr21);
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public void print(String... a) {
        for (String i : a) {
            System.out.println(i);
        }
    }

    public void printArray(int[] a) {
        for (int i : a) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }

    public void print(int... a) {
        for (int i : a) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
    }

}