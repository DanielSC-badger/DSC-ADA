package grafosv1;
import java.util.ArrayList;
import java.util.Random;
//import java.lang.Math;
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Set;

/**
 * Daniel Silva Contreras
 * A200474
 * Nov 2020 - Creation
 * Dec 2020 - Trees 
 * Jan 2021 - Dijkstra
 */

public class graph {
    private ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> wGraph = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> gDijkstra = new ArrayList<>();
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
    
     ArrayList<ArrayList<Integer>> get_weightedGraph(){
        return wGraph;
    }
     
    ArrayList<ArrayList<Integer>> get_Dijkstra(){
        return gDijkstra;
    }
    
    ArrayList<ArrayList<Integer>> get_Tree(){
        return treeEdges_DFSI;               //Change tree as needed *CAUTION* 
    }
    ArrayList<node> get_graphNodes(){
        return graphNodes;
    }
    int getSomeNode(int limit){
        int num=0;
        boolean valid=false;
        while(valid==false){
            num=rand.nextInt(limit);
            for(node n : graphNodes){
                if(num==n.getID()){
                    valid=true;
                }
            }
        }
        return num;        
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
            if(getNode(n1)==null){
                node newNode=new node(false);
                newNode.setID(n1);
                graphNodes.add(newNode);
            }
            if(getNode(n2)==null){
                node newNode2=new node(false);
                newNode2.setID(n2);
                graphNodes.add(newNode2);
            }
            if(dirigido){
                getNode(n1).connectWith(n2);                
            }
            else
                getNode(n1).connectWith(getNode(n2));
            
            ArrayList<ArrayList<Integer>> _edge= getNode(n1).getEdges();
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
                            if (dirigido)
                                pnode.connectWith(n2);
                            else
                                pnode.connectWith(g);  //connect the nodes
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
                        if (dirigido)
                            possibleNodes.get(i).connectWith(n2);  //connect the nodes
                        else
                            possibleNodes.get(i).connectWith(possibleNodes.get(n2));
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
                        if (dirigido)
                            possibleNodes.get(i).connectWith(n2);  //connect the nodes
                        else
                            possibleNodes.get(i).connectWith(possibleNodes.get(n2));
                        ArrayList<Integer> _edge=new ArrayList<>();
                        _edge.add(n1); _edge.add(n2);
                        graph.add(_edge);                       //add the edge to the graph
                        deg.set(i, deg.get(i)+1); //Increase de degree of i
                    }
            }
        }
    }
    void assignWeights(int range){
        int wRange;
        ArrayList<ArrayList<Integer>>  weights= new ArrayList<>();
        if(range<graph.size())
            wRange=graph.size();
        else
            wRange=range;
        for(int i=0;i<graph.size();i++){
            ArrayList<Integer> e=graph.get(i);
            int w=rand.nextInt(wRange);
            ArrayList<Integer> nEdge = new ArrayList<>();
            nEdge.add(e.get(0));
            nEdge.add(e.get(1));
            nEdge.add(w);
            weights.add(nEdge);
        }
        wGraph=weights;
    }
    
    void Dijkstra(int s){
        System.out.println("Source Node: "+s);
        ArrayList<node> S=new ArrayList<>();      
        PriorityQueue<node> pq = new PriorityQueue<>(graphNodes.size()-1,new ArrayComparator());  
        for(int i=0;i<graphNodes.size();i++){
            node cNode=graphNodes.get(i);
            cNode.setWeight(Integer.MAX_VALUE);
            pq.add(cNode);}
        node nNode=getNode(s);
        nNode.setWeight(0);
        pq.add(nNode);
        while(!pq.isEmpty()){
            node u=pq.poll();
            if (!S.contains(u) && u.getWeight()!=Integer.MAX_VALUE){
                S.add(u);
                ArrayList<ArrayList<Integer>> uEdges=u.getEdges();
                for(int i=0;i<uEdges.size();i++){
                    ArrayList<Integer> edge = uEdges.get(i);
                    node vNode=getNode(edge.get(1));
                    if (!S.contains(vNode)){ //outcoming edges
                        int newWeight=u.getWeight();
                        int eIndex= graph.indexOf(edge);
                        int vEdgeW=wGraph.get(eIndex).get(2);
                        newWeight+=vEdgeW;    //new weight
                        if (newWeight<vNode.getWeight()){
                            vNode.setWeight(newWeight);
                            pq.add(vNode);}
                    }  
                }
            }  
        }
        ArrayList<Integer> _S=new ArrayList<>();
        for (node n : S){
            if (!_S.contains(n.getID()))
                _S.add(n.getID());
            for (ArrayList<Integer> edge : n.getEdges()){
                int eIndex= graph.indexOf(edge);
                if (!_S.contains(edge.get(1))){
                    _S.add(edge.get(1));
                    gDijkstra.add(wGraph.get(eIndex));
                }
            } 
        }    
    }

    //Method to print on console. Notice that the format match with Gephi
    //Not any more.
    void printGraph(){
        System.out.println("Edges in " + Name + ": "+ graph);
        System.out.println("Nodes: " + graphNodes.size());
        for(node r : graphNodes){
         System.out.println("ID: " + r.getID() + " connected with: " + r.getConnections() );
        }
    }
    
    void clearFiles(){
        File folder = new File(System.getProperty("user.dir"));
        File fList[] = folder.listFiles();
        for (File _file : fList) {
            String fileName=_file.getName();
            if (fileName.endsWith(".dot")) {
                System.out.println("Erase: "+fileName);
                _file.delete();
                }
        }
    }
    
    //Method to save the Graph as a text document compatible with Gephi
    void saveGraph(boolean w, boolean dijkstra){
        String graphStr="";
        if (dijkstra){
            graphStr="graph"+Name+"_Dijkstra"+".dot";}
        else{
            graphStr="graph"+Name+".dot";}
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
            if(w){
                if(dijkstra)
                {
                    for(ArrayList<Integer> e : gDijkstra)
                        ghostr.write("nodo_"+e.get(0)+"("+getNode(e.get(0)).getWeight()+")"+
                                "->nodo_"+e.get(1)+"("+getNode(e.get(1)).getWeight()+")"+
                                        "[label="+e.get(2)+"]"+";\n");  
                }
                else{
                    for(ArrayList<Integer> e : wGraph)
                        ghostr.write("nodo_"+e.get(0)+"->nodo_"+e.get(1)+"[label="+e.get(2)+"]"+";\n"); 
                }
            }
            else{
                for(ArrayList<Integer> e : graph)
                    ghostr.write("nodo_"+e.get(0)+"->nodo_"+e.get(1)+";\n");
            }
            
                      
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
        String type;
        if(tree==1)
            type="BFS";
        else if(tree==2)
            type="DFSR";
        else
            type="DFSI";
                
        String graphStr="graphTree_"+type+"_"+Name+".dot";
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
    
    private void unexplore(){
        for (node n : graphNodes){
            n.setExplored(false);
            n.setParent(-1);
        }
    }
    
    private node getNode(int _ID){
        for(node n : graphNodes){
            if(_ID==n.getID())
                return n;
        }
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
                            treeEdges_BFS.add(_edge);
                            L.get(i+1).add(getNode(con));
                        }
                    }
                }
                i++;
            }            
        } 
    }
   
    void DFS_R (int n){ // Depth First Search recursive
        node u=getNode(n);
        u.setExplored(true);    //mark as explored
        for(ArrayList<Integer> e : u.getEdges() ){
            int v=e.get(1);
            if (!getNode(v).isExplored() ){
                treeEdges_DFS.add(e);
                DFS_R(v);                                  //Recursive call
            }   
        }  
    }
    private boolean isConnected(ArrayList<Integer> edge, ArrayList<ArrayList<Integer>> tree){
        int u=edge.get(0);
        int v=edge.get(1);
        boolean connected=false;
        if(tree.isEmpty())
            return true;    
        for(ArrayList<Integer> e : tree){
            int eU=e.get(0), eV=e.get(1);
            if((u==eU)||(u==eV)||(v==eU)||(v==eV))
                connected=true;
        }
        return connected;
    }
    void DFS_I (int nodeStart){ // Depth First Search iterative
        unexplore();
        ArrayList<node> memory=new ArrayList<>();
        getNode(nodeStart).setParent(nodeStart);
        memory.add(0,getNode(nodeStart));
        
        while (!memory.isEmpty()){
            node u= memory.get(0);      //Extract the first element
            memory.remove(0);           //Erase from memory the first element
            
            for(int i=u.getEdges().size()-1;i>=0 ;i--){
                int v=u.getEdges().get(i).get(1);
                if (!getNode(v).isExplored() && (getNode(v).getParent()==-1) )     //Only do something if the node has not been visited && !memory.contains(getNode(v)) 
                    memory.add(0,getNode(v));       //every non-explored node must go into memory           
            }
            int path=-1;
            for(ArrayList<Integer> e : u.getEdges()){
                int v=e.get(1);
                node nodeV= getNode(v);
                if ( !nodeV.isExplored() && (nodeV.getParent()==-1) && isConnected(e,treeEdges_DFSI) )  { //not explored and no parent
                    if(path==-1){
                        nodeV.setParent(u.getID());
                        treeEdges_DFSI.add(e);
                        path=1;
                    }
                }          
            }
            if(path==-1){   //If there is no possible path, mark as explored
                u.setExplored(true);                
            }
        }  
    }    
  
}
