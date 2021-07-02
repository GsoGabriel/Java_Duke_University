
/**
 * Escreva a descrição da classe EfficientMarkovModel aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel  {
    private HashMap<String, ArrayList<String>> followsMap;
    private Integer nextChar;
    
    public EfficientMarkovModel(int n){
        followsMap = new HashMap<String, ArrayList<String>>();
        nextChar = n;
        order = n;
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
        buildMap();
        printHashMapInfo();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-nextChar);
        String key = myText.substring(index, index+nextChar);
        sb.append(key); 
        for (int k=0; k< numChars-nextChar; k++){
            ArrayList<String> follows = followsMap.get(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
        
    }
    
    public String toString(){
         return String.format("this is the EfficientMarkovModel class of %d", nextChar);
    }
    
    public void buildMap(){
        for (int k = 0; k <= myText.length()-nextChar; k++){
            String newKey = myText.substring(k, k+nextChar);
            if (!followsMap.containsKey(newKey)){
                ArrayList<String> follows = getFollows(newKey);
                followsMap.put(newKey, follows);
            }
        }
    }
    
    public void printHashMapInfo(){
        int sizeGet = 0;
        String lKey = "";
        for (String k : followsMap.keySet()){
            System.out.println("Key " + k + " " + followsMap.get(k));
            if (followsMap.get(k).size() > sizeGet){
                sizeGet = followsMap.get(k).size();
            }
        }
        System.out.println("Hashmap size: " + followsMap.size());
        System.out.println("Size of the largest value in the HashMap: " + sizeGet);
        for (String k : followsMap.keySet()){
            if (followsMap.get(k).size() == sizeGet){
                System.out.println(k + " ");
            }
        }
    }
    
}
