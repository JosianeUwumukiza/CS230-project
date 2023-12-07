
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
import java.io.PrintWriter;

//*********************************************************************************************************
//
//
//use an undirected graph G with vertices representing movies and actors. Edges will reflect the relationship 
//“actor played role in movie”. Every actor A that played in a movie M will result in an undirected edge
//*********************************************************************************************************
public class HollywoodGraph<T> {

    private ArrayList<String> names;
    private AdjListsGraph<String> adj;
    private ArrayList<Movie> movies; //holds genders, create movie class

    public HollywoodGraph(){
        names = new ArrayList<String>();
        adj = new AdjListsGraph<String>();
        movies = new ArrayList<Movie>();
    }

    public void graphBuilder(String fName){
        try{
            Scanner scan = new Scanner (new File(fName)).useDelimiter(",");

            String titles = scan.nextLine();

            while(scan.hasNextLine()){
                scan.useDelimiter(",");
                String movie = scan.next().replace("\"", "");
                //System.out.println(movie);
                String actor = scan.next().replace("\"", "");
                //System.out.println(actor);
                int index;
                if (!names.contains(movie)){ //if movie is in vertex
                    // find index in arraylist
                    //add to linked list in array list
                    //adj.addVertex(movie);

                    //using classes:
                    names.add(movie);
                    adj.addVertex(movie);
                    //create new movie object
                    Movie m = new Movie(movie);
                    movies.add(m);
                    //System.out.println("Movie Added to Movies: " + movie);
                    //find way to create indiv titles for each movie
                    //if necessary
                }
                //System.out.println("List of Movies in movies: " + movies.toString());
                //get next token for name
                adj.addVertex(actor);
                if(!names.contains(actor)){//if name is in vertex
                    names.add(actor);
                }

                //adding actor to movie arraylist object
                //Movie m = new Movie(movie);
                scan.next();
                scan.next();
                scan.next();
                scan.useDelimiter("\n");
                String gender = scan.next().replace("\"", "").replace(",", "");
                //System.out.println("name"+ actor + " gender: " + gender);
                Actor a = new Actor(actor,gender);

                for (int i = 0; i < movies.size(); i++){
                    if (movies.get(i).getMovieName().equals(movie)){
                        movies.get(i).addActor(a);
                    }
                } 

                adj.addEdge(movie,actor);
                scan.nextLine();
            }
            scan.close();
        }catch(IOException ex){
            System.out.println(ex);
        }
        //System.out.println(m.toString());
    }

    public boolean genderAnalysis(String movieName){
        //int index;
        //index = movies.indexOf(movieName);
        //return movies.get(index).ratio();
        boolean answer = false;
        for (int i = 0; i < movies.size(); i++){
            //System.out.println(movies.get(i).getName());
            if (movies.get(i).getMovieName().equals(movieName)){
                answer = movies.get(i).ratio();
            }
        } 
        return answer;
    }

    public String toString(){
        return adj.toString();
    }

    /** Returns true if this graph is empty, false otherwise. */
    public boolean isEmpty(){
        return adj.isEmpty();
    }

    /** Returns the number of vertices in this graph. */
    public int getNumVertices(){
        return adj.getNumVertices();
    }

    /** Returns true iff an edge exists between two given vertices
     * which means that two corresponding arcs exist in the graph */
    public boolean isEdge (String vertex1, String vertex2){
        return adj.isEdge(vertex1, vertex2);
    }

    /** Adds a vertex to this graph, associating object with vertex.
     * If the vertex already exists, nothing is inserted. */
    public void addVertex (String vertex){
        adj.addVertex(vertex);
    }

    /** Removes a single vertex with the given value from this graph.
     * If the vertex does not exist, it does not change the graph. */
    public void removeVertex (String vertex){
        adj.removeVertex(vertex);
    }

    /** Inserts an edge between two vertices of this graph,
     * if the vertices exist. Else does not change the graph. */
    public void addEdge (String vertex1, String vertex2){
        adj.addEdge(vertex1, vertex2);
    }

    /** Removes an edge between two vertices of this graph,
    if the vertices exist, else does not change the graph. */
    public void removeEdge (String vertex1, String vertex2){
        adj.removeEdge(vertex1, vertex2);
    }

    /** Saves the current graph into a .tgf file.
    If it cannot write the file, a message is printed. */
    public void saveTGF(String tgf_file_name){
        adj.saveTGF(tgf_file_name);
    }
    
    /**
     * Given a movie, prints out all the actors in that movie
     * 
     * @param  m  the title of the movie
     */
    public void getActors(String m) {

        for (int i = 0; i < movies.size(); i++){
            //System.out.println(movies.get(i).getName());
            if (movies.get(i).getName().equals(m)){
                //System.out.println("selected movie: " + movies.get(i).getName());
                System.out.println(movies.get(i).toString());
            }
        } 
    }
    
    public static void main(String[] args){
        HollywoodGraph<String> hollywood = new HollywoodGraph<String>();

        //hollywood.graphBuilder("nextBechdel_castGender.txt");
        hollywood.graphBuilder("small_castGender.txt");
        //System.out.println(hollywood.toString());
        System.out.println(hollywood.genderAnalysis("Alpha")); //false
        //hollywood.getActors("Alpha");

        //hollywood.saveTGF("HollywoodGraph.tgf");
    }
}
