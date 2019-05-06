package jordan.szalontai.representation;

public interface Operator {
    
    public boolean isApplicable(State state);
    
    public State apply(State state);
}
