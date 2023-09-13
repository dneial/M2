package solvers;
import java.util.*; 

import representation.*;
public class NbConstraintsVariableHeuristic implements VariableHeuristic{
    private Set<Constraint> ensembleConst;
    private boolean bool;

    public NbConstraintsVariableHeuristic(Set<Constraint> ensembleConst, boolean bool){
        this.ensembleConst = ensembleConst;
        this.bool = bool;
    }

    @Override
    public Variable best(Set<Variable> ensembleVariables, Map<Variable, Set<Object>> ensembleDomaine){
        Variable varRetour = null;
        if(this.bool){
            int countVarPrec = 0;
            for(Variable var : ensembleVariables){
                int countVarEnCours = 0;
                for(Constraint contrainte : this.ensembleConst){
                    if(contrainte.getScope().contains(var)){
                        countVarEnCours++;
                    }
                }
                if(countVarPrec < countVarEnCours){
                    varRetour = var;
                }
            }
            return varRetour;
        }
        else{
            int countVarPrec = 100000;
            for(Variable var : ensembleVariables){
                int countVarEnCours = 0;
                for(Constraint contrainte : this.ensembleConst){
                    if(contrainte.getScope().contains(var)){
                        countVarEnCours++;
                    }
                }
                if(countVarPrec>countVarEnCours){
                    varRetour=var;
                }

            }
            return varRetour;
        }
    }

}