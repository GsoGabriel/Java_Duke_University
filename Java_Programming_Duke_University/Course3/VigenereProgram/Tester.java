
/**
 * Escreva a descrição da classe Tester aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import edu.duke.*;

public class Tester {
    public void test1SliceString(){
        VigenereBreaker vb = new VigenereBreaker();
        System.out.println(vb.sliceString("abcdefghijklm", 0, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 3));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 4));
        System.out.println(vb.sliceString("abcdefghijklm", 0, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 1, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 2, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 3, 5));
        System.out.println(vb.sliceString("abcdefghijklm", 4, 5));
    }
    public void test2tryKeyLength(){
        FileResource fr = new FileResource();
        String str = fr.asString();
        VigenereBreaker vb = new VigenereBreaker();
        int[] tkl = vb.tryKeyLength(str,5,'e');
	for(int i=0; i<tkl.length; i++){
		System.out.println("posicao " + (i+1) + " = " +tkl[i] );
	}
    }
}
