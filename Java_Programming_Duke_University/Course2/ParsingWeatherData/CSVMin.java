
/**
 * Escreva a descrição da classe CSVMin aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMin {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for(CSVRecord currentRow : parser){
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "TemperatureF");
        }
        return smallestSoFar;
    }
   
    public String fileWithColdestTemperaure(){
        CSVRecord smallestSoFar = null;
        String currentName = "";
        File fileOfSmallestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            smallestSoFar = getSmallestOfTwo(currentRow, smallestSoFar, "TemperatureF");
            if (currentRow == smallestSoFar){
                currentName = f.getName();
                fileOfSmallestSoFar = f;
            }
        }
        System.out.println("Coldest day was in file " + currentName);
        System.out.println("Coldest temperature on that day was " + smallestSoFar.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        FileResource fr = new FileResource(fileOfSmallestSoFar);
        for (CSVRecord currentRow : fr.getCSVParser()){
            System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
        }
        return currentName;
    }
    
    public CSVRecord getSmallestOfTwo(CSVRecord currentRow, CSVRecord smallestSoFar, String columnWeather){
        if(smallestSoFar == null){
            smallestSoFar = currentRow;
        }
        else{
            if ((currentRow.get(columnWeather)).equals("N/A")){
                
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get(columnWeather));
                double smallestTemp = Double.parseDouble(smallestSoFar.get(columnWeather));
                
                if((currentTemp < smallestTemp) && (currentTemp != -9999)){
                    smallestSoFar = currentRow;
                }
            }
        }
        return smallestSoFar;
    }
    
    public void testFileWithColdestTemperature(){
        String nameFile = fileWithColdestTemperaure();
        System.out.println(nameFile);
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestHumidity = null;
        for (CSVRecord currentRow : parser){
            lowestHumidity = getSmallestOfTwo(currentRow, lowestHumidity, "Humidity");
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentHumidity = lowestHumidityInFile(parser);
            lowestHumidity = getSmallestOfTwo(currentHumidity, lowestHumidity, "Humidity");
        }
        return lowestHumidity;
    }
    
    public void testLowestHumidityInManyFiles(){
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double sumTemp = 0.0;
        int sumTime = 0;
        double avgTemp = 0;
        for (CSVRecord currentRow : parser){
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentTemp != -9999){
                sumTemp += currentTemp;
                sumTime++;
            }
        }
        if(sumTime != 0){
            avgTemp = sumTemp / sumTime;
        }
        return avgTemp;
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double sumTemp = 0.0;
        int sumTime = 0;
        double avgTemp = 0;
        for (CSVRecord currentRow : parser){
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if ((currentHumidity >= value) && (currentTemp != -9999)){
                sumTemp += currentTemp;
                sumTime++;
            }
        }
        if(sumTime != 0){
            avgTemp = sumTemp / sumTime;
        }
        return avgTemp;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        if (avgHumidity == 0){
            System.out.println("No temperatures with that humidity");
        }
        else{
            System.out.println("Average Temp when high Humidity is " + avgHumidity);
        }
    }
    
}
