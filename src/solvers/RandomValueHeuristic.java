package solvers;
import java.util.*;
import representation.*;

public class RandomValueHeuristic implements ValueHeuristic{
    private Random genAleatoire;
    public RandomValueHeuristic(Random genAleatoire){
        this.genAleatoire = genAleatoire;
    }

    @Override
    public List<Object> ordering(Variable variable, Set<Object> domaine){
        List<Object> domainListe = new ArrayList<>(domaine);
        List<Object> domainAlea = new ArrayList<>();
        for(int i = 0; i < domainListe.size(); i++){
            int randIdx = this.genAleatoire.nextInt(domainListe.size());
            domainAlea.add(domainListe.get(this.genAleatoire.nextInt(domainListe.size())));
        }
        return domainAlea;
    }
}