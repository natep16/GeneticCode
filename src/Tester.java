import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Tester{
    File text = new File("rna.txt");
    private Scanner in = new Scanner(text);

    public Tester() throws FileNotFoundException {
    }

    public static void main (String[] args)
    {
        String seq = "AUGUGUCGUGGUGGCGGAGGGUUUUCUUAUUGUCUUCCUCAUCGUAUUACUAAUAGUGUUGCUGAUGGUUAAUUU";
        GeneticCode a = new GeneticCode(seq);
        a.printPolypeptide();

    }
}
