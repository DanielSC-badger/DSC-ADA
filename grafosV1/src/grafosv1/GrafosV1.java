package grafosv1;

/**
 * Daniel Silva Contreras
 * A200474
 * Análisis y diseño de Algoritmos
 * PREOYECTO 1: Grafos
 */
public class GrafosV1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        graph g=new graph("Barabasi_1");

        //g.CretateGraph(graph.ERDOS, 100, 150);
        //g.CretateGraph(graph.GILBERT, 50, 0.5);
        //g.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 50, 0.2);
        g.CretateGraph(graph.BARABASI, 50,10);
        g.printGraph();
        g.saveGraph();
    }
    
}