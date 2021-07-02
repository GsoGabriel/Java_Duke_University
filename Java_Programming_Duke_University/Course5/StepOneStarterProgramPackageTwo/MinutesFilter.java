
/**
 * Escreva a descrição da classe MinutesFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MinutesFilter implements Filter {
    private int myMinutesMin;
    private int myMinutesMax;
    
    public MinutesFilter (int min, int max){
        myMinutesMin = min;
        myMinutesMax = max;
    }
    
    @Override
    public boolean satisfies(String id){
        int minutesMovie = MovieDatabase.getMinutes(id);
        if ( minutesMovie >= myMinutesMin && minutesMovie <= myMinutesMax ) { return true; }
        else { return false; }
    }
}
