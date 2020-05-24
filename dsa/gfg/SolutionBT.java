import java.util.*;

public class SolutionBT {

    public static void main(String[] args) {
        var s = new SolutionBT();

        // int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0
        // }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
        // { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0,
        // 9, 0, 6, 0, 0 },
        // { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2,
        // 0, 6, 3, 0, 0 } };
        // System.out.println(s.sodoku(grid));
        // for (int[] row : grid)
        // System.out.println(Arrays.toString(row));

        // int maze[][] = { { 1, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 }, { 1, 1, 1, 1
        // } };

        // s.ratInMaze(maze);
        // char boggle[][] = { { 'G', 'I', 'Z' }, { 'U', 'E', 'K' }, { 'Q', 'S', 'E' }
        // };
        // var words = Set.of("GEEKS", "FOR", "QUIZ");
        // s.wordBoggle(boggle, words);

        int board[][] = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        s.nQueen(board);
    }

    /**
     * 
     * @param grid
     */
    public void nQueen(int[][] grid) {
        if (nQueenUtil(grid, 0)) {
            for (var row : grid) {
                System.out.println(Arrays.toString(row));
            }
        } else {
            System.out.println("Not possible");
        }
    }

    private boolean nQueenUtil(int[][] grid, int col) {
        if (col >= grid[0].length) {
            return true;
        }

        for (int row = 0; row < grid.length; row++) {
            if (isSafeForQueen(grid, row, col)) {
                grid[row][col] = 1;
                if (nQueenUtil(grid, col + 1)) {
                    return true;
                }
                grid[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isSafeForQueen(int[][] grid, int row, int col) {
        // check the column
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][col] == 1) {
                return false;
            }
        }

        // check the row
        for (int i = 0; i < grid[row].length; i++) {
            if (grid[row][i] == 1) {
                return false;
            }
        }

        // left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (grid[i][j] == 1) {
                return false;
            }
        }

        // right diagonal
        for (int i = row, j = col; i < grid.length && j >= 0; i++, j--) {
            if (grid[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    /**
     * 
     * @param grid
     * @param words
     */
    public void wordBoggle(char[][] grid, Set<String> words) {
        var M = grid.length;
        var N = grid[0].length;
        var visited = new boolean[M][N];

        var str = "";
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                wordBoggleUtil(grid, words, visited, str, i, j, M, N);
            }
        }
    }

    private void wordBoggleUtil(char[][] grid, Set<String> words, boolean[][] visited, String str, int row, int col,
            int M, int N) {
        visited[row][col] = true;
        var oldStr = new String(str);
        str += grid[row][col];

        if (words.contains(str)) {
            System.out.println(str);
        }

        // all eight sides
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < M && j >= 0 && j < N && !visited[i][j]) {
                    wordBoggleUtil(grid, words, visited, str, i, j, M, N);
                }
            }
        }
        str = new String(oldStr);
        visited[row][col] = false;
    }

    /**
     * 
     * @param maze
     */
    public void ratInMaze(int[][] maze) {
        var solution = new int[maze.length][maze[0].length];

        if (mazeUtil(maze, solution, 0, 0)) {

            for (int[] row : solution) {
                System.out.println(Arrays.toString(row));
            }
        } else {
            System.out.println("No solution");
        }
    }

    private boolean mazeUtil(int[][] maze, int[][] solution, int row, int col) {
        if (row == maze.length - 1 && col == maze[maze.length - 1].length - 1 && maze[row][col] == 1) {
            solution[row][col] = 1;
            return true; // reached the goal
        }

        if (isSafeMazePos(maze, row, col)) {
            solution[row][col] = 1;

            if (mazeUtil(maze, solution, row + 1, col) || mazeUtil(maze, solution, row, col + 1)) {
                return true;
            }
            solution[row][col] = 0;
        }
        return false;
    }

    private boolean isSafeMazePos(int[][] maze, int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[row].length && maze[row][col] == 1;
    }

    /**
     * 
     * @param A
     * @return
     */
    public boolean sodoku(int[][] A) {

        var unassigned = findUnassigned(A);
        if (unassigned[0] == -1 && unassigned[1] == -1) {
            return true; // all spaces are filled
        }

        var row = unassigned[0];
        var col = unassigned[1];
        for (int i = 1; i <= 9; i++) {
            if (isSafe(A, row, col, i)) {
                A[row][col] = i;
                if (sodoku(A)) {
                    return true;
                }
                A[row][col] = 0; // back to old
            }
        }
        return false;
    }

    private boolean isSafe(int[][] A, int row, int col, int num) {
        return isSafeInRow(A, row, col, num) && isSafeInCol(A, row, col, num) && isSafeInBox(A, row, col, num);
    }

    private boolean isSafeInBox(int[][] A, int row, int col, int num) {
        var rowStart = row - row % 3;
        var colStart = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (A[rowStart + i][colStart + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isSafeInCol(int[][] A, int row, int col, int num) {
        for (int i = 0; i < A.length; i++) {
            if (A[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean isSafeInRow(int[][] A, int row, int col, int num) {
        for (int i = 0; i < A.length; i++) {
            if (A[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    private int[] findUnassigned(int[][] A) {
        var rowCol = new int[] { -1, -1 };
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] == 0) {
                    return new int[] { i, j };
                }
            }
        }
        return rowCol;
    }

}