package representation;

import java.util.*;

//interface Constraint représentant une contrainte quelconque
public interface Constraint {
	//getScope retourne l'enselble des variables sur lesquelles proutte la contrainte
	public Set<Variable> getScope();
	//isSatisfiedBy retourne un boolean selon que la contrainte est satisfaite ou non
	public boolean isSatisfiedBy(Map<Variable, Object> map);

}
