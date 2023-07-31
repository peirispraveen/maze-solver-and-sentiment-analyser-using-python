import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeSolver {
    private char[][] maze;  // Represents the maze
    private int rows;
    private int cols;
    private int startRow;  // Starting row of S
    private int startCol;  // Starting column of S
    private int endRow;  // Ending row of E
    private int endCol;  // Ending column of E

    public MazeSolver(String fileName) {
        parseMazeFile(fileName);
    }

    private void parseMazeFile(String fileName) {  // Reading the maze text file
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            rows = line.length();  // Number of rows
            cols = line.split("").length;  // Number of columns in each row
            maze = new char[rows][cols];

            int row = 0;
            while (line != null) {
                for (int col = 0; col < cols; col++) {
                    maze[row][col] = line.charAt(col);
                    if (maze[row][col] == 'S') {  // Scanning the starting point
                        startRow = row;
                        startCol = col;
                    } else if (maze[row][col] == 'E') {  // Scanning the ending point
                        endRow = row;
                        endCol = col;
                    }
                }
                row++;
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMaze() { // This will print the current view of the maze
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public void solveMaze() {
        MazeStack stack = new MazeStack(rows * cols); // Create a new MazeStack stack

        boolean[][] visited = new boolean[rows][cols]; // Records the visited paths for backtracking
        boolean foundDestination = findPath(stack, startRow, startCol, visited);

        System.out.println();
        if (foundDestination) {
            System.out.println("Path Found!");
        } else {
            System.out.println("No Path Found!");
        }
        System.out.println();
    }

    private boolean findPath(MazeStack stack, int row, int col, boolean[][] visited) {
        if (!isValid(row, col) || visited[row][col] || maze[row][col] == '#') {  // Validity of boundaries
            return false;
        }

        stack.push(row, col); // Push the current position onto the MazeStack
        visited[row][col] = true;

        if (row == endRow && col == endCol) {
            return true;
        }

        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // Right, Down, Left, Up directions
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (findPath(stack, newRow, newCol, visited)) {
                maze[row][col] = '*'; // Mark the valid path with '*'
                return true;
            }
        }

        stack.pop(); // Pop the current position from the MazeStack as it doesn't lead to the destination
        return false;
    }

    private boolean isValid(int row, int col) {  // Check if the rows and columns are valid
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static void main(String[] args) {
        MazeSolver mazeSolver = new MazeSolver("maze6.txt");
        System.out.println();
        mazeSolver.printMaze();
        mazeSolver.solveMaze();
        mazeSolver.printMaze(); // Print the modified maze with the path highlighted
    }
}
