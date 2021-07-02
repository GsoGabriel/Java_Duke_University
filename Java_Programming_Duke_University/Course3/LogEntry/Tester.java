
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        //la.printAll();
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs");
    }
    
    public void testVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> uniqueIPs = la.uniqueIPVisitsOnDay("Sep 27");
        System.out.println("There are " + uniqueIPs.size() + " IPs");
    }
    
    public void testInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPsInRange(400,499);
        System.out.println("There are " + uniqueIPs + " IPs");
    }
    
    public void testMostNumberVisitsByIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int mostNumber = la.mostNumberVisitsByIP(counts);
        System.out.println("The most number of visits by IP: " + mostNumber);
        ArrayList<String> ips = la.iPsMostVisits(counts);
        System.out.println("IP most visits: ");
        for (String s : ips){
            System.out.println(s);
        }
    }
    
    public void testDayWithMostIPVisits(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> iPsForDays = la.iPsForDays();
        System.out.println(la.dayWithMostIPVisits(iPsForDays));
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> mapIpDays = la.iPsForDays();
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(mapIpDays, "Sep 29");
        for (String s : ips){
            System.out.println(s);
        }
    }
}
