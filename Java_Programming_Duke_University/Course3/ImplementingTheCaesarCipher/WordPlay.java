
/**
 * Transform words from a file into another form, such as replacing vowels with an asterix.
 * 
 * Transforma palavras de um arquivo em outra forma, como trocar vogais para asterisco.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String vowels = "aeiou";
        String vowelsUpper = "AEIOU";
        int idxLower = vowels.indexOf(ch);
        int idxUpper = vowelsUpper.indexOf(ch);
        
        if (idxLower != -1 || idxUpper != -1){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder replacedPhrase = new StringBuilder(phrase);
        for (int i = 0; i < phrase.length(); i++){
            if (isVowel(replacedPhrase.charAt(i))){
                replacedPhrase.setCharAt(i, ch);
            }
        }
        return replacedPhrase.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder replacedPhrase = new StringBuilder(phrase);
        char chUpper = Character.toUpperCase(ch);
        for (int i = 0; i < phrase.length(); i++){
            if (replacedPhrase.charAt(i) == ch || replacedPhrase.charAt(i) == chUpper){
                if (i%2 == 0){
                    replacedPhrase.setCharAt(i, '*');
                }
                else{
                    replacedPhrase.setCharAt(i, '+');
                }
            }
        }
        return replacedPhrase.toString();
    }
    
    public void testIsVowel(){
        System.out.println(isVowel('T'));
        System.out.println(isVowel('E'));
        System.out.println(isVowel('S'));
        System.out.println(isVowel('T'));
        System.out.println(isVowel('A'));
        System.out.println(isVowel('t'));
        System.out.println(isVowel('e'));
        System.out.println(isVowel('s'));
        System.out.println(isVowel('t'));
        System.out.println(isVowel('a'));
        System.out.println(isVowel('A'));
        System.out.println(isVowel('E'));
        System.out.println(isVowel('I'));
        System.out.println(isVowel('O'));
        System.out.println(isVowel('U'));
        System.out.println(isVowel('a'));
        System.out.println(isVowel('e'));
        System.out.println(isVowel('i'));
        System.out.println(isVowel('o'));
        System.out.println(isVowel('u'));
    }
    
    public void testReplaceVowels(){
        System.out.println(replaceVowels("Meu nome é Gabriel da Silva Oliveira", '*'));
    }
    
    public void testEmphasize(){
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}
