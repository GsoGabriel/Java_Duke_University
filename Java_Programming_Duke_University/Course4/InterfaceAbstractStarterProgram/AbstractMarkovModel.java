
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected Integer order;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);
    
    protected ArrayList<String> getFollows(String key){
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
    
    public String toString(){
        return String.format("MarkovModel of order %d", order);
    }
}
