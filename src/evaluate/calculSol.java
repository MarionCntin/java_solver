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

public class calculSol {
	
	public static void main(String[] args) {
		
		
		int nbVar = 10;
		int valMax = 4;
		
		
		List<Variable> vars = new ArrayList<Variable>();
		for (int i = 0 ; i<nbVar; i++) {
			vars.add(new Variable(new DomainBitSet(1,valMax),"x"+i));
		}
		
		List<Constraint> cons = new ArrayList<Constraint>();
		
		
		Differe x2DiffereTox4 = new Differe(vars.get(2),vars.get(4),"x2 differe to x4");
		Differe x2DiffereTox5 = new Differe(vars.get(2),vars.get(5),"x2 differe to x5");
		Differe x4DiffereTox5 = new Differe(vars.get(4),vars.get(5),"x4 differe to x5");
		Differe x6DiffereTox3 = new Differe(vars.get(6),vars.get(3),"x6 differe to x3");
		Differe x7DiffereTox8 = new Differe(vars.get(7),vars.get(8),"x7 differe to x8");
		cons.add(x2DiffereTox4);
		cons.add(x2DiffereTox5);
		cons.add(x4DiffereTox5);
		cons.add(x7DiffereTox8);
		cons.add(x6DiffereTox3);
		
		
		
		Inferior x9InferiorTox4 = new Inferior(vars.get(9),vars.get(4),"x9 inferiorTo x4");
		Inferior x0InferiorTox1 = new Inferior(vars.get(0),vars.get(1),"x0 inferiorTo x1");
		Inferior x0InferiorTox4 = new Inferior(vars.get(0),vars.get(4),"x0 inferiorTo x4");
		Inferior x4InferiorTox6 = new Inferior(vars.get(4),vars.get(6),"x4 inferiorTo x6");
		Inferior x4InferiorTox3 = new Inferior(vars.get(4),vars.get(3),"x4 inferiorTo x3");
		Inferior x8InferiorTox6 = new Inferior(vars.get(8),vars.get(6),"x8 inferiorTo x6");
		cons.add(x9InferiorTox4);
		cons.add(x0InferiorTox1);
		cons.add(x0InferiorTox4);
		cons.add(x4InferiorTox6);
		cons.add(x4InferiorTox3);
		cons.add(x8InferiorTox6);
		
		
		InferiorOrEqual x9InferiorOrEqualTox0 = new InferiorOrEqual(vars.get(9),vars.get(0),"x9 inferiorOrEqualTo x0");
		InferiorOrEqual x9InferiorOrEqualTox2 = new InferiorOrEqual(vars.get(9),vars.get(2),"x9 inferiorOrEqualTo x2");
		InferiorOrEqual x0InferiorOrEqualTox7 = new InferiorOrEqual(vars.get(0),vars.get(7),"x0 inferiorOrEqualTo x7");
		InferiorOrEqual x1InferiorOrEqualTox6 = new InferiorOrEqual(vars.get(1),vars.get(6),"x1 inferiorOrEqualTo x6");
		InferiorOrEqual x5InferiorOrEqualTox8 = new InferiorOrEqual(vars.get(5),vars.get(8),"x5 inferiorOrEqualTo x8");
		InferiorOrEqual x3InferiorOrEqualTox8 = new InferiorOrEqual(vars.get(3),vars.get(8),"x3 inferiorOrEqualTo x8");
		cons.add(x9InferiorOrEqualTox0);
		cons.add(x9InferiorOrEqualTox2);
		cons.add(x0InferiorOrEqualTox7);
		cons.add(x1InferiorOrEqualTox6);
		cons.add(x5InferiorOrEqualTox8);
		cons.add(x3InferiorOrEqualTox8);
		
		
		Csp csp = new Csp(vars, cons);
		
		
		boolean INITIALIZE = true;
		boolean ALL_SOLUTIONS = true;
		resolution.SearchV2.solve(csp, INITIALIZE, ALL_SOLUTIONS);


	

	}

}
