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

    /**
     * Fill the pixel adjacent to the given pixel with the same color
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
     * Reach a cell at row r and column c
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
}