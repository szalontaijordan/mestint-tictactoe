package jordan.szalontai.mestint.tictactoe;

import jordan.szalontai.representation.BoardState;
import jordan.szalontai.representation.State;

/**
 * Class implementing the {@code State} interface.
 * 
 * The state includes a {@code BoardState} and the current player.
 * 
 * @author Szalontai Jordán
 */
public class TicTacToeState implements State {

    private BoardState boardState;
    private int player;

    /**
     * Constructs a state based on the parameters.
     * 
     * @param boardState the board state
     * @param player the player
     */
    public TicTacToeState(BoardState boardState, int player) {
        this.boardState = boardState;
        this.player = player;
    }

    /**
     * Constructs a copy of a state.
     * 
     * @param from the state to copy
     */
    public TicTacToeState(TicTacToeState from) {
        this.boardState = new TicTacToeBoard((TicTacToeBoard) from.boardState);
        this.player = from.player;
    }

    @Override
    public BoardState getBoardState() {
        return boardState;
    }

    @Override
    public int getPlayer() {
        return this.player;
    }

    @Override
    public Integer getWinner() {
        return OperatorImpl.getPrevPlayer(this);
    }

    @Override
    public boolean isOver() {
        return boardState.isFinal();
    }
}