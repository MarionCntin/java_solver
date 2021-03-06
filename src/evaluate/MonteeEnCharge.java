package evaluate;

import java.util.ArrayList;
import java.util.List;

import definition.Constraint;
import definition.Csp;
import definition.DomainBitSet;
import definition.Inferior;
import definition.InferiorOrEqual;
import definition.Variable;
import resolution.SearchV2;

public class MonteeEnCharge {
	
	
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
		
		while ( timeToSolved   <  60*1000) {
			System.out.println("----------------");
			System.out.println("Round : "+ (int)Math.pow(10, e)+ "\n");

			circuitInferieurs((int) Math.pow(10, e));
			timeToSolved = resolution.SearchV2.getTime();
			e++;
		}
		
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
		for (int i =1 ; i<nb ; i++) {
			int j =i-1;
			cons.add(new Inferior(vars.get(i-1),vars.get(i), "x"+j+" inferior to x"+i));
		}
		cons.add(new Inferior(vars.get(nb-1),vars.get(0), vars.get(nb-1).getName()+" inferior to " + vars.get(0).getName()));
		
		
		Csp csp = new Csp(vars,cons);
		
		boolean INITIALIZE = true;
		boolean ALL_SOLUTIONS = true;
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);

	
		
	}
	
}


