import java.util.Random;

/**
 * An exercise in recursion. Prints a maze to be navigated moving only down and to the right and returns
 * the number of times the program was able to reach the far bottom-corner (a maximum times of 2 from the
 * looks of it).
 *
 * I'd like to get all the permutations of the ways to get there but I couldn't quite figure it out. I'm studying
 * for finals so I decided to move on.
 *
 * @author Hamish
 * @date 2017-04-08
 */
public class RecursionMaze {

    /*
    Number of columns and rows in the maze.
     */
    private static int squares = 12;

    /*
    Number of valid pathways through the maze.
     */
    private static int pathCount = 0;

    /*
    Number of invalid squares in the maze (higher numbers lower success rate).
     */
    private static int invalidSquares = 50;

    /**
     * Driver.
     *
     * @param argv
     */
    public static void main(String[] argv) {

        // Create the maze and memoization store.
        boolean[][] grid = createGrid(squares);
        int[][] paths = new int[squares][squares];

        // Display the grid in console.
        printGrid(grid);

        // Engage recursive function; traverse through maze.
        countPathways(grid, 0, 0, paths);

        System.out.println("Number of valid pathways: " + pathCount);

    }

    /**
     * Recursively progress through the maze.
     * @param grid  The maze being traversed.
     * @param row   Row.
     * @param col   Column.
     * @param paths Memoization storage.
     * @return Recursive method.
     */
    private static int countPathways(boolean grid[][], int row, int col, int[][] paths) {

        if (!validSquare(grid, row, col)) {
            return 0;
        }

        // Check for end of grid.
        if (lastSquare(grid, row, col)) {
            pathCount++;
            return 1;
        }

        // Memoization; check if computation has been done already.
        if (paths[row][col] == 0) {
            paths[row][col] = countPathways(grid, row + 1, col, paths) + countPathways(grid, row, col + 1, paths);
        }

        return paths[row][col];
    }

    /**
     * Validate square within the maze.
     * @param grid  The maze being traversed.
     * @param row   Row.
     * @param col   Column.
     * @return True if valid square, else false.
     */
    private static boolean validSquare(boolean[][] grid, int row, int col) {
        return row < squares && col < squares && grid[row][col];
    }

    /**
     * Validate a successful finish at the end of the maze.
     * @param grid  The maze being traversed.
     * @param row   Row.
     * @param col   Column.
     * @return True if valid square, else false.
     */
    private static boolean lastSquare(boolean[][] grid, int row, int col) {
        return (row == squares - 1 && col == squares - 1);
    }

    /**
     * Create a maze with randomized pathways to the finish. Number of invalid squares defined by
     * 'invalidSquares' variable.
     * @param size  The dimensions of the maze.
     * @return A maze with dimension of 'size' instance variable.
     */
    private static boolean[][] createGrid(int size) {

        boolean[][] grid = new boolean[size][size];
        Random gen = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = true;
            }
        }

        while (invalidSquares > 0) {
            int randRow = gen.nextInt(size);
            int randCol = gen.nextInt(size);
            grid[randRow][randCol] = false;
            invalidSquares--;
        }

        if (!grid[0][0])
            grid[0][0] = true;

        if (!grid[size-1][size-1])
            grid[size-1][size-1] = true;

        return grid;
    }

    /**
     * Prints a simple rendition of the randomly generated maze.
     * @param grid  The maze being printed.
     */
    private static void printGrid(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            System.out.print("|");
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j])
                    System.out.print("   |");
                else
                    System.out.print(" x |");
            }
            System.out.println();
        }
    }

}
