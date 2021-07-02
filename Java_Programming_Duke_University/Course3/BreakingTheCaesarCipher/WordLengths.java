
/**
 * This program is to figure out the most common word length of words from a file.
 * 
 * Esse programa é para descobrir o tamanho mais comum das palavras de um arquivo.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;

public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts){
        int numChar = 0;
        int arrayLength = counts.length;
        for (String s : resource.words()){
            int currLength = s.length();
            for (int i=0; i < currLength; i++){
                char currChar = s.charAt(i);
                if (i != 0 && i != currLength-1){
                    numChar++;
                }
                else{
                    if (Character.isLetter(currChar)){
                        numChar++;
                    }
                }
            }
            if (numChar >= arrayLength){
                counts[arrayLength-1]++;
            }
            else{
                counts[numChar]++;
            }
            numChar = 0;
        }       
    }
    
    public int indexOfMax(int[] values){
        int largestElement = 0;
        int currElement = 0;
        for(int i=0; i < values.length; i++){
            if (values[i] > currElement){
                currElement = values[i];
                largestElement = i;
            }
        }
        return largestElement;
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr,counts);
        for (int i=1; i<31; i++){
            if (counts[i] != 0){
                System.out.println(counts[i] + " words of length " + i);
            }
        }
        countWordLengths(fr,counts);
        System.out.println("The most common word length in the file is " + indexOfMax(counts));
    }
}
