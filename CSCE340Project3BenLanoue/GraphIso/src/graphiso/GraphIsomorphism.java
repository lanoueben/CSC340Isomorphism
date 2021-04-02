
package graphiso;

import java.util.LinkedList;

public class GraphIsomorphism
{   
    /**
     * Extend a partial isomorphism p between two graphs g and h
     * @param p
     * @param g adjacency matrix
     * @param h adjacency matrix
     * @return  true if and only if p was successfully extended to a full isomorphism
     */
    static boolean extend(LinkedList<Integer> p, boolean [ ][ ]g, boolean [ ][ ]h )
    {
        
        //if the partial solution is complete, return true
        if(p.size()==g.length){return true;}
     
        //i will represent the node in h we're adding onto
        //the extended solution
        //note: I am using g as the base for comparing the partial solution
        //      p to. I will add on to p using h.
        for (int i = 0; i <g.length;i++){
            if(compatible(p,i,g,h)){
                p.add(i);
                boolean success = extend(p,g,h);
                if(success){return true;}
                p.removeLast();
            }//end if
            
        }//end for
        return false;
    }
    /**
     * 
     * @param p partial solution linked list
     * @param v vertex tested for compatibility
     * @param g adjacency matrix
     * @param h adjacency matrix
     * @return true if v assigned to s, where s is p.size() extends p as an
     *              isomorphism between the graphs g and h
     */
    static boolean compatible(LinkedList<Integer> p, int v, boolean [][]g, boolean [][]h)
    {  
        //we assume that the campatibility is true until we find
        //a scenerio/test that proves otherwise
        
        //This will test if p(v) (which represents the node we want to add/test)
        //is in the partial solution already. If so return false
        if (p.contains(v)){return false;}
    

        
		
		//This will iterate through all the nodes in linked list p
        for(int i = 0; i < p.size();i++){
			
			//g is the adjacency matrix that is the solution. So that means we will compare
			//h to g to find if the certain node in h matches g. The match correlation will
			//be represented by p. 
			//the position of the nodes p will correlate to the 
			//adjacency matrix g while the values in the node p will correlate to the
			//adjacency matrix h. 
			//So knowing this we will test the edge of the
			//node v, which would be p.size() position when added to p, and test it
			//with i, the current partial solution node being tested for compatibility.
			//If these values don't match, then they're not compatible
            if(g[p.size()][i] != h[v][p.get(i)]) {return false;}
			
			//This is needed to test for directed graphs by swapping 
			//i and v, along with their positions
            if(g[i][p.size()] != h[p.get(i)][v]) {return false;}
			
        }//end for

        return true;
    }
}


















