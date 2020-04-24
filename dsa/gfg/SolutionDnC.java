import java.util.*;

class SolutionDnC {
    public static void main(String[] args) throws Exception {
        // Empty
        var s = new SolutionDnC();

        // int[] arr = { 1, 1, 2, 4, 4, 5, 5, 6, 6 };
        // System.out.println(s.numberOccuringOnce(arr, 0, arr.length - 1));

        // int arr[] = { 2, 3, 4, 10, 40 };
        // System.out.println(s.binarySearch(arr, 10, 0, arr.length - 1));

        // int arr[] = { 4, 5, 6, 7, 8, 9, 1, 2, 3 };
        // System.out.println(s.searchInSortedAndRotatedArray(arr, 6, 0, arr.length -
        // 1));
        // int arr1[] = { 5, 6, 7, 8, 9, 10, 1, 2, 3 };
        // System.out.println(s.findInRotatedArray(arr1, 3));
        // int[] A = { 1, 2, 5, 11, 15 };
        // int[] B = { 3, 4, 13, 17, 18 };

        // System.out.println(s.medianOfSortedArrays(A, B, 0, A.length - 1, 0, B.length
        // - 1));
        int[] A = { 5, 6, 1, 2, 3, 5, 8, 1, 2, 3, 4, 5 };
        // s.quickSort(A, 0, A.length - 1);
        s.mergeSort(A, 0, A.length - 1);
        System.out.println(Arrays.toString(A));
    }

    /**
     * 
     * @param A
     * @param begin
     * @param end
     */
    public void mergeSort(int[] A, int begin, int end) {
        if (begin >= end) {
            return;
        }

        var mid = (begin + end) / 2;
        mergeSort(A, begin, mid);
        mergeSort(A, mid + 1, end);

        merge(A, begin, mid, end);
    }

    private void merge(int[] A, int l, int m, int r) {
        var n1 = m - l + 1; // A[l..m] if m = 3 and l = 1, then size 3 - 1 + 1
        var n2 = r - m; // A[m+1..r]

        var L = Arrays.copyOfRange(A, l, l + n1);
        var R = Arrays.copyOfRange(A, m + 1, m + 1 + n2);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] < R[j])
                A[k++] = L[i++];
            else
                A[k++] = R[j++];
        }
        while (i < n1)
            A[k++] = L[i++];
        while (j < n2)
            A[k++] = R[j++];

    }

    /**
     * 
     * @param A
     * @param begin
     * @param end
     */
    public void quickSort(int[] A, int begin, int end) {
        if (begin >= end) {
            return;
        }
        var pivot = partition(A, begin, end);
        quickSort(A, begin, pivot - 1);
        quickSort(A, pivot + 1, end);

    }

    private int partition(int[] A, int begin, int end) {
        var pivot = A[end];
        var i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (A[j] < pivot) {
                swap(A, ++i, j);
            }
        }
        swap(A, ++i, end);
        return i;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * 
     * @param A
     * @param B
     * @param startA
     * @param endA
     * @param startB
     * @param endB
     * @return
     */
    public double medianOfSortedArrays(int[] A, int[] B, int startA, int endA, int startB, int endB) {
        // n == 2 case
        if (endA - startA == 1 && endB - startB == 1) {
            return (Math.max(A[startA], B[startB]) + Math.min(A[endA], B[endB])) / 2;
        }

        int m1Index = (endA + startA) / 2;
        int m2Index = (endB + startB) / 2;

        // m1 == m2 case, return m1 or m2
        if (A[m1Index] == B[m2Index]) {
            return A[m1Index];
        }

        // m1 < median < m2, search between m1 and m2 now
        if (A[m1Index] < B[m2Index]) {
            return medianOfSortedArrays(A, B, m1Index, endA, startB, m2Index);
        } else {
            // m2 < median < m1
            return medianOfSortedArrays(A, B, startA, m1Index, m2Index, endB);
        }
    }

    public int searchInSortedAndRotatedArray(int[] A, int key, int low, int high) {
        if (low > high) {
            return -1;
        }

        var mid = (low + high) / 2;
        if (A[mid] == key) {
            return mid;
        }

        if (A[low] <= A[mid]) {
            if (key >= A[low] && key <= A[mid])
                // sorted range and key lies in this range
                return searchInSortedAndRotatedArray(A, key, low, mid - 1);
            else
                return searchInSortedAndRotatedArray(A, key, mid + 1, high);
        }

        if (key >= A[mid] && key <= A[high]) {
            return searchInSortedAndRotatedArray(A, key, mid + 1, high);
        }

        return -1;
        // return searchInSortedAndRotatedArray(A, key, low, high);
    }

    /**
     * Find the element that appears once in a sorted array
     * 
     * @param A
     * @param low
     * @param high
     * @return
     */
    public int numberOccuringOnce(int[] A, int low, int high) {
        if (low > high) {
            return -1;
        }
        if (low == high) {
            return A[low];
        }

        var mid = (low + high) / 2;
        if (mid % 2 == 0) {
            if (A[mid] == A[mid + 1]) {
                return numberOccuringOnce(A, mid + 2, high);
            } else {
                return numberOccuringOnce(A, low, mid);
            }
        } else {
            if (A[mid] == A[mid - 1]) {
                return numberOccuringOnce(A, mid + 1, high);
            } else {
                return numberOccuringOnce(A, low, mid - 1);
            }
        }
    }

    /**
     * Search an element in a sorted and rotated array
     * 
     * Approach: find the pivot element of the rotated array. Search in first array
     * and second.
     * 
     * @param A
     * @param n
     * @return
     */
    public int findInRotatedArray(int[] A, int n) {

        var pivot = findPivotElement(A, 0, A.length - 1);

        if (pivot == -1) {
            return binarySearch(A, n, 0, A.length - 1);
        }

        if (n == A[pivot]) {
            return pivot;
        }

        if (n >= A[0]) {
            binarySearch(A, n, 0, pivot - 1);
        }

        return binarySearch(A, n, pivot + 1, A.length - 1);

    }

    private int findPivotElement(int[] A, int low, int high) {
        if (low > high) {
            return -1;
        }

        if (low == high)
            return low;

        var mid = (low + high) / 2;

        if (mid < high && A[mid] > A[mid + 1]) {
            return mid;
        }

        if (mid > low && A[mid - 1] > A[mid]) {
            return mid - 1;
        }

        if (A[low] >= A[mid]) { // why?
            return findPivotElement(A, low, mid - 1);
        }
        return findPivotElement(A, mid + 1, high);
    }

    /**
     * 
     * @param A
     * @param key
     * @param low
     * @param high
     * @return
     */
    public int binarySearch(int[] A, int key, int low, int high) {
        if (low > high)
            return -1;

        var mid = (low + high) / 2;
        if (A[mid] == key)
            return mid;

        if (key > A[mid]) {
            return binarySearch(A, key, mid + 1, high);
        } else {
            return binarySearch(A, key, low, mid - 1);
        }
    }
}