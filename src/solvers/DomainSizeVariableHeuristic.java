package solvers;
import java.util.*; 

import representation.*;
public class DomainSizeVariableHeuristic implements VariableHeuristic{

    private boolean bool;
 
    public DomainSizeVariableHeuristic(boolean bool){
        this.bool=bool;
    }

    @Override
    public Variable best(Set<Variable> ensembleVariables, Map<Variable, Set<Object>> ensembleDomaine){
        Variable variableRetour = null;
    
        if(this.bool){
            for(Variable variable : ensembleVariables){
                if (variable.getDomain().size() > variableRetour.getDomain().size()){
                    variableRetour = variable;
                }
            }
            return variableRetour;
        }
        else{
            int count = 1000;
            for(Variable variable : ensembleVariables){
                if (variable.getDomain().size() < count){
                    count = variable.getDomain().size();
                    variableRetour = variable;
                }
            }
            return variableRetour;
        }
    }
}