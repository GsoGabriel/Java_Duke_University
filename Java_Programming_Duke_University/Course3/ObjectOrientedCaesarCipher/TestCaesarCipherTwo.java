
/**
 * Escreva a descrição da classe TestCaesarCipherTwo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    private int[] countLetters(String message){
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
    
    private int maxIndex(int[] values){
        int maxDex = 0;
        for (int i=0; i < values.length; i++){
            if (values[i] > values[maxDex]){
                maxDex = i;
            }
        }
        System.out.println("FINAL: "+maxDex);
        return maxDex;
    }
    
    private String halfOfString(String message, int start){
        String newMessage = new String();
        for (int i=start; i<message.length(); i += 2){
            newMessage += message.charAt(i);
        }
        System.out.println(newMessage);
        return newMessage;
    }
    
    private int getKey(String s){
        int[] freqs = countLetters(s);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4-maxDex);
        }
        return dkey;
    }
    
    public String breakCaesarCipher(String input){
        int dkey1 = getKey(halfOfString(input, 0));
        int dkey2 = getKey(halfOfString(input, 1));
        System.out.println(dkey1 + "\t" + dkey2);
        CaesarCipherTwo cc = new CaesarCipherTwo(dkey1, dkey2);
        return cc.decrypt(input);
    }
    
    public void simpleTests(){
        FileResource fr = new FileResource();
        String frString = fr.asString();
        CaesarCipherTwo cc = new CaesarCipherTwo(14, 24);
        //String encrypt = cc.encrypt(frString);
        String decrypt = breakCaesarCipher(frString);
        //System.out.println("Encrypted: " + encrypt);
        System.out.println("Decrypted: " + decrypt);
    }
    
}
