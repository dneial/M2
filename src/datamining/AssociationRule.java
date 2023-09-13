package datamining;

public class AssociationRule{
    protected Set<BooleanVariable> premise, conclusion;
    protected float frequence, confiance;
    public AssociationRule(Set<BooleanVariable> premise, Set<BooleanVariable> conclusion, float frequence, float coonfiance){
        this.premise=premise;
        this.conclusion=conclusion;
        this.frequence=frequence;
        this.confiance=confiance;
    }

    public Set<BooleanVariable> getPremise(){
        return this.premise;
    }
    public Set<BooleanVariable> getConclusion(){
        return this.conclusion;
    }
    public float getFrequence(){
        return this.frequence;
    }
    public float getConfiance(){
        return this.confiance;
    }
}