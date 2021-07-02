
/**
 * Escreva a descrição da classe EfficientRater aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class EfficientRater {
    private String myID;
    private HashMap<String,Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        if (myRatings.containsKey(item)){
            return myRatings.get(item).getValue();
        }       
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(String s : myRatings.keySet()){
            list.add(s);
        }
        return list;
    }
    
    public HashMap<String,Rating> getMyRatings(){
        return myRatings;
    }
    
}
