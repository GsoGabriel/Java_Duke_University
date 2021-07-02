
/**
 * Escreva a descrição da classe CaesarCipher aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key){
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);
        //Call from 0 to < length of encrypted, (call it i)
        for (int i=0; i < encrypted.length(); i++){
            int idx = 0;
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar)){
                // Find the index of currchar in the alphabet (call it idx)
                idx = alphabet.indexOf(currChar);
                if (idx!=-1){
                    //get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    // Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            if (Character.isLowerCase(currChar)){
                idx = alphabetLower.indexOf(currChar);
                if (idx!=-1){
                    //get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetLower.charAt(idx);
                    // Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            // Otherwise: do nothing
        }
        // Your answer is the string inside of encrypted
        return encrypted.toString();
    }
    
    public void testCaesar(){
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }
}
