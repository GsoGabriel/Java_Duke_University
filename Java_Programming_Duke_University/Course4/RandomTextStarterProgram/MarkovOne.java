
/**
 * Escreva a descrição da classe MarkovOne aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuffer sb = new StringBuffer();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        for (int k=0; k< numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0){
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length()){
            int index = myText.indexOf(key, pos);
            if (index != -1 && index+key.length() < myText.length()){
                String subStr = myText.substring(index+key.length(), index+key.length()+1);
                follows.add(subStr);
            }
            else{
                break;
            }
            pos = index+1;
        }
        return follows;
    }
}
