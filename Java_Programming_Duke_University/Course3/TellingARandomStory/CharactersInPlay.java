
/**
 * Escreva a descrição da classe CharactersInPlay aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

//Create a class named CharactersInPlay. Put all the following items below in this class.

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    //You will need to create two private ArrayLists. One to store the the names of the characters you find and one to store the
    //corresponding counts for each character. 
    private ArrayList<String> charNames;
    private ArrayList<Integer> countChar;
    
    public CharactersInPlay(){
        charNames = new ArrayList<String>();
        countChar = new ArrayList<Integer>();
    }
    
    //Write a void method named update that has one String parameter named person. This method should update the two ArrayLists,
    //adding the character’s name if it is not already there, and counting this line as one speaking part for this person.
    private void update(String person){
        int index = charNames.indexOf(person);
        if (index == -1){
            charNames.add(person);
            countChar.add(1);
        }
        else{
            int currFreq = countChar.get(index);
            int newFreq = currFreq + 1;
            countChar.set(index, newFreq);
        }
    }
    
    //Write a void method called findAllCharacters that opens a file, and reads the file line-by-line. For each line, if there
    //is a period on the line, extract the possible name of the speaking part, and call update to count it as an occurrence for
    //this person. Make sure you clear the appropriate instance variables before each new file.
    public void findAllCharacters(){
        charNames.clear();
        countChar.clear();
        FileResource fr = new FileResource();
        for (String s : fr.lines()){
            int period = s.indexOf(".");
            if (period != -1){
                String name = s.substring(0, period);
                update(name);
            }
        }
    }
    
    //Write a void method called tester that has no parameters. This method should call findAllCharacters, and then for each
    //main character, print out the main character, followed by the number of speaking parts that character has. A main
    //character is one who has more speaking parts than most people. You’ll have to estimate what that number should be. 
    //Test your method on the file macbethSmall.txt. and then macbeth.txt. 
    public void tester(){
        findAllCharacters();
        charactersWithNumParts(10, 15);
        
    }
    
    //Write a void method called charactersWithNumParts that has two int parameters named num1 and num2, where you can assume
    //num1 should be less than or equal to num2. This method should print out the names of all those characters that have
    //exactly number speaking parts, where number is greater than or equal to num1 and less than or equal to num2. Add code
    //in tester to test this method out.
    private void charactersWithNumParts(int num1, int num2){
        for (int i = 0; i < charNames.size(); i++){
            if ((countChar.get(i) >= num1) && (countChar.get(i) <= num2)){
                System.out.println(charNames.get(i) + " " + countChar.get(i));
            }
        }
    }
}
