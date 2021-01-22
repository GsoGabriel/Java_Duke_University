
/**
 * Escreva a descrição da classe Part1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord rec : parser){
            String countryName = rec.get("Country");
            if (countryName.contains(country)){
                String exports = rec.get("Exports");
                String vallue = rec.get("Value (dollars)");
                return country + ": " + exports + ": " + vallue;
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)){
                String getCountryName = record.get("Country");
                System.out.println(getCountryName);
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int countCountries = 0;
        for (CSVRecord record : parser){
            String exports = record.get("Exports");
            if (exports.contains(exportItem)){
                countCountries++;
            }
        }
        return countCountries;
    }
    
    public void bigExporters(CSVParser parser, String amount){
        for (CSVRecord record : parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                String countryName = record.get("Country");
                System.out.println(countryName + value);
            }
        }
    }
    
    public void testListExportersTwoProducts(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
    }
    
    public void testBigExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
    
    public void testCountryInfo(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
    }
    
    public void testNumberOfExporters(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, "cocoa"));
    }
}
