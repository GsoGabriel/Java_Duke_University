
/**
 * Escreva a descrição da classe Part1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import edu.duke.*;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if (diff %3 == 0){
                return currIndex;
            }
            else{
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dna.length();
    }
    
    public String findGene(String dna, int startDNA){
        String dnaUpper = dna.toUpperCase();
        int findStartCodon = dnaUpper.indexOf("ATG", startDNA);
        if (findStartCodon == -1){
            return "";
        }
        
        int stopCodonTAA = findStopCodon(dnaUpper, findStartCodon, "TAA");
        int stopCodonTAG = findStopCodon(dnaUpper, findStartCodon, "TAG");
        int stopCodonTGA = findStopCodon(dnaUpper, findStartCodon, "TGA");
        
        int firstComparison = Math.min(stopCodonTAA, stopCodonTAG);
        int minIndex = Math.min(firstComparison, stopCodonTGA);
        
        if (minIndex == dna.length()){
            return "";
        }
        else{
            return dna.substring(findStartCodon, minIndex+3);
        }
        
    }
    
    public void printAllGenes(String dna){
        int startGene = 0;
        String gene = findGene(dna, startGene);
        while (gene != ""){
            System.out.println(gene);
            startGene = dna.indexOf(gene, startGene+1);
            gene = findGene(dna, startGene + gene.length());
        }
        
    }
    
    public StorageResource getAllGenes(String dna){
        int startGene = 0;
        String gene = findGene(dna, startGene);
        StorageResource geneList = new StorageResource();
        while (gene != ""){
            geneList.add(gene);
            startGene = dna.indexOf(gene, startGene+1);
            gene = findGene(dna, startGene + gene.length());
        }
        return geneList;
    }
    
    public double cgRatio(String dna){
        int start = 0;
        double countCG = 0;
        String dnaUpper = dna.toUpperCase();
        while (true){
            int findC = dnaUpper.indexOf("C", start);
            if(findC == -1){
                break;
            }
            countCG++;
            start = findC + 1;
        }
        start = 0;
        while (true){
            int findG = dnaUpper.indexOf("G", start);
            if(findG == -1){
                break;
            }
            countCG++;
            start = findG + 1;
        }
        double cgRatio = countCG / dna.length();
        return cgRatio;
    }
    
    public int countCTG(String dna){
        int start = 0;
        int countCTG = 0;
        String dnaUpper = dna.toUpperCase();
        while (true){
            int findCG = dnaUpper.indexOf("CTG", start);
            if(findCG == -1){
                break;
            }
            countCTG++;
            start = findCG + 3;
        }
        return countCTG;
    }
    
    public void processGenes(StorageResource sr){
        int countLonger = 0;
        int ratioHigher = 0;
        int countGenes = 0;
        String higherString = "";
        for (String g : sr.data()){
            countGenes++;
            System.out.println(countGenes);
            if (g.length() > 60){
                countLonger++;
                System.out.println(g);
            }
            if (cgRatio(g) > 0.35){
                ratioHigher++;
                System.out.println(g);
            }
            if (g.length() > higherString.length()){
                higherString = g;
            }
        }
        System.out.println("countLonger " + countLonger);
        System.out.println("countGenes " + countGenes);
        System.out.println("ratioHigher " + ratioHigher);
        System.out.println("The higherString " + higherString);
        System.out.println("higherString length " + higherString.length());
    }
        
    public void testFindStopCodon(){
        //            01234567890123456789012346578901234567890123456789
        String dna = "TAAxxxTAAyyyzTAAzzxxxTAAyyyzzzxxxyyyzzTAAzyyyxxxyy";
        int dex = findStopCodon(dna, 0, "TAA");
        if (dex != 6) {System.out.println("Error on 6");}
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) {System.out.println("Error on 21");}
        System.out.println("Tests finished");
    }
    
    
    public void testFindGene(){
        String dna1 = "atggatcctccatatacaacggtatctccacctcaggtttagatctcaacaacggaaccattgccgacatgagacagttaggtatcgtcgagagttacaagctaaaacgagcagtagtcagctctgcatctgaagccgctgaagttctactaagggtggataacatcatccgtgcaagaccaagaaccgccaatagacaacatatgtaacatatttaggatatacctcgaaaataataaaccgccacactgtcattattataattagaaacagaacgcaaaaattatccactatataattcaaagacgcgaaaaaaaaagaacaacgcgtcatagaacttttggcaattcgcgtcacaaataaattttggcaacttatgtttcctcttcgagcagtactcgagccctgtctcaagaatgtaataatacccatcgtaggtatggttaaagatagcatctccacaacctcaaagctccttgccgagagtcgccctcctttgtcgagtaattttcacttttcatatgagaacttattttcttattctttactctcacatcctgtagtgattgacactgcaacagccaccatcactagaagaacagaacaattacttaatagaaaaattatatcttcctcgaaacgatttcctgcttccaacatctacgtatatcaagaagcattcacttaccatgacacagcttcagatttcattattgctgacagctactatatcactactccatctagtagtggccacgccctatgaggcatatcctatcggaaaacaataccccccagtggcaagagtcaatgaatcgtttacatttcaaatttccaatgatacctataaatcgtctgtagacaagacagctcaaataacatacaattgcttcgacttaccgagctggctttcgtttgactctagttctagaacgttctcaggtgaaccttcttctgacttactatctgatgcgaacaccacgttgtatttcaatgtaatactcgagggtacggactctgccgacagcacgtctttgaacaatacataccaatttgttgttacaaaccgtccatccatctcgctatcgtcagatttcaatctattggcgttgttaaaaaactatggttatactaacggcaaaaacgctctgaaactagatcctaatgaagtcttcaacgtgacttttgaccgttcaatgttcactaacgaagaatccattgtgtcgtattacggacgttctcagttgtataatgcgccgttacccaattggctgttcttcgattctggcgagttgaagtttactgggacggcaccggtgataaactcggcgattgctccagaaacaagctacagttttgtcatcatcgctacagacattgaaggattttctgccgttgaggtagaattcgaattagtcatcggggctcaccagttaactacctctattcaaaatagtttgataatcaacgttactgacacaggtaacgtttcatatgacttacctctaaactatgtttatctcgatgacgatcctatttcttctgataaattgggttctataaacttattggatgctccagactgggtggcattagataatgctaccatttccgggtctgtcccagatgaattactcggtaagaactccaatcctgccaatttttctgtgtccatttatgatacttatggtgatgtgatttatttcaacttcgaagttgtctccacaacggatttgtttgccattagttctcttcccaatattaacgctacaaggggtgaatggttctcctactattttttgccttctcagtttacagactacgtgaatacaaacgtttcattagagtttactaattcaagccaagaccatgactgggtgaaattccaatcatctaatttaacattagctggagaagtgcccaagaatttcgacaagctttcattaggtttgaaagcgaaccaaggttcacaatctcaagagctatattttaacatcattggcatggattcaaagataactcactcaaaccacagtgcgaatgcaacgtccacaagaagttctcaccactccacctcaacaagttcttacacatcttctacttacactgcaaaaatttcttctacctccgctgctgctacttcttctgctccagcagcgctgccagcagccaataaaacttcatctcacaataaaaaagcagtagcaattgcgtgcggtgttgctatcccattaggcgttatcctagtagctctcatttgcttcctaatattctggagacgcagaagggaaaatccagacgatgaaaacttaccgcatgctattagtggacctgatttgaataatcctgcaaataaaccaaatcaagaaaacgctacacctttgaacaacccctttgatgatgatgcttcctcgtacgatgatacttcaatagcaagaagattggctgctttgaacactttgaaattggataaccactctgccactgaatctgatatttccagcgtggatgaaaagagagattctctatcaggtatgaatacatacaatgatcagttccaatcccaaagtaaagaagaattattagcaaaacccccagtacagcctccagagagcccgttctttgacccacagaataggtcttcttctgtgtatatggatagtgaaccagcagtaaataaatcctggcgatatactggcaacctgtcaccagtctctgatattgtcagagacagttacggatcacaaaaaactgttgatacagaaaaacttttcgatttagaagcaccagagaaggaaaaacgtacgtcaagggatgtcactatgtcttcactggacccttggaacagcaatattagcccttctcccgtaagaaaatcagtaacaccatcaccatataacgtaacgaagcatcgtaaccgccacttacaaaatattcaagactctcaaagcggtaaaaacggaatcactcccacaacaatgtcaacttcatcttctgacgattttgttccggttaaagatggtgaaaatttttgctgggtccatagcatggaaccagacagaagaccaagtaagaaaaggttagtagatttttcaaataagagtaatgtcaatgttggtcaagttaaggacattcacggacgcatcccagaaatgctgtgattatacgcaacgatattttgcttaattttattttcctgttttattttttattagtggtttacagataccctatattttatttagtttttatacttagagacatttaattttaattccattcttcaaatttcatttttgcacttaaaacaaagatccaaaaatgctctcgccctcttcatattgagaatacactccattcaaaattttgtcgtcaccgctgattaatttttcactaaactgatgaataatcaaaggccccacgtcagaaccgactaaagaagtgagttttattttaggaggttgaaaaccattattgtctggtaaattttcatcttcttgacatttaacccagtttgaatccctttcaatttctgctttttcctccaaactatcgaccctcctgtttctgtccaacttatgtcctagttccaattcgatcgcattaataactgcttcaaatgttattgtgtcatcgttgactttaggtaatttctccaaatgcataatcaaactatttaaggaagatcggaattcgtcgaacacttcagtttccgtaatgatctgatcgtctttatccacatgttgtaattcactaaaatctaaaacgtatttttcaatgcataaatcgttctttttattaataatgcagatggaaaatctgtaaacgtgcgttaatttagaaagaacatccagtataagttcttctatatagtcaattaaagcaggatgcctattaatgggaacgaactgcggcaagttgaatgactggtaagtagtgtagtcgaatgactgaggtgggtatacatttctataaaataaaatcaaattaatgtagcattttaagtataccctcagccacttctctacccatctattcataaagctgacgcaacgattactattttttttttcttcttggatctcagtcgtcgcaaaaacgtataccttctttttccgaccttttttttagctttctggaaaagtttatattagttaaacagggtctagtcttagtgtgaaagctagtggtttcgattgactgatattaagaaagtggaaattaaattagtagtgtagacgtatatgcatatgtatttctcgcctgtttatgtttctacgtacttttgatttatagcaaggggaaaagaaatacatactattttttggtaaaggtgaaagcataatgtaaaagctagaataaaatggacgaaataaagagaggcttagttcatcttttttccaaaaagcacccaatgataataactaaaatgaaaaggatttgccatctgtcagcaacatcagttgtgtgagcaataataaaatcatcacctccgttgcctttagcgcgtttgtcgtttgtatcttccgtaattttagtcttatcaatgggaatcataaattttccaatgaattagcaatttcgtccaattctttttgagcttcttcatatttgctttggaattcttcgcacttcttttcccattcatctctttcttcttccaaagcaacgatccttctacccatttgctcagagttcaaatcggcctctttcagtttatccattgcttccttcagtttggcttcactgtcttctagctgttgttctagatcctggtttttcttggtgtagttctcattattagatctcaagttattggagtcttcagccaattgctttgtatcagacaattgactctctaacttctccacttcactgtcgagttgctcgtttttagcggacaaagatttaatctcgttttctttttcagtgttagattgctctaattctttgagctgttctctcagctcctcatatttttcttgccatgactcagattctaattttaagctattcaatttctctttgatc"; //no ATG
        String dna2 = "CACTTGCTATGAAAAAACTTCCTACCATTAACACTGGCCCTATGCATATG"; //no valid stopcodon
        String dna3 = "ACACGTATCAATCCCTATCATATTAGCCTGCATGCCATAGCAAACATAAA"; //TAA
        String dna4 = "GAAATTATGCTGACAAAAGAATTACTTTGATAGAGTAAACTATAGAGGTT"; //TAG
        String dna5 = "TAAGCCCTCTTATGTCATAGATTATAGGATTCGAACCATAACCTAAGAAT"; //both
        
        String testFindGene1 = findGene(dna1,0);
        System.out.println(testFindGene1);
        System.out.println(testFindGene1.length());
        String testFindGene12 = findGene(dna1,42);
        System.out.println(testFindGene12);
        String testFindGene2 = findGene(dna2,0);
        System.out.println(testFindGene2);
        String testFindGene3 = findGene(dna3,0);
        System.out.println(testFindGene3);
        String testFindGene4 = findGene(dna4,0);
        System.out.println(testFindGene4);
        String testFindGene5 = findGene(dna5,0);
        System.out.println(testFindGene5);
    }
    
    public void testPrintAllGenes(){
        printAllGenes("CACTTGCTATGAAAAAACTTCCTACCATTAACACTGGCCCTATGCATATG");
    }
    
    public void testGetAllGenes(){
        String dna = "TAAGCCCTCTTATGTCATAGATTATAGGATTCGAACCATAACCTAAGAAT";
        System.out.println("Testing getAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g : genes.data()){
            System.out.println(g);
        }
    }
    
    public void testCgRatio(){
        String dna = "xxxccgccg";
        System.out.println(cgRatio(dna));
    }
    
    public void testCountCTG(){
        String dna = "CACTTGCTATGAAAAAACTTCCTACCATTAACACTGGCCCTATGCATATG";
        System.out.println(countCTG(dna));
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
        System.out.println("countCTG " + countCTG(dna));
    }
}
