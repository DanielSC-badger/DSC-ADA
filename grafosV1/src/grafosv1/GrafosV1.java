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
       
        graph gE30=new graph("Erdos_1");
        graph gE100=new graph("Erdos_2");
        graph gE500=new graph("Erdos_3");
        
        graph gG30=new graph("Gilbert_1");
        graph gG100=new graph("Gilbert_2");
        graph gG500=new graph("Gilbert_3");
        
        graph gGeo30=new graph("Geo_1");
        graph gGeo100=new graph("Geo_2");
        graph gGeo500=new graph("Geo_3");
        
        graph gB30=new graph("Barabasi_1");
        graph gB100=new graph("barabasi_2");
        graph gB500=new graph("barabasi_3");

        //g.CretateGraph(graph.ERDOS, 100, 150);
        //g.CretateGraph(graph.GILBERT, 50, 0.5);
        //g.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 50, 0.2);
        gE30.CretateGraph(graph.ERDOS, 30,100);
        gE100.CretateGraph(graph.ERDOS, 100,200);
        gE500.CretateGraph(graph.ERDOS, 500,1000);
        
        gG30.CretateGraph(graph.GILBERT, 30,0.4);
        gG100.CretateGraph(graph.GILBERT, 100,0.4);
        gG500.CretateGraph(graph.GILBERT, 500,0.4);
        
        gGeo30.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 30,0.3); //not all nodes are connected, those parameters are used for illustrative purposes.
        gGeo100.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 100,0.3);
        gGeo500.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 500,0.3);
        
        gB30.CretateGraph(graph.BARABASI, 30,10);
        gB100.CretateGraph(graph.BARABASI, 100,20);
        gB500.CretateGraph(graph.BARABASI, 500,75);
        
        gE30.saveGraph();
        gE100.saveGraph();
        gE500.saveGraph();
        
        gG30.saveGraph();
        gG100.saveGraph();
        gG500.saveGraph();
        
        gGeo30.saveGraph();
        gGeo100.saveGraph();
        gGeo500.saveGraph();
        
        gB30.saveGraph();
        gB100.saveGraph();
        gB500.saveGraph();
        
    }
    
}