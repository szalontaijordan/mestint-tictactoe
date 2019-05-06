package jordan.szalontai.mestint.tictactoe;

import java.util.List;

import jordan.szalontai.representation.BoardState;
import jordan.szalontai.representation.Operator;
import jordan.szalontai.representation.State;
import jordan.szalontai.representation.Step;

public class OperatorImpl implements Operator {

    private int i;
    private int j;

    public OperatorImpl(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public boolean isApplicable(State state) {        
        StepImpl corrStep = this.findCorrespondingStep(state);

        return corrStep.isPossible(state.getBoardState());
    }

    @Override
    public State apply(State state) {
        StepImpl corrStep = this.findCorrespondingStep(state);

        BoardState newState = corrStep.make(state.getBoardState());
        int player = getNextPlayer(state);

        return new TicTacToeState(newState, player);
    }

    @Override
    public String toString() {
        return i + "," + j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    private StepImpl findCorrespondingStep(State state) {
        List<Step> steps = Interaction.getInstance().getSteps();

        for (Step step : steps) {
            StepImpl s = (StepImpl) step;

            if (s.getI() == i && s.getJ() == j && s.getK() == state.getPlayer()) {
                return s;
            }
        }

        return null;
    }

    public static int getNextPlayer(State state) {
        return state.getPlayer() == 3 ? 1 : state.getPlayer() + 1;
    }

    public static int getPrevPlayer(State state) {
        return state.getPlayer() == 1 ? 3 : state.getPlayer() - 1;
    }
}
