package evaluate;

import java.util.ArrayList;
import java.util.List;

import definition.Constraint;
import definition.Csp;
import definition.Differe;
import definition.DomainBitSet;
import definition.Inferior;
import definition.InferiorOrEqual;
import definition.Variable;
import resolution.SearchV2;

public class MonteeEnCharge2 {
	
	
	/*
	 *  On souhaite maintenant résoudre une succession de problèmes simples de taille croissante.
	 *  Chaque problème est composé de n variables x0, x1, . . . , xn 1 toutes de domaines initiaux 
	 *  {1, 2, . . . 10}, et liées par les n contraintes x0 < x1,x1 < x2, ... ,xn 2 < xn 1,xn 1 < x0. 
	 *  Il n’y a bien évidemment pas de solution (circuit de contraintes inférieur strict).
	 */
	
	static int valMax = 10;
	
	//---------------------------------------------------------------------------------------------------
    // main
    //---------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
		long timeToSolved = 0;
		int e = 1;
		
		circuitInferieurs(10000);
		/*
		while ( timeToSolved  <  1000) {
			System.out.println("----------------");
			System.out.println("Nombre de variables : "+ (int)Math.pow(10, e));
			circuitInferieurs((int) Math.pow(10, e));
			timeToSolved = resolution.SearchV2.getTime();
			e++;
		}
		*/
		
	}
	
	//---------------------------------------------------------------------------------------------------
    // circuitInferieurs
    //---------------------------------------------------------------------------------------------------
	
	
	public static void circuitInferieurs(int nb) {
		
		// Création de n variables 
		List<Variable> vars = new ArrayList<Variable>();
		for (int i = 0 ; i<nb; i++) {
			vars.add(new Variable(new DomainBitSet(1,valMax),"x"+i));
		}
		
		// Création de n contrainte.
		List<Constraint> cons = new ArrayList<Constraint>();
		//cons.add(new Differe(vars.get(0),vars.get(nb-1),"x0 differe to xn-1"));
		for (int i =1 ; i<nb ; i++) {
			int j =i-1;
			cons.add(new InferiorOrEqual(vars.get(i-1),vars.get(i), "x"+j+" inferior to x"+i));
		}
		cons.add(new InferiorOrEqual(vars.get(nb-1),vars.get(0), vars.get(nb-1).getName()+" inferior to " + vars.get(0).getName()));
		Csp csp = new Csp(vars,cons);


		boolean INITIALIZE = false;
		boolean ALL_SOLUTIONS = false;
		csp.propagation();
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);


	
		
	}
	
}


