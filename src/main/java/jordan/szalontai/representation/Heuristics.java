package jordan.szalontai.representation;

/**
 * Interface for defining a heuristic function like object.
 */
public interface Heuristics {
    
    /**
     * Returns an integer value of heuristics in favor of the supported player
     * based on the given state.
     * 
     * @param state the state
     * @param supportedPlayer the supported player's symbol
     * @return an integer heuristic value
     */
    public int getValue(State state, int supportedPlayer);

    /**
     * Returns the possible maximum integer value of the heuristics.
     * 
     * @return the possible maximum integer value of the heuristics
     */
    public int getMax();
}
