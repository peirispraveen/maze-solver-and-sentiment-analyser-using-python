public class MazeStack {
    private final int maxSize;
    private final int[][] stackArray;
    private int top;

    public MazeStack(int maxSize) {
        this.maxSize = maxSize;
        stackArray = new int[maxSize][2]; // Each element is an int[2] representing [row, col]
        top = -1;
    }

    public void push(int row, int col) {
        top++;
        stackArray[top][0] = row;
        stackArray[top][1] = col;
    }

    public int[] pop() {
        int[] result = stackArray[top];
        top--;
        return result;
    }

//    public boolean isEmpty() {
//        return top == -1;
//    }

    public int size() {
        return top + 1;
    }

//    public int[] peek() {
//        if (isEmpty()) {
//            throw new IllegalStateException("Stack is empty");
//        }
//        return stackArray[top];
//    }
}
