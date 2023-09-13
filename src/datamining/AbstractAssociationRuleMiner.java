package datamining;

public abstract class AbstractAssociationRuleMiner implements AssociationRuleMiner{
    protected BooleanDatabase database;

    public AbstractAssociationRuleMiner(BooleanDatabase database){
        this.database = database;
    }

    @Override
    public BooleanDatabase getDatabase(){
        return this.database;
    }

    public static float frequency(Set<BooleanVariable> ensembleItems, Set<Itemset> ensembleItemsets){
        for(Set<Itemset> itemset: ensembleItemsets){
            if(itemset.getItems()==ensembleItems){
                return itemset.getFrequency();
            }
        }
        return null;        
    }

    public static float confidence(Set<BooleanVariable> ensembleItemsPremisse, Set<BooleanVariable> ensembleItemsConclusion, Set<Itemset> ensembleItemsets){
        Set<Itemset> union = new HashSet<>();
        return getFrequency()/.getFrequency
    }

    @Override
    public Set<AssociationRule> extract(float frequence, float confianceMin);
}