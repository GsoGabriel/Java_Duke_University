
/**
 * Escreva a descrição da classe Simple aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Simple {
     private String word; 
     private String phrase; 
     public Simple(int number, String w) {  
          word = w;     
          phrase = mystery(number, w);          
     }   
     private String mystery(int num, String s) {    
          String answer = "";   
          for (int k=0; k<num; k++) {       
               answer = answer + s;     
          }     
          return answer; 
     }
     
     public String toString() {     
          return phrase + " is " + word + " repeated";
     }
     
}
