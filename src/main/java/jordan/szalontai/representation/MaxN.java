package jordan.szalontai.representation;

import java.util.List;

/**
 * Interface for the MaxN decision maker algorithm.
 */
public interface MaxN {
    
    /**
     * Returns the operator, which is a suggested step in the current state.
     * 
     * The MaxN algorithm defines a structure called {@code Record} which contains
     * goodness values for a state and an operator which was used to create the state.
     * 
     * The algorithm should have a bound which limits the depth of the game tree.
     * 
     * @param state the current state
     * @param applicable the list of applicable operators
     * @param bound a bound that should be used to limit the depth of the game tree
     * @param h heuristics
     * 
     * @return an operator to apply which is the most favorable regarding the current state
     */
    public Operator maxN(State state, List<Operator> applicable, int bound, Heuristics h);
}
