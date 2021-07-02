
/**
 * Escreva a descrição da classe TestCaesarCipher aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;

public class TestCaesarCipher {
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
    
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (dkey < 0){
            dkey = 26 - (4-maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        return cc.decrypt(input);
    }
    
    public void simpleTests(){
        //FileResource fr = new FileResource();
        String fileString = "Can you imagine life WITHOUT the internet AND computers in your pocket?"; //fr.asString();
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(fileString);
        System.out.println("Encrypted: " + encrypted);
        //String decrypted = cc.decrypt(encrypted);
        System.out.println("Decrypted: " + breakCaesarCipher(encrypted));
    }
    
}
