
/**
 * Escreva a descrição da classe Tester aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import java.util.*;

public class Tester {
    public void testGetFollows(){
        MarkovOne mo = new MarkovOne();
        String trainingText = "this is a test yes this is a test.";
        mo.setTraining(trainingText);
        System.out.println(mo.getFollows("es").toString());
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String st = fr.asString();
        st = st.replace('\n', ' ');
        MarkovOne markov = new MarkovOne();
        markov.setRandom(42);
        markov.setTraining(st);
        
        ArrayList<String> gf = markov.getFollows("he");
        System.out.println(gf.size());
    }
    
    void testesoma(){
        String i= "um";
        switch (i) {
            case "um":
                System.out.println("é 1");
                break;
            case "dois":
                System.out.println("é 2");
                break;
            default:
                System.out.println("né não");
        }
    }
    static void myStaticMethod() {
        System.out.println("Static methods can be called without creating objects");
    }
    public void myPublicMethod() {
        System.out.println("Public methods must be called by creating objects");
    }
    public static void main(String[] args) {
        myStaticMethod(); // Call the static method
        // myPublicMethod(); This would compile an error
    
        Tester myObj = new Tester(); // Create an object of Main
        myObj.myPublicMethod(); // Call the public method on the object
    }
}
