
/**
 * Escreva a descrição da classe Part1 aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Part1 {
    public String findSimpleGene(String dna){
        int startCodon = dna.indexOf("atg");
        if (startCodon == -1){
            return "No Gene (there is no atg)";
        }
        int stopCodon = dna.indexOf("taa", startCodon);
        if (stopCodon == -1){
            return "No gene (There is no taa)";
        }
        int lengthDNA = startCodon - stopCodon + 3;
        if (lengthDNA%3 == 0){
            String codon = dna.substring(startCodon, stopCodon + 3);
            return codon;
        }
        return "No gene (Gene is not multiple 3)";
    }
    public void testSimpleGene(){
        Part1 p1 = new Part1();
        String simpleGene1 = p1.findSimpleGene("AAATGCCCTAACTAGATTAAGAAACC");
        System.out.println(simpleGene1);
        String simpleGene2 = p1.findSimpleGene("tataagttttcattattttcaaaaaggggga");
        System.out.println(simpleGene2);
        String simpleGene3 = p1.findSimpleGene("cgtctatgacctcctaaaggac");
        System.out.println(simpleGene3);
        String simpleGene4 = p1.findSimpleGene("ccgcttcctatgagtttaactgccgg");
        System.out.println(simpleGene4);
        String simpleGene5 = p1.findSimpleGene("tttattgtgggtttaggtaagaaatt");
        System.out.println(simpleGene5);
    }
}
