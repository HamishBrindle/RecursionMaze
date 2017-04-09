import java.util.Random;

/**
 * @author Hamish
 * @date 2017-04-08
 */
public class Series {

    private static int squares = 12;
    private static int pathCount = 0;
    private static int invalidSquares = 30;

    public static void main(String[] argv) {

        boolean[][] grid = createGrid(squares);
        int[][] paths = new int[squares][squares];

        printGrid(grid);
        countPathways(grid, 0, 0, paths);

        System.out.println("Number of valid pathways: " + getPaths());

    } 

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

    private static boolean validSquare(boolean[][] grid, int row, int col) {
        return row < squares && col < squares && grid[row][col];
    }

    private static boolean lastSquare(boolean[][] grid, int row, int col) {
        return (row == squares - 1 && col == squares - 1);
    }

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

    // 'x' is false square
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

    public static int getPaths() {
        return pathCount;
    }
}
