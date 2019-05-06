package jordan.szalontai.representation;

public class Record {

    private Operator operator;
    private int[] goodness;

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
