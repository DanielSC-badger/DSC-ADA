package grafosv1;

/**
 * Daniel Silva Contreras
 * A200474
 * Análisis y diseño de Algoritmos
 * PROYECTO 1: Grafos
 * PROYECTO 2: Árboles
 * All comments are in english.
 */
public class GrafosV1 {

    public static void main(String[] args){
        System.out.println("Generating Graph...");
        int nodes=250;
        graph G= new graph("Barabasi_dir_"+nodes);
        G.genBarabasiAlbert(nodes,20,true,false);  
        G.assignWeights(nodes+1);  
        G.Dijkstra(G.getSomeNode(nodes));

        G.saveGraph(true,false);    //Save complete graph with weights
        G.saveGraph(true,true);     //Save Dijkstra graph

    }
    
}