package resolution;

import java.util.ArrayList;
import java.util.List;


import definition.Constraint;
import definition.Csp;
import definition.Differe;
import definition.Domain;
import definition.DomainBitSet;
import definition.Inferior;
import definition.InferiorOrEqual;
import definition.Variable;

public class SearchV2 {
	
    private static int indexSolution = 1 ; 
    private static int index = 1;
    
    private static List<String> cspSolution = new ArrayList<String>();
    
    private static long time ; 

    
    
  
    
    
    

    //---------------------------------------------------------------------------------------------------
    // SOLVE AND SHOW 
    //---------------------------------------------------------------------------------------------------
    
    
    public static void solve(Csp csp , boolean INITIALIZE ,  boolean ALL_SOLUTIONS ) {
    	
    	
    	long lStartTime = System.nanoTime();
    	if (INITIALIZE) {
        	csp.propagation();
    	}
    	backtrack(csp);
		long lEndTime = System.nanoTime();
		long timeToSolved = lEndTime - lStartTime;
		
		
    	printSolution(ALL_SOLUTIONS);
    	
		
		System.out.println("Solved in milliseconds: " + timeToSolved/1000000);
		time = timeToSolved/1000000;

		printStats();
		
		indexSolution = 1;
    }
    
    
    
    
    



    //---------------------------------------------------------------------------------------------------
    // BACKTRACK 
    //---------------------------------------------------------------------------------------------------
    
    public static void backtrack(Csp csp) {
    	
    	
    	//---------------------------------------------------
    	// SAVE SOLUTION 
        //---------------------------------------------------
        if (csp.allInstanciated()) {
        	if (csp.hasSolution()) {
        		
        		String txt = "";
        		txt += "Solution"+" "+indexSolution++ + "\n";
        		for(Variable var : csp.getVars()) {
        			txt+= var.getName() + " = "+ var.getValue()+ "  ;  ";
        		}
        		txt += "\n";
        		cspSolution.add(txt);
        		
        		
        	}
        }
        
        //---------------------------------------------------
    	// EXPLORATION
        //---------------------------------------------------
        else {
        	
        	// NEXT VARIABLE TO INSTANCIATE
            Variable y = csp.nextVarToInstantiate();
            
            for (int val : y.getDomain().clone()) {
            	
            	y.instantiate(val);

            	//---------------------------------------------------
            	// SAVE CURRENT DOMAIN
                //---------------------------------------------------
                 List<Domain> saveCurrentDomains = new ArrayList<Domain>();
                 for(Variable var : csp.getVars()) {
                	 saveCurrentDomains.add(var.getDomain().clone());
                 }

            	//---------------------------------------------------
            	// CLEAN WHAT IT IS NOT NECESSARY : FILTER + PROPAGATION
                //---------------------------------------------------
            	csp.propagation();
                
                
                //---------------------------------------------------
            	// APPEL RECURSIF
                //---------------------------------------------------
            	backtrack(csp);
            	
            	//---------------------------------------------------
            	// RESTORING DOMAINS
                //---------------------------------------------------
            	csp.setDomainSaved(saveCurrentDomains);
            	csp.restoreDomain();
            	csp.updateDomain();

            }
        }
    }
    
    
    
    
  
    
    
    
	//---------------------------------------------------
	// PRINT SOLUTION
    //---------------------------------------------------
    
    public static void printSolution(boolean ALL_SOLUTIONS) {
    	if (SearchV2.getIndexSolution()-1==0) {
    		System.out.println("No solution found");
    		return;
    	}
    	
    	if (!ALL_SOLUTIONS){
    		System.out.println(cspSolution.get(0));
    		}
    	
    	else {
    		for (String txt : cspSolution) {
        		System.out.println(txt);
        		}
    		}
    	}
    
    
	//---------------------------------------------------
	// PRINT STATS
    //---------------------------------------------------
    
    public static void printStats() {
    	int nbSol = SearchV2.getIndexSolution()-1;
    	System.out.println("Nombre de solution : "+ nbSol);
    }
    
	//---------------------------------------------------
	// PRINT TIME
    //---------------------------------------------------
    public static long printTime() {
    	return time;
    }
    
    
    
	//---------------------------------------------------
	// GET INDEX SOLUTION
    //---------------------------------------------------
    public static int getIndexSolution() {
    	return SearchV2.indexSolution;
    }
    
    public static long getTime() {
    	return SearchV2.time;
    }
    
    
    
    }
    
    

