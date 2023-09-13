package representation;

import java.util.HashSet;
import java.util.Set;

//Classe boolean variable, héritant de variable, représentant une variable dons le domaine est (true,false)
public class BooleanVariable extends Variable{

	private final static Set<Object> domaine = new HashSet<Object>();
	static { //Pour proteger/eviter de modifier les valeurs de l'objet domaine
		domaine.add(true);
		domaine.add(false);
	}
	public BooleanVariable(String nom) {
		super(nom, domaine);
	}

}
