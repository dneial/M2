package solvers;

import solvertests.*;

public class TestSolvers {

	public static void main(String[] args) {


		boolean ok = true;
		ok = ok && AbstractSolverTests.testIsConsistent();
		ok = ok && BacktrackSolverTests.testSolve();
		ok = ok && ArcConsistencyTests.testEnforceNodeConsistency();
		ok = ok && ArcConsistencyTests.testRevise();
		ok = ok && ArcConsistencyTests.testAC1();
		ok = ok && MACSolverTests.testSolve();
		// ok = ok && HeuristicMACSolverTests.testSolve();
		// ok = ok && NbConstraintsVariableHeuristicTests.testBest();
		// ok = ok && DomainSizeVariableHeuristicTests.testBest();
		// ok = ok && RandomValueHeuristicTests.testOrdering();
		System.out.println(ok ? "All tests passed" : "At least one test failed");

	}

}
