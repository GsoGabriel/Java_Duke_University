
/**
 * Escreva a descrição da classe DepthFilter aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class DepthFilter implements Filter {
    private double minDepth;
    private double maxDepth;
    
    public DepthFilter(){
        minDepth = 0;
        maxDepth = 0;
    }
    
    public DepthFilter(double min, double max){
        minDepth = min;
        maxDepth = max;
    }
    
    public boolean satisfies(QuakeEntry qe){
        if (qe.getDepth() >= minDepth && qe.getDepth() <= maxDepth){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getName(){
        return "Depth";
    }
    
    
}
