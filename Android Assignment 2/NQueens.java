import java.util.*;

public class NQueens {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter value of n");
        int n = sc.nextInt();
        List<List<String>> solutions = solveNQueens(n);
        System.out.println("Possible solutions are ");
        for (List<String> board : solutions) {
            for (String row : board)
                System.out.println(row);
            System.out.println();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board)
            Arrays.fill(row, '.');
        backtrack(0, board, res);
        return res;
    }

    public static void backtrack(int row, char[][] board, List<List<String>> res) {
        if (row == board.length) {
            List<String> temp = new ArrayList<>();
            for (char[] r : board)
                temp.add(new String(r));
            res.add(temp);
            return;
        }
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(row + 1, board, res);
                board[row][col] = '.';
            }
        }
    }

    public static boolean isValid(char[][] board, int row, int col) {
        for (int i = 0; i < row; i++)
            if (board[i][col] == 'Q')
                return false;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q')
                return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++)
            if (board[i][j] == 'Q')
                return false;
        return true;
    }
}
