
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    } 
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double sum = 0;
        int numRatings = 0;
        for (EfficientRater r : myRaters){
            double ratingValue = r.getRating(id);
            if (ratingValue != -1){
                sum += ratingValue;
                numRatings++;
            }
        }
        if (numRatings >= minimalRaters){
            return sum/numRatings;
        }
        else { return 0.0; }
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingArray = new ArrayList<Rating>();
        for (Movie m : myMovies){
            double avgMovieRatings = getAverageByID(m.getID(), minimalRaters);
            if (avgMovieRatings != 0){
                 Rating rating = new Rating(m.getID(), avgMovieRatings);
                 ratingArray.add(rating);
            }
        }
        return ratingArray;
    }
    
    public String getTitle(String id){
        for (Movie m : myMovies){
            if (m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "The ID was not found!";
    }
    
    public String getID(String title){
        for (Movie m : myMovies){
            if (m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "NO SUCH TITLE!";
    }
    
}
