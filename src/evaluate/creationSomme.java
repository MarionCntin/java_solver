package evaluate;

import java.util.ArrayList;
import java.util.List;

import definition.Constraint;
import definition.Csp;
import definition.DomainBitSet;
import definition.Somme;
import definition.Variable;

public class creationSomme {
	
	
	//---------------------------------------------------------------------------------------------------
    // main
    //---------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
		List<Variable> vars = new ArrayList<Variable>();
		Variable x = new Variable(new DomainBitSet(3,5),"x");
		Variable y = new Variable(new DomainBitSet(2,10),"y") ;
		Variable s = new Variable(new DomainBitSet(0,12),"s"); 
		vars.add(x);
		vars.add(y);
		vars.add(s);
		
		
		List<Constraint> cons = new ArrayList<Constraint>();
		cons.add(new Somme(vars.get(0),vars.get(1),vars.get(2)," x + y = s "));
		
		
		Csp csp = new Csp(vars,cons);
		
		boolean INITIALIZE = false;
		boolean ALL_SOLUTIONS = true;
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);
		
	}

}
