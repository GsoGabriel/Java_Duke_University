
/**
 * It's a method to decrypt messages that were encrypted in CaesarCipher class.
 * 
 * Esse é um método de descripitografar as mensagens que foram encriptografadas na classe CaesarCipher.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String message){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int i = 0; i < 26; i++){
            counts[i]=0;
        }
        for (int k=0; k < message.length(); k++){
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alph.indexOf(ch);
            if (dex != -1){
                counts[dex] += 1;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] values){
        int maxDex = 0;
        for (int i=0; i < values.length; i++){
            if (values[i] > values[maxDex]){
                maxDex = i;
            }
        }
        return maxDex;
    }
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return cc.encrypt(encrypted, 26-dkey);
    }
    
    public void testDecrypt(){
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt("Let's have a teeeeeeest!", 17);
        System.out.println(message);
        message = decrypt(message);
        System.out.println(message);
    }
    
    public String halfOfString(String message, int start){
        String newMessage = new String();
        for (int i=start; i<message.length(); i += 2){
            newMessage += message.charAt(i);
        }
        return newMessage;
    }
    
    public void testHalfOfString() {
        String message = "Qbkm Zgis";
        System.out.println("0: " + halfOfString(message, 0));
        System.out.println("1: " + halfOfString(message, 1));
        System.out.println("2: " + halfOfString(message, 2));
    }
    
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        System.out.println(maxDex);
        int dkey = maxDex - 4;
        if (dkey < 0){
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }
    
    public String decryptTwoKeys(String s){
        String firstString = halfOfString(s, 0);
        String secondString = halfOfString(s, 1);
        int key1 = getKey(firstString);
        int key2 = getKey(secondString);
        System.out.println("Key 1: " + key1);
        System.out.println("Key 2: " + key2);
        CaesarCipher cc = new CaesarCipher();
        String decrypt = cc.encryptTwoKeys(s, 26-key1, 26-key2);
        System.out.println("Decrypt: "+ decrypt);
        return decrypt;
    }
    
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        System.out.println(decryptTwoKeys(st));
    }
    
    public void meuteste(){
        
        String g = "Top ncmy qkff vi vguv vbg ycpx";
        System.out.println(decryptTwoKeys(g));
    }
}
