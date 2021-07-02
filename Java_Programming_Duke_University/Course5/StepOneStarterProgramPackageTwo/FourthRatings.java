
/**
 * Escreva a descrição da classe FourthRatings aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.*;

public class FourthRatings {
    
    public FourthRatings(){
        // se quiser inicializar MovieDatabase e RaterDatabase fora de FourthRatings
    }
    
    public FourthRatings(String moviefile, String raterfile){
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(raterfile);
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double sum = 0;
        int numRatings = 0;
        for (Rater r : RaterDatabase.getRaters()){
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
    
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> myRatings = me.getItemsRated();
        double sum = 0;
        for (String movieID : myRatings){
            if (r.hasRating(movieID)){
                double meValue = me.getRating(movieID) - 5;
                double rValue = r.getRating(movieID) - 5;
                sum = sum + (meValue * rValue);
            }
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()){
            if (!r.getID().equals(id)){
                double dot_product = dotProduct(me,r);
                if(dot_product > 0){
                    Rating rating = new Rating(r.getID(), dot_product);
                    list.add(rating);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (String m : MovieDatabase.filterBy(new TrueFilter())){
            int totalRaters = 0;
            double sum = 0;
            if (list.size() >= numSimilarRaters){
                for (int k = 0; k < numSimilarRaters-1; k++){
                    Rating r = list.get(k);
                    Rater rater = RaterDatabase.getRater(r.getItem());
                    if (rater.hasRating(m)){
                        totalRaters++;
                        double value = r.getValue();
                        sum = sum + (value * rater.getRating(m));
                    }
                }
            }
            else{
                for (int k = 0; k < list.size(); k++){
                    Rating r = list.get(k);
                    Rater rater = RaterDatabase.getRater(r.getItem());
                    if (rater.hasRating(m)){
                        totalRaters++;
                        double value = r.getValue();
                        sum = sum + (value * rater.getRating(m));
                    }
                }
            }
            if (totalRaters >= minimalRaters){
                double weightedAverage = sum / totalRaters;
                ret.add(new Rating(m, weightedAverage));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, 
                                                        int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<Rating>();
        for (String m : MovieDatabase.filterBy(filterCriteria)){
            int totalRaters = 0;
            double sum = 0;
            for (int k = 0; k < numSimilarRaters; k++){
                Rating r = list.get(k);
                Rater rater = RaterDatabase.getRater(r.getItem());
                if (rater.hasRating(m)){
                    totalRaters++;
                    double value = r.getValue();
                    sum = sum + (value * rater.getRating(m));
                }
            }
            if (totalRaters >= minimalRaters){
                double weightedAverage = sum / totalRaters;
                ret.add(new Rating(m, weightedAverage));
            }
        }
        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }
    
}
