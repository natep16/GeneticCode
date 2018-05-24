import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Tester{


    public static void main (String[] args) throws FileNotFoundException
    {
        File text = new File("/home/jadon/projects/GeneticCode/src/rna.txt");
        Scanner in = new Scanner(text);
        StringBuilder seq = new StringBuilder();
        while (in.hasNext())
            seq.append(in.next().toUpperCase());
        in.close();
        GeneticCode a = new GeneticCode(seq.toString());
        System.out.println(a.transcribe());
    }
}
