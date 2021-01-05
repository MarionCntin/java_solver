package definition;

public class Variable {
	
    private Domain dom; // le domaine associe
    private String name; // le nom associe
    private Domain domSaved; // le domaine en cours de sauvagarde
    private Domain domInitial; // le domaine initial
    private boolean isModified = false ;
    
    // Constructor
    public Variable( Domain dom , String name) {
    	this.name = name;
    	this.dom = dom;
    	
    }
    

   
    public Domain getDomain() { 
        return dom;
    }
    
    // retourne le nom de la variable 
    public String getName() {
    	return this.name;
    }
    
    // retourne vrai ssi la variable est instanciee : OK 
    public boolean isInstantiated() {
        return (getDomainSize()==1);
    }

    // retourne vrai ssi le domaine de la variable contient la valeur v : OK
    public boolean canBeInstantiatedTo(int v) {
        return dom.contains(v);
    }

    // retourne le nombre de valeurs dans le domaine de la variable : OK
    public int getDomainSize() {
        return dom.size();
    }

    // supprime la valeur v du domaine de la variable : OK
    public void remValue(int v) {
    	dom.remove(v);
    }

    // supprime toutes les valeurs entre min (inclus) et max (inclus) : OK
    public void remValues(int min, int max) {
    	for (int val : dom) {
    		if (val >= min & val <= max) {
    			dom.remove(val);
    		}
    	}
    }

    // vide le domaine : supprime toutes ses valeurs : OK
    public void remAllValues() {
    	dom.removeAll();
    }

    // instantie la variable a la valeur v : OK
    public void instantiate(int v) {
    	dom.instantiate(v);
    }

    // retourne la plus petite valeur du domaine : OK
    public int getInf() {
    	/*
    	int inf = 0;
    	for (int val : dom) {
    		if (val<inf) {
    			val = inf;
    		}
    	}
        return inf;
        */
    	return dom.firstValue();
    }

    // retourne la plus grande valeur du domaine  : OK
    public int getSup() {
    	/*
    	int max = 0;
    	for (int val : dom) {
    		if (val>max) {
    			val = max;
    		}
    	}
        return max;
        */
    	return dom.lastValue();
    }

    // retourne la valeur affectee a la variable ssi la variable est effectivement instanciee, sinon -1 : OK
    public int getValue() {
    	if (this.isInstantiated()) {
    		return dom.firstValue();
    	}
        return -1;
    }
    
    // retourne si ...
    public boolean isEmpty() {
    	return (getDomainSize()==0);
    }
    
    public void setDomain (Domain newDom) {
    	this.dom = newDom.clone();
    }
    

}