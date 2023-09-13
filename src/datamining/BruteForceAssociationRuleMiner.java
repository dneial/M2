package datamining;
public class BruteForceAssociationRuleMiner extends AbstractAssociationRuleMiner{
    protected BooleanDatabase database;

    public BruteForceAssociationRuleMiner(BooleanDatabase database){
        this.database=database;
    }

    public static Set<Set<BooleanVariable>> allCandidatePremises(Set<BooleanVariable> ensembleItems){

    }

    public Set<AssociationRule> extract(float frequence, float confianceMin){

    }
}