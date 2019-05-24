package jordan.szalontai.representation;

/**
 * Interface for a step on the board state.
 */
public interface Step {

    /**
     * Returns if this step can be made on the given board state.
     * 
     * @param boardState the current board state
     * @return {@code true} if this step can be made, {@code false} if not
     */
    public boolean isPossible(BoardState boardState);

    /**
     * Makes the step on the board and returns the new board state.
     * 
     * @param boardState the old board state
     * @return the new board state with the effect of this step
     */
    public BoardState make(BoardState boardState);
}