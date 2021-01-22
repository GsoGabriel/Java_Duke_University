
/**
 * Analisa os arquivos CSV que estão na pasta Data e possui
 * funções que verificam nomes, ranks e nomes que seriam se
 * tivessem nascido em outro ano, de acordo com o Ranking.
 * 
 * Nesses arquivos temos na primeira coluna nomes, na 
 * segunda o gênero desses nomes e na terceira o número de
 * pessoas que nasceram com esses nomes no ano descrito no
 * nome do arquivo.
 * 
 * Os nomes estão ordenados pela quantidade de nascimentos,
 * primeiro os nomes do sexo feminino, depois os masculinos.
 * 
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 5){
                System.out.println("Name " + rec.get(0) + " Gender " + rec.get(1) + " Num Born " + numBorn);  
            }
        }
        
    }
    
    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBoysNames = 0;
        int totalGirlsNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
                totalBoysNames++;
            }
            else{
                totalGirls += numBorn;
                totalGirlsNames++;
            }
        }
        System.out.println("Total births = " + totalBirths);
        System.out.println("Total girls = " + totalGirls);
        System.out.println("Total girls names = " + totalGirlsNames);
        System.out.println("Total boys = " + totalBoys);
        System.out.println("Total boys names = " + totalBoysNames);
    }
    
    public int totalGirls(FileResource fr){
        int totalGirls = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            if (rec.get(1).equals("F")){
                totalGirls++;
            }
        }
        return totalGirls;
    }
    
    public int getRank(int year, String name, String gender){
        int numRank = 0;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        
        for (CSVRecord rec : fr.getCSVParser(false)){
            String genderInFile = rec.get(1);
            if (genderInFile.equals(gender)){
                numRank++;
                String nameInFile = rec.get(0);
                if(nameInFile.equals(name)){
                    return numRank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, Integer rank, String gender){
        Integer numRank = 0;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        
        for (CSVRecord rec : fr.getCSVParser(false)){
            String genderInFile = rec.get(1);
            if (genderInFile.equals(gender)){
                numRank++;
                String nameInFile = rec.get(0);
                if(numRank.equals(rank)){
                    return nameInFile;
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        String pronoun = "";
        if (gender.equals("F")){
            pronoun = "she";
        }
        else{
            pronoun = "he";
        }
        System.out.println(name + " born in " + year + " would be " + newName + " if " + pronoun + " was born in " + newYear);
    }
    
    public String yearOfHighestRank(String name, String gender){
        int highestRank = 100000000;
        String nameFile = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            int numRank = 0;
            FileResource fr = new FileResource(f);
            for (CSVRecord rec : fr.getCSVParser(false)){
                String genderInFile = rec.get(1);
                if (genderInFile.equals(gender)){
                    numRank++;
                    String nameInFile = rec.get(0);
                    if(nameInFile.equals(name) && numRank < highestRank){
                        highestRank = numRank;
                        nameFile = f.getName();
                    }
                }
            }
        }
        String year = nameFile.substring(3,7);
        return year;
    }
    
    public double getAverageRank(String name, String gender){
        double sumNumRank = 0;
        int numFile = 0;
        String nameFile = "";
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            double numRank = 0;
            numFile++;
            FileResource fr = new FileResource(f);
            for (CSVRecord rec : fr.getCSVParser(false)){
                String genderInFile = rec.get(1);
                if (genderInFile.equals(gender)){
                    numRank++;
                    String nameInFile = rec.get(0);
                    if(nameInFile.equals(name)){
                        sumNumRank += numRank;
                    }
                }
            }
        }
        if (sumNumRank != 0){
            double averageRank = sumNumRank / numFile;
            return averageRank;
        }
        else{
            return -1.0;
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int sumBirths= 0;
        FileResource fr = new FileResource("data/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)){
            String nameInFile = rec.get(0);
            String genderInFile = rec.get(1);
            if (genderInFile.equals(gender)){
                if (nameInFile.equals(name)){
                    break;
                }
                else{
                    sumBirths += Integer.parseInt(rec.get(2));
                }
            }
        }
        return sumBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }
        
    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Mason", "M"));
    }
    
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Jacob", "M"));
    }
    
    public void testGetName(){
        System.out.println(getName(2012, 10, "M"));
    }
    
    public void testGetRank(){
        int test = getRank(2012, "Mason", "M");
        System.out.println(test);
    }
    
    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
}
