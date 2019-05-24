package jordan.szalontai.representation;

/**
 * Interface for an operator of the representation.
 */
public interface Operator {
    
    /**
     * Returns if this operator is applicable in the current state.
     * 
     * @param state the current state
     * @return {@code true} if the operator is applicable, {@code false} if not
     */
    public boolean isApplicable(State state);
    
    /**
     * Applies this operator to the state and returns with the new one.
     * 
     * @param state the current state
     * @return the new state with the effect of the operator
     */
    public State apply(State state);
}
