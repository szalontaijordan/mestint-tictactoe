package jordan.szalontai.representation;

/**
 * Class for a record that is associated with the nodes of the game tree.
 */
public class Record {

    private Operator operator;
    private int[] goodness;

    /**
     * Contstructs a record for a node of the game tree.
     * 
     * @param operator the operator creating the node this record is associated with
     * @param goodness an array containing goodness values for each player
     */
    public Record(Operator operator, int[] goodness) {
        this.operator = operator;
        this.goodness = goodness;
    }

    public Operator getOperator() {
        return operator;
    }

    public int[] getGoodness() {
        return goodness;
    }
    
    public int getGoodness(int player) {
        return goodness[player - 1];
    }

    public void setOperator(Operator o) {
        this.operator = o;
    }

    @Override
    public String toString() {
        return "Record{" + "operator=" + operator + ", goodness=" + goodness + '}';
    }
}
