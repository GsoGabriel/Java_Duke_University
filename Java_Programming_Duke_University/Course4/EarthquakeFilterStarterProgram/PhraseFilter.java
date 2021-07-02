
/**
 * Escreva a descrição da classe PhraseFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class PhraseFilter implements Filter {
    private String whereToSearch;
    private String phraseToSearch;
    
    public PhraseFilter(String where, String phrase){
        whereToSearch = where.toLowerCase();
        phraseToSearch = phrase;
    }
    
    public boolean satisfies(QuakeEntry qe){
        String info  = qe.getInfo();
        int index = info.indexOf(phraseToSearch);
        if (whereToSearch.equals("start") && index == 0){
            return true;
        }
        else if (whereToSearch.equals("end") && index == (info.length() - phraseToSearch.length())){
            return true;
        }
        else if (whereToSearch.equals("any") && index != -1){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    public String getName(){
        return "Phrase";
    }
}
