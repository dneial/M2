package solvers;
import java.util.*; 

import representation.*;
public class HeuristicMACSolver extends AbstractSolver{

    protected VariableHeuristic varHeuristic;
    protected ValueHeuristic valHeuristic;

    public HeuristicMACSolver(Set<Variable> ensembleVariables, Set<Constraint> ensembleContraintes, VariableHeuristic varHeuristic, ValueHeuristic valHeuristic){
        super(ensembleVariables, ensembleContraintes);
        this.varHeuristic = varHeuristic;
        this.valHeuristic = valHeuristic;
    }

    @Override
    public Map<Variable, Object> solve(){
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
        return this.macHeuristic(new HashMap<Variable, Object>(), variableNonInstanciees, domaines);
    }

}