package planning;
import java.util.*;
import representation.*;

public class BasicAction implements Action{
    protected Map<Variable,Object> precond, effet;
    protected int cout;

    public BasicAction(Map<Variable,Object> precond, Map<Variable,Object> effet, int cout){
        this.precond = precond;
        this.effet = effet;
        this.cout = cout;
    }

    public boolean isApplicable(Map<Variable,Object> etat){
        return etat.entrySet().containsAll(this.precond.entrySet());
    }

    public Map<Variable,Object> successor(Map<Variable,Object> etat){
        StackWalker walker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        Class<?> callerClass = walker.getCallerClass();
        System.out.println("caller class: " + callerClass);
        System.out.println("successor call with etat: " + etat);
        if(etat.isEmpty()) {
            return this.effet;
        }

        Map<Variable,Object> newEtat = new HashMap<>();
        for(Variable var : etat.keySet()) {
            newEtat.put(var,etat.get(var));
        }

        if(this.isApplicable(newEtat)) {
            System.out.println("ok");
            newEtat.putAll(this.effet);
        } else {
            throw new IllegalArgumentException("non applicable");
        }

        return newEtat;

    }
    
    public int getCost(){
        return this.cout;
    }

    public String toString(){
        return "precond: " + this.precond + "\n" + "effet: " + this.effet + "\n" + "cout: " + this.cout;
    }

}