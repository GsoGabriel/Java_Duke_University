
/**
 * Escreva a descrição da classe EfficientMarkovWord aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private Integer myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        map = new HashMap<WordGram, ArrayList<String>>();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
        // System.out.println("Criado");
        printHashMapInfo();
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram wg = new WordGram(myText, index, myOrder);
        sb.append(wg.toString());
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            if (!map.containsKey(wg)){
                buildMap(wg);
            }
            ArrayList<String> follows = map.get(wg);
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
    
    private void buildMap(){
        for (int i = 0; i <= myText.length-myOrder; i++){
            WordGram wg = new WordGram(myText, i, myOrder);
            if (!map.containsKey(wg)){
                ArrayList<String> follows = getFollows(wg);
                map.put(wg, follows);
            }
        }
    }
    
    private void buildMap(WordGram wg){
        ArrayList<String> follows = getFollows(wg);
        map.put(wg, follows);
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
    
    void printHashMapInfo(){
        int largest = 0;
        for (WordGram wg : map.keySet()){
            if (map.get(wg).size() > largest){
                largest = map.get(wg).size();
            }
            // System.out.println("Worda: " + wg.toString() + " follows " + map.get(wg));
        }
        // System.out.println(map.size());
        System.out.println(largest);
        // for (WordGram wg : map.keySet()){
            // if (map.get(wg).size() ==largest){
                // System.out.println("Worda com largest: " + wg.toString());
            // }
        // }
        
    }
}
