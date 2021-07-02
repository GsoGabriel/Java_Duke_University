
/**
 * Escreva a descrição da classe MarkovWordTwo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private int indexOf(String[] words, String target1, String target2, int start){
        for (int k = start; k < words.length; k++){
            if (target1.equals(words[k]) && target2.equals(words[k+1])){
                return k;
            }
        }
        return -1;
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length){
            int index = indexOf(myText, key1, key2, pos);
            if (index != -1 && index+2 < myText.length){
                String subStr = myText[index+2];
                follows.add(subStr);
            }
            else{
                break;
            }
            pos = index+2;
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
