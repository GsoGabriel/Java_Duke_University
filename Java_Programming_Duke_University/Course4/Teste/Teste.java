
/**
 * Escreva a descrição da classe Teste aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class Teste {
    //Ordena a ArrayList<Integer>
    
    public Integer getSmallest(ArrayList<Integer> original){
        Integer minElement = original.get(0);
        for (Integer i : original){
                if (i < minElement){
                    minElement = i;
                }
        }
        return minElement;
    }
    
    public ArrayList<Integer> mySort(ArrayList<Integer> original){
        ArrayList<Integer> modified = new ArrayList<Integer>();
        while (!original.isEmpty()){
            Integer minElement = getSmallest(original);
            original.remove(new Integer(minElement));
            modified.add(minElement);
        }
        return modified;
    }
    
    public void testMySort(){
        ArrayList<Integer> original = new ArrayList<Integer>();
        original.add(9);
        original.add(-3);    
        original.add(0);
        System.out.println(mySort(original));
    }
}
