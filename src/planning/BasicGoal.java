package planning;
import representation.*;
import java.util.*;

public class BasicGoal implements Goal {
    protected Map<Variable, Object> buts;

    public BasicGoal(Map<Variable, Object> buts) {
        this.buts = buts;
    }
    public boolean isSatisfiedBy(Map<Variable,Object> etat) {
        return etat.entrySet().containsAll(this.buts.entrySet());
    }
    
    public String toString() {
        return this.buts + "\n";
    }
}