
/**
 * Escreva a descrição da classe FirstRatings aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    private ArrayList<Movie> movieArray;
    private ArrayList<EfficientRater> raterArray;
    private HashMap<String, ArrayList<Movie>> moviesDirectors;
    
    public FirstRatings(){
        moviesDirectors = new HashMap<String, ArrayList<Movie>>();
    }
    
    public ArrayList<Movie> loadMovies(String fileName){
        FileResource fr = new FileResource("data/" + fileName);
        movieArray = new ArrayList<Movie>();
        for (CSVRecord currentRow : fr.getCSVParser()){
            Integer minutes = Integer.parseInt(currentRow.get("minutes").trim());
            Movie currMovie = new Movie(currentRow.get("id"), currentRow.get("title"),
                                        currentRow.get("year"), currentRow.get("genre"),
                                        currentRow.get("director"),currentRow.get("country"),
                                        currentRow.get("poster"), minutes);
            this.movieArray.add(currMovie);
        }
        return this.movieArray;
    }
    
    private int greaterThanMin(int min){
        int greaterThan = 0;
        for (Movie m : movieArray){
            if (m.getMinutes() > min){
                greaterThan++;
            }
        }
        return greaterThan;
    }
    
    private int anyDirectorsAndTheirMovies(){
        int lengthDir = 0;
        for (Movie m : movieArray){
            String directors = m.getDirector();
            String[] dirArray = directors.split(", ");
            ArrayList<Movie> movies = new ArrayList<Movie>();
            if (dirArray.length > lengthDir){
                lengthDir = dirArray.length;
            }
            for (String dir : dirArray){
                if (!moviesDirectors.containsKey(dir)){
                    movies.add(m);
                    moviesDirectors.put(dir,movies);
                }
                else{
                    ArrayList<Movie> moviesGotten = moviesDirectors.get(dir);
                    moviesGotten.add(m);
                    moviesDirectors.put(dir,moviesGotten);
                }
            }
        }
        return lengthDir;
    }
    
    public ArrayList<EfficientRater> loadRaters(String fileName){
        FileResource fr = new FileResource("data/" + fileName);
        raterArray = new ArrayList<EfficientRater>();
        for (CSVRecord currRating : fr.getCSVParser()){
            String id = currRating.get("rater_id");
            boolean found = false;
            for (EfficientRater rater : raterArray){
                if (rater.getID().equals(id)){
                    rater.addRating(currRating.get("movie_id"), Double.parseDouble(currRating.get("rating")));
                    found = true;
                    break;
                }
            }
            if (!found){
                EfficientRater rater = new EfficientRater(id);
                rater.addRating(currRating.get("movie_id"), Double.parseDouble(currRating.get("rating")));
                raterArray.add(rater);
            }
        }
        return this.raterArray;
    }
    
    
    
    public void testLoadMovies(){
        loadMovies("ratedmoviesfull");
        System.out.println("Number of Movies: " + movieArray.size());
        // for (Movie m : movieArray){
            // System.out.println(m.toString());
        // }
        int comedyNum = 0;
        int greaterThan = greaterThanMin(150);
        for (Movie m : movieArray){
            if (m.getGenres().contains("Comedy") || m.getGenres().contains("comedy")){
                comedyNum++;
            }
        }
        System.out.println("Number of comedy movies: " + comedyNum);
        System.out.println("Number of movies greater than 150 minutes: " + greaterThan);
        System.out.println("Maximum number of movies by any director: " + anyDirectorsAndTheirMovies());
    }
    
    
    
    private void printNumRatings(){
        for (EfficientRater rater : raterArray){
            System.out.println("RaterID: " + rater.getID() + "; Number of ratings: " + rater.numRatings());
            // for (Rating r : rater.getMyRatings()){
                // System.out.println(r.toString());
            // }
        }
    }
    
    private void particularRater(String id){
        for (EfficientRater rater : raterArray){
            if (rater.getID().equals(id)){
                System.out.println("ID: " + id + "; Number of ratings: " + rater.numRatings());
                break;
            }
        }
    }
    
    private void findMaxRatings(){
        ArrayList<String> idArray = new ArrayList<String>();
        int greaterRatings = 0;
        for (EfficientRater rater : raterArray){
            int numRatings = rater.numRatings();
            if ( numRatings > greaterRatings){
                greaterRatings = numRatings;
                idArray = new ArrayList<String>();
                idArray.add(rater.getID());
            }
            else if (numRatings == greaterRatings){
                idArray.add(rater.getID());
            }
            else continue; 
        }
        System.out.println("The maximum number of ratings by any rater: " + greaterRatings +
                            "\nHow many raters have this maximum number: " + idArray.size() +
                            "\nwho those raters are: " + idArray.toString());
    }
    
    private int ratingsParticularMovie(String movieID){
        ArrayList<Double> ratings = new ArrayList<Double>();
        for (EfficientRater rater : raterArray){
            double rate = rater.getRating(movieID);
            if (rate != -1){
                ratings.add(rate);
            }
        }
        return ratings.size();
    }
    
    private int howManyMoviesRated(){
        ArrayList<String> moviesRated = new ArrayList<String>();
        for (EfficientRater rater : raterArray){
            ArrayList<String> moviesThisRater = rater.getItemsRated();
            for (String s : moviesThisRater){
                if (!moviesRated.contains(s)){
                    moviesRated.add(s);
                }
            }
        }
        return moviesRated.size();
    }
    
    public void testLoadRaters(){
        loadRaters("ratings");
        System.out.println("Number of Raters: " + raterArray.size());
        // printNumRatings();
        particularRater("193");
        findMaxRatings();
        System.out.println(ratingsParticularMovie("1798709"));
        System.out.println("How many different movies have been rated? " + howManyMoviesRated());
    }
}
