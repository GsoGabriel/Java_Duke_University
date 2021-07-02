
/**
 * Escreva a descrição da classe TitleAndDepthComparator aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.*;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{
    public int compare(QuakeEntry q1, QuakeEntry q2){
        int compareInfo = q1.getInfo().compareTo(q2.getInfo());
        if(compareInfo == 0){
            return Double.compare(q1.getDepth(), q2.getDepth());
        }
        return compareInfo;
    }
    
}
