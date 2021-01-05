package definition;

import java.util.ArrayList;
import java.util.List;

public class Csp {

    List<Variable> vars; // l'ensemble des variables du CSP. Note: les domaines sont connus au travers des variables
    List<Constraint> cons; // l'ensemble des contraintes du CSP
    
    
    private List<Domain> domains = new ArrayList<Domain>();
    private List<Domain> domainsSaved = new ArrayList<Domain>();
    
    // Constructor
    public Csp(List<Variable> vars , List<Constraint> cons) {
    	this.vars = vars ; 
    	this.cons = cons ;
    	this.initializeDomains();
    }


    public List<Variable> getVars() {
        return this.vars;
    }
    
    public List<Constraint> getConstraints() {
	return this.cons;
    }

    // retourne la premiere variable non instanciee du csp : OK 
    public Variable nextVarToInstantiate() {
    	for ( Variable var : vars) {
    		if (var.isInstantiated()==false) {
    			return var;
    		}
    	}
        return null;
    }
    
    
    

    // retourne vrai ssi toutes les variables sont instanciées : OK
    public boolean allInstanciated() {
    	if (nextVarToInstantiate()==null) {
    		return true;
    	}
        return false;
    }

    // retourne vrai ssi le CSP possède (au moins) une solution : OK?
    // l'ensemble des contraintes du CSP est vérifié
    // ATTENTION : ce n'est pas la seule condition
    public boolean hasSolution() {
    	for (Constraint con : cons ) {
    		if(!con.isSatisfied()) {
    			return false;
    		}
    	}
        return true;
    }
    
   
    
    public void initializeDomains() {
    	for (Variable var : this.getVars()) {
    		this.domains.add(var.getDomain().clone());
    	}
    }
    
    public List<Domain> getDomains(){
    	return this.domains;
    }
    
    
    public void saveCurrentDomain(){
    	this.domainsSaved.clear();
    	for (Domain dom : this.domains) {
    		this.domainsSaved.add(dom.clone());
    	}
    	
    }
    
    public List<Domain> getDomainSaved() {
    	return this.domainsSaved;
    }
    
    public void restoreDomain() {
    	/*
    	this.domains.clear();
    	for(Domain dom : domainsSaved) {
    		System.out.println(dom);
    		this.domains.add(dom.clone());
    	}
    	*/
    	for (int i = 0 ; i< this.vars.size() ; i++) {
    		this.getVars().get(i).setDomain(this.getDomainSaved().get(i).clone());
    	}
    }
    
    public void updateDomain() {
    	this.domains.clear();
    	for (Variable var : this.getVars()) {
    		this.domains.add(var.getDomain().clone());
    	}
    }
    
    public void setDomainSaved(List<Domain> doms) {
    	this.domainsSaved=doms;
    }

 
    public void propagation() {
    	boolean pointFixe = false ; 
    	
    	while (pointFixe == false) {
    		boolean modified = false;
    		
        	for (Constraint con : this.getConstraints()) {
        		if (con.filter() == true) {
        			modified = true;
        		}
        	}
        	
        	if (!modified){
        		pointFixe = true;
        	}
    	}


    }
}
