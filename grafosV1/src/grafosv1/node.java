package grafosv1;
import java.util.ArrayList;
/**
 * Daniel Silva Contreras
 * A200474
 * Análisis y Diseño de Algoritmos
 * 
 * This class defines the object node.
 */

public class node {
    private static int IDcount=0;
    private int ID;
    private int parentID=-1; //use in case of a tree... or not.
    private ArrayList<Integer> connectedWith;
    private ArrayList<ArrayList<Integer>> edges;
    private String name;
    private boolean explored;
    
    node(){
        connectedWith=new ArrayList<>();
        edges= new ArrayList<>();
        ID=IDcount;
        IDcount++;
        //System.out.println("ID = "+ID); 
    }
    node(boolean mode){
        connectedWith=new ArrayList<>();
        edges= new ArrayList<>();
        if (mode){                      //if true generates a self identified node
            ID=IDcount;
            IDcount++;
        }                               //if false generates a dummy/manual node
    }
    void setName (String _name){
        name=_name;
    }
    String getName(){
        return name;
    }
    void setID (int _ID){
        ID=_ID;
    }
    int getID (){
        return ID;
    }
    void setParent(int _parent){
        parentID=_parent;
    }
    int getParent(){
        return parentID;
    }
    boolean isExplored(){
        return explored;
    }
    void setExplored(boolean status){
        explored=status;
    }
    ArrayList<Integer> getConnections(){
        return connectedWith;
    }
    ArrayList<ArrayList<Integer>> getEdges(){
        return edges;
    }
    void connectWith(int _ID){ //connects self -> _ID;
        ArrayList<Integer> _edge = new ArrayList<>();
        _edge.add(ID);
        _edge.add(_ID);    
        connectedWith.add(_ID); //add to the list of connected
        edges.add(_edge);       //add the new edge to edges list
    }
    void connectWith(node newNode){ //connects self->_ID and _ID->self 
        ArrayList<Integer> _edge = new ArrayList<>();
        int _ID = newNode.getID();
        _edge.add(ID);
        _edge.add(_ID); 
        connectedWith.add(_ID); //add to the list of connected
        edges.add(_edge);       //add the new edge to edges list
      
        //Check the other node connections
        ArrayList<Integer> connections=newNode.getConnections();
        if (!connections.contains(ID)){
            newNode.connectWith(ID);
        }

    }
}
