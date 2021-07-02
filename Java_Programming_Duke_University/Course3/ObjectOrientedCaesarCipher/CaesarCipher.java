
/**
 * In this assignment, you will put together the CaesarCipher class from 
 * the lesson and add a decrypt method to decrypt with the same key. 
 * In addition you will create a second class, TestCaesarCipher to test 
 * examples that use the CaesarCipher class, including writing a method 
 * that will automatically decrypt an encrypted file by determining the 
 * key and then decrypting with that key.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class CaesarCipher {
    private String alphabet;
    private String alphLower;
    private String shiftedAlphabet;
    private String shiftedLower;
    private int mainKey;
    
    public CaesarCipher(int key){
        mainKey = key;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphLower = alphabet.toLowerCase();
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);
        shiftedLower = alphLower.substring(key) + alphLower.substring(0,key);
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0; i<input.length(); i++){
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);
            int idxLower = alphLower.indexOf(currChar);
            
            if (idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
            
            if (idxLower != -1){
                char newChar = shiftedLower.charAt(idxLower);
                encrypted.setCharAt(i, newChar);
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
    
    
}
