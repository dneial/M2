package umlutils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Region;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Transition;
import org.eclipse.uml2.uml.Trigger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.Vertex;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallEvent;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Event;
import org.eclipse.uml2.uml.Package;

public class Transformer {
	
	
	public static List<StateMachine> getStateMachines(Class c){
		EList<Behavior> bs = c.getOwnedBehaviors();
		List<StateMachine> sm = new ArrayList<>();
		for(Behavior b: bs) {
			if(b instanceof StateMachine) sm.add((StateMachine) b);
		}
		return sm;
	}
	
	public static boolean isValid(StateMachine sm) {
		return sm.getRegions().size() == 1;
	}
	
	public static List<State> getStates(StateMachine sm) {
		Region r = sm.getRegions().get(0);
		EList<Vertex> vertex = r.getSubvertices();
		List<State> states = new ArrayList<>();
		for(Vertex v: vertex) {
			if(v instanceof State) {
				states.add((State) v);
			}
		}
		return states;
		
	}

	
	public static List<Operation> getOperations(StateMachine sm) {
		Region r = sm.getRegions().get(0);
		EList<Transition> ts = r.getTransitions();
		List<Operation> ops = new ArrayList<>();
		for(Transition t: ts) {
			for(Trigger tr: t.getTriggers()) {
				Event e = tr.getEvent();
				if(e instanceof CallEvent) {
					Operation op = ((CallEvent) e).getOperation();
					if(!ops.contains(op)) ops.add(op);
				}
			}
		}
		return ops;
	}
	
	public static void applyStatePattern(Class c) {
		
		if(getStateMachines(c).isEmpty()) {
			return;
		} 
		StateMachine sm = getStateMachines(c).get(0);
		String className = c.getName();
		String stateClassName = "State" + className;
		Package pack = c.getPackage();
		
		// create State Class
		Class stateClass = UMLFactory.eINSTANCE.createClass();
		pack.getPackagedElements().add(stateClass);
		stateClass.setName(stateClassName);
		stateClass.setIsAbstract(true);
		List<Operation> ops = getOperations(sm); 
		for(Operation op: ops) {
			Operation cOperation = EcoreUtil.copy(op);
			cOperation.setIsAbstract(true);
			stateClass.getOwnedOperations().add(cOperation);
		}
		
		// create State subclasses
		List<State> states = getStates(sm);
		for(State s: states) {
			Class subClass = UMLFactory.eINSTANCE.createClass();
			subClass.setName(s.getName());
			subClass.createGeneralization(stateClass);
			for(Operation op: ops) {
				Operation cOperation = EcoreUtil.copy(op);
				subClass.getOwnedOperations().add(cOperation);
			}
			pack.getPackagedElements().add(subClass);
		}
		
		// create lien association Class c - StateClass
		Property curState = UMLFactory.eINSTANCE.createProperty();
		curState.setType(stateClass);
		curState.setName("currentState");
		curState.setLower(1);
		curState.setUpper(1);
		c.getOwnedAttributes().add(curState);
		
		Property cl = UMLFactory.eINSTANCE.createProperty();
		cl.setType(c);
		cl.setName("class");
		cl.setLower(1);
		cl.setUpper(1);
		stateClass.getOwnedAttributes().add(cl);
		
		Association ass = UMLFactory.eINSTANCE.createAssociation();
		ass.getMemberEnds().add(curState);
		ass.getMemberEnds().add(cl);
		
		pack.getPackagedElements().add(ass);
		
		System.out.println("Saving model...");
		LoadUML.sauverModele("model/statepattern.uml", pack);
		System.out.println("Model saved");
		
		
	}
	
	public void globalApplyStatePattern(String uri) {
		for(NamedElement e: LoadUML.chargerModele(uri).getMembers()) {
			if(e instanceof Class) {
				applyStatePattern((Class) e);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model umlP = LoadUML.chargerModele("model/state.uml");
		Class c = (Class) umlP.getMember("Contexte");
		applyStatePattern(c);

	}

}
