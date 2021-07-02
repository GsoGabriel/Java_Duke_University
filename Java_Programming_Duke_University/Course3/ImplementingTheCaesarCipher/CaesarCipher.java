
/**
 * Caesar Cipher algorithm. The basic idea of this algorithm is to substitute
 * each letter with the letter obtained by shifting the alphabet by a fixed 
 * amount.
 * 
 * A ideia básica desse algoritimo é substituir cada letra com a letra obtida
 * no alfabeto alterado através de um número fixo, ou quantidade fixa.
 * 
 * Essa é uma versão de melhorada da classe CaesarCipher feita pelo professor
 * nos vídeos.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;

public class CaesarCipher {
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
    public String encrypt (String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0,key);
        
        for (int i=0; i<input.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            int idxLower = alphabetLower.indexOf(currChar);
            
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            
            if (idxLower != -1){
                char newChar = shiftedAlphabetLower.charAt(idxLower);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String shiftedAlphabetKey1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabetLowerKey1 = alphabetLower.substring(key1) + alphabetLower.substring(0,key1);
        
        String shiftedAlphabetKey2 = alphabet.substring(key2) + alphabet.substring(0,key2);
        String shiftedAlphabetLowerKey2 = alphabetLower.substring(key2) + alphabetLower.substring(0,key2);
        
        for (int i=0; i<input.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            int idxLower = alphabetLower.indexOf(currChar);
            
            if (i%2==0){
                if (idx != -1){
                    char newChar = shiftedAlphabetKey1.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            
                if (idxLower != -1){
                    char newChar = shiftedAlphabetLowerKey1.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }
            }
            
            else{
                if (idx != -1){
                    char newChar = shiftedAlphabetKey2.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            
                if (idxLower != -1){
                    char newChar = shiftedAlphabetLowerKey2.charAt(idxLower);
                    encrypted.setCharAt(i, newChar);
                }
            }
            
        }
        return encrypted.toString();
    }
    
    public void testEncrypt(){
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public void testEncryptTwoKeys(){
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        
    }
}
