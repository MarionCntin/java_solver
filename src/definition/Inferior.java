package definition;

import java.util.ArrayList;
import java.util.List;

public class Inferior implements Constraint{
	
	
	private Variable var1; 
	private Variable var2;
	private String name;
	
	
	// Constructor
	public Inferior (Variable var1, Variable var2, String name) {
		this.var1 = var1;
		this.var2 = var2;
		this.name = name;
	}
	
	// Retourne le nom de la contraine
	public String getName(){
		return this.name;
	}

	@Override
	public List<Variable> getVars() {
		List<Variable> vars = new ArrayList<Variable>();
		vars.add(var1);
		vars.add(var2);
		return vars;
	}

    // Retourne vrai ssi toutes les variables de la contrainte sont instanciees et la contrainte est verifiee
	public boolean isSatisfied() {
		return (allInstantiated() && isNecessary());
	}
	
    // Une condition necessaire a la satisfaction de la contrainte :
    // retourne vrai ssi il existe encore un tuple satisfaisant la contrainte
	public boolean isNecessary() {
			return (var1.getValue()<var2.getValue());
	}


    // Toutes les variables sur les quelles porte la contrainte sont instanciées
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

	
	for (int val : var1.getDomain()) {
		// val = max(x1) = max(var2)
		if (val>=var2.getSup()) {
			modified =true ;
			var1.remValues(val,var1.getSup());
			break;
		}
	}
	
	for (int val : var2.getDomain()) {
		if (val<=var1.getInf()) {
			modified =true ;
			var2.remValues(val,var1.getInf());
			break;
		}
	}
	
	return modified;
}




}
