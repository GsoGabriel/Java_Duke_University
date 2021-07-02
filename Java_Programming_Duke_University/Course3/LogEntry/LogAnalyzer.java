
/**
 * Escreva a descrição da classe LogAnalyzer aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer {
    private ArrayList<LogEntry> records;
    public LogAnalyzer(){
        records = new ArrayList<LogEntry>();
    }
    
    public void readFile(String filename){
        FileResource fr = new FileResource(filename);
        for (String s : fr.lines()){
            LogEntry le = WebLogParser.parseEntry(s);
            records.add(le);
        }
    }
    
    public HashMap<String,Integer> countVisitsPerIP(){
        // make an empty HashMap<String,Integer> counts
        HashMap<String,Integer> counts = new HashMap<String,Integer>();
        //For each le in records
        for (LogEntry le : records){
            //ip is le's ipAddress
            String ip = le.getIpAddress();
            //check if ip is in counts
            if (!counts.containsKey(ip)){
                //if not: put ip in with a value of 1
                counts.put(ip,1);
            }
                //if so: update the value to be more
            else{
                counts.put(ip,counts.get(ip) + 1);
            }
        }
        //counts is the answer
        return counts;
    }
    
    public void printAll(){
        for (LogEntry le : records){
            System.out.println(le);
        }
    }
    
    public int ipAnalyzer(){
        ArrayList<String> ipList = new ArrayList<String>();
        for (LogEntry le : records){
            String ipAddress = le.getIpAddress();
            if (!ipList.contains(ipAddress)){
                ipList.add(ipAddress);
            }
        }
        return ipList.size();
    }
    
    /**
     * In the LogAnalyzer class, write the method countUniqueIPs that has no parameters. This method should return an
     * integer representing the number of unique IP addresses. It should also assume that the instance variable records
     * already has its ArrayList of Strings read in from a file, and should access records in computing this value.
     * For help, refer to the lectures in this lesson on the unique IP algorithm and code.
     */
    
    public int countUniqueIPs(){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if (!uniqueIPs.contains(ipAddr)){
                uniqueIPs.add(ipAddr);
            }
        }
        return uniqueIPs.size();
    }
    
    /**
     * In the Tester class (or you can write a new class for testing) write the void method testUniqueIP that has no parameters.
     * This method should create a LogAnalyzer, read from the file short-test_log, and then test the method countUniqueIPs. 
     */
    
    /**
     * In the LogAnalyzer class, write the void method printAllHigherThanNum that has one integer parameter num. This
     * method should examine all the web log entries in records and print those LogEntrys that have a status code greater than
     * num. Be sure to add code in the Tester class to test out this method with the file short-test_log.
     */
    
    public void printAllHigherThanNum(int num){
        for (LogEntry le : records){
            if (le.getStatusCode() > num){
                System.out.println(le);
            }
        }
    }
    
    /**
     * In the LogAnalyzer class, write the method uniqueIPVisitsOnDay that has one String parameter named someday in the
     * format “MMM DD” where MMM is the first three characters of the month name with the first letter capitalized and the 
     * others in lowercase, and DD is the day in two digits (examples are “Dec 05” and “Apr 22”). This method accesses the web 
     * logs in records and returns an ArrayList of Strings of unique IP addresses that had access on the given day. (Note that the
     * dates in LogEntrys are stored as a Date object, but using toString will allow you to access the characters in the Date.
     * For example, consider that d is a Date. String str = d.toString(); allows you to now use a String representation of the
     * date.) Be sure to test your program with code in the Tester class.  Using the file weblog-short_log you should see that
     * the call to uniqueIPVisitsOnDay(“Sep 14”) returns an ArrayList of 2 items and uniqueIPVisitsOnDay(“Sep 30”) returns an 
     * ArrayList of 3 items. 
     */
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> visitsOnDay = new ArrayList<String>();
        for (LogEntry le : records){
            Date d = le.getAccessTime();
            String dString = d.toString();
            String ipAddr = le.getIpAddress();
            if (dString.contains(someday)){
                if (!visitsOnDay.contains(ipAddr)){
                    visitsOnDay.add(ipAddr);
                }
            }
        }
        return visitsOnDay;
    }
    
    /**
     * In the LogAnalyzer class, write the method countUniqueIPsInRange that has two integer parameters named low and high.
     * This method returns the number of unique IP addresses in records that have a status code in the range from low to high, 
     * inclusive. Be sure to test your program on several ranges. For example, using the file short-test_log, the call 
     * countUniqueIPsInRange(200,299) returns 4, as there are four unique IP addresses that have a status code from 200 to 299.
     * The call countUniqueIPsInRange(300,399) returns 2. In this case, note that there are three entries in the file that have
     * a status code in the 300 range, but two of them have the same IP address.
     */
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> uniqueIPs = new ArrayList<String>();
        for (LogEntry le : records){
            String ipAddr = le.getIpAddress();
            if (le.getStatusCode() >= low && le.getStatusCode() <= high){
                if (!uniqueIPs.contains(ipAddr)){
                    uniqueIPs.add(ipAddr);
                }
            }
        }
        return uniqueIPs.size();
    }
    
    /**
     * In the LogAnalyzer class, write the method mostNumberVisitsByIP, which has one parameter, a HashMap<String, Integer>
     * that maps an IP address to the number of times that IP address appears in the web log file. This method returns the 
     * maximum number of visits to this website by a single IP address. For example, the call mostNumberVisitsByIP on a
     * HashMap formed using the file weblog3-short_log returns 3.
     */
    public int mostNumberVisitsByIP(HashMap<String, Integer> mapIpAddress){
        int numVisitsMax = 0;
        for (String s : mapIpAddress.keySet()){
            int currVisits = mapIpAddress.get(s);
            if (currVisits > numVisitsMax){
                numVisitsMax = currVisits;
            }
        }
        return numVisitsMax;
    }
    
    /**
     * In the LogAnalyzer class, write the method iPsMostVisits, which has one parameter, a HashMap<String, Integer> that 
     * maps an IP address to the number of times that IP address appears in the web log file. This method returns an ArrayList
     * of Strings of IP addresses that all have the maximum number of visits to this website. For example, the call
     * iPsMostVisits on a HashMap formed using the file weblog3-short_log returns the ArrayList with these two IP addresses, 
     * 61.15.121.171 and  84.133.195.161. Both of them visited the site three times, which is the maximum number of times any 
     * IP address visited the site. 
     */
    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> mapIpAddress){
        int numVisits = mostNumberVisitsByIP(mapIpAddress);
        ArrayList<String> ips = new ArrayList<String>();
        for (String s : mapIpAddress.keySet()){
            if (mapIpAddress.get(s) == numVisits){
                ips.add(s);
            }
        }
        return ips;
    }
    
    /**
     * In the LogAnalyzer class, write the method iPsForDays, which has no parameters. This method returns a
     * HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses that 
     * occurred on that day (including repeated IP addresses). A day is in the format “MMM DD” where MMM is the first three
     * characters of the month name with the first letter capital and the others in lowercase, and DD is the day in two digits 
     * (examples are “Dec 05” and “Apr 22”). For example, for the file weblog3-short_log, after building this HashMap, if you
     * print it out, you will see that Sep 14 maps to one IP address, Sep 21 maps to four IP addresses, and Sep 30 maps to 
     * five IP addresses.
     */
    public HashMap<String,ArrayList<String>> iPsForDays(){
        HashMap<String,ArrayList<String>> mapIPDays = new HashMap<String,ArrayList<String>>();
        String[] months = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        for (LogEntry le : records){
            Date time = le.getAccessTime();
            String timeStr = time.toString();
            String justDate = "";
            for (String s : months){
                int idx = timeStr.indexOf(s);
                if (idx != -1){
                    justDate = timeStr.substring(idx, idx+6);
                    break;
                }
            }
            String ip = le.getIpAddress();
            if (mapIPDays.containsKey(justDate)){
                ArrayList<String> currArray = mapIPDays.get(justDate);
                currArray.add(ip);
                mapIPDays.put(justDate, currArray);
            }
            else{
                ArrayList<String> currArray = new ArrayList<String>();
                currArray.add(ip);
                mapIPDays.put(justDate, currArray);
            }
        }
        return mapIPDays;
    }
    
    /**
     * In the LogAnalyzer class, write the method dayWithMostIPVisits, which has one parameter that is a
     * HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses that 
     * occurred on that day. This method returns the day that has the most IP address visits. If there is a tie, then return 
     * any such day. For example, if you use the file weblog3-short_log, then this method should return the day most visited 
     * as Sep 30.
     */
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> mapDays){
        int ipVisits = 0;
        String currDay = "";
        for (String s : mapDays.keySet()){
            ArrayList<String> currArray = mapDays.get(s);
            int size = currArray.size();
            if (size > ipVisits){
                ipVisits = size;
                currDay = s;
            }
        }
        return currDay;
    }
    
    /**
     * In the LogAnalyzer class, write the method iPsWithMostVisitsOnDay, which has two parameters—the first one is a 
     * HashMap<String, ArrayList<String>> that uses records and maps days from web logs to an ArrayList of IP addresses that 
     * occurred on that day, and the second parameter is a String representing a day in the format “MMM DD” described above. 
     * This method returns an ArrayList<String> of IP addresses that had the most accesses on the given day. For example, if 
     * you use the file weblog3-short_log, and the parameter for the day is “Sep 30”, then there are two IP addresses in the 
     * ArrayList returned: 61.15.121.171 and 177.4.40.87. Hint: This method should call another method you have written.
     */
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> mapDays, String day){
        ArrayList<String> arrayOfDay = mapDays.get(day);
        HashMap<String,Integer> hashMapIPs = new HashMap<String,Integer>();
        for (String s : arrayOfDay){
            if (!hashMapIPs.containsKey(s)){
                hashMapIPs.put(s,1);
            }
            else{
                hashMapIPs.put(s, hashMapIPs.get(s) + 1);
            }
        }
        return iPsMostVisits(hashMapIPs);
    }
}
