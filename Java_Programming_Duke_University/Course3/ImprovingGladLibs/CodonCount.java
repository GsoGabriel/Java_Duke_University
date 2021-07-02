
/**
 * Escreva a descrição da classe CodonCount aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
//Create a new class for this problem
import java.util.*;
import edu.duke.*;

public class CodonCount {
    //Create a private variable to store a HashMap to map DNA codons to their count.
    HashMap<String,Integer> mapCodon;
    
    //Write a constructor to initialize the HashMap variable.
    public CodonCount(){
        mapCodon = new HashMap<String,Integer>();
    }
    
    //Write a void method named buildCodonMap that has two parameters, an int named start
    //and a String named dna. This method will build a new map of codons mapped to their 
    //counts from the string dna with the reading frame with the position start (a value of 0, 1, or 2).
    //You will call this method several times, so make sure your map is empty before building it.
    private void buildCodonMap(int start, String dna){
        mapCodon.clear();
        int currStart = start;
        while(currStart <= dna.length()-3){
            String codon = dna.substring(currStart, currStart+3);
            currStart +=3;
            if (mapCodon.keySet().contains(codon)){
                int count = mapCodon.get(codon);
                mapCodon.put(codon, count+1);
            }
            else{
                mapCodon.put(codon, 1);
            }
        }
    }
    
    //Write a method named getMostCommonCodon that has no parameters. This method returns a String,
    //the codon in a reading frame that has the largest count. If there are several such codons, 
    //return any one of them. This method assumes the HashMap of codons to counts has already been built.
    public String getMostCommonCodon(){
        String mostCommon = "";
        int largestNum = 0;
        for (String w : mapCodon.keySet()){
            if (mapCodon.get(w) > largestNum){
                mostCommon = w;
                largestNum = mapCodon.get(w);
            }
        }
        return mostCommon;
    }
    
    //Write a void method named printCodonCounts that has two int parameters, start and end.
    //This method prints all the codons in the HashMap along with their counts if their count is between 
    //start and end, inclusive.
    public void printCodonCounts(int start, int end){
        for (String w : mapCodon.keySet()){
            if (mapCodon.get(w) >= start && mapCodon.get(w) <= end){
                System.out.println(w + " " + mapCodon.get(w));
            }
        }
    }
    
    //Write a tester method that prompts the user for a file that contains a DNA strand (could be upper 
    //or lower case letters in the file, convert them all to uppercase, since case should not matter). 
    //Then for each of the three possible reading frames, this method builds a HashMap of codons to their 
    //number of occurrences in the DNA strand, prints the total number of unique codons in the reading
    //frame, prints the most common codon and its count, and prints the codons and their number of occurrences 
    //for those codons whose number of occurrences in this reading frame are between two numbers inclusive.
    public void tester(){
        FileResource fr = new FileResource();
        String fileString = fr.asString().toUpperCase().trim();
        for (int i=0; i < 3; i++){
            buildCodonMap(i, fileString);
            System.out.println("\nReading frame starting with " + i + " results in " + mapCodon.size() + " unique codons");
            String mostCommon = getMostCommonCodon();
            System.out.println(" and most common codon is " + mostCommon + " with count " + mapCodon.get(mostCommon));
            System.out.println("Counts of codons between 7 and 7 inclusive are:");
            printCodonCounts(7,7);
        }
        
    }
}
