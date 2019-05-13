package jordan.szalontai.mestint.tictactoe;

import jordan.szalontai.representation.Heuristics;
import jordan.szalontai.representation.State;

/**
 * Class implementing the {@code Heuristics} interface.
 * 
 * @author Szalontai Jordán
 */
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