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
        int nodes=500;
        
        graph graphT=new graph("Barabasi"+nodes);
        graphT.CretateGraph(graph.BARABASI, nodes,50); // 20 75 
        
    //    graphT.printGraph();
        System.out.println("Saving Graph...");
        graphT.saveGraph();
        int nodeNum=graphT.getSomeNode(nodes);
    //    System.out.println("Tree from node " + nodeNum);
        System.out.println("Generating Trees...");
        graphT.BFS(nodeNum);
        graphT.DFS_R(nodeNum);
        graphT.DFS_I(nodeNum);
    //    System.out.println("Tree: " + graphT.get_Tree());
        System.out.println("Saving Trees...");
        graphT.saveTree(1);
        graphT.saveTree(2);
        graphT.saveTree(3);

    }
    
}