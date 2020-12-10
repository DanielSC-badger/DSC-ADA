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
 * Nov 2020 - Creation
 * Dec 2020 - Trees 
 */

public class graph {
    private ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private ArrayList<node> graphNodes = new ArrayList<>();
    //enough trees to form it from the same graph
    private ArrayList<ArrayList<Integer>> treeEdges_BFS = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> treeEdges_DFS = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> treeEdges_DFSI = new ArrayList<>();
    private String Name="";
    private Random rand;
    static final String ERDOS="Erdös y Rényi";
    static final String GILBERT="Gilbert";
    static final String GEOGRAPHIC_SIMPLE="Geographic_simple";
    static final String BARABASI="Barabási-Albert";
    
    graph(){
        init();
    }
    graph(String graph_name){
        init();
        Name=graph_name;
    }
    
    private void init(){
        graph=new ArrayList<>();//strores Edges of the GRAPH
        rand=new Random();
    }
    
    private void init_tree(){
        treeEdges_BFS=null;
        treeEdges_BFS=new ArrayList<>();//store Edges of the TREE
        treeEdges_DFS=null;
        treeEdges_DFS=new ArrayList<>();
        treeEdges_DFSI=null;
        treeEdges_DFSI=new ArrayList<>();
    }
    
    void set_graphName(String graphName){
        Name=graphName;
    }
    String get_graphName(){
        return Name;
    }
    
    ArrayList<ArrayList<Integer>> get_Graph(){
        return graph;
    }
    
    ArrayList<ArrayList<Integer>> get_Tree(){
        return treeEdges_BFS;               //Change tree as needed *CAUTION* 
    }
    ArrayList<node> get_graphNodes(){
        return graphNodes;
    }
    int getSomeNode(int limit){
        node validNode=graphNodes.get(rand.nextInt(limit));
        int validID=validNode.getID();
        return validID;        
    }
    boolean isRepeated(int n1, int n2){
        ArrayList<Integer> nEdge = new ArrayList<>();
        ArrayList<Integer> _nEdge = new ArrayList<>();
        nEdge.add(n1); nEdge.add(n2);
        _nEdge.add(n2); _nEdge.add(n1);        
        if (graph.contains(nEdge) | graph.contains(_nEdge))
            return true;
        else
            return false;
    }
    //Auxiliar ... meeeh not as useful as intented.
    boolean edgeExist(ArrayList<Integer> edge){
        ArrayList<Integer> nEdge = new ArrayList<>();
        nEdge.add(edge.get(1)); nEdge.add(edge.get(0));
        if (treeEdges_BFS.contains(edge) || treeEdges_BFS.contains(nEdge))
            return true;
        else
            return false;
    }
    
    void CretateGraph(String method, int n, double p)
    {
        switch(method){
            case ERDOS:
                genErdosRenyi(n,(int)p,false,false);  
                break;
            case GILBERT:
                genGilbert(n,p,false,false);
                break;              
            case GEOGRAPHIC_SIMPLE:
                genGeografico(n,p,false,false);
                break;
            case BARABASI:
                genBarabasiAlbert(n,p,false,false);
                break;
            default:
                System.out.println("ERROR: Graph method INVALID");
                break;
        } 
    }
    
    //Methods according to the homework specs
    void genErdosRenyi(int n, int m, boolean dirigido, boolean auto){
        for (int i=0;i<(int)m;i++){
            int n1=-1, n2=-1;
            while((n1==n2) || (isRepeated(n1,n2))){
                n1=rand.nextInt(n)+1;
                n2=rand.nextInt(n)+1;
            }                    
            node newNode=new node(false);
            node newNode2=new node(false);
            newNode.setID(n1);
            newNode2.setID(n2);
            newNode.connectWith(n2);
            if (!graphNodes.contains(newNode))
                graphNodes.add(newNode);
            if (!graphNodes.contains(newNode2))
                graphNodes.add(newNode2);
            ArrayList<ArrayList<Integer>> _edge= newNode.getEdges();
            graph.add(_edge.get(_edge.size()-1));      
        }  
    }
    
    void genGilbert(int n, double p, boolean dirigido, boolean auto){
        node newNode;
        ArrayList<node> possibleNodes = new ArrayList<>();   
        for (int i=1;i<=n;i++){ //Generates all possible pairs
            newNode=new node();
            possibleNodes.add(newNode);
        }
        for(node pnode : possibleNodes)
            if (rand.nextDouble()<=p){      //randomly add nodes
                graphNodes.add(pnode);      //add node to the list
                if (graphNodes.size()>1){   //add the corresponding edges
                    int n1=pnode.getID();
                    for(node g : graphNodes){
                        int n2=g.getID();
                        if (!isRepeated(n1,n2) && (n1!=n2)){
                            if (!graphNodes.contains(g))
                                graphNodes.add(g);
                            pnode.connectWith(n2);  //connect the nodes
                            ArrayList<Integer> _edge=new ArrayList<>();
                            _edge.add(n1); _edge.add(n2);
                            graph.add(_edge);       //add the edge to the graph
                        } 
                    }
                }
            }
    }
   
    void genGeografico(int n, double r, boolean dirigido, boolean auto){
        ArrayList<Double>[] dist=new ArrayList[2];
        dist[0]=new ArrayList<>();
        dist[1]=new ArrayList<>();
        node newNode;
        ArrayList<node> possibleNodes = new ArrayList<>();
        int n1, n2;
        for (int i=0;i<n;i++){
            newNode=new node();            //create n nodes
            possibleNodes.add(newNode);
            dist[0].add(rand.nextDouble());//X coordinate
            dist[1].add(rand.nextDouble());//Y coordinate     
            }
        for (int i=0;i<dist[0].size();i++){
            n1=possibleNodes.get(i).getID();
            for (int j=0;j<dist[1].size();j++){
                n2=possibleNodes.get(j).getID();
                 if ((j>i)  && !(isRepeated(n1,n2))){
                    double distx=dist[0].get(i)-dist[0].get(j);
                    double disty=dist[1].get(i)-dist[1].get(j);
                    double distance=Math.sqrt(Math.pow(distx,2)+Math.pow(disty,2));
                    if (distance<=r){
                        if (!graphNodes.contains(possibleNodes.get(i)))
                            graphNodes.add(possibleNodes.get(i));
                        if (!graphNodes.contains(possibleNodes.get(j)))
                            graphNodes.add(possibleNodes.get(j));
                        possibleNodes.get(i).connectWith(n2);  //connect the nodes
                        ArrayList<Integer> _edge=new ArrayList<>();
                        _edge.add(n1); _edge.add(n2);
                        graph.add(_edge);                       //add the edge to the graph
                        }
                }
            }
        }         
    }
    
    void genBarabasiAlbert(int n, double d, boolean dirigido, boolean auto){
        ArrayList<Integer> deg=new ArrayList<>();
        ArrayList<node> possibleNodes = new ArrayList<>();
        node newNode;
        int n1, n2;
        for(int i=0;i<n;i++){//Set all the node's degree in 0
            deg.add(0);
            newNode=new node();            //create n nodes
            possibleNodes.add(newNode);
        }
        for (int i=0;i<n;i++){                           //For each graph...
            n1=possibleNodes.get(i).getID();
            for (int j=0;j<n;j++){                       //compare with the other graphs...
                n2=possibleNodes.get(j).getID();
                if ((j>i) && !(isRepeated(n1,n2)))        //Except for itself...
                    if (deg.get(i)<d){                  //If there is room, add an edge...
                        if (!graphNodes.contains(possibleNodes.get(i)))
                            graphNodes.add(possibleNodes.get(i));
                        if (!graphNodes.contains(possibleNodes.get(j)))
                            graphNodes.add(possibleNodes.get(j));
                        possibleNodes.get(i).connectWith(n2);  //connect the nodes
                        ArrayList<Integer> _edge=new ArrayList<>();
                        _edge.add(n1); _edge.add(n2);
                        graph.add(_edge);                       //add the edge to the graph
                        deg.set(i, deg.get(i)+1); //Increase de degree of i
                    }
            }
        }
    }
    
