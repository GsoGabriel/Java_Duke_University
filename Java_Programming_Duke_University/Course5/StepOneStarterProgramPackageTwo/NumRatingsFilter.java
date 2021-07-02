
/**
 * Escreva a descrição da classe NumRatingsFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.*;

public class NumRatingsFilter implements Filter {
    private int myRatings;
    
    public NumRatingsFilter(int ratings) {
    	myRatings = ratings;
    }
    
    private int ratingsParticularMovie(String movieID){
        ArrayList<Rater> raterArray = RaterDatabase.getRaters();
        ArrayList<Double> ratings = new ArrayList<Double>();
        for (Rater rater : raterArray){
            double r = rater.getRating(movieID);
            if (r != -1){
                ratings.add(r);
            }
        }
        return ratings.size();
    }
    
    @Override
    public boolean satisfies(String id) {
    	return ratingsParticularMovie(id) >= myRatings;
    }
}
