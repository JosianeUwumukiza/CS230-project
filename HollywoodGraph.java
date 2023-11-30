import java.io.File;
import java.util.Scanner;
import javafoundations.*;
import java.util.ArrayList;
import java.io.IOException;

//*********************************************************************************************************
//
//
//use an undirected graph G with vertices representing movies and actors. Edges will reflect the relationship 
//“actor played role in movie”. Every actor A that played in a movie M will result in an undirected edge
//*********************************************************************************************************
public class HollywoodGraph<T> { //implements Graph<T> {

    private ArrayList<String> names;
    private AdjListsGraph<String> adj;
    
    public HollywoodGraph(){
        names = new ArrayList<String>();
        adj = new AdjListsGraph<String>();
    }
    
    public void graphBuilder(String fName){
        try{
            Scanner scan = new Scanner (new File(fName)).useDelimiter(",");
            
            String titles = scan.nextLine();
            
             while(scan.hasNextLine()){
                String movie = scan.next();
                System.out.println(movie);
                String actor = scan.next();
                System.out.println(actor);
                if (names.contains(movie)){ //if movie is in vertex
                    // find index in arraylist
                    //add to linked list in array list
                    adj.addVertex(movie);
                }else // if movie not in vertex
                {   names.add(movie);
                    adj.addVertex(movie);
                }
                //get next token for name
                if(names.contains(actor)){//if name is in vertex
                    adj.addVertex(actor);
                }
                else{
                    names.add(actor);
                    adj.addVertex(actor);
                }//if name is not in vertex
                adj.addEdge(movie,actor);
                //scan.nextLine();
            }
            scan.close();
        }catch(IOException ex){
            System.out.println(ex);
        }
        
    }

    public String toString(){
        return adj.toString();
    }
    
    public static void main(String[] args){
        HollywoodGraph<String> hollywood = new HollywoodGraph<String>();
        
        hollywood.graphBuilder("small_castGender.txt");
        System.out.println(hollywood.toString());
    }

  /** Returns true if this graph is empty, false otherwise. */
   public boolean isEmpty();

   /** Returns the number of vertices in this graph. */
   public int getNumVertices();

   /** Returns the number of arcs in this graph. */
   public int getNumArcs();

   /** Returns true iff a directed edge exists b/w given vertices */
   public boolean isArc (T vertex1, T vertex2);

   /** Returns true iff an edge exists between two given vertices
   * which means that two corresponding arcs exist in the graph */
   public boolean isEdge (T vertex1, T vertex2);

   /** Adds a vertex to this graph, associating object with vertex.
   * If the vertex already exists, nothing is inserted. */
   public void addVertex (T vertex);

   /** Removes a single vertex with the given value from this graph.
   * If the vertex does not exist, it does not change the graph. */
   public void removeVertex (T vertex);

   /** Inserts an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
   public void addArc (T vertex1, T vertex2);

   /** Removes an arc between two vertices of this graph,
   * if the vertices exist. Else it does not change the graph. */
   public void removeArc (T vertex1, T vertex2);

   /** Inserts an edge between two vertices of this graph,
   * if the vertices exist. Else does not change the graph. */
   public void addEdge (T vertex1, T vertex2);

   /** Removes an edge between two vertices of this graph,
   if the vertices exist, else does not change the graph. */
   public void removeEdge (T vertex1, T vertex2);

   /** Returns a string representation of the graph. */
   public String toString();

   /** Saves the current graph into a .tgf file.
   If it cannot write the file, a message is printed. */
   public void saveTGF(String tgf_file_name);
  
}
