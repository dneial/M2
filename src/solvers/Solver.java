package solvers;
import java.util.Map;
import representation.*;

//Solver représentant des solvers, donnant des solutions concordant avec les contraintes 
public interface Solver {
	public Map<Variable, Object> solve();

}
