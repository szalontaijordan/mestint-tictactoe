package jordan.szalontai.representation;

public interface State {

    BoardState getBoardState();

    int getPlayer();

    boolean isOver();

    Integer getWinner();
}
