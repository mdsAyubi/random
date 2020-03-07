import java.util.*;

class SolutionHashing {
    public static void main(String[] args) {
        SolutionHashing s = new SolutionHashing();
        // s.sortByFrequency(new ArrayList<Integer>(List.of(4, 4, 2, 2, 2, 2, 3, 3, 1,
        // 1, 6, 7, 5)));
        // int arr[] = { 15, -2, 2, -8, 1, 7, 10, 23 };
        // s.largestSubarrayWithZeroSum(arr);
        int arr[] = { 92, 75, 65, 48, 45, 35 };
        int k = 10;
        System.out.println(s.divideArrayIntoPairsDivisibleByK(arr, k));
    }

    /**
     * Sort the array based on the relative position of elements in the second
     * array(https://www.geeksforgeeks.org/sort-array-according-order-defined-another-array/)
     * 
     * Approach: Keep the key, count of elements in A1 in a Hashmap. Iterate on A2
     * and, if the element in found in the map, put those many in the output as the
     * count. Remove the element from the map.
     * 
     * Another approach is use a custom comparator
     * 
     * @param A1
     * @param A2
     */
    public void relativeSorting(int[] A1, int[] A2) {

    }

    /**
     * Sort the elements in the array by frequency, if the frequency is same them by
     * value/index(https://www.geeksforgeeks.org/sort-elements-by-frequency-set-5-using-java-map/)
     * 
     * Approach: Keep a map of element and its frequency. Use a `Comparator` while
     * running Collections.sort
     * 
     * @param A
     */
    public void sortByFrequency(List<Integer> A) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : A) {
            int count = map.getOrDefault(i, 0);
            map.put(i, count + 1);
        }

        Collections.sort(A, (first, second) -> {
            int count1 = map.get(first);
            int count2 = map.get(second);

            int diff = count2 - count1;

            if (diff != 0) {
                return diff;
            }
            return first.compareTo(second);
        });
        System.out.println(A.toString());

    }

    /**
     * Find the largest subarray with sum =
     * 0(https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/)
     * 
     * Approach: Iterate over the array and keep track of sum from 0 to ith element
     * in a map<sum, index>
     * 
     * If a sum is found in the map, it means, from the previous index to this
     * index, the sum of elements is zero. Get this difference from prev to current
     * index
     * 
     * @param A
     */
    public void largestSubarrayWithZeroSum(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();

        int sum = 0;
        int maxLength = 0;

        for (int i = 0; i < A.length; i++) {

            sum += A[i];

            if (A[i] == 0 && maxLength == 0) {
                maxLength = 1;
            }

            if (sum == 0) {
                maxLength = i + 1;
            }
            Integer prevIndex = map.get(sum);

            if (prevIndex != null) {
                maxLength = Math.max(maxLength, i - prevIndex);
            } else {
                map.put(sum, i);
            }
        }
        System.out.println(maxLength);
    }

    /**
     * Find four elements that sum to a given value
     * 
     * Approach: First ietrate through all pairs and keep them in a hash table
     * 
     * Iterate again and for every pair find a pair with the value X - currentPair
     * 
     * @param A
     * @param x
     */
    public void fourElementsToSum(int[] A, int x) {

    }

    /**
     * Given an array of size n and an integer k, return the of count of distinct
     * numbers in all windows of size k.
     * 
     * Approach: Use a hashmap to keep the distinct counts of elements
     * 
     * Iterate the array, remove the first element of the previous window, if the
     * removed element had count 1, then remove from the map, and make the
     * distCount--. If not then just decrement the counter.
     * 
     * Add the current element to the map, if it's new, then increment the
     * distCount++. If not then just increment the frequency
     * 
     * @param A
     * @param k
     */
    public void distinctElementsInAWindow(int[] A, int k) {

    }

    /**
     * Check if an array can be divided into pairs whose sum is divisible by k
     * 
     * Approach: Iterate and keep track of the frequency of the remainders
     * 
     * Iterare again, for every element, find the remainder wrt k If the 2*rem == k,
     * then there should be even number of elements with rem. Then only those
     * numbers can be paired where the sum is divisible by k
     * 
     * If the rem is 0, then also the number of such numbers should be even. If not
     * then you can pair the numbers
     * 
     * If the rem is x, then the frequency of the numbers with remainder as k-x
     * should be equal to the frequency of numbers with remainder as x
     * 
     * @param A
     * @param k
     */
    public boolean divideArrayIntoPairsDivisibleByK(int[] A, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            int rem = A[i] % k;
            int count = map.getOrDefault(rem, 0);
            map.put(rem, count + 1);
        }

        for (int i = 0; i < A.length; i++) {
            int rem = A[i] % k;

            if (2 * rem == k) {
                if (map.get(rem) % 2 != 0) {
                    return false;
                }
            } else if (rem == 0) {
                if (map.get(rem) % 2 != 0) {
                    return false;
                }
            } else {
                if (map.get(rem) != map.get(k - rem)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Given an array of integers, find the length of the longest sub-sequence such
     * that elements in the subsequence are consecutive integers
     * 
     * Approach: Put all the elements in hash.
     * 
     * Iterate again, for every element A[i], check if A[i] - 1 is in the hash. If
     * no, then this is the starting point in the array. Run a loop from A[i] till
     * the elements are present in the hash like -- j = A[i];
     * while(hash.contains(j)) j++. Update the max of such numbers
     * 
     * If present then this element can't be the starting point, skip
     * 
     * @param A
     */
    public void consectiveIntegerSubsequence(int[] A) {

    }

    /**
     * Find whether an array is subset of another array
     * 
     * Approach: hash the elements of one array. Iterate over the other array and
     * find if all the elements are present
     * 
     * @param A1
     * @param A2
     * @return
     */
    public boolean arraySubsetOfAnotherArray(int[] A1, int[] A2) {
        return false;
    }

    /**
     * Given two unsorted arrays, find all pairs whose sum is x
     * 
     * Apparaoch: hash the elements of the first array. iterate over the other array
     * and serach for sum - elem in the hash. If found, then print the elem and the
     * hash key
     * 
     * @param A1
     * @param A2
     * @param sum
     */
    public void allPairsWithGivenSum(int[] A1, int[] A2, int sum) {

    }

    /**
     * Find first repeated character
     * 
     * Approach: Iterate and maintain a hash, if the character is found in the hash,
     * return
     * 
     * @param ch
     */
    public void firstRepeatedChar(char[] ch) {

    }

    /**
     * Print all subarrays with 0 sum
     * 
     * Approach: Maintain a hash of sum so far with its index in a map<sum, index>
     * 
     * If the sum is 0, then there is a subarray from 0 to current index. If the sum
     * is present in the map then we have seen this sum before and the elements
     * since that index to the current index are also summing upto 0. Print the
     * elements since the last sum's index to the current index
     * 
     * @param A
     */
    public void allSubArraysWith0Sum(int[] A) {

    }

    /**
     * Find the character in first string that is present at minimum index in second
     * string
     * 
     * Approach: Keep the min index of str in a map<char, minindex>
     * 
     * Iterate over the pat array, search for character in the map, if the index in
     * less than current min index, update current min index
     * 
     * @param str
     * @param pat
     */
    public void mindIndexedCharcter(char[] str, char[] pat) {

    }

    /**
     * Check if two arrays are equal or not
     * 
     * Approach: Keep the count of distinct elements in a map. Iterate on the second
     * array and compare the counts
     * 
     * @param A1
     * @param A2
     * @return
     */
    public boolean isEqualArray(int[] A1, int[] A2) {
        return false;
    }

    /**
     * Find uncommon characters of the two strings
     * 
     * Approach: Iterate on the first array, keep an array[26] of characters and
     * mark the index as 1 Iterate the second array and if the charcater is found in
     * the first array, make it -1 to suggest it is present in both. If not found in
     * first then make it 2 to suggest it is present only in str 2.
     * 
     * In the end, print all characters where count is 1 or 2
     * 
     * @param A1
     * @param A2
     */
    public void uncommonCharacters(char[] A1, char[] A2) {

    }

    /**
     * Check if frequency of all characters can become same by one removal
     * 
     * Approach: Keep the frequency in an array. Change the frequency of charcaters
     * one by one and see if leads to result
     * 
     * @param str
     */
    public void isSameFreq(char[] str) {

    }

    /**
     * First element to occur k times
     * 
     * Approach: Traverse, keep the frequency in a Hash table
     * 
     * @param A
     */
    public void firstElementKTimes(int[] A) {

    }

    /**
     * Find the smallest window in a string containing all characters of another
     * string
     * 
     * Approach: Keep the frequency of characters in a hash for pattern
     * 
     * Iterate over the str, check if between 0 and ith element, all the characters
     * match.
     * 
     * If yes, then check if the frequency of characters from 0..i is more than
     * required. Remove such. Also check if the characters from 0..i don't appear in
     * pat, remove those as well.
     * 
     * @param str
     * @param pat
     */
    public void smallestWindowWithAllCharacters(char[] str, char[] pat) {

    }

}