
/**
 * Escreva a descrição da classe DirectorsFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class DirectorsFilter implements Filter {
    private String[] myDirectors;
    
    public DirectorsFilter(String directors){
        myDirectors = directors.split(",");
    }
    
    @Override
    public boolean satisfies(String id){
        for (String d : myDirectors){
            if (MovieDatabase.getDirector(id).contains(d)){
                return true;
            }
        }
        return false;
    }

}
