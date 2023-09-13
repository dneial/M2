package datamining;
import java.util.*;
import representation.*;

public class BooleanDatabase{
    protected Set<BooleanVariable> items;
    protected List<Set<BooleanVariable>> transactions;

    public BooleanDatabase(Set<BooleanVariable> items){
        this.transactions = new ArrayList<>();
        this.items = items;
    }

    public void add(Set<BooleanVariable> transaction){
        this.transactions.add(transaction);
    }

    public Set<BooleanVariable> getItems(){
        return this.items;
    }
    
    public List<Set<BooleanVariable>> getTransactions(){
        return this.transactions;
    }
}