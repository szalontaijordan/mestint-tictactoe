package jordan.szalontai.mestint.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;
import java.util.stream.Collectors;

import jordan.szalontai.representation.DecisionMaker;
import jordan.szalontai.representation.Operator;
import jordan.szalontai.representation.State;

public class Main {

    private static final int BOUND = 5;
    private static final int NUMBER_OF_PLAYERS = 3;

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
            System.out.println(TicTacToeBoard.getPlayerSymbol(state.getWinner()) + " nyert");
        } catch (IOException e) {
        }
    }

    private static State computerStep(DecisionMaker dm, PrimitiveHeuristics h, State state, List<Operator> applicable) {
        Operator next = dm.maxN(new TicTacToeState((TicTacToeState) state), applicable, BOUND, h);
        state = next.apply(state);
        return state;
    }

    private static State playerStep(State state, List<Operator> applicable) throws IOException {
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

    private static String getInput(String applicableString) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        do {
            System.out.println(applicableString);
            System.out.println();
            input = br.readLine();
        } while (!(input.matches("[0-9],[0-9]") && applicableString.contains(input)));

        return input;
    }

    private static List<Operator> getApplicableOperators(State state) {
        return Interaction.getInstance().getOperators().stream()
            .filter(o -> o.isApplicable(state))
            .collect(Collectors.toList());
    }
}
