package planning;

import java.util.*;


import representation.Variable;

public class AStarPlanner implements Planner{
    protected Map<Variable, Object> etatInit;
    protected Set<Action> ensembleAction;
    protected Goal but;
    protected Heuristic heuristic;

    public AStarPlanner(Map<Variable, Object> etatInit, Set<Action> ensembleAction, Goal but, Heuristic heuristic){
        this.etatInit = etatInit;
        this.ensembleAction = ensembleAction;
        this.but = but;
        this.heuristic = heuristic;
    }

    @Override
    public List<Action> plan() {
        System.out.println("------------- A Star Plan -----------");

        List<Action> plan = new ArrayList<>();

        if(this.but.isSatisfiedBy(this.etatInit)){
            return plan;
        }

        Map<Map<Variable, Object>, Float> distance = new HashMap<>();
        distance.put(this.etatInit, (float) 0);

        Map<Map<Variable, Object>, Float> value = new HashMap<>();
        value.put(this.etatInit, this.heuristic.estimate(this.etatInit));

        Deque<Map<Variable, Object>> open = new LinkedList<Map<Variable, Object>>();
        open.add(this.etatInit);

        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<Map<Variable, Object>, Map<Variable, Object>>();
        father.put(this.etatInit, null);

        

        while(!open.isEmpty()){
            Map<Variable, Object> instantiation = new HashMap<>();
            float valueMin = Float.MAX_VALUE;
            for(Map<Variable, Object> state : open){
                if(value.containsKey(state)){
                    if(value.get(state) < valueMin){
                        valueMin = value.get(state);
                        instantiation = state;
                    }
                }
            }

            if(this.but.isSatisfiedBy(instantiation)) {
                return plan;
            } 

            open.remove(instantiation);
            

            for(Action action : this.ensembleAction){
                System.out.println("----------------------- \naction: \n" + action + "\ninstantiation: " + instantiation);
                if(action.isApplicable(instantiation)){
                    Map<Variable, Object> next = action.successor(instantiation);
                    System.out.println("next etat: " + next);
                    if(!(distance.containsKey(next))){
                        distance.put(next, Float.MAX_VALUE);
                    }
                    if(distance.get(next) > distance.get(instantiation) + action.getCost()){
                        distance.put(next, distance.get(instantiation) + action.getCost());
                        value.put(next, distance.get(next) + this.heuristic.estimate(next));
                        father.put(next, instantiation);
                        plan.add(action);
                        open.add(next);
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
