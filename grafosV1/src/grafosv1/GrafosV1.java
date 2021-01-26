package grafosv1;

/**
 * Daniel Silva Contreras
 * A200474
 * Análisis y diseño de Algoritmos
 * PROYECTO 1: Grafos
 * PROYECTO 2: Árboles
 * PROYECTO 3: Dijkstra
 * PROYECTO 4: Kruskal, Prim
 * All comments are in english.
 */
public class GrafosV1 {

    public static void main(String[] args){
        System.out.println("Generating Graph...");
        int nodes=150;
        graph G= new graph("Barabasi_"+nodes);
        G.genBarabasiAlbert(nodes,15,false,false);  
        
        G.assignWeights(nodes+1);  
        G.kruskalD();
        G.kruskalI();
        G.prim();
        G.saveGraph(true,graph.GRAPH);      //Save complete graph with weights
        G.saveGraph(true,graph.KRUSKALD); 
        G.saveGraph(true,graph.KRUSKALI);
        G.saveGraph(true,graph.PRIM);
    }
}