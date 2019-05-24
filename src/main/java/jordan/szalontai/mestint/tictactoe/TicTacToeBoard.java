package jordan.szalontai.mestint.tictactoe;

import java.util.ArrayList;
import java.util.List;

import jordan.szalontai.representation.BoardState;

/**
 * Class implementing the {@code BoardState} interface.
 * 
 * The board of the game is a 4x4 matrix of integer values from 0, to 3. The number 0
 * means that the "cell" of the board is empty, the numbers from 1 to 3 are the
 * symbols of the players.
 * 
 * @author Szalontai Jordán
 */
class TicTacToeBoard implements BoardState {

    /**
     * The size of the board.
     */
    public static final int BOARD_SIZE = 4;
    /**
     * The symbols of the players.
     */
    public static final int[] PLAYERS = { 1, 2, 3 };

    private int[][] board;

    /**
     * Constructs an empty board state. (every cell is 0)
     */
    public TicTacToeBoard() {
        this.board = new int[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Constructs a board state based on another one, with only 1 cell difference.
     * 
     * @param old the another board state
     * @param p the row coordinate of the different cell
     * @param q the column coordinate of the differenct cell
     * @param k the new symbol in the different cell
     */
    public TicTacToeBoard(TicTacToeBoard old, int p, int q, int k) {
        this(old);
        this.board[p][q] = k;
    }

    /**
     * Constructs a deep copy of a board state.
     * 
     * @param from the board state it copies
     */
    public TicTacToeBoard(TicTacToeBoard from) {
        this.board = new int[from.board.length][from.board[0].length];
        for (int i = 0; i < this.board.length; i++) {
            this.board[i] = from.board[i].clone();
        }
    }

    /**
     * Returns the value of a cell based on row and column coordinates.
     * 
     * @param i row coordinate
     * @param j column coordinate
     * @return the value of the cell at {@code [i][j]} or -1 if the coordinates are
     * not in bounds
     */
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

    /**
     * Returns if the current board state indicates a draw.
     * 
     * There is a draw situation if the number of zeros on the board is 0.
     * 
     * @return {@code true} if the state of the board indicates a draw
     * {@code false} otherwise
     */
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

    /**
     * Splits the 4x4 matrix to 4 3x3 matrices.
     * 
     * @param a the matrix to split
     * @return a list of the sub matrices (see the representation or readme)
     */
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

    /**
     * Returns the string represenation of a player's symbol.
     * 
     * @param number the player's symbol
     * @return <ul>
     *  <li>".", if the symbol is 0</li>
     *  <li>"o", if the symbol is 1</li>
     *  <li>"x", if the symbol is 2</li>
     *  <li>"#", if the symbol is 3</li>
     *  <li>empty string otherwise</li>
     * </ul>
     */
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
