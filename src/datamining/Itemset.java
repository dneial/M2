package datamining;
import java.util.*;
import representation.*;

public class Itemset{
    protected Set<BooleanVariable> items; 
    protected float frequence;

    public Itemset(Set<BooleanVariable> items, float frequence){
        this.items = items;
        this.frequence = frequence;
    }

    public Set<BooleanVariable> getItems(){
        return this.items;
    }
    
    public float getFrequency(){
        return this.frequence;
    }

}
