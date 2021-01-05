package definition;

import java.util.ArrayList;
import java.util.List;

public class Somme implements Constraint {
	
	// var1 + var2 = s
	
	private Variable var1; 
	private Variable var2;
	private Variable s;
	private String name;
	
	public Somme(Variable var1, Variable var2, Variable s, String name) {
		this.var1 = var1;
		this.var2 = var2;
		this.s = s;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public List<Variable> getVars() {
		List<Variable> vars = new ArrayList<Variable>();
		vars.add(var1);
		vars.add(var2);
		vars.add(s);
		return vars;
	}

	public boolean isSatisfied() {
		return (allInstantiated() && isNecessary());
	}


	public boolean isNecessary() {
		return (var1.getValue()+var2.getValue()== s.getValue());
	}

	public boolean allInstantiated() {
		for (Variable var : getVars()) {
			if (!var.isInstantiated()) {
				return false;
			}
		}
		return true;
	}

	public boolean filter() {
		
		boolean modified = false ;
		
		// min(x) + min(y) >= min(s)
		if (var1.getInf()+var2.getInf() > s.getInf()) {
			s.remValues(s.getInf(), var1.getInf() + var2.getInf()-1);
			modified = true;
		}
		
		
		// max(x) + max(y) <= max(s)
		if (var1.getSup() + var2.getSup() < s.getSup()) {
			s.remValues(var1.getSup() + var2.getSup() +1, s.getSup());
			modified = true;
		}
		
		
		
		// max(x) + max(y) > max(s)
		if( var1.getSup() + var2.getSup() > s.getSup()) {
			int delta1 = s.getSup() - var1.getSup();
			var2.remValues(delta1+1, var2.getSup());
			modified = true;
		}
	
		if( var1.getSup() + var2.getSup() > s.getSup()) {
			int delta2 = s.getSup() - var2.getSup();
			var1.remValues(delta2+1, var2.getSup());
			modified = true;
		}
		
		return modified;
	}


}
