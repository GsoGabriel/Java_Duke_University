
/**
 * Escreva a descrição da classe ThirdRatings aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String m : movies){
            double avgMovieRatings = getAverageByID(m, minimalRaters);
            if (avgMovieRatings != 0){
                 Rating rating = new Rating(m, avgMovieRatings);
                 ratingArray.add(rating);
            }
        }
        return ratingArray;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters , Filter filterCriteria){
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        for (String m : movies){
            double avgMovieRatings = getAverageByID(m, minimalRaters);
            if (avgMovieRatings != 0){
                 Rating rating = new Rating(m, avgMovieRatings);
                 averageRatings.add(rating);
            }
        }
        return averageRatings;
    }
}
