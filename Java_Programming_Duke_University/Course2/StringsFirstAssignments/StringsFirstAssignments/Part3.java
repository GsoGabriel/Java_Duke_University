
/**
 * Escreva a descrição da classe Part3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        boolean conditionFor = true;
        int newI = 0;
        int foundStringa = 0;
        
        for(int i = 0; conditionFor==true; i = newI + stringa.length()){
            newI = stringb.indexOf(stringa, i);
            if (newI != -1){
                foundStringa++;
            }
            else{
                conditionFor = false;
            }
        }
        
        if (foundStringa >= 2) {
            return true;
        }
        
        else {
            return false;
        }
    }
    
    public String lastPart(String stringa, String stringb){
        int firstOccurrence = stringb.indexOf(stringa);
        if(firstOccurrence != -1){
            return stringb.substring(firstOccurrence + stringa.length());
        }
        else{
            return stringb;
        }
    }
    
    private void getPrint(Part3 p3, String a, String b) {
        String c = p3.lastPart(a, b);
        System.out.println("The part of the string after " + a + " in " + b + " is " + c);
    }
    
    public void testing(){
        Part3 p3 = new Part3();
        boolean firstTest = p3.twoOccurrences("by", "A story by Abby Long");
        boolean secondTest = p3.twoOccurrences("a", "banana");
        boolean thirdTest = p3.twoOccurrences("atg", "ctgtatgta");
        System.out.println(firstTest);
        System.out.println(secondTest);
        System.out.println(thirdTest);
        getPrint(p3, "an", "banana");
        getPrint(p3, "zoo", "forest");
    }
}
