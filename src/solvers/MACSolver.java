package solvers;
import representation.*;
import java.util.*;

public class MACSolver extends AbstractSolver {
    private ArcConsistency arcCons;
    public MACSolver(Set<Variable> ensembleVar, Set<Constraint> ensembleConst) {
        super(ensembleVar, ensembleConst);
        try {
            this.arcCons = new ArcConsistency(ensembleConst);
        } catch (Exception e) {
            this.arcCons = null;
        }
    }

    public Map<Variable, Object> solve(){
        System.out.println("------ mac solver ------");
        Deque<Variable> variableNonInstanciees;
        if(super.ensembleVar == null){
            variableNonInstanciees = new LinkedList<Variable>();
        } else {
            variableNonInstanciees = new LinkedList<>(super.ensembleVar);
        }
        Map<Variable, Set<Object>> domaines = new HashMap<>();
        for(Variable var : super.ensembleVar){
            domaines.put(var, var.getDomain());
        }
        return this.mac(new HashMap<Variable, Object>(), variableNonInstanciees, domaines);
    }

    public Map<Variable, Object> mac(Map<Variable, Object> instantiationPartielle, Deque<Variable> variableNonInstanciees, Map<Variable, Set<Object>> domaines){
        
        if(variableNonInstanciees.isEmpty()){
            return instantiationPartielle;
        }

        Map<Variable, Set<Object>> varsEtDomaines = new HashMap<>();
        for(Variable variable: super.ensembleVar){
            varsEtDomaines.put(variable, new HashSet<>(domaines.get(variable)));
        }

        if(!this.arcCons.ac1(varsEtDomaines)){
            System.out.println("vars et domaines sont nuls");
            return null;
        }
        Variable var = variableNonInstanciees.pop();
        for(Object value: var.getDomain()){
            instantiationPartielle.put(var, value);
            if(super.isConsistent(instantiationPartielle)){
                Set<Object> domRes = new HashSet<Object>();
                domRes.add(value);
                varsEtDomaines.put(var,domRes);
                Map<Variable, Object> resultat = this.mac(instantiationPartielle, variableNonInstanciees, varsEtDomaines);
                if(resultat != null) {
                    System.out.println("resultat est nul");
                    return resultat;
                }
            }
            instantiationPartielle.remove(var);
        }
        variableNonInstanciees.push(var);

       

        System.out.println("return null");
        return null;

    }
}
