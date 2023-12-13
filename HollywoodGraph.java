
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
    /**
    *The method Analyses movies based on the percentage of women and men cast in movies
    *@return s-the string that shows if the movie has women cast over 48%
    *
    */
    public String totalGenderAnalysis(){
        String s = "";
        for (int i = 0; i< movies.size(); i++){
            s += "\n" + movies.get(i).getMovieName() + " has a cast that is over 48% female: " + genderAnalysis(movies.get(i).getMovieName());
            s += "\nThe ratio is: " + movies.get(i).ratioInt();
        }
        return s;
    }
    /**
    *The method calculates the ratio of female and male actors in a movie
    *@param movieName - the movie to be analysed
    *@return answer - a boolean true if the female actors are more that 48% and false otherwise
    */
    public boolean genderAnalysis(String movieName){
        //int index;
        //index = movies.indexOf(movieName);
        boolean answer = false;
        for (int i = 0; i < movies.size(); i++){
            //System.out.println(movies.get(i).getName());
            if (movies.get(i).getMovieName().equals(movieName)){
                answer = movies.get(i).ratio();
            }
        } 
        return answer;
    }
    /**
    *The methods gets a list of movies an actor is in.
    *@param a- an actor whose movies we are finding
    *@return s- the string movies actor a is casted in.
    */
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
    /**
    *The method gets a list of actors a movie has
    *@param m- the name of the movie
    *
    */
    public void getActors(String m) {
        for (int i = 0; i < movies.size(); i++){
            //System.out.println(movies.get(i).getName());
            if (movies.get(i).getMovieName().equals(m)){
                System.out.println("selected movie: " + movies.get(i).getName());
                
            }
        } 
    }
    /**
    *checks if string a1 and a2 are both vertices in our graph
    *@return a boolean -true if they are vertices and false otherwise.
    */
    public boolean validNames(String a1, String a2) {
        return (names.contains(a1) && names.contains(a2));
    }

    /**
    *the method return the index of a movie or an actor in the list of names of all vertices
    *@return- the index of a1
    */
    public int getIndex1(String a1) {
        return names.indexOf(a1);
    }
    /**
    *writes the graph into a String
    *@return - the string represantation of the graph
    */
    public String toString(){
        return adj.toString();
    }

    /** 
    *Checks if the graph is empty
    *@return - Returns true if this graph is empty, false otherwise. 
    */
    public boolean isEmpty(){
        return adj.isEmpty();
    }

    /** 
    *Gets the number of vertices in the Hollywood Graph
    *@return - Returns the number of vertices in this graph. 
    */
    public int getNumVertices(){
        return adj.getNumVertices();
    }

    /** 
     *checks if the edge exist between an actor and a movie
     *@retur - Returns true iff an edge exists between two given vertices
     * which means that two corresponding arcs exist in the graph 
     *@param vertex1- movie
     *@param vertex2- actor
     */
    public boolean isEdge (String vertex1, String vertex2){
        return adj.isEdge(vertex1, vertex2);
    }

    /** Adds a vertex to this graph, associating object with vertex.
     * If the vertex already exists, nothing is inserted.
     *@param vertex- a movie or actor to be added
     */
    public void addVertex (String vertex){
        adj.addVertex(vertex);
    }

    /** Removes a single vertex with the given value from this graph.
     * If the vertex does not exist, it does not change the graph. 
     *@param vertex- movie or actor to be removed
     */
    public void removeVertex (String vertex){
        adj.removeVertex(vertex);
    }

    /** Inserts an edge between two vertices of this graph,
     * if the vertices exist. Else does not change the graph.
     *@param vertex1- movie
     *@param vertex2- actor
     */
    public void addEdge (String vertex1, String vertex2){
        adj.addEdge(vertex1, vertex2);
    }

    /** Removes an edge between two vertices of this graph,
    *if the vertices exist, else does not change the graph. 
    *@param vertex1- movie
    *@param vertex2- actor
    */
    public void removeEdge (String vertex1, String vertex2){
        adj.removeEdge(vertex1, vertex2);
    }

    /** Saves the current graph into a .tgf file.
    * If it cannot write the file, a message is printed.
    */
    public void saveTGF(String tgf_file_name){
        adj.saveTGF(tgf_file_name);
    }
    /**
    *Gets the movie object given the name of a movie
    *@param movie- the name of the movie
    *@return -the movie object
    */
    public Movie getMovieObj(String movie){
        for (int i = 0; i < movies.size(); i++){
            if (movies.get(i).getMovieName().equals(movie)){
                return movies.get(i);
            }
        } 
        return null;
    }
     /**
    *Gets the actor object given the name of a actor
    *@param actor- the name of the actor
    *@return -the actor object
    */
    public Actor getActorObj(String actor){
        for (int i = 0; i < actors.size(); i++){
            if (actors.get(i).getActorName().equals(actor)){
                return actors.get(i);
            }
        } 
        return null;
    }
    /**
    *Gets the index of an actor in a list of actors
    *@param- actorName- the actor
    *@return i- the index of actorName
    */
    public int getIndex(String actorName) {
        for (int i = 0; i < actors.size(); i++) {
            if (actors.get(i).getActorName().equals(actorName)) {
                return i;
            }
        }
        return -1; // Return -1 if the actor is not found
    }
    /**
    *Returns the degree of separation between two actors  Given two actors a1 and a2, what is the number of movies that separate these actors? 
    *For example, if the actors have played in a movie together, their separation number would be 0. If the actors have not played in a movie together, 
    *but a1 played in a movie m1 with another actor a, who played in a movie m2 with a2, then this number would be 1. 
    *@param a- the first actor
    *@param b- the second actor
    *@return int-degree of separation
    */

    public int getRel1(String a, String b) {
        if (a.equals(b)) {
            return -1; //returns -1 if inputted actors are the same 
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

            return -2; // no connection
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
