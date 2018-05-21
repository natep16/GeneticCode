import java.util.ArrayList;
import java.util.HashMap;

public class GeneticCode {
    // instance variables
    private String dna, rna;
    private ArrayList<String> polypeptide;
    private static final String[] RNA_BASES = {"U","C","A","G"};

    /**
     * Constructor for GeneticCode objects. Use for sequences longer than 20 base pairs.
     */
    public GeneticCode(String code)

    {
        if (typeOfCode(code).equalsIgnoreCase("DNA"))
        {
            dna = code;
            //rna = transcribe(code);
        }
        else if (typeOfCode(code).equalsIgnoreCase("RNA"))
        {
            rna = code;
            //dna = reverseTranscribe(code);
        }
        polypeptide = translate();
    }

    /**
     * Constructor for GeneticCode objects. Use for sequences of 20 or less base pairs. When code is DNA, type = 0. When code is RNA, type = 1.
     */
    public GeneticCode(String code, int type)
    {
        if (type == 0)
        {
            dna = code;
            //rna = transcribe(code);
        }
        else
        {
            rna = code;
            //dna = reverseTranscribe(code);
        }
    }

    private int numCodons()
    {
        return rna.length() / 3;
    }

    private String typeOfCode(String code)
    {
        if (code.contains("U"))
            return "RNA";
        else
            return "DNA";

    }

    private static boolean equals(String a, String b) {
        return (a.compareTo(b) == 0);
    }

    /**
     * Translates mRNA into a polypeptide of amino acids.
     * @return ArrayList<String>
     */
    public ArrayList<String> translate()
    {
        ArrayList<String> codons = rnaToCodons(rna);
        ArrayList<String> polypeptide = new ArrayList<>();
        int start = findFirstCodon(codons, "AUG");
        boolean go = true;
        HashMap<String, String> AminoAcidMap= getAminoAcidMap();

        int i = 0;
        while(go)
        {
            String aminoAcid = AminoAcidMap.get(codons.get(i));

            if (!(aminoAcid.equals("stop")))
            {
                polypeptide.add(aminoAcid);
                i++;
            }
            else
                go = false;
        }
        return polypeptide;
    }

    /**
     * Returns index of the first occurence of specified codon within given codon ArrayList.
     * @return int index
     */
    public static int findFirstCodon(ArrayList<String> codons, String find)
    {
        for (int i = 0; i < codons.size(); i+=3)
            if (equals(codons.get(i),find))
                return i;
        return -1;
    }



    /**
     * Converts RNA string to an ArrayList of codon Strings
     * @return ArrayList<String>
     */
    public static ArrayList<String> rnaToCodons(String rna)
    {
        ArrayList<String> codons = new ArrayList<String>();

        for (int i = 0; i < rna.length(); i+=3)
            codons.add(rna.substring(i, i + 3));

        return codons;
    }
    /**
     * Returns a map that properly matches the codon (not anticodon) with the corresponding amimo acid it codes for.
     * @return HashMap<String, String>
     */
    public static HashMap<String, String> getAminoAcidMap()
    {
        HashMap<String,String> map = new HashMap<String,String>();
        ArrayList<String> possibleCodons = new ArrayList<String>();
        String[] aminoAcidsInOrder = {"phe","phe","leu","leu", "ser","ser","ser","ser","tyr","tyr","stop","stop","cys","cys","stop","trp", "leu","leu","leu","leu", "pro","pro","pro","pro","his","his","gln","gln","arg","arg","arg","arg","ile","ile","ile", "met","thr","thr","thr","thr","asn","asn","lys","lys","ser","ser","arg","arg","val","val","val","val","ala","ala","ala","ala","asp","asp","glu","glu","gly","gly", "gly", "gly"};

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                for (int k = 0; k < 4; k++)
                    possibleCodons.add(RNA_BASES[i] + RNA_BASES[j] + RNA_BASES[k]); //adds every possible codon to the possibleCodons ArrayList

        for (int i = 0; i < possibleCodons.size(); i++)
            map.put(possibleCodons.get(i),aminoAcidsInOrder[i]); //correctly matches each codon with the right amino acid

        return map;
    }

    public void printPolypeptide()
    {
        for (String aminoAcid: polypeptide)
            System.out.println(aminoAcid);
    }


}