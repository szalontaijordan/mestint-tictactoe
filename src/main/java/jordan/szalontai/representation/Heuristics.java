package jordan.szalontai.representation;

public interface Heuristics {
    
    public int getValue(State state, int supportedPlayer);

    public int getMax();
}
