
/**
 * Escreva a descrição da interface Recommender aqui.
 * 
 * @author (seu nome) 
 * @version (número da versão ou data)
 */
import java.util.*;


public interface Recommender {
    public ArrayList<String> getItemsToRate();
    public void printRecommendationsFor(String webRaterID);
}
