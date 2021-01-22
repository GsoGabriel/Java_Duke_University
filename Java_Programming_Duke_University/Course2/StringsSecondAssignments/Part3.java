
/**
 * Escreva a descrição da classe Part3 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Part3 {
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
    
    public int countGenes(String dna){
        int startGene = 0;
        String gene = findGene(dna, startGene);
        int countGenes = 0;
        while (gene != ""){
            countGenes++;
            startGene = dna.indexOf(gene, startGene+1);
            gene = findGene(dna, startGene + gene.length());
        }
        return countGenes;
    }
    
    public void testCountGenes(){
        String dna = "aacggtatctccacctcaggtttagatctcaacaacggaaccattgccgacatgagacagttaggtatcgtcgagagttacaagctaaaacgagcagtagtcagctctgcatctgaagccgctgaagttctactaagggtggataacatcatccgtgcaagaccaagaaccgccaatagacaacatatgtaacatatttaggatatacctcgaaaataataaaccgccacactgtcattattataattagaaacagaacgcaaaaattatccactatataattcaaagacgcgaaaaaaaaagaacaacgcgtcatagaacttttggcaattcgcgtcacaaataaattttggcaacttatgtttcctcttcgagcagtactcgagccctgtctcaagaatgtaataatacccatcgtaggtatggttaaagatagcatctccacaacctcaaagctccttgccgagagtcgccctcctttgtcgagtaattttcacttttcatatgagaacttattttcttattctttactctcacatcctgtagtgattgacactgcaacagccaccatcactagaagaacagaacaattacttaatagaaaaattatatcttcctcgaaacgatttcctgcttccaacatctacgtatatcaagaagcattcacttaccatgacacagcttcagatttcattattgctgacagctactatatcactactccatctagtagtggccacgccctatgaggcatatcctatcggaaaacaataccccccagtggcaagagtcaatgaatcgtttacatttcaaatttccaatgatacctataaatcgtctgtagacaagacagctcaaataacatacaattgcttcgacttaccgagctggctttcgtttgactctagttctagaacgttctcaggtgaaccttcttctgacttactatctgatgcgaacaccacgttgtatttcaatgtaatactcgagggtacggactctgccgacagcacgtctttgaacaatacataccaatttgttgttacaaaccgtccatccatctcgctatcgtcagatttcaatctattggcgttgttaaaaaactatggttatactaacggcaaaaacgctctgaaactagatcctaatgaagtcttcaacgtgacttttgaccgttcaatgttcactaacgaagaatccattgtgtcgtattacggacgttctcagttgtataatgcgccgttacccaattggctgttcttcgattctggcgagttgaagtttactgggacggcaccggtgataaactcggcgattgctccagaaacaagctacagttttgtcatcatcgctacagacattgaaggattttctgccgttgaggtagaattcgaattagtcatcggggctcaccagttaactacctctattcaaaatagtttgataatcaacgttactgacacaggtaacgtttcatatgacttacctctaaactatgtttatctcgatgacgatcctatttcttctgataaattgggttctataaacttattggatgctccagactgggtggcattagataatgcta";
        printAllGenes(dna);
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }
}