import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        String sliceMsg = new String();
        for (int i = whichSlice; i < message.length(); i += totalSlices){
            sliceMsg += message.charAt(i);
        }
        return sliceMsg;
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i=0; i < klength; i++){
            String sliceString = sliceString(encrypted, i, klength);
            int dkey = cc.getKey(sliceString);
            key[i] = dkey;
        }
        return key;
    }

    public void breakVigenere() {
        FileResource fr = new FileResource();
        String str = fr.asString();
        FileResource dictionary = new FileResource();
        int[] tkl = tryKeyLength(str,38,'e');
        //System.out.println(Arrays.toString(tkl));
        HashSet<String> dictHS = readDictionary(dictionary);
        VigenereCipher vc = new VigenereCipher(tkl);
        //String messageDecrypt = breakForLanguage(str,dictHS);
        //System.out.println(messageDecrypt);
        String decrypt = vc.decrypt(str);
        int count = countWords(decrypt,dictHS);
        System.out.println(count);
    }
    
    /**
     * In the VigenereBreaker class, write the public method readDictionary, which has one parameter—a FileResource fr. This 
     * method should first make a new HashSet of Strings, then read each line in fr (which should contain exactly one word per
     * line), convert that line to lowercase, and put that line into the HashSet that you created. The method should then return the
     * HashSet representing the words in a dictionary. All the dictionary files, including the English dictionary file, are included in
     * the starter program you download. They are inside the folder called ‘dictionaries’.
     */
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> hs = new HashSet<String>();
        for (String w : fr.lines()){
            String wLower = w.toLowerCase();
            hs.add(wLower);
        }
        return hs;
    }
    
    /**
     * In the VigenereBreaker class, write the public method countWords, which has two parameters—a String message, and a 
     * HashSet of Strings dictionary. This method should split the message into words (use .split(“\\W+”), which returns a String
     * array), iterate over those words, and see how many of them are “real words”—that is, how many appear in the dictionary. 
     * Recall that the words in dictionary are lowercase. This method should return the integer count of how many valid words it found.
     */
    public int countWords(String message, HashSet<String> dictionary){
        int validWords = 0;
        for (String word : message.split("\\W+")){
            String wordLower = word.toLowerCase();
            if (dictionary.contains(wordLower)){
                validWords++;
            }
        }
        return validWords;
    }
    
    /**
     * In the VigenereBreaker class, write the public method breakForLanguage, which has two parameters—a String 
     * encrypted, and a HashSet of Strings dictionary. This method should try all key lengths from 1 to 100 (use your
     * tryKeyLength method to try one particular key length) to obtain the best decryption for each key length in that range. For
     * each key length, your method should decrypt the message (using VigenereCipher’s decrypt method as before), and count
     * how many of the “words” in it are real words in English, based on the dictionary passed in (use the countWords method
     * you just wrote). This method should figure out which decryption gives the largest count of real words, and return that
     * String decryption. Note that there is nothing special about 100; we will just give you messages with key lengths in the range
     * 1–100. If you did not have this information, you could iterate all the way to encrypted.length(). Your program would just 
     * take a bit longer to run.
     */
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
        int validWords = 0;
        int keyLength = 0;
        String messageDecrypted = "";
        for (int i = 1; i < 101; i++){
            int[] keys = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypt = vc.decrypt(encrypted);
            int currValidWords = countWords(decrypt,dictionary);
            if (currValidWords > validWords){
                validWords = currValidWords;
                messageDecrypted = decrypt;
                keyLength = i;
            }
        }
        System.out.println("Valid Words: " + validWords);
        System.out.println("KeyLength: " + keyLength);
        return messageDecrypted;
    }
    
    /**
     * Finally, you need to modify your breakVigenere method to make use of your new code. The steps below describe what
     * your breakVigenere method should do. They are similar to the steps from the previous portion of this project but have 
     * been updated: new items are in italics, [and old items that you should no longer do are in square brackets.]
     */
    /**
     * Create a new FileResource using its default constructor (which displays a dialog for you to select a file to decrypt).
     * Use that FileResource’s asString method to read the entire contents of the file into a String.
     * You should make a new FileResource to read from the English dictionary file that we have provided. [Use the tryKeyLength method that you just wrote to find the key for the message you read in. For now, you should just pass ‘e’ for mostCommon.]
     * You should use your readDictionary method to read the contents of that file into a HashSet of Strings. [You should create a new VigenereCipher, passing in the key that tryKeyLength found for you.]
     * You should use the breakForLanguage method that you just wrote to decrypt the encrypted message. [You should use a VigenereCipher object to decrypt the encrypted message.]
     * Finally, you should print out the decrypted message!
     * Test this method on the text file athens_keyflute.txt. The first line should be “SCENE II. Athens. QUINCE'S house”, and the key is “flute”, or {5, 11, 20, 19, 4}. This file contains 376 valid words out of 386 (total word count ignores whitespace). 
     */
    public void breakVigenere2() {
        FileResource fr = new FileResource();
        String str = fr.asString();
        FileResource dictionary = new FileResource();
        HashSet<String> dictHS = readDictionary(dictionary);
        String messageDecrypt = breakForLanguage(str,dictHS);
        System.out.println(messageDecrypt);
    }
    
    /**
     * In the VigenereBreaker class, write the public method mostCommonCharIn, which has one parameter—a HashSet of
     * Strings dictionary. This method should find out which character, of the letters in the English alphabet, appears most often 
     * in the words in dictionary. It should return this most commonly occurring character. Remember that you can iterate over a
     * HashSet of Strings with a for-each style for loop.
     */
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character,Integer> alphabet = new HashMap<Character,Integer>();
        int largestValue = 0;
        char mostCommonChar = 'e';
        for (String s : dictionary){
            for (char ch : s.toCharArray()){
                if (!alphabet.containsKey(ch)){
                    alphabet.put(ch,1);
                }
                else{
                    int value = alphabet.get(ch);
                    alphabet.put(ch, value+1);
                }
            }
        }
        for (Character c : alphabet.keySet()){
            if (alphabet.get(c) > largestValue){
                largestValue = alphabet.get(c);
                mostCommonChar = c;
            }
        }
        return mostCommonChar;
    }
    
    /**
     * In the VigenereBreaker class, write the public method breakForAllLangs, which has two parameters—a String encrypted,
     * and a HashMap, called languages, mapping a String representing the name of a language to a HashSet of Strings 
     * containing the words in that language. Try breaking the encryption for each language, and see which gives the best results!
     * Remember that you can iterate over the languages.keySet() to get the name of each language, and then you can use .get()
     * to look up the corresponding dictionary for that language. You will want to use the breakForLanguage and countWords
     * methods that you already wrote to do most of the work (it is slightly inefficient to re-count the words here, but it is simpler,
     * and the inefficiency is not significant). You will want to print out the decrypted message as well as the language that you 
     * identified for the message.
     */
    public void breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int numWords = 0;
        String decrypted = "";
        String lang = "";
        for (String s : languages.keySet()){
            String currDecrypted = breakForLanguage(encrypted, languages.get(s));
            int countWords = countWords(currDecrypted,languages.get(s));
            if (countWords > numWords){
                numWords = countWords;
                decrypted = currDecrypted;
                lang = s;
            }
        }
        System.out.println(lang);
        System.out.println(decrypted);
    }
    
    /**
     * Modify the method breakForLanguage to make use of your mostCommonCharIn method to find the most common 
     * character in the language, and pass that to tryKeyLength instead of ‘e’.
     */
    
    /**
     * Modify your breakVigenere method to read many dictionaries instead of just one. In particular, you should make a
     * HashMap mapping Strings to a HashSet of Strings that will map each language name to the set of words in its dictionary.
     * Then, you will want to read (using your readDictionary method) each of the dictionaries that we have provided (Danish,
     * Dutch, English, French, German, Italian, Portuguese, and Spanish) and store the words in the HashMap you made. Reading 
     * all the dictionaries may take a little while, so you might add some print statements to reassure you that your program is 
     * making progress. Once you have made that change, you will want to call breakForAllLangs, passing in the message (the 
     * code to read in the message is unchanged from before), and the HashMap you just created.
     */
    public void breakVigenere3(){
        FileResource fr = new FileResource();
        String str = fr.asString();
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        DirectoryResource dictionaries = new DirectoryResource();
        for (File f : dictionaries.selectedFiles()){
            FileResource dictionary = new FileResource(f);
            HashSet<String> dictHS = readDictionary(dictionary);
            String fileName = f.getName();
            languages.put(fileName, dictHS);
        }
        breakForAllLangs(str,languages);
        //int[] tkl = tryKeyLength(str,38,'e');
        //System.out.println(Arrays.toString(tkl));
        
        //VigenereCipher vc = new VigenereCipher(tkl);
        //String messageDecrypt = breakForLanguage(str,dictHS);
        //System.out.println(messageDecrypt);
        //String decrypt = vc.decrypt(str);
        //int count = countWords(decrypt,dictHS);
        //System.out.println(count);
    }
}
