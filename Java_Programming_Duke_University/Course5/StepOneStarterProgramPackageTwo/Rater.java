
/**
 * Escreva a descrição da interface Rater aqui.
 * 
 * @author (seu nome) 
 * @version (número da versão ou data)
 */

import java.util.*;

public interface Rater {
    public void addRating(String item, double rating);
    
    public boolean hasRating(String item) ;

    public String getID();

    public double getRating(String item);

    public int numRatings();

    public ArrayList<String> getItemsRated();
    
    public ArrayList<Rating> getMyRatings();
}
