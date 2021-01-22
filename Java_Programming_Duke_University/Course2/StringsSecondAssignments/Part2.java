
/**
 * Escreva a descrição da classe Part2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int startIndex = 0;
        int occurrence = stringb.indexOf(stringa, startIndex);
        int timesOccurrence = 0;
        while (occurrence != -1){
            timesOccurrence++;
            startIndex = occurrence + stringa.length();
            occurrence = stringb.indexOf(stringa, startIndex);
        }
        return timesOccurrence;
    }
    public void testHowMany(){
         System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
         System.out.println(howMany("AA", "ATAAAA"));
         
    }
}
