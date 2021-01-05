package resolution;

import java.util.ArrayList;
import java.util.List;

import definition.Constraint;
import definition.Csp;
import definition.Differe;
import definition.Domain;
import definition.DomainBitSet;
import definition.Inferior;
import definition.Variable;

public class SearchV1 {
	
	
    //---------------------------------------------------------------------------------------------------
    // main
    //---------------------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
		
		// csp1
		Variable x1 = new Variable(new DomainBitSet(0,2),"x1");
		Variable x2 = new Variable(new DomainBitSet(0,2),"x2");
		Variable x3 = new Variable(new DomainBitSet(0,2),"x3");
		
		List<Variable> vars = new ArrayList<Variable>();
		vars.add(x1);
		vars.add(x2);
		vars.add(x3);
		
		Differe differeTo = new Differe(x1,x3,"x1 differeTo x3");
		Inferior inferiorTo = new Inferior(x2,x3,"x2 inferiorTo x3");
		
		List<Constraint> cons = new ArrayList<Constraint>();
		cons.add(differeTo);
		cons.add(inferiorTo);
		
		Csp csp1 = new Csp(vars, cons);
		
		//bruteForceSearch(csp1);
		// Time
		long lStartTime = System.nanoTime();
		generateAndTest1(csp1);
		long lEndTime = System.nanoTime();
		long timeToSolved = lEndTime - lStartTime;
		
		System.out.println("Solved in milliseconds: " + timeToSolved/1000000);
		
		
		
		
	} 
    
	
	
    //---------------------------------------------------------------------------------------------------
    // bruteForceSearch : on génère toutes les instanciations possibles :
    // aucune contrainte : toute instanciation complète est une solution
    //---------------------------------------------------------------------------------------------------
    private static int indexSolution = 1 ; 

    public static void bruteForceSearch(Csp csp) {
        if (csp.allInstanciated()) {
            // traitement du cas où une instanciation est complète
        	if (csp.hasSolution()) {
        		System.out.println("Solution"+" "+indexSolution++ );
        		for(Variable var : csp.getVars()) {
        			System.out.println( var.getName() + ":"+ var.getValue());
        		}
        		System.out.println("\n");
        	}
        }
        else {
            Variable y = csp.nextVarToInstantiate();
            Domain domainSaved = y.getDomain().clone();
            for (int val : domainSaved) {
                // to complete
                y.instantiate(val);
                
                // Saving csp with a variable instanciated
                List<Constraint> consSaved = new ArrayList<Constraint>();
                List<Variable> varsSaved = new ArrayList<Variable>();
                for (Variable var : csp.getVars()) {
                	varsSaved.add(new Variable(var.getDomain().clone(),var.getName()));
                }
                
                //bruteForceSearch(new Csp(varsSaved,consSaved));
                
            }
        }
    }

    
    //---------------------------------------------------------------------------------------------------
    // generateAndTest : on ajoute un test pour vérifier si une instanciation complète est une solution
    // Note : si le csp n'a aucune contrainte, c'est le même comportement que bruteForceSearch
    //---------------------------------------------------------------------------------------------------


    public static void generateAndTest1(Csp csp) {
        if (csp.allInstanciated()) {
        	if (csp.hasSolution()) {
        		System.out.println("Solution"+" "+indexSolution++);
        		for(Variable var : csp.getVars()) {
        			System.out.println( var.getName() + ":"+ var.getValue());
        		}
        		System.out.println("\n");
        		
        	}
        }
        else {
            Variable y = csp.nextVarToInstantiate();
            Domain domainSaved = y.getDomain().clone();
            for (int val : domainSaved) {
            	Domain dom1 = y.getDomain().clone();
                y.instantiate(val);
                generateAndTest1(csp);
                

            }
            y.setDomain(domainSaved);
        }
    }

}
