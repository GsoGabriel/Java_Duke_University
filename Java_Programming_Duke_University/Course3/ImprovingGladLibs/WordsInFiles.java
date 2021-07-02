
/**
 * Escreva a descrição da classe WordsInFiles aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
//Create a new class called WordsInFiles. Put all the remaining listed items in this class.
import java.util.*;
import edu.duke.*;
import java.io.File;

public class WordsInFiles {
    //Create a private variable to store a HashMap that maps a word to an ArrayList of filenames.
    HashMap<String, ArrayList<String>> mapWords;
    
    //Write a constructor to initialize the HashMap variable.
    public WordsInFiles(){
        mapWords = new HashMap<String, ArrayList<String>>();
    }
    
    //Write a private void method named addWordsFromFile that has one parameter f of type File. 
    //This method should add all the words from f into the map. If a word is not in the map, then 
    //you must create a new ArrayList of type String with this word, and have the word map to
    //this ArrayList. If a word is already in the map, then add the current filename to its ArrayList,
    //unless the filename is already in the ArrayList. You can use the File method getName to get the 
    //filename of a file. 
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        String fileName = f.getName();
        for (String s : fr.words()){
            if (mapWords.containsKey(s)){
                ArrayList<String> value = mapWords.get(s);
                if (!value.contains(fileName)){
                    value.add(fileName);
                    mapWords.put(s,value);
                }
            }
            else{
                ArrayList<String> value = new ArrayList<String>();
                value.add(fileName);
                mapWords.put(s,value);
            }
        }
    }
    
    //Write a void method named buildWordFileMap that has no parameters. This method first clears the map, and then uses a 
    //DirectoryResource to select a group of files. For each file, it puts all of its words into the map by calling the method 
    //addWordsFromFile. The remaining methods to write all assume that the HashMap has been built.
    public void buildWordFileMap(){
        mapWords.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    //Write the method maxNumber that has no parameters. This method returns the maximum number of files any word 
    //appears in, considering all words from a group of files. In the example above, there are four files considered. No word
    //appears in all four files. Two words appear in three of the files, so maxNumber on those four files would return 3. This
    //method assumes that the HashMap has already been constructed.
    public int maxNumber(){
        int maxNum = 0;
        for (String s : mapWords.keySet()){
            ArrayList<String> values = mapWords.get(s);
            int times = values.size();
            if (times > maxNum){
                maxNum = times;
            }
        }
        return maxNum;
    }
    
    //Write the method wordsInNumFiles that has one integer parameter called number. This method returns an ArrayList of
    //words that appear in exactly number files. In the example above, the call wordsInNumFiles(3) would return an ArrayList
    //with the words  “cats” and “and”, and the call wordsInNumFiles(2) would return an ArrayList with the words 
    //“love”, “are”, and “dogs”, all the words that appear in exactly two files.
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> words = new ArrayList<String>();
        for (String s : mapWords.keySet()){
            ArrayList<String> values = mapWords.get(s);
            int times = values.size();
            if (times == number){
                words.add(s);
            }
        }
        return words;
    }
    
    //Write the void method printFilesIn that has one String parameter named word. This method prints the names of the files
    //this word appears in, one filename per line. For example, in the example above, the call printFilesIn(“cats”) would
    //print the three filenames: brief1.txt, brief3.txt, and brief4.txt, each on a separate line.
    public void printFilesIn(String word){
        if (mapWords.containsKey(word)){
            ArrayList<String> values = mapWords.get(word);
            for (int i = 0; i < values.size(); i++){
                System.out.println(values.get(i));
            }
        }
    }
    
    //Write the void method tester that has no parameters. This method should call buildWordFileMap to select a group of files
    //and build a HashMap of words, with each word mapped to an ArrayList of the filenames this word appears in, determine
    //the maximum number of files any word is in, considering all words, and determine all the words that are in the maximum 
    //number of files and for each such word, print the filenames of the files it is in. (optional) If the map is not too big,
    //then you might want to print out the complete map, all the keys, and for each key its ArrayList. This might be helpful
    //to make sure the map was built correctly.
    public void tester(){
        buildWordFileMap();
        /**for (String s : mapWords.keySet()){
            System.out.print(s + " ");
            for (int i = 0; i < mapWords.get(s).size(); i++){
                System.out.print(mapWords.get(s).get(i) + " ");
            }
            System.out.println("");
        }*/
        
        for (String s : mapWords.keySet()){
            if (s.equals("tree")){
                for (int i = 0; i < mapWords.get(s).size(); i++){
                    System.out.print(mapWords.get(s).get(i) + " ");
                }   
            }
        }
        
        //int maxNum = maxNumber();
        //System.out.println(maxNum);
        //ArrayList<String> words = wordsInNumFiles(4);
        //System.out.println(words.size());
        
        
        
        /**for (int i = 0; i < words.size(); i++){
            printFilesIn(words.get(i));
        }*/
        
    }
}
