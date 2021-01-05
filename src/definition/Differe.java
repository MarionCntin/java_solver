package definition;

import java.util.ArrayList;
import java.util.List;

public class Differe implements Constraint {
	
	private Variable var1; 
	private Variable var2;
	private String name;
	
	// Constructor
	public Differe(Variable var1, Variable var2 , String name) {
		this.var1 = var1;
		this.var2 = var2;
		this.name = name;
	}
	
	// Retourne le nom de la contrainte
	public String getName() {
		return this.name;
	}

	// Retourne les variables de la contrainte
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
		return (var1.getValue()!=var2.getValue()); 
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

		if (var2.isInstantiated() && !(var1.isInstantiated())) {
			if (var1.getDomain().contains(var2.getValue())) {
				var1.getDomain().remove(var2.getValue());
				return true;
			}

		}
		
		if (var1.isInstantiated() && !(var2.isInstantiated())) {
			if(var2.getDomain().contains(var1.getValue())) {
				var2.getDomain().remove(var1.getValue());
				return true;
			}
		}
		
		return false;
	}
	

}
