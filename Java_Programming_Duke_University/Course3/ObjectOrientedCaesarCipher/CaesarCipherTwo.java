
/**
 * Escreva a descrição da classe CaesarCipherTwo aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public CaesarCipherTwo(int key1, int key2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder(input);
        for (int i=0; i<input.length(); i++){
            char currChar = encrypted.charAt(i);
            boolean lower = Character.isLowerCase(currChar);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            
            if (i%2==0){
                if (idx != -1){
                    if (lower){
                        char newChar = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    }
                    else{
                        char newChar = shiftedAlphabet1.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
            
            else{
                if (idx != -1){
                    if (lower){
                        char newChar = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, Character.toLowerCase(newChar));
                    }
                    else{
                        char newChar = shiftedAlphabet2.charAt(idx);
                        encrypted.setCharAt(i, newChar);
                    }
                }
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        System.out.println("Key 1: " + mainKey1);
        System.out.println("Key 2: " + mainKey2);
        CaesarCipherTwo cc = new CaesarCipherTwo(26-mainKey1, 26-mainKey2);
        String decrypt = cc.encrypt(input);
        return decrypt;
    }
}