    //Method to print on console. Notice that the format match with Gephi
    void printGraph(){
        System.out.println("Edges in " + Name + ": "+ graph);
        System.out.println("Nodes: " + graphNodes.size());
        for(node r : graphNodes){
         System.out.println("ID: " + r.getID() + " connected with: " + r.getConnections() );
        }
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
            
            for(ArrayList<Integer> e : graph)
                ghostr.write("nodo_"+e.get(0)+"->nodo_"+e.get(1)+";\n");
                      
            ghostr.write("}");
            ghostr.close();
            } 
        catch (IOException e) {
            System.out.println("ERROR: Can't write on the specified file.");
            e.printStackTrace();
            }
    }
    

    //Methods corresponding to project 2 (DFS and BFS tree) 
     void saveTree(int tree){ //Yes, is redundant with the above function
        String graphStr="tree_"+Name+".dot";
        try {
            File myObj = new File(graphStr);
            if (myObj.createNewFile()) 
                System.out.println("File "+graphStr+" created successfully");
            else 
                System.out.println("File already exists.");
            } 
        catch (IOException e) {
            System.out.println("ERROR: Unable to create the Tree file");
            e.printStackTrace();
            }
        try {
            FileWriter ghostr = new FileWriter(graphStr);
            ghostr.write("graph abstract {\n");
            
            switch (tree){
                case 1:
                    for(ArrayList<Integer> e : treeEdges_BFS)
                        ghostr.write("nodo_"+e.get(0)+"->nodo_"+e.get(1)+";\n");
                    break;
                case 2:
                    for(ArrayList<Integer> e : treeEdges_DFS)
                        ghostr.write("nodo_"+e.get(0)+"->nodo_"+e.get(1)+";\n");
                    break;
                case 3:
                    for(ArrayList<Integer> e : treeEdges_DFSI)
                        ghostr.write("nodo_"+e.get(0)+"->nodo_"+e.get(1)+";\n");
                    break;    
                 default:
                     System.out.println("ERROR: INVALID TREE TYPE");
                     break;
                    
            }
     
            ghostr.write("}");
            ghostr.close();
            } 
        catch (IOException e) {
            System.out.println("ERROR: Can't write on the specified file.");
            e.printStackTrace();
            }
    }
     
    private node getNode(int _ID){
        for(node n : graphNodes){
            if(_ID==n.getID())
                return n;
        }
        System.out.println("Node " + _ID + " not found");
        return null;
    } 
    
    void BFS (int nodeStart){//Breadth First Search
        ArrayList<Integer> visited=new ArrayList<>(); //visited nodes
        ArrayList<ArrayList<node>> L = new ArrayList<>();
        boolean nodeExist=false;
        for (node n : graphNodes){      //Check if the start node exist
            if (nodeStart==n.getID()){
                nodeExist=true;
                L.add(new ArrayList<>());
                L.get(0).add(n);        //add node to level 0
            }      
        }
        
        int i=0; // layer counter
        if (nodeExist){
            //System.out.println("NodeExist");
            init_tree();
            visited.add(nodeStart);
            while(!L.get(i).isEmpty()){                
                L.add(new ArrayList<>()); //add the next layer
                for(node cNode : L.get(i)){
                    ArrayList<Integer> connections=new ArrayList<>();
                    connections=cNode.getConnections();
                    for(int con : connections){
                        if(!visited.contains(con)){
                            visited.add(con);
                            ArrayList<Integer> _edge=new ArrayList<>();
                            _edge.add(cNode.getID()); _edge.add(con);
                            //System.out.println("Edge added: " + _edge);
                            treeEdges_BFS.add(_edge);
                            L.get(i+1).add(getNode(con));
                        }
                    }
                }
                i++;
            }            
        } 
    }
    
    
    void DFS_R (int nodeStart){ // Depth First Search recursive
        node u=getNode(nodeStart);
        u.setExplored(true);    //mark as explored
        for(ArrayList<Integer> e : u.getEdges() ){
            int v=e.get(1);
            if (!getNode(v).isExplored() ){
                DFS_R(v);                                   //Recursive call
                ArrayList<Integer> _edge=new ArrayList<>();
                _edge.add(u.getID()); _edge.add(v);
                if(!edgeExist(_edge))
                    treeEdges_DFS.add(_edge);               //Add the edge      
            }     
        }  
    }
    void DFS_I (int nodeStart){ // Depth First Search iterative
        ArrayList<node> memory=new ArrayList<>();
        memory.add(0,getNode(nodeStart));
        int lastNode=-1;
        while (!memory.isEmpty()){
            node u= memory.get(0);      //Extract the first element
            memory.remove(0);           //Erase from memory the first element
            if (!u.isExplored()){       //Only do something if the node has not been visited
                u.setExplored(true);        //mark as explored
                if(lastNode!=-1){           //use lastNode to form the edge
                    ArrayList<Integer> _edge=new ArrayList<>();
                    _edge.add(u.getID()); _edge.add(lastNode);
                    if(!edgeExist(_edge))
                        treeEdges_DFSI.add(_edge);               //Add the edge
                    lastNode=u.getID();
                }
                else{                       //If is the first element just store it
                    lastNode=u.getID();
                }
                for(int con : u.getConnections()){  //Add to memory each node to be visited
                    memory.add(0,getNode(con));
                } 
            }
        }   
    }    
   
    
    
}
