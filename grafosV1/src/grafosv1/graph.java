package grafosv1;
import java.util.ArrayList;
import java.util.Random;
//import java.lang.Math;
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException;

/**
 * Daniel Silva Contreras
 * A200474
 * Nov 2020
 *  
 */

public class graph {
    private  ArrayList<Integer>[] graph=new ArrayList[2]; //graph variable
    private String Name="";
    private Random rand;
    static final String ERDOS="Erdös y Rényi";
    static final String GILBERT="Gilbert";
    static final String GEOGRAPHIC_SIMPLE="Geographic_simple";
    static final String BARABASI="Barabási-Albert";
    
    graph(){
        graph[0]=new ArrayList<>();//variables to store the pairs
        graph[1]=new ArrayList<>();
        rand=new Random();
    }
    graph(String graph_name){
        graph[0]=new ArrayList<>();//variables to store the pairs
        graph[1]=new ArrayList<>();
        rand=new Random();
        Name=graph_name;
    }
    void set_graphName(String graphName){
        Name=graphName;
    }
    String get_graphName(){
        return Name;
    }
    
    ArrayList<Integer>[] get_Graph(){
        return graph;
    }
    
    void CretateGraph(String method, int n, double p)
    {
        switch(method){
            case ERDOS:
                for (int i=0;i<(int)p;i++){
                    int n1=rand.nextInt(n)+1;
                    int n2=rand.nextInt(n)+1;
                    graph[0].add(n1);
                    graph[1].add(n2);                   
                }     
                break;
            case GILBERT:
                ArrayList<Integer>[] allPairs=new ArrayList[2]; 
                allPairs[0]=new ArrayList<>();
                allPairs[1]=new ArrayList<>();
                for (int i=1;i<=n;i++) //Generates all possible pairs
                    for(int j=1;j<=n;j++)
                        if (i!=j){
                            allPairs[0].add(i);
                            allPairs[1].add(j);
                        }   
                for (int i=0;i<allPairs[0].size();i++)
                    if (rand.nextDouble()<=p){
                        graph[0].add(allPairs[0].get(i));
                        graph[1].add(allPairs[1].get(i));
                    }
                break;
            case GEOGRAPHIC_SIMPLE:
                ArrayList<Double>[] dist=new ArrayList[2];
                dist[0]=new ArrayList<>();
                dist[1]=new ArrayList<>();
                for (int i=0;i<n;i++){
                    dist[0].add(rand.nextDouble());//X coordinate
                    dist[1].add(rand.nextDouble());//Y coordinate                   
                }
                for (int i=0;i<dist[0].size();i++)
                    for (int j=0;j<dist[1].size();j++)
                        if (j!=i){
                            double distx=dist[0].get(i)-dist[0].get(j);
                            double disty=dist[1].get(i)-dist[1].get(j);
                            double distance=Math.sqrt(Math.pow(distx,2)+Math.pow(disty,2));
                            if (distance<=p){
                                graph[0].add(i);
                                graph[1].add(j);
                            }
                        }
                break;
            case BARABASI:
                ArrayList<Integer> deg=new ArrayList<>();
                for(int i=0;i<n;i++)//Set all the node's degree in 0
                    deg.add(0);
                 for (int i=0;i<n;i++)                  //For each graph...
                    for (int j=0;j<n;j++)               //compare with the other graphs...
                        if (j!=i)                       //Except for itself...
                            if (deg.get(i)<(int)p){     //If there is room, add an edge...
                                graph[0].add(i);
                                graph[1].add(j);
                                deg.set(i, deg.get(i)+1); //Increase de degree of i
                            }
                break;
            default:
                graph[0].add(0);
                graph[1].add(0);
                System.out.println("ERROR: Graph method INVALID");
                break;
        } 
    }
    
    //Methods according to the homework specs
    void genErdosRenyi(int n, int m, boolean dirigido, boolean auto){
        for (int i=0;i<m;i++){
            int n1=rand.nextInt(n)+1;
            int n2=rand.nextInt(n)+1;
            graph[0].add(n1);
            graph[1].add(n2);                   
            }  
    }
    
    void genGilbert(int n, double p, boolean dirigido, boolean auto){
        ArrayList<Integer>[] allPairs=new ArrayList[2]; 
        allPairs[0]=new ArrayList<>();
        allPairs[1]=new ArrayList<>();
        for (int i=1;i<=n;i++) //Generates all possible pairs
            for(int j=1;j<=n;j++)
                if (i!=j){
                    allPairs[0].add(i);
                    allPairs[1].add(j);
                    }   
        for (int i=0;i<allPairs[0].size();i++)
            if (rand.nextDouble()<=p){
                graph[0].add(allPairs[0].get(i));
                graph[1].add(allPairs[1].get(i));
                }
    }
    
    void genGeografico(int n, double r, boolean dirigido, boolean auto){
        ArrayList<Double>[] dist=new ArrayList[2];
        dist[0]=new ArrayList<>();
        dist[1]=new ArrayList<>();
        for (int i=0;i<n;i++){
            dist[0].add(rand.nextDouble());//X coordinate
            dist[1].add(rand.nextDouble());//Y coordinate     
            }
        for (int i=0;i<dist[0].size();i++)
            for (int j=0;j<dist[1].size();j++)
                if (j!=i){
                    double distx=dist[0].get(i)-dist[0].get(j);
                    double disty=dist[1].get(i)-dist[1].get(j);
                    double distance=Math.sqrt(Math.pow(distx,2)+Math.pow(disty,2));
                    if (distance<=r){
                        graph[0].add(i);
                        graph[1].add(j);
                        }
                }
    }
    
    void genBarabasiAlbert(int n, double d, boolean dirigido, boolean auto){
        ArrayList<Integer> deg=new ArrayList<>();
        for(int i=0;i<n;i++)//Set all the node's degree in 0
            deg.add(0);
        for (int i=0;i<n;i++)                  //For each graph...
            for (int j=0;j<n;j++)               //compare with the other graphs...
                if (j!=i)                       //Except for itself...
                    if (deg.get(i)<d){     //If there is room, add an edge...
                        graph[0].add(i);
                        graph[1].add(j);
                        deg.set(i, deg.get(i)+1); //Increase de degree of i
                    }
    }
    
    //Method to print on console. Notice that the format match with Gephi
    void printGraph(){
        for (int i=0; i<graph[0].size();i++){
            System.out.println("nodo_" + graph[0].get(i)+"->nodo_"+graph[1].get(i)+";\n");
            }
        System.out.println("Edges: "+String.valueOf( graph[0].size()));
    }
    
    //Method to save the Graph as a text document compatible with Gephi
    void saveGraph(){
        String graphStr="graph"+Name+".dot";
        try {
            File myObj = new File(graphStr);
            if (myObj.createNewFile()) 
                System.out.println("File "+graphStr+" created successfully");
            else 
                System.out.println("File already exists.");
            } 
        catch (IOException e) {
            System.out.println("ERROR: Unable to create the Graph file");
            e.printStackTrace();
            }
        try {
            FileWriter ghostr = new FileWriter(graphStr);
            ghostr.write("graph abstract {\n");
            for (int i=0; i<graph[0].size();i++)
                ghostr.write("nodo_" + graph[0].get(i)+"->nodo_"+graph[1].get(i)+";\n");
            ghostr.write("}");
            ghostr.close();
            } 
        catch (IOException e) {
            System.out.println("ERROR: Can't write on the specified file.");
            e.printStackTrace();
            }
    }
}
