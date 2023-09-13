package datamining;

import representation.*;
import java.util.*;

public abstract class AbstractItemsetMiner implements ItemsetMiner{
    protected BooleanDatabase database;
    public static final Comparator<BooleanVariable> COMPARATOR = (var1, var2) -> var1.getName().compareTo(var2.getName());
    
    public AbstractItemsetMiner(BooleanDatabase database){
        this.database = database;
    }

    public BooleanDatabase getDatabase(){
        return this.database;
    }

    public float frequency(Set<BooleanVariable> items){
        float support = 0;
        for(Set<BooleanVariable> transaction: this.database.getTransactions()) {
            if(transaction.containsAll(items)){
                support++;
            }
        }

        float frequence = (float) support/this.database.getTransactions().size();

        return frequence;
    }

    public abstract Set<Itemset> extract(float freqMin);


}
