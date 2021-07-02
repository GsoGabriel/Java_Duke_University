import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        /*
        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        */
        
        Filter mf = new MagnitudeFilter(0.0, 2.0);
        //Filter df = new DepthFilter(-55000.0, -20000.0);
        ArrayList<QuakeEntry> magArray  = filter(list, mf);
        //ArrayList<QuakeEntry> depArray  = filter(magArray, df);
        for (QuakeEntry qe : magArray){
            System.out.println(qe);
        }
        System.out.println(magArray.size());
        /*
        
        Location denver = new Location(39.7392, -104.9903);
        Filter distF = new DistanceFilter(denver, 1000000);
        Filter byPhrase = new PhraseFilter("end","a");
        ArrayList<QuakeEntry> distArray  = filter(list, distF);
        ArrayList<QuakeEntry> phraseArray  = filter(distArray, byPhrase);
        for (QuakeEntry qe : phraseArray){
            System.out.println(qe);
        }
        System.out.println(phraseArray.size());
        */
    }
    
    public void testMatchAllFilter(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        Filter mf = new MagnitudeFilter(1.0, 4.0);
        Filter df = new DepthFilter(-180000.0, -30000.0);
        Filter pf = new PhraseFilter("any", "o");
        maf.addFilter(mf);
        maf.addFilter(df);
        maf.addFilter(pf);
        ArrayList<QuakeEntry> mafArray = filter(list, maf);
        for (QuakeEntry qe : mafArray){
            System.out.println(qe);
        }
        System.out.println("How many: " + mafArray.size());
        System.out.println("Filters used are: " + maf.getName());
    }
    
    public void testMatchAllFilter2(){
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        Location tulsaOklahoma = new Location(55.7308, 9.1153);
        MatchAllFilter maf = new MatchAllFilter();
        Filter mf = new MagnitudeFilter(0.0, 5.0);
        Filter df = new DistanceFilter(tulsaOklahoma, 3000000);
        Filter pf = new PhraseFilter("any", "e");
        maf.addFilter(mf);
        maf.addFilter(df);
        maf.addFilter(pf);
        ArrayList<QuakeEntry> mafArray = filter(list, maf);
        for (QuakeEntry qe : mafArray){
            System.out.println(qe);
        }
        
        System.out.println("How many: " + mafArray.size());
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }

}
