package planning;

import representation.*;
import planningtests.*;
import java.util.*;

public class Tests{

    public static void main(String[] args){
        boolean ok = true;
        // ok = ok && BasicActionTests.testIsApplicable();
        // ok = ok && BasicActionTests.testSuccessor();
        // ok = ok && BasicActionTests.testGetCost();
        // ok = ok && BasicGoalTests.testIsSatisfiedBy();
        // ok = ok && DFSPlannerTests.testPlan();
        // ok = ok && BFSPlannerTests.testPlan();
        // ok = ok && AStarPlannerTests.testPlan()
        ok = ok && DijkstraPlannerTests.testPlan();
        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }

}