package datamining;

public interface AssociationRuleMiner{
    public BooleanDatabas getDatabase();
    public Set<AssociationRule> extract(float frequence, float confianceMin);
}