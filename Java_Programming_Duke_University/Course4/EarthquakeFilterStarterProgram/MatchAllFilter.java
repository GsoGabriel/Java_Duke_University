
/**
 * Escreva a descrição da classe MatchAllFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;
import java.lang.*;

public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;
    
    public MatchAllFilter(){
        filters = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        filters.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        
        for (Filter f: filters){
            if (!f.satisfies(qe)){
                return false;
            }
        }
        
        return true;
    }
    
    
    public String getName(){
        StringBuilder allFilters = new StringBuilder();
        for (int k = 0; k < filters.size(); k++){
            Filter f = filters.get(k);
            String fname = f.getName();
            if (!(k == filters.size() - 1)){
                allFilters.append(fname+" ");
            }
            else{
                allFilters.append(fname);
            }
        }
        return allFilters.toString();
    }
}
