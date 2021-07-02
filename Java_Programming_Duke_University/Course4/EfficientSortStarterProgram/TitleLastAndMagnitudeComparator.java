
/**
 * Escreva a descrição da classe TitleLastAndMagnitudeComparator aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    
    public int compare(QuakeEntry q1, QuakeEntry q2){
        String lastWordTitleQ1 = q1.getInfo().substring(q1.getInfo().lastIndexOf(" ")+1);
        String lastWordTitleQ2 = q2.getInfo().substring(q2.getInfo().lastIndexOf(" ")+1);
        int compareStr = lastWordTitleQ1.compareTo(lastWordTitleQ2);
        if (compareStr == 0){
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
        return compareStr;
    }
    
}
