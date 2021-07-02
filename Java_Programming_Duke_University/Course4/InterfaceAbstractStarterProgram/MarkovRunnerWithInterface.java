
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov.toString());
        for(int k=0; k < 3; k++){
            String st= markov.getRandomText(size);
            printOut(st);
        }
        System.out.println("END");
    }
    
    public void runMarkov() {
        // FileResource fr = new FileResource();
        // String st = fr.asString();
        String st = "TESTO SIM";
        st = st.replace('\n', ' ');
        int size = 9;
        int seed = 1;
        
        IMarkovModel mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        IMarkovModel mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        IMarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        IMarkovModel mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
    
    public void testHashMap(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        int seed = 531 ;
        // String st = "yes-this-is-a-thin-pretty-pink-thistle";
        int size = 1000;
        IMarkovModel eModel = new EfficientMarkovModel(5);
        runModel(eModel, st, size, seed);
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        int seed = 42;
        int size = 1000;
        long inicio1 = System.nanoTime();
        IMarkovModel eModel = new EfficientMarkovModel(2);
        runModel(eModel, st, size, seed);
        long termino1 = System.nanoTime();
        System.out.println(termino1 - inicio1);
        long inicio2 = System.nanoTime();
        IMarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        long termino2 = System.nanoTime();
        System.out.println(termino2 - inicio2);
        System.out.println(" DIFERENÇA: " + ((termino2 - inicio2) - (termino1 - inicio1)));
    }
}
