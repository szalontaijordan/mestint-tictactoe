package jordan.szalontai.representation;

public interface Step {

    public boolean isPossible(BoardState boardState);

    public BoardState make(BoardState boardState);
}