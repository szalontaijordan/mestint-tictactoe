package jordan.szalontai.mestint.tictactoe;

import jordan.szalontai.representation.BoardState;
import jordan.szalontai.representation.State;

public class TicTacToeState implements State {

    private BoardState boardState;
    private int player;

    public TicTacToeState(BoardState boardState, int player) {
        this.boardState = boardState;
        this.player = player;
    }

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