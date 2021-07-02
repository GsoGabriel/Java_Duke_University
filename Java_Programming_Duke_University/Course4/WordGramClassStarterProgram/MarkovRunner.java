
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        // String st = "ESSE E UM TESTE ESSE E UM TESTE ESSE E UM TESTE NOVO"; 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 200, 844); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

    void testHashMap(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        // String st = "this is a test yes this is really a test"; 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(2); 
        runModel(markovWord, st, 50, 65 );
        
    }
    
    void compareMethods(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        // String st = "this is a test yes this is really a test";
        long time1 = System.nanoTime();
        MarkovWord markovWord = new MarkovWord(2); 
        runModel(markovWord, st, 100, 42); 
        long time2 = System.nanoTime();
        EfficientMarkovWord eMarkovWord = new EfficientMarkovWord(2); 
        runModel(eMarkovWord, st, 100, 42);
        long time3 = System.nanoTime();
        
        System.out.println(" tempo markov: " + (time2-time1) + " tempo e-markov: " + (time3-time2) + " Diferença: " + ((time3-time2) - (time2-time1)));
    }
    
}
