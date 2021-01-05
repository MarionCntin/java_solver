package partiel;

import java.util.ArrayList;
import java.util.List;

import definition.Constraint;
import definition.Csp;
import definition.Differe;
import definition.DomainBitSet;
import definition.Inferior;
import definition.InferiorOrEqual;
import definition.Min;
import definition.Variable;

public class partiel {
	
	
	
	
	// -----------------------
	// MAIN
	// -----------------------
	public static void main(String[] args) {
		
		// ****************************
		// QUESTION 1
		// ****************************
		
		// valMax   |   nbSol
		// ---------------------
		// 3        |   0 
		// 4        |   8 
		// 5        |   141 
		// 6        |   1200 
		// 7        |   6804 
		// 8        |   29472
		/*
		for (int valMax=3 ; valMax <= 8 ; valMax++) {
			System.out.println("****** valMax = "+ valMax + " *****");
			resolutionPb(valMax);
			System.out.println("\n");
		}
		*/
		
		
		// ****************************
		// QUESTION 2
		// ****************************
		
		// question 2.1
		//circuitSuperieursOuEgaux(10); // OK
		//circuitSuperieursOuEgaux(100); // OK
		//circuitSuperieursOuEgaux(1000); // OK
		//circuitSuperieursOuEgaux(8000); // OK / 59s 
		//circuitSuperieursOuEgaux(10000); // OK / 72 secondes 
		//circuitSuperieursOuEgaux(100000); // NOT OK 
		// MAX EN 60s ; nb = 8 000
		
		
		// question 2.2
		//circuitSuperieursEtDifferents(10); // OK
		//circuitSuperieursEtDifferents(100); // OK
		//circuitSuperieursEtDifferents(1000); // OK
		//circuitSuperieursEtDifferents(8000); // OK
		//circuitSuperieursEtDifferents(10000); //  NOT OK / 91s
		//circuitSuperieursEtDifferents(100000); // NOT OK 
		// MAX EN 60s ; nb = 8 000
		
		// question 2.3
		//circuitSuperieursStricts(10); // OK
		//circuitSuperieursStricts(100); // OK
		//circuitSuperieursStricts(1000); // OK
		//circuitSuperieursStricts(10000); // OK 
		//circuitSuperieursStricts(100000); // OK
		//circuitSuperieursStricts(1000000); // OK
		//circuitSuperieursStricts(1000000); // OK
		//circuitSuperieursStricts(5000000); // OK
		//circuitSuperieursStricts(10000000) ; // OUT OF MEMORY
		// MAX EN 60s / OUT OF MEMORY  ; nb = 5 000 000
		
		
		
		
		// ****************************
		// QUESTION 3
		// ****************************
		//question3();
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	// -----------------------
	// Question 1 
	// -----------------------
	
	public static void resolutionPb(int valMax) {
		
		// Variables 
		int nbVar = 10;
		List<Variable> vars = new ArrayList<Variable>();
		for (int i = 0 ; i<nbVar; i++) {
			vars.add(new Variable(new DomainBitSet(1,valMax),"x"+i));
		}
		
		// Contraintes
		List<Constraint> cons = new ArrayList<Constraint>();
		
			// Differe
		Differe x0DiffereTox3 = new Differe(vars.get(0),vars.get(3),"x0 differe to x3");
		Differe x2DiffereTox3 = new Differe(vars.get(2),vars.get(3),"x2 differe to x3");
		Differe x7DiffereTox9 = new Differe(vars.get(7),vars.get(9),"x7 differe to x9");
		cons.add(x0DiffereTox3);
		cons.add(x2DiffereTox3);
		cons.add(x7DiffereTox9);
		
		
			// Inferior
		Inferior x0InferiorTox8 = new Inferior(vars.get(0),vars.get(8),"x0 inferiorTo x8");
		Inferior x1InferiorTox4 = new Inferior(vars.get(1),vars.get(4),"x1 inferiorTo x4");
		Inferior x6InferiorTox4 = new Inferior(vars.get(6),vars.get(4),"x6 inferiorTo x4");
		Inferior x4InferiorTox9 = new Inferior(vars.get(4),vars.get(9),"x4 inferiorTo x9");
		Inferior x5InferiorTox9 = new Inferior(vars.get(5),vars.get(9),"x5 inferiorTo x9");
		Inferior x6InferiorTox9 = new Inferior(vars.get(6),vars.get(9),"x6 inferiorTo x9");
		Inferior x9InferiorTox8 = new Inferior(vars.get(9),vars.get(8),"x9 inferiorTo x8");
		cons.add(x0InferiorTox8);
		cons.add(x1InferiorTox4);
		cons.add(x6InferiorTox4);
		cons.add(x4InferiorTox9);
		cons.add(x5InferiorTox9);
		cons.add(x6InferiorTox9);
		cons.add(x9InferiorTox8);
		
			// Inferior or equal
		InferiorOrEqual x7InferiorOrEqualTox0 = new InferiorOrEqual(vars.get(7),vars.get(0),"x7 inferiorOrEqualTo x0");
		InferiorOrEqual x4InferiorOrEqualTox2 = new InferiorOrEqual(vars.get(4),vars.get(2),"x4 inferiorOrEqualTo x2");
		InferiorOrEqual x8InferiorOrEqualTox3 = new InferiorOrEqual(vars.get(8),vars.get(3),"x8 inferiorOrEqualTo x3");
		InferiorOrEqual x4InferiorOrEqualTox7 = new InferiorOrEqual(vars.get(4),vars.get(7),"x4 inferiorOrEqualTo x7");
		InferiorOrEqual x6InferiorOrEqualTox8 = new InferiorOrEqual(vars.get(6),vars.get(8),"x6 inferiorOrEqualTo x8");
		cons.add(x7InferiorOrEqualTox0);
		cons.add(x4InferiorOrEqualTox2);
		cons.add(x8InferiorOrEqualTox3);
		cons.add(x4InferiorOrEqualTox7);
		cons.add(x6InferiorOrEqualTox8);
		
		Csp csp = new Csp(vars, cons);
		
		
		boolean INITIALIZE = true;
		boolean ALL_SOLUTIONS = true;
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);
		
		
	}
	
	
	// -----------------------
	// Question 2
	// -----------------------
	
	
	// Question 2.1
	public static void circuitSuperieursOuEgaux(int nb) {
		
		// Création de nb variables 
		List<Variable> vars = new ArrayList<Variable>();
		for (int i = 0 ; i<nb; i++) {
			vars.add(new Variable(new DomainBitSet(1,10),"x"+i));
		}
		
		// Création des contrainte.
		List<Constraint> cons = new ArrayList<Constraint>();
		for (int i =1 ; i<nb ; i++) {
			int j =i-1;
			cons.add(new InferiorOrEqual(vars.get(i-1),vars.get(i), "x"+j+" InferiorOrEqual to x"+i));
		}
		cons.add(new InferiorOrEqual(vars.get(nb-1),vars.get(0), vars.get(nb-1).getName()+" InferiorOrEqual to " + vars.get(0).getName()));
		
		
		Csp csp = new Csp(vars,cons);
		
		boolean INITIALIZE = true;
		boolean ALL_SOLUTIONS = false;
		System.out.println("Exercice 2.1 ; nb = "+ nb);
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);
		
	}
	
	// Question 2.2
	
	public static void circuitSuperieursEtDifferents(int nb) {
		
		// Création de nb variables 
		List<Variable> vars = new ArrayList<Variable>();
		for (int i = 0 ; i<nb; i++) {
			vars.add(new Variable(new DomainBitSet(1,10),"x"+i));
		}
		
		// Création des contrainte.
		List<Constraint> cons = new ArrayList<Constraint>();
		for (int i =1 ; i<nb ; i++) {
			int j =i-1;
			cons.add(new InferiorOrEqual(vars.get(i-1),vars.get(i), "x"+j+" InferiorOrEqual to x"+i));
			cons.add(new Differe( vars.get(i-1) , vars.get(i) ,"x"+j+" Differe to x"+i ));
		}
		
		cons.add(new InferiorOrEqual(vars.get(nb-1),vars.get(0), vars.get(nb-1).getName()+" InferiorOrEqual to " + vars.get(0).getName()));
		cons.add(new Differe( vars.get(nb-1) , vars.get(0) ,vars.get(nb-1).getName()+" Differe to " + vars.get(0).getName() ));
		
		Csp csp = new Csp(vars,cons);
		
		boolean INITIALIZE = true;
		boolean ALL_SOLUTIONS = false;
		System.out.println("Exercice 2.2 ; nb = "+ nb);
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);
		
	}
	
	
	// Question 2.3
	
	public static void circuitSuperieursStricts(int nb) {
		
		// Création de n variables 
		List<Variable> vars = new ArrayList<Variable>();
		for (int i = 0 ; i<nb; i++) {
			vars.add(new Variable(new DomainBitSet(1,10),"x"+i));
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
		boolean ALL_SOLUTIONS = false;
		System.out.println("Exercice 2.3 ; nb = "+ nb);
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);
		
	}
	
	
	
	// -----------------------
	// Question 3
	// -----------------------
	
	public static  void question3() {
		
		// Variables 
		List<Variable> vars = new ArrayList<Variable>();
		Variable x = new Variable(new DomainBitSet(1,5),"x");
		Variable y = new Variable(new DomainBitSet(6,10),"y");
		Variable m = new Variable(new DomainBitSet(2,4),"m");
		vars.add(x);
		vars.add(y);
		vars.add(m);
		
		// Constraint 
		List<Constraint> cons = new ArrayList<Constraint>();
		Min minXY = new Min (m,x,y, "minXY");
		cons.add(minXY);
		
		
		Csp csp = new Csp(vars, cons);

		boolean INITIALIZE = false;
		boolean ALL_SOLUTIONS = true;
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);
		
		
		
	}
	
	
	
	

}
