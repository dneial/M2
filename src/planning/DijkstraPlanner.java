package planning;

import representation.*;
import java.util.*;


public class DijkstraPlanner implements Planner{
    protected Map<Variable, Object> etatInit;
    protected Set<Action> ensembleAction;
    protected Goal but;

    public DijkstraPlanner(Map<Variable, Object> etatInit, Set<Action> ensembleAction, Goal but){
        this.etatInit = etatInit;
        this.ensembleAction = ensembleAction;
        this.but = but;
    }

    @Override
    public List<Action> plan() {
        System.out.println("------------- Dijkstra Plan -----------");

        List<Action> plan = new ArrayList<>();

        if(this.but.isSatisfiedBy(this.etatInit)){
            return plan;
        }

        Map<Map<Variable, Object>, Float> distance = new HashMap<>();
        distance.put(this.etatInit, (float) 0);

        Deque<Map<Variable, Object>> open = new LinkedList<Map<Variable, Object>>();
        open.add(this.etatInit);

        Map<Map<Variable, Object>, Map<Variable, Object>> father = new HashMap<Map<Variable, Object>, Map<Variable, Object>>();
        father.put(this.etatInit, null);

        List<Map<Variable, Object>> goals = new ArrayList<>();
        

        while(!open.isEmpty()){
            Map<Variable, Object> instantiation = new HashMap<>();
            float distanceMin = Float.MAX_VALUE;
            for(Map<Variable, Object> state : open){
                if(distance.containsKey(state)){
                    if(distance.get(state) < distanceMin){
                        distanceMin = distance.get(state);
                        instantiation = state;
                    }
                }
            }
            open.remove(instantiation);
            System.out.println(instantiation);
            if(this.but.isSatisfiedBy(instantiation)) {
                goals.add(instantiation);
            }
            for(Action action : this.ensembleAction){
                System.out.println("----------------------- \naction: \n" + action + "\ninstantiation: " + instantiation);
                if(action.isApplicable(instantiation)){
                    Map<Variable, Object> next = action.successor(instantiation);
                    System.out.println("next etat: " + next);
                    if(!(distance.containsKey(next))){
                        distance.put(next, Float.MAX_VALUE);
                    }
                    if(distance.get(next) > distance.get(instantiation) + action.getCost()) {
                        distance.put(next, distance.get(instantiation) + action.getCost());
                        father.put(next, instantiation);
                        plan.add(action);
                        open.add(next);
                    }
                }
            }
        }
        if(goals.isEmpty()){
            return null;
        } else {
            return plan;
        }
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
