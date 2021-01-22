import java.io.*;
import java.util.*;
/**
 * Escreva a descrição da classe Teste aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Teste {
   public static void main(String[] args) {

      Double d1 = new Double(-1.0/0.0);
      Double d2 = new Double(0.0/0.0);
      
      System.out.println(2.0 - 1.1);
  
      // returns true if this Double value is a Not-a-Number (NaN) 
      System.out.println(d1 + " = " + d1.isNaN());
  
      // returns false for other cases
      System.out.println(d2 + " = " + d2.isNaN());
      
      String greeting = "Hello";
      int n = greeting.length(); // is 5
      int cpCount = greeting.codePointCount(0, greeting.length());
      
      System.out.println(cpCount);
      
      //Scanner type. It's to input.
      Scanner in = new Scanner(System.in);
      System.out.print("What is your name? ");
      String name = in.nextLine();
      String firstName = in.nextLine();
      System.out.print("How old are you? ");
      int age = in.nextInt();
      System.out.println("Hello, " + name + ". Next year, you'll be " + (age + '\ufffd'));
      Console cons = System.console();
      String username = cons.readLine("User name: ");
      char[] passwd = cons.readPassword("Password: ");
   }
}