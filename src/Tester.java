import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Tester{


    public static void main (String[] args) throws FileNotFoundException
    {
        File text = new File("C:\\Users\\nathanpiersma\\IdeaProjects\\GeneticCode\\src\\rna.txt");
        Scanner in = new Scanner(text);


        String seq = "";
        while (in.hasNext())
            seq+=in.next().toUpperCase();
        in.close();
        GeneticCode a = new GeneticCode(seq);
        a.printPolypeptide();

    }
}
