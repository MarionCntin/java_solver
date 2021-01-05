package definition;

import java.util.List;

import definition.Variable;

public interface Constraint {
	
	// Retourn le nom de la contrainte
	public String getName();

    // Retourne les variables de la contrainte
    public List<Variable> getVars();

    // Retourne vrai ssi toutes les variables de la contrainte sont instanciees et la contrainte est verifiee
    public boolean isSatisfied();

    // Une condition necessaire a la satisfaction de la contrainte :
    // retourne vrai ssi il existe encore un tuple satisfaisant la contrainte
    public boolean isNecessary();
    
    // Filtre les domaines des variables de la contrainte
    // (supprime les valeurs inconsistantes)

    
    // Toutes les variables sur les quelles porte la contrainte sont instanciées
    public boolean allInstantiated();
    
    
    // Réduit le domaine, sans recopier un csp
    public boolean filter();
    
   
    




}
