package jordan.szalontai.mestint.tictactoe;

import java.util.ArrayList;
import java.util.List;

import jordan.szalontai.representation.Operator;
import jordan.szalontai.representation.Step;

/**
 * Singleton class containing the list of all possible steps and operators.
 * 
 * @author Szalontai Jordán
 */
public class Interaction {

    private static Interaction interaction;

    private List<Step> steps;
    private List<Operator> operators;

    private Interaction() {
        this.steps = new ArrayList<>();
        this.operators = new ArrayList<>();

        for (int i = 0; i < TicTacToeBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < TicTacToeBoard.BOARD_SIZE; j++) {
                this.operators.add(new OperatorImpl(i, j));

                for (int k = 0; k < TicTacToeBoard.PLAYERS.length; k++) {
                    this.steps.add(new StepImpl(i, j, TicTacToeBoard.PLAYERS[k]));
                }
            }
        }
    }

    /**
     * Returns the singleton instance of this class.
     * 
     * @return the singleton instance of this class
     */
    public static Interaction getInstance() {
        if (interaction == null) {
            interaction = new Interaction();
        }
        return interaction;
    }

    public List<Step> getSteps() {
        return this.steps;
    }

    public List<Operator> getOperators() {
        return this.operators;
    }
}
