
/**
 * Escreva a descrição da classe WordFrequencies aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

//Create a new project in BlueJ and then create a new class called WordFrequencies. Put all the following items in this class.;

import java.util.*;
import edu.duke.*;

public class WordFrequencies {
    //Create two private variables. One is called myWords and should be an ArrayList of type String to store unique words from
    //a file, and one is called myFreqs and should be an ArrayList of type Integer. The kth position in myFreqs should represent
    //the number of times the kth word in myWords occurs in the file. 
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    //Write a constructor WordFrequencies, and initialize the private variables. 
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    //Write a void method findUnique that has no parameters. This method should first clear both myWords and myFreqs, 
    //using the .clear() method. Then it selects a file and then iterates over every word in the file, putting the unique words found 
    //into myWords. For each word in the kth position of myWords, it puts the count of how many times that word occurs from 
    //the selected file into the kth position of myFreqs, as was demonstrated in the lesson. 
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        int currFreq = 0;
        int newFreq = 0;
        FileResource fr = new FileResource();
        for (String s : fr.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else{
                currFreq = myFreqs.get(index);
                newFreq = currFreq + 1;
                myFreqs.set(index, newFreq);
            }
        }
    }
    
    //Write a void tester method that has no parameters. This method should call findUnique. Then print out the number of 
    //unique words, and for each unique word, print the frequency of each word and the word, as was demonstrated in the lesson.
    public void tester(){
        findUnique();
        System.out.println("Number of unique words: " + myWords.size());
        //for (int i = 0; i < myWords.size(); i++){
        //    int freqs = myFreqs.get(i);
          //  String words = myWords.get(i);
          //  System.out.print(freqs + " " + words + "\n");
        //}
        System.out.println("The word that occurs most often and its count are: " + myWords.get(findIndexOfMax()) + " " + myFreqs.get(findIndexOfMax()));
    }
    
    //Write the method findIndexOfMax that has no parameters. This method returns an int that is the index location of the
    //largest value in myFreqs. If there is a tie, then return the first such value.
    public int findIndexOfMax(){
        int indexMax = 0;
        int largestNum = 0;
        for (int i = 0; i < myFreqs.size(); i++){
            int freqs = myFreqs.get(i);
            if (freqs > largestNum){
                largestNum = freqs;
                indexMax = i;
            }
        }
        return indexMax;
    }
    
    //Add code to the tester method to determine and print the word that occurs the most often in a selected file and how many
    //times it occurs. You should find it helpful to call findIndexOfMax.
    
}
