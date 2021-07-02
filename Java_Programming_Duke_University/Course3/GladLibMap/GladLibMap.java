import edu.duke.*;
import java.util.*;

public class GladLibMap {
    HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        ArrayList<String> arrayList = new ArrayList<String>(); 
        String[] category = {"country", "color", "noun", "name", "adjective", "animal", "timeframe", "verb", "fruit"};
        for (int i = 0; i < category.length; i++){
            arrayList = readIt(source + "/" + category[i] + ".txt"); 
            myMap.put(category[i],arrayList);
        }
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (myMap.containsKey(label)){
            if (!usedCategories.contains(label)){
                usedCategories.add(label);
            }
            return randomFrom(myMap.get(label));
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return "**UNKNOWN**";
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int indexUsedWords = usedWords.indexOf(sub);
        while (indexUsedWords != -1){
            sub = getSubstitute(w.substring(first+1,last));
            indexUsedWords = usedWords.indexOf(sub);
        }
        usedWords.add(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public void makeStory(){
        usedWords.clear();
        usedCategories.clear();
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\nTotal words replaced: " + usedWords.size());
        System.out.println("\nTotal number of words that were possible to pick from: " + totalWordsInMap());
        System.out.println("Total number of words in the ArrayLists of the categories that were used: " + totalWordsConsidered());
    }
    
    public int totalWordsInMap(){
        int count = 0;
        for (String s : myMap.keySet()){
            ArrayList<String> currArray = myMap.get(s);
            int currCount = currArray.size();
            count += currCount;
        }
        return count;
    }
    
    int totalWordsConsidered(){
        int count = 0;
        for (String s : usedCategories){
            ArrayList<String> currArray = myMap.get(s);
            int currCount = currArray.size();
            count += currCount;
        }
        return count;
    }

}
