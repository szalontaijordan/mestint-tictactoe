package jordan.szalontai.mestint.tictactoe;

import jordan.szalontai.representation.BoardState;
import jordan.szalontai.representation.Step;

/**
 * Class implementing the {@code Step} interface.
 * 
 * @author Szalontai Jordán
 */
public class StepImpl implements Step {

    private int i;
    private int j;
    private int k;

    /**
     * Constructs a step implementation based on the parameters.
     * 
     * This means, that we put the {@code k} symbol in the {@code i}-th row and {@code j}-th column.
     * 
     * @param i row
     * @param j column
     * @param k symbol
     */
    public StepImpl(int i, int j, int k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    @Override
    public boolean isPossible(BoardState boardState) {
        if (boardState instanceof TicTacToeBoard) {
            return ((TicTacToeBoard) boardState).get(i, j) == 0;
        }
        return false;
    }

    @Override
    public BoardState make(BoardState boardState) {
        if (boardState instanceof TicTacToeBoard) {
            return new TicTacToeBoard((TicTacToeBoard) boardState, i, j, k);
        }
        return null;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getK() {
        return k;
    }
}