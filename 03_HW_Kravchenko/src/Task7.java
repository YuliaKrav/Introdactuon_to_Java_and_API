import java.util.Arrays;

//    7.	На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
public class Task7 {

    private static int boardSize = 9;
    private static int countSolves = 0;

    public static void main(String[] args) {
        char[][] board = new char[boardSize][boardSize];
        playQueens(0, board);
        System.out.println("For board " + boardSize + "x" + boardSize + " = " + countSolves + " arrangements.");
    }


    public static void playQueens(int i, char[][] board) {
        if (i == boardSize) {
            countSolves++;
            printBoard(board);
        }

        char[][] newBoard = copyOfBoard(board);

        for (int j = 0; j < boardSize; j++) {
            if (isPossiblePosition(i, j, newBoard)) {
                setRow(i, j, newBoard);
                playQueens(i + 1, newBoard);
            }
        }
    }


    private static void setRow(int posIQ, int posJQ, char[][] board) {
        for (int j = 0; j < boardSize; j++) {
            board[posIQ][j] = j == posJQ ? 'Q' : ' ';
        }
    }


    public static boolean isPossiblePosition(int posIQ, int posJQ, char[][] board) {
        for (int i = posIQ - 1; i >= 0; i--) {
            if (board[i][posJQ] == 'Q') {
                return false;
            }
        }

        for (int i = posIQ - 1, j = posJQ - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = posIQ - 1, j = posJQ + 1; i >= 0 && j < boardSize; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }


    public static char[][] copyOfBoard(char[][] board) {
        char[][] newBoard = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }


    public static void printBoard(char[][] board) {
        char[] boardLetter = new char[boardSize];
        for (int i = 0; i < boardSize; i++) {
            boardLetter[i] = (char) ('A' + i);
        }
        System.out.println("  " + Arrays.toString(boardLetter));

        int boardNumber = 1;
        for (var line : board) {
            System.out.print(boardNumber + " ");
            boardNumber++;

            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }
}
