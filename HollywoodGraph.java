
/**
 *The HollywoodGraph class reads a file containing movies and actors and creates an undirected graph that represents 
 *which actors are in which movies and vice-versa. It also provides several methods to access information from the graph.
 * @author Aubrey, Josiane, Lauren
 * @version 12/1/23
 */

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
public class HollywoodGraph<T> {

    private ArrayList<String> names;
    private AdjListsGraph<String> adj;

    /**
     * Constructor for class HollywoodGraph
     */
    public HollywoodGraph(){
        names = new ArrayList<String>();
        adj = new AdjListsGraph<String>();
    }
    
    /**
     * Creates a graph from the input file
     * 
     * @param  fName  the name of the file to turn into a graph
     */
    public void graphBuilder(String fName){
        try{
            Scanner scan = new Scanner (new File(fName)).useDelimiter(",");

            String titles = scan.nextLine();

            while(scan.hasNextLine()){
                String movie = scan.next().replace("\"", "");
                //System.out.println(movie);
                String actor = scan.next().replace("\"", "");
                //System.out.println(actor);
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
                scan.nextLine();
            }
            scan.close();
        }catch(IOException ex){
            System.out.println(ex);
        }

    }

    /** 
     * Checks if the graph has any vertices
     * 
     * @return  true if this graph is empty, false otherwise.  
     */
    public boolean isEmpty(){
        return adj.isEmpty();
    }

    /** 
     * Gets the number of vertices in the graph
     * 
     * @return the number of vertices in this graph. 
     */
    public int getNumVertices(){
        return adj.getNumVertices();
    }

    /** 
     * Checks if there is an edge between two vertices
     * 
     * @param  vertex1  the name of the first vertex to check
     * @param  vertex2  the name of the second vertex to check
     * 
     * @return  true if an edge exists between two given vertices
     *          which means that two corresponding arcs exist in the graph 
     *          and false otherwise
     */
    public boolean isEdge (String vertex1, String vertex2){
        return adj.isEdge(vertex1, vertex2);
    }

    /** 
     * Adds a vertex to this graph, associating object with vertex.
     * If the vertex already exists, nothing is inserted.
     * 
     * @param  vertex  the name of the vertex to be added
     */
    public void addVertex (String vertex){
        adj.addVertex(vertex);
    }

    /** 
     * Removes a single vertex with the given value from this graph.
     * If the vertex does not exist, it does not change the graph.
     * 
     * @param  vertex  the name of the vertex to be removed
     */
    public void removeVertex (String vertex){
        adj.removeVertex(vertex);
    }

    /** 
     * Inserts an edge between two vertices of this graph, if the vertices 
     * exist. Else does not change the graph. 
     * 
     * @param  vertex1  the name of the first vertex to add an edge to
     * @param  vertex2  the name of the second vertex to add an edge to
     */
    public void addEdge (String vertex1, String vertex2){
        adj.addEdge(vertex1, vertex2);
    }

    /** 
     * Removes an edge between two vertices of this graph, if the vertices 
     * exist, else does not change the graph.
     * 
     * @param  vertex1  the name of the first vertex to remove an edge from
     * @param  vertex2  the name of the second vertex to remove an edge from
     */
    public void removeEdge (String vertex1, String vertex2){
        adj.removeEdge(vertex1, vertex2);
    }

    /** 
     * Saves the current graph into a .tgf file. If it cannot write the file, 
     * a message is printed. 
     * 
     * @param  tgf_file_name  the name of the tgf file that will be created
     */
    public void saveTGF(String tgf_file_name){
        adj.saveTGF(tgf_file_name);
    }
    
    /**
     * Creates a string version of the graph
     * 
     * @return  the String version of the graph
     */
    public String toString(){
        return adj.toString();
    }

    /**
     * main runner for class HollywoodGraph
     */
    public static void main(String[] args){
        HollywoodGraph<String> hollywood = new HollywoodGraph<String>();

        hollywood.graphBuilder("nextBechdel_castGender.txt");
        //hollywood.graphBuilder("small_castGender.txt");
        System.out.println(hollywood.toString());
        hollywood.saveTGF("HollywoodGraph.tgf");
    }

}

