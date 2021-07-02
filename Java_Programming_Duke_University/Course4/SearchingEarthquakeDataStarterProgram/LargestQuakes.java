
/**
 * Escreva a descrição da classe LargestQuakes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;

public class LargestQuakes {
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
        HashMap<Double, Integer> magMap = new HashMap<Double, Integer>();
        double[] mag = new double[data.size()];
        for (int k=0; k < data.size(); k++){
            QuakeEntry qe = data.get(k);
            double magnitude = qe.getMagnitude();
            magMap.put(magnitude, k);
            mag[k] = magnitude;
        }
        Arrays.sort(mag);
        return magMap.get(mag[data.size() - 1]);
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quakeDataMod = quakeData;
        for (int i = 0; i < howMany; i++){
            int index = indexOfLargest(quakeDataMod);
            answer.add(quakeData.get(index));
            quakeDataMod.remove(index);
        }
        return answer;
    }
    
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*for (QuakeEntry qe : list){
            System.out.println(qe);
        }*/
        System.out.println("read data for "+list.size()+" quakes");
        System.out.println("the largest such earthquake is at location " + indexOfLargest(list) + " and has magnitude " + list.get(indexOfLargest(list)).getMagnitude());
        ArrayList<QuakeEntry> gL = getLargest(list,50);
        for (int k=0; k<gL.size(); k++){
            QuakeEntry qe = gL.get(k);
            System.out.println(k + " " + qe);
        }
    }
}
