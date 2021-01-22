
/**
 * Escreva a descrição da classe Part4 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;

public class Part4 {
    public void findingWebLinks(String linkDoc){
        URLResource docToAccess = new URLResource(linkDoc);
        for (String word : docToAccess.words()) {
            String wordLower = word.toLowerCase();
            if(wordLower.indexOf("youtube.com") != -1){
                int firstDoubleQuote = word.indexOf("\"");
                int lastDoubleQuote = word.indexOf("\"", firstDoubleQuote+1);
                System.out.println(word.substring(firstDoubleQuote+1, lastDoubleQuote));
            }
        }
        
    }
    public void testing(){
        findingWebLinks("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
}
