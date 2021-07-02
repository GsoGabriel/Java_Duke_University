
/**
 * Escreva a descrição da classe MarkovWord aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private Integer myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(wg);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            wg = wg.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        for (int k = start; k < words.length-target.length(); k++){
            WordGram wg = new WordGram(words, k, target.length());
            if (target.equals(wg)){
                return k;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length){
            int index = indexOf(myText, kGram, pos);
            if (index != -1 && index+1 < myText.length){
                String subStr = myText[index+kGram.length()];
                follows.add(subStr);
            }
            else{
                break;
            }
            pos = index+1;
        }
        return follows;
    }
    
    
    void testIndexOf(){
        String texto = "this is just a test yes this is a simple test";
        String[] array = texto.split(" "); 
        int k = 0;
        for(String s : array){
            System.out.println(k + " " + s);
            k++;
        }
    }
}
