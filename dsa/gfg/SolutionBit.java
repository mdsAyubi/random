
public class SolutionBit {

    public static void main(String[] args) {
        var s = new SolutionBit();
        // s.firstSetBit(16);
        // s.rightMostDifferentBit(52, 4);

        // int n = 5, k = 1;
        // System.out.println(s.kthBitSet(n, k));
        // s.toggleBitsLtoR(50, 2, 5);
        // System.out.println(s.setKthBit(10, 2));
        // System.out.println(s.powerOf2(14));
        // System.out.println(s.bitDifference(10, 20));
        // System.out.println(s.sumOfBitDifference(new int[] { 1, 3, 5 }));
        // System.out.println(s.leftRotateBits(16, 2));
        // System.out.println(s.rightRotateBits(16, 2));
        // System.out.println(s.swapBits(23));
        // System.out.println(s.closestIntegerWithSameWeight(92));
        // System.out.println(s.countSetBits(0xA));
        // System.out.println(s.maxConsecutiveOnes(222));
        // System.out.println(s.isSparse(72));
        // System.out.println(s.aloneInCouplesArray(new int[] { 1, 2, 3, 2, 1 }));
        s.van();
    }

    public void van() {
        double result = (3 / 6) / (15.5 / 33);
        System.out.println(result);
    }

    public int aloneInCouplesArray(int[] A) {
        var result = A[0];
        for (int i = 1; i < A.length; i++) {
            result ^= A[i];
        }
        return result;
    }

    /**
     * Check if a given number is sparse or not
     * 
     * Approach: if the number AND its half, i.e n >> 1 is 0, then the number is
     * sparse
     * 
     * @param n
     * @return
     */
    public boolean isSparse(int n) {
        return (n & (n >> 1)) == 0;
    }

    /**
     * Length of the Longest Consecutive 1s in Binary Representation
     * 
     * Approach: x AND x << 1, removes one treailing 1 from all the consecutive
     * ones. The number of times this operation is needed to reach 0 is the highest
     * consecutive set bit
     * 
     * @param n
     * @return
     */
    public int maxConsecutiveOnes(int n) {
        var count = 0;
        while (n != 0) {
            n &= (n << 1);
            count++;
        }
        return count;
    }

    /**
     * 
     * Approach: Subtracting 1 from a number flips the right most set bit and all
     * the bits after that(e.g 1010 -> 1001).
     * 
     * If you repeatedly do that, the number of times the bits were flipped will
     * give the count of set bits
     * 
     * @param n
     * @return
     */
    public int countSetBits(int n) {
        var count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 
     * @param n
     * @return
     */
    public int closestIntegerWithSameWeight(int n) {
        for (int i = 0; i < 32; i++) {

            if ((n & (1 << i)) != (n & (1 << (i + 1)))) {
                n ^= ((1 << i) | (1 << (i + 1)));
                return n;
            }
        }
        return -1;
    }

    /**
     * 
     * @param n
     * @return
     */
    public int swapBits(int n) {
        int evenBits = n & 0xAAAAAAAA;
        int oddBits = n & 0x55555555;
        return (evenBits >> 1) | (oddBits << 1);
    }

    public int leftRotateBits(int n, int d) {
        var maxBits = 32;
        return (n << d) | (n >> (maxBits - d));
    }

    public int rightRotateBits(int n, int d) {
        var maxBits = 32;
        return (n >> d) | (n << (maxBits - d));
    }

    public int sumOfBitDifference(int[] A) {
        var result = 0;
        for (int i = 0; i < 32; i++) {
            var count = 0;
            for (int j = 0; j < A.length; j++) {
                if ((A[j] & (1 << i)) != 0) {
                    count++;
                }
            }
            result += count * (A.length - count) * 2; // TODO (why this works?)
        }
        return result;
    }

    /**
     * 
     * @param m
     * @param n
     * @return
     */
    public int bitDifference(int m, int n) {
        int d = m ^ n;
        int count = 0;
        while (d != 0) {
            if ((d & 1) == 1) {
                count++;
            }
            d >>= 1;
        }
        return count;
    }

    public boolean powerOf2(int n) {
        return n != 0 && ((n & n - 1) == 0);
    }

    public int setKthBit(int n, int k) {
        return 1 << k | n;
    }

    public void toggleBitsLtoR(int n, int l, int r) {
        int num = (1 << r) - 1 ^ (1 << (l - 1) - 1); // 1 << r makes 2's power, -1 removes 1 and hence all bits get set

        System.out.println(n ^ num);
    }

    public void firstSetBit(int n) {
        var pos = 1;
        var m = 1;
        while ((m & n) == 0) {
            pos++;
            m <<= 1;
        }
        System.out.println(pos);
    }

    public void rightMostDifferentBit(int m, int n) {
        var xored = m ^ n;

        // now find the first set bit
        System.out.println((int) (Math.log10(xored & -xored) / Math.log10(2)) + 1);
    }

    /**
     * 
     * @param n
     * @param k
     * @return
     */
    public boolean kthBitSet(int n, int k) {
        return ((1 << k - 1) & n) != 0;
    }
}