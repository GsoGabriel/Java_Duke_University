
/**
 * Escreva a descrição da classe MovieRunnerAverage aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class MovieRunnerAverage {

    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings();
        System.out.println("Movies: " + sr.getMovieSize() + "; Raters: " + sr.getRaterSize());
        ArrayList<Rating> ratingArray = sr.getAverageRatings(12);
        Collections.sort(ratingArray);
        for (Rating r : ratingArray){
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings();
        System.out.println("Movies: " + sr.getMovieSize() + "; Raters: " + sr.getRaterSize());
        String movie = "Vacation";
        String id = sr.getID(movie);
        ArrayList<Rating> ratingArray = sr.getAverageRatings(0);
        for (Rating r : ratingArray){
            if (r.getItem().equals(id)){
                System.out.println(r.getValue() + " " + movie);
                break;
            }
        }
    }
    
    
}
