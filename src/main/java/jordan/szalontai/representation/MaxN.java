package jordan.szalontai.representation;

import java.util.List;

public interface MaxN {
    
    public Operator maxN(State state, List<Operator> applicable, int bound, Heuristics h);
}
