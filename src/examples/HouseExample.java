package examples;

import solvers.*;
import representation.*;
import planning.*;

import java.util.*;


import datamining.*;

public class HouseExample{
  protected int largeur, longueur;
  protected Set<Object> domaine;
  protected BooleanVariable dalleCoulee; 
  protected BooleanVariable solHumide;
  protected BooleanVariable mursEleves;
  protected BooleanVariable toitureTerminee;
  protected Set<Variable> pieces;
  protected Set<Constraint> contraintes;
  public static Set<String> salles_deau = new HashSet<>(){{
    add("Salle de bain");
    add("WC");
    add("cuisine");
  }};
  protected String[][] grille_maison;
 
  public HouseExample(int largeur, int longueur, Set<Object> domaine){
    this.largeur = largeur;
    this.longueur = longueur;
    this.domaine = domaine;
    this.dalleCoulee = new BooleanVariable("dalleCoulee");
    this.solHumide = new BooleanVariable("solHumide");
    this.mursEleves = new BooleanVariable("mursEleves");
    this.toitureTerminee = new BooleanVariable("toitureTerminee");
    this.pieces = this.creerPieces();
    this.contraintes = this.creerContraintes();
    this.grille_maison = new String[this.longueur][this.largeur];
  }

  private Set<Constraint> creerContraintes() {
    Set<Constraint> contraintes = new HashSet<>();
    Constraint toitAvantMur = new Implication(this.mursEleves, false, this.toitureTerminee, false);
    Constraint mursSurSolHumide = new Implication(this.solHumide, true, this.mursEleves, false);
    
    contraintes.add(toitAvantMur);
    contraintes.add(mursSurSolHumide);
    return contraintes;
  }

  public Set<Variable> creerPieces(){
    Set<Variable> pieces = new HashSet<Variable>();
    for(int i=0; i<this.longueur; i++){
      for(int j=0; j<this.largeur; j++){
        Variable variable = new Variable("piece (" + i + "," + j + ")", this.domaine);
        pieces.add(variable);
      }
    }
    return pieces;
  }

  public Set<Variable> getVariables(){
    Set<Variable> resultat = new HashSet<Variable>();
    resultat.addAll(this.pieces);
		resultat.add(this.dalleCoulee);
		resultat.add(this.solHumide);
    resultat.add(this.mursEleves);
    resultat.add(this.toitureTerminee);
		return resultat;
  }
 
  public Set<Constraint> getConstraints(){
    return null;
  }

  public Set<Action> creerActions(){
    Set<Action> actions = new HashSet<Action>();
    Map<Variable,Object> precond = new HashMap<>();
    Map<Variable,Object> effet = new HashMap<>();
    int cout = 1;

    effet.put(this.dalleCoulee, true);
    Action coulerDalle = new BasicAction(precond, effet, cout);
    effet.remove(this.dalleCoulee);

    precond.put(this.dalleCoulee, true);
    effet.put(this.solHumide, false);
    Action attendre = new BasicAction(precond, effet, cout);
    effet.remove(this.solHumide);

    precond.put(this.solHumide, false);
    effet.put(this.mursEleves, true);
    Action eleverMurs = new BasicAction(precond, effet, cout);
    effet.remove(this.mursEleves);

    precond.put(this.mursEleves, true);
    effet.put(this.toitureTerminee, true);
    Action toituree = new BasicAction(precond, effet, cout);
    effet.remove(this.toitureTerminee);

    actions.add(coulerDalle);
    actions.add(attendre);
    actions.add(eleverMurs);
    actions.add(toituree);

    return actions;
  }

  public Action amenagement(Variable varPiece, Object piece){
    Map<Variable, Object> kjb;
    return null;
  }
}
