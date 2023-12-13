
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
import java.util.LinkedList;

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
    private ArrayList<Actor> actors;
    private boolean[] isVisited;
    private ArrayList<Integer> pathList;
    private int degreeCount;
    private ArrayList<String> pathList1;
    private int movCount;
    private int actCount;
    private Actor actorObj;

    public HollywoodGraph(){
        names = new ArrayList<String>();
        adj = new AdjListsGraph<String>();
        movies = new ArrayList<Movie>();
        actors = new ArrayList<Actor>();
        pathList = new ArrayList<>();
        degreeCount = 0;
        pathList1 = new ArrayList<>();
        movCount = 0;
        actCount = 0;
        actorObj = null;
    }
/**
*graphBuilder reads a text file and creates an adjListsGraph object.
*@param fName - the text file with movies and their actors
*@return s - the undirected graph of relationships between movies and actors
*/
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

                //adding actor to movie arraylist object
                //Movie m = new Movie(movie);
                scan.next();
                scan.next();
                scan.next();
                scan.useDelimiter("\n");
                String gender = scan.next().replace("\"", "").replace(",", "");

                Actor a = new Actor(actor,gender);

                if(!names.contains(actor)){//if name is in vertex
                    names.add(actor);
                    actors.add(a);
                }

                //System.out.println("name"+ actor + " gender: " + gender);

                //adds movies to actor objects in arraylist 
                for (int i = 0; i < actors.size(); i++){
                    if (actors.get(i).getActorName().equals(actor)){
                        actors.get(i).addMovies(movie);
                        //System.out.println(actor);
                        //System.out.println(movie);
                    }
                }

                //adds actors to movie objects in arraylist 
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

        isVisited = new boolean[names.size()];
        for (int i = 0; i< names.size(); i++){
            isVisited[i] = false;
        }
    }

    public String totalGenderAnalysis(){
        String s = "";
        for (int i = 0; i< movies.size(); i++){
            s += "\n" + movies.get(i).getMovieName() + " has a cast that is over 48% female: " + genderAnalysis(movies.get(i).getMovieName());
            s += "\nThe ratio is: " + movies.get(i).ratioInt();
        }
        return s;
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

    public String getMovies(String a){
        String s = "";
        for (int i = 0; i < actors.size(); i++){
            //System.out.println(movies.get(i).getName());
            if (actors.get(i).getActorName().equals(a)){
                //System.out.println("selected movie: " + movies.get(i).getName());
                s = actors.get(i).toString();
            }
        } 
        return s;
    }

    public void getActors(String m) {
        for (int i = 0; i < movies.size(); i++){
            //System.out.println(movies.get(i).getName());
            if (movies.get(i).getMovieName().equals(m)){
                //System.out.println("selected movie: " + movies.get(i).getName());
                //return movies.get(i).toString();
            }
        } 
    }

    public boolean validNames(String a1, String a2) {
        return (names.contains(a1) && names.contains(a2));
    }

    public int getIndex1(String a1) {
        return names.indexOf(a1);
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

    public Movie getMovieObj(String movie){
        for (int i = 0; i < movies.size(); i++){
            if (movies.get(i).getMovieName().equals(movie)){
                return movies.get(i);
            }
        } 
        return null;
    }

    public Actor getActorObj(String actor){
        for (int i = 0; i < actors.size(); i++){
            if (actors.get(i).getActorName().equals(actor)){
                return actors.get(i);
            }
        } 
        return null;
    }

    public int getIndex(String actorName) {
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getActorName().equals(actorName)) {
                return i;
            }
        }
        return -1; // Return -1 if the actor is not found
    }

    public int getRel1(String a, String b) {
        if (a.equals(b)) {
            return degreeCount; //returns degrees apart if inputted actors are the same 
        } else {
            degreeCount++;
            for (int i = 0; i < actors.size(); i++) { 
                if (actors.get(i).getActorName().equals(a)) { //getting the actor object
                    actorObj = getActorObj(actors.get(i).getActorName());
                    ArrayList<String> mov = actors.get(i).getMoviesList(); //getting the list of movies associated with the actor
                    for (int x = 0; x < mov.size(); x++) { //cycling through the actor's movies
                        if (adj.isEdge(mov.get(x), b)) { //checking if actor b is in any of actor a's movies
                            // conditionals: if there is an edge
                            //removed:  from conditional && !pathList1.contains(mov.get(x))
                            //removed: pathList1.add(mov.get(x));
                            return degreeCount; //exits out, returns number of degrees apart
                        }
                    }
                }
            }
            //getting the first movie that the actor is in
            //System.out.println(a);
            int num = actors.indexOf(actorObj);
            //System.out.println(names);
            //System.out.println(num);
            ArrayList<String> mov = actors.get(num).getMoviesList(); 
            String firstMovA = mov.get(movCount); //maybe implement a counter to go through the actor's movie list
            //int movieIndex = getMovieObj(a).getIndex(b); 
            movCount ++;
            if (movCount != -1) {
                Actor actorInMovie = getMovieObj(firstMovA).getAllActors().get(actCount); //gets x actor in movie based on global counter
                actCount ++;
                return getRel1(actorInMovie.getActorName(), b);
            }

            return -1; // no connection
        }
    }

    public static void main(String[] args){
        HollywoodGraph<String> hollywood = new HollywoodGraph<String>();

        //hollywood.graphBuilder("nextBechdel_castGender.txt"); //big file
        hollywood.graphBuilder("small_castGender.txt"); //small file
        //System.out.println(hollywood.toString());
        //hollywood.getActors("The Jungle Book"); //testing movies
        //System.out.println(hollywood.getMovies(("Jennifer Lawrence"))); //testing actors
        //System.out.println(hollywood.iteratorBFS(0));
        //System.out.println(hollywood.totalGenderAnalysis());
        //System.out.println(hollywood.names);
        //System.out.println(hollywood.names.indexOf("Tyler Perry"));
        //System.out.println(hollywood.names.indexOf("Takis"));
        //System.out.println(hollywood.names.indexOf(" Takis"));
        System.out.println(hollywood.getRel1("Tyler Perry","Takis"));

        //hollywood.saveTGF("HollywoodGraph.tgf");
    }
}
