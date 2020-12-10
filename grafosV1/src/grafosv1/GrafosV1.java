package grafosv1;

import java.util.ArrayList;

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
        
        System.out.println("Generating Graphs...");
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
        
        System.out.println("Erdos Graphs...");
        gE30.CretateGraph(graph.ERDOS, 30,100);
        gE100.CretateGraph(graph.ERDOS, 100,200);
        gE500.CretateGraph(graph.ERDOS, 500,1000);
        System.out.println("Gilbert Graphs...");
        gG30.CretateGraph(graph.GILBERT, 30,0.4);
        gG100.CretateGraph(graph.GILBERT, 100,0.4);
        gG500.CretateGraph(graph.GILBERT, 500,0.4);
        System.out.println("Geo Graphs...");
        gGeo30.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 30,0.3); //not all nodes are connected, those parameters are used for illustrative purposes.
        gGeo100.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 100,0.3);
        gGeo500.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 500,0.3);
        System.out.println("Barabasi Graphs...");
        gB30.CretateGraph(graph.BARABASI, 30,10);
        gB100.CretateGraph(graph.BARABASI, 100,20);
        gB500.CretateGraph(graph.BARABASI, 500,75);
        
        System.out.println("Saving Graphs...");
        
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
        
      System.out.println("BFS ...");
      //Tree BFS generation
      gE30.BFS(gE30.getSomeNode(30));
      gE100.BFS(gE100.getSomeNode(80));
      gE500.BFS(gE500.getSomeNode(400));
      
      gG30.BFS(gG30.getSomeNode(30));
      gG100.BFS(gG30.getSomeNode(80));
      gG500.BFS(gG30.getSomeNode(400));
        
      gGeo30.BFS(gGeo30.getSomeNode(30));
      gGeo100.BFS(gGeo30.getSomeNode(80));
      gGeo500.BFS(gGeo30.getSomeNode(400));
        
      gB30.BFS(gB30.getSomeNode(30));
      gB100.BFS(gB30.getSomeNode(80));
      gB500.BFS(gB30.getSomeNode(400));
      
      System.out.println("DFS Recursive...");
      //Tree DFS recursive generation
      gE30.DFS_R(gE30.getSomeNode(30));
      gE100.DFS_R(gE100.getSomeNode(80));
      gE500.DFS_R(gE500.getSomeNode(400));
      
      gG30.DFS_R(gG30.getSomeNode(30));
      gG100.DFS_R(gG100.getSomeNode(80));
      gG500.DFS_R(gG500.getSomeNode(400));
        
      gGeo30.DFS_R(gGeo30.getSomeNode(30));
      gGeo100.DFS_R(gGeo100.getSomeNode(80));
      gGeo500.DFS_R(gGeo500.getSomeNode(400));
        
      gB30.DFS_R(gB30.getSomeNode(30));
      gB100.DFS_R(gB100.getSomeNode(80));
      gB500.DFS_R(gB500.getSomeNode(500));
      
        System.out.println("DFS Iterative...");
      //Tree DFS Iterative generation
      gE30.DFS_I(gE30.getSomeNode(30));
      gE100.DFS_I(gE100.getSomeNode(80));
      gE500.DFS_I(gE500.getSomeNode(500));
      
      gG30.DFS_I(gG30.getSomeNode(30));
      gG100.DFS_I(gG100.getSomeNode(80));
      gG500.DFS_I(gG500.getSomeNode(500));
        
      gGeo30.DFS_I(gGeo30.getSomeNode(30));
      gGeo100.DFS_I(gGeo100.getSomeNode(80));
      gGeo500.DFS_I(gGeo500.getSomeNode(500));
        
      gB30.DFS_I(gB30.getSomeNode(30));
      gB100.DFS_I(gB100.getSomeNode(80));
      gB500.DFS_I(gB500.getSomeNode(500));
      
        System.out.println("SAVING...");
      //Save Trees
      gE30.saveTree(1);
      gE100.saveTree(1);
      gE500.saveTree(1);
        
      gG30.saveTree(1);
      gG100.saveTree(1);
      gG500.saveTree(1);
        
      gGeo30.saveTree(1);
      gGeo100.saveTree(1);
      gGeo500.saveTree(1);
        
      gB30.saveTree(1);
      gB100.saveTree(1);
      gB500.saveTree(1);
              
/*
     graph gGeo30=new graph("GeoTree1");
     gGeo30.CretateGraph(graph.GEOGRAPHIC_SIMPLE, 300,0.6);
     //gGeo30.printGraph();
     gGeo30.DFS_I(2);
     System.out.println("Tree: "+  gGeo30.get_Tree() );
        
     gGeo30.saveTree(); 
     gGeo30.saveGraph();
  */   
    }
    
}