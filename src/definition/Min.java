package definition;

import java.util.ArrayList;
import java.util.List;

public class Min implements Constraint{

	private Variable m; 
	private Variable x;
	private Variable y;
	private String name;
	
	
	public Min(Variable m, Variable x, Variable y,  String name) {
		this.m = m;
		this.x = x;
		this.y = y ; 
		this.name = name ;
		
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Variable> getVars() {
		List<Variable> vars = new ArrayList<Variable>();
		vars.add(m);
		vars.add(x);
		vars.add(y);
		return vars;
	}

	@Override
	public boolean isSatisfied() {
		return (allInstantiated() && isNecessary());
	}

	
		
	@Override
	public boolean isNecessary() {
		return ( Math.min( x.getValue() , y.getValue() ) == m.getValue()  );
	}

	@Override
	public boolean allInstantiated() {
		for (Variable var : getVars()) {
			if (!var.isInstantiated()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean filter() {

		boolean modified = false ;
	
		// Pour X 
		
		if(x.getInf()<m.getInf()) {
			x.remValues(x.getInf(), m.getInf());
			modified = true ; 
			
		}
		
		// Pour Y
		if(y.getInf()<m.getInf()) {
			y.remValues(y.getInf(), m.getInf());
			modified = true ; 
			
		}
		
		
		// Pour M 
		int tool = Math.min(x.getSup(), y.getSup());
		if (m.getSup()> tool) {
			m.remValues(tool+1, m.getSup());
			modified = true ; 
		}
		
		
	

		
		
		return modified;
	}
	

}
