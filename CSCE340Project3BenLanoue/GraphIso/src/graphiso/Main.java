package graphiso;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class Main
{
    public static void main(String[] args) throws FileNotFoundException
    {       

        System.out.println("This program looks for an isomorphism between two graphs.");
        System.out.print("Select the input file for the first graph.\n");
        
        // Pop up a file chooser and select a file containing graph data        
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
        {
            System.out.println("No file selected.");
            return;
        }
        // Read the file  and put a scanner on it
        Scanner graphScanner = new Scanner(chooser.getSelectedFile());

        Graph graph1 = new Graph(graphScanner);

        System.out.println(graph1);
        
         System.out.print("Select the input file for the second graph.\n");
        
        // Pop up a file chooser and select a file containing graph data        
        chooser = new JFileChooser();
        result = chooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION)
        {
            System.out.println("No file selected.");
            return;
        }
        // Read the file  and put a scanner on it
        graphScanner = new Scanner(chooser.getSelectedFile());

        Graph graph2 = new Graph(graphScanner);

        System.out.println(graph2);
        
        if (graph1.adj.length != graph2.adj.length)
        {
             System.out.println("The two graphs are not isomorphic");
            return;
        }
        
        // Look for an isomorphism
        LinkedList<Integer> p = new LinkedList<>();
        boolean success = GraphIsomorphism.extend(p, graph1.adj, graph2.adj);
        if (!success)
        {
            System.out.println("The two graphs are not isomorphic");
            return;
        }
        // Found an isomorphism
        System.out.println("Here is the isomorphism:\n");
        for (int v = 0; v < p.size(); v++)
        {
            System.out.printf("%s -> %s\n", graph1.vertexName(v), graph2.vertexName(p.get(v)));
        }
        
    }
}
