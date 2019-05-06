package jordan.szalontai.mestint.tictactoe;

import jordan.szalontai.representation.Heuristics;
import jordan.szalontai.representation.State;

public class PrimitiveHeuristics implements Heuristics {

    @Override
    public int getValue(State state, int supportedPlayer) {
        return 0;
    }

    @Override
    public int getMax() {
        return 10;
    }
}