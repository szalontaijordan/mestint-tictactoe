package jordan.szalontai.mestint.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;
import java.util.stream.Collectors;

import jordan.szalontai.representation.DecisionMaker;
import jordan.szalontai.representation.Operator;
import jordan.szalontai.representation.State;

/**
 * Main class for the game.
 * 
 * @author Szalontai Jordán
 */
public class Main {

    /**
     * Bound value used for computer player 1.
     */
    public static final int BOUND_1 = 5;
    /**
     * Bound value used for computer player 2.
     */
    public static final int BOUND_2 = 5;
    /**
     * Number of players in the game.
     */
    public static final int NUMBER_OF_PLAYERS = 3;

    /**
     * Starts the three-player 4x4 tic-tac-toe.
     * 
     * There are two computer players playing the game. It uses the maxN algorithm
     * to recommend steps. The computer players "accept" its recommendation.
     * 
     * If the game will print the winner player's symbol to the standard output
     * or it will print "döntetlen" if it is a draw situation.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            DecisionMaker dm = new DecisionMaker(NUMBER_OF_PLAYERS);
            PrimitiveHeuristics h = new PrimitiveHeuristics();

            State state = new TicTacToeState(new TicTacToeBoard(), TicTacToeBoard.PLAYERS[0]);

            System.out.println("Start...\n");
            System.out.println(state.getBoardState());

            while (!state.isOver()) {
                System.out.println(TicTacToeBoard.getPlayerSymbol(state.getPlayer()) + "tesz\n");
                
                List<Operator> applicable = getApplicableOperators(state);

                if (state.getPlayer() > 1) {
                    state = computerStep(dm, h, state, applicable);
                } else {
                    state = playerStep(state, applicable);
                }

                System.out.println(state.getBoardState());
            }
            boolean isDraw = state.getWinner() == 0;
            
            if (isDraw) {
                System.out.println("döntetlen");
            } else {
                System.out.println(TicTacToeBoard.getPlayerSymbol(state.getWinner()) + " nyert");
            }
        } catch (IOException e) {
        }
    }

    /**
     * Returns the new state with a computer player's step applied.
     * 
     * The step (operator) is chosen with the maxN algorithm.
     * 
     * @param dm a decision maker object, that has some public methods returning an operator
     * @param h a {@code Heuristics} object
     * @param state the current state
     * @param applicable a list of operators that can be applied to the state
     * @return the new state with the computer's step applied
     */
    public static State computerStep(DecisionMaker dm, PrimitiveHeuristics h, State state, List<Operator> applicable) {
        int bound = state.getPlayer() == 2 ? BOUND_1 : BOUND_2;
        Operator next = dm.maxN(new TicTacToeState((TicTacToeState) state), applicable, bound, h);
        state = next.apply(state);
        return state;
    }

    /**
     * Returns the new state with the player's step applied.
     * 
     * The player is shown a list of applicable operators separated by semi-colons.
     * An operator is written by its {@code i} and {@code j} values (e.g. 0,1).
     * 
     * Then the player can type in one of the applicable operators then it will be applied.
     * 
     * @param state the current state
     * @param applicable a list of operators that can be applied to the state
     * @return the new state with the player's step applied
     * 
     * @throws IOException if there are some errors with reading from the standard input
     */
    public static State playerStep(State state, List<Operator> applicable) throws IOException {
        String applicableString = applicable.stream()
            .map(o -> o.toString())
            .collect(Collectors.joining(" ; "));

        String[] coors = getInput(applicableString).split(",");

        Operator chosen = applicable.stream().filter(o -> {
            int oi = ((OperatorImpl) o).getI();
            int oj = ((OperatorImpl) o).getJ();

            return oi == Integer.parseInt(coors[0]) && oj == Integer.parseInt(coors[1]);
        }).findFirst().get();

        return chosen.apply(state);
    }

    /**
     * Returns the user's input as a string which represents an operator.
     * 
     * @param applicableString a string representation of a list of operators
     * @return the string the user entered as input
     * 
     * @throws IOException if there are some errors with reading from the standard input
     */
    public static String getInput(String applicableString) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        do {
            System.out.println(applicableString);
            System.out.println();
            input = br.readLine();
        } while (!(input.matches("[0-9],[0-9]") && applicableString.contains(input)));

        return input;
    }

    /**
     * Returns the list of the applicable operators to the current state.
     * 
     * @param state the current state
     * @return the list of the applicable operators
     */
    public static List<Operator> getApplicableOperators(State state) {
        return Interaction.getInstance().getOperators().stream()
            .filter(o -> o.isApplicable(state))
            .collect(Collectors.toList());
    }
}
