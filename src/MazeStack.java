public class MazeStack {
    private final int maxSize;  // The max size of the stack
    private final int[][] stackArray;  // The 2d array representing the stack
    private int top;  // The top of the stack

    public MazeStack(int maxSize) {
        this.maxSize = maxSize;
        stackArray = new int[maxSize][2]; // Each element is an int[2] representing [row, col]
        top = -1;
    }

    public void push(int row, int col) {  // Adds new rows and columns to the stack
        top++;
        stackArray[top][0] = row;
        stackArray[top][1] = col;
    }

    public int[] pop() {  // Removes and returns the top rows and columns of the stack
        int[] result = stackArray[top];
        top--;
        return result;
    }

    public int size() {
        return top + 1;
    }

}
