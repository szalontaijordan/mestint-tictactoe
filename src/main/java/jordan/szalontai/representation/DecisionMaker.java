package jordan.szalontai.representation;

import java.util.List;

public class DecisionMaker implements MaxN {

    private final int numberOfPlayers;

    public DecisionMaker(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public Operator maxN(State state, List<Operator> applicable, int bound, Heuristics h) {
        return getMaxNRecord(state, applicable, bound, h).getOperator();
    }

    private Record getMaxNRecord(State state, List<Operator> operators, int bound, Heuristics h) {
        if (state.isOver()) {
            return new Record(null, calculateOverRecordGoodness(state, h));
        }

        if (bound == 0) {
            return new Record(null, calculateBoundZeroRecordGoodness(h, state));
        }

        Record max = null;
        for (Operator o : operators) {
            if (o.isApplicable(state)) {
                State newState = o.apply(state);
                Record newRecord = getMaxNRecord(newState, operators, bound - 1, h);
                newRecord.setOperator(o);

                if (max == null || newRecord.getGoodness(state.getPlayer()) > max.getGoodness(state.getPlayer())) {
                    max = newRecord;
                }
            }
        }
        return max;
    }

    private int[] calculateBoundZeroRecordGoodness(Heuristics h, State state) {
        int[] resultRecord = new int[this.numberOfPlayers];
        for (int i = 0; i < this.numberOfPlayers; i++) {
            resultRecord[i] = h.getValue(state, i);
        }
        return resultRecord;
    }

    private int[] calculateOverRecordGoodness(State state, Heuristics h) {
        int[] resultRecord = new int[this.numberOfPlayers];
        if (state.getWinner() != null) {
            for (int i = 0; i < this.numberOfPlayers; i++) {
                if (((int) state.getWinner()) == i + 1) {
                    resultRecord[i] = h.getMax();
                } else {
                    resultRecord[i] = -1 * h.getMax();
                }
            }
        }
        return resultRecord;
    }
}
