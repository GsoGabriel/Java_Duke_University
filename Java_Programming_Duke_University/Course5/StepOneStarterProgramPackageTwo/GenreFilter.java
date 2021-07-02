
/**
 * Escreva a descrição da classe GenreFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

public class GenreFilter implements Filter {
    private String myGenre;
    
    public GenreFilter (String genre){
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id){
        return MovieDatabase.getGenres(id).contains(myGenre);
    }
}
