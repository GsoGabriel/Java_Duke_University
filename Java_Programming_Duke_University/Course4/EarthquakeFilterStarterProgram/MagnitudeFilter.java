
/**
 * Escreva a descrição da classe MagnitudeFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
    
    public MagnitudeFilter(){
        magMin = 0;
        magMax = 0;    
    }
    
    public MagnitudeFilter(double min, double max){
        magMin = min;
        magMax = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if (qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getName(){
        return "Magnitude";
    }
}
