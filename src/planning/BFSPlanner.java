package planning;
import representation.*;
import java.util.*;

public class BFSPlanner implements Planner{
    protected Map<Variable, Object> etatInit;
    protected Set<Action> ensembleAction;
    protected Goal but;

    public BFSPlanner(Map<Variable, Object> etatInit, Set<Action> ensembleAction, Goal but){
        this.etatInit = etatInit;
        this.ensembleAction = ensembleAction;
        this.but = but;
    }

    @Override
    public List<Action> plan() {
        System.out.println("------------- BFS Plan -----------");

        List<Action> plan = new ArrayList<Action>();

        if(this.but.isSatisfiedBy(this.etatInit)){
            return plan;
        }

        HashSet<Map<Variable, Object>> closed = new HashSet<Map<Variable, Object>>();
        closed.add(this.etatInit);

        Deque<Map<Variable, Object>> open = new LinkedList<Map<Variable, Object>>();
        open.add(this.etatInit);

        HashMap<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<Map<Variable, Object>, Map<Variable, Object>>();
        father.put(this.etatInit, null);


      

        while(!open.isEmpty()){
            Map<Variable, Object> instantiation = open.pop();
            closed.add(instantiation);
            for(Action action : this.ensembleAction){
                System.out.println("----------------------- \n action: \n" + action + "\ninstantiation: " + instantiation);
                if(action.isApplicable(instantiation)){
                    Map<Variable, Object> next = action.successor(instantiation);
                    System.out.println("next etat: " + next);
                    if((!(closed.contains(next)) && !(open.contains(next)))){
                        father.put(next, instantiation);
                        plan.add(action);
                        if(this.but.isSatisfiedBy(next)){
                            return plan;
                        }
                        else{
                            open.addLast(next);
                        }
                    }
                } 
            }
        }
        return null;
    }

    @Override
    public Map<Variable, Object> getInitialState() {
        return this.etatInit;

    }

    @Override
    public Set<Action> getActions() {
        return this.ensembleAction;

    }

    @Override
    public Goal getGoal() {
        return this.but;

    }

}