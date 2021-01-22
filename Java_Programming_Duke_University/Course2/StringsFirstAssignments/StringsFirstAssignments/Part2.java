
/**
 * Escreva a descrição da classe Part2 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon){
        String dnaUpper = dna.toUpperCase();
        
        String startCodonUpper = startCodon.toUpperCase();
        
        String stopCodonUpper = stopCodon.toUpperCase();
        
        int positionStartCodon = dnaUpper.indexOf(startCodonUpper);
        if (positionStartCodon == -1){
            return "No Gene (there is no atg)";
        }
        
        int positionStopCodon = dnaUpper.indexOf(stopCodonUpper, positionStartCodon);
        if (positionStopCodon == -1){
            return "No gene (There is no taa)";
        }
        
        int lengthDNA = positionStartCodon - positionStopCodon + 3;
        if (lengthDNA%3 == 0){
            String codon = dnaUpper.substring(positionStartCodon, positionStopCodon + 3);
            return codon;
        }
        
        return "No gene (Gene is not multiple 3)";
    }
    public void testSimpleGene(){
        Part2 p2 = new Part2();
        String simpleGene1 = p2.findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC", "atg", "Taa");
        System.out.println(simpleGene1);
        String simpleGene2 = p2.findSimpleGene("tataagttttcattattttcaaaaaggggga", "atg", "taa");
        System.out.println(simpleGene2);
        String simpleGene3 = p2.findSimpleGene("cgtctatgacctcctaaaggac", "atg", "taa");
        System.out.println(simpleGene3);
        String simpleGene4 = p2.findSimpleGene("ccgcttcctatgagtttaactgccgg", "atg", "taa");
        System.out.println(simpleGene4);
        String simpleGene5 = p2.findSimpleGene("tttattgtgggtttaggtaagaaatt", "atg", "taa");
        System.out.println(simpleGene5);
    }
}
