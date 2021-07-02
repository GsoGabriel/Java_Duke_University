
/**
 * Escreva a descrição da classe DistanceFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class DistanceFilter implements Filter{
    private Location location;
    private double maxDistance;
    
    public DistanceFilter(){
        maxDistance = 0;
    }
    
    public DistanceFilter(Location loc, double max){
        location = loc;
        maxDistance = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if (qe.getLocation().distanceTo(location) < maxDistance){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    public String getName(){
        return "Distance";
    }
    
}
