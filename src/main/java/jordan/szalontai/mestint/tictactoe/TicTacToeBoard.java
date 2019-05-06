package jordan.szalontai.mestint.tictactoe;

import java.util.ArrayList;
import java.util.List;

import jordan.szalontai.representation.BoardState;

class TicTacToeBoard implements BoardState {

    public static final int BOARD_SIZE = 4;
    public static final int[] PLAYERS = { 1, 2, 3 };

    private int[][] board;

    public TicTacToeBoard() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    public TicTacToeBoard(TicTacToeBoard old, int p, int q, int k) {
        this(old);
        this.board[p][q] = k;
    }

    public TicTacToeBoard(TicTacToeBoard from) {
        this.board = new int[from.board.length][from.board[0].length];
        for (int i = 0; i < this.board.length; i++) {
            this.board[i] = from.board[i].clone();
        }
    }

    public int get(int i, int j) {
        try {
            return board[i][j];
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }
        
    }

    @Override
    public boolean isFinal() {
        List<int[][]> slices = S(board);

        for (int[][] s: slices) {
            boolean P = isThreeInRow(s) || isThreeInCol(s) || isThreeInDiag(s);
            if (P) {
                return true;
            }
        }
        return this.isDraw();
    }

    @Override
    public String toString() {
        String re = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                re += getPlayerSymbol(board[i][j]);
            }
            re += "\n";
        }
        return re;
    }

    public boolean isDraw() {
        int count = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    count++;
                }
            }
        }

        return count == 0;
    }

    private boolean isThreeInRow(int[][] s) {
        for (int i = 0; i < s.length; i++) {
            if (s[i][0] != 0 && s[i][0] == s[i][1] && s[i][1] == s[i][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean isThreeInCol(int[][] s) {
        for (int j = 0; j < s.length; j++) {
            if (s[0][j] != 0 && s[0][j] == s[1][j] && s[1][j] == s[2][j]) {
                return true;
            }
        }
        return false;
    }

    private boolean isThreeInDiag(int[][] s) {
        boolean diag1 = s[0][0] == s[1][1] && s[1][1] == s[2][2];
        boolean diag2 = s[2][0] == s[1][1] && s[1][1] == s[0][2];

        return s[1][1] != 0 && (diag1 || diag2);
    }

    public static List<int[][]> S(int[][] a) {
        List<int[][]> parts = new ArrayList<>();
        parts.add(new int[][] {
            { a[0][0], a[0][1], a[0][2] },
            { a[1][0], a[1][1], a[1][2] },
            { a[2][0], a[2][1], a[2][2] }
        });
        parts.add(new int[][] {
            { a[0][1], a[0][2], a[0][3] },
            { a[1][1], a[1][2], a[1][3] },
            { a[2][1], a[2][2], a[2][3] },
        });
        parts.add(new int[][] {
            { a[1][0], a[1][1], a[1][2] },
            { a[2][0], a[2][1], a[2][2] },
            { a[3][0], a[3][1], a[3][2] }
        });
        parts.add(new int[][] {
            { a[1][1], a[1][2], a[1][3] },
            { a[2][1], a[2][2], a[2][3] },
            { a[3][1], a[3][2], a[3][3] }
        });
        return parts;
    }

    public static String getPlayerSymbol(int number) {
        switch (number) {
            case 0:
                return ". ";
            case 1:
                return "o ";
            case 2:
                return "x ";
            case 3:
                return "# ";
        }
        return "  ";
    }
}
