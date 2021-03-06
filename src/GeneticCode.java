import java.util.ArrayList;
import java.util.HashMap;

public class GeneticCode {
    // instance variables
    private int amntA,amntC,amntG,amntU,amntT;
    private String dna, rna;
    //Bases order goes : A C G T
    private int[] dnaBasesCount;
    //Bases order goes : A C G U
    private int[] rnaBasesCount;

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
            dnaBasesCount = countBasesDNA();
            rna = transcribe();
            rnaBasesCount = countBasesRNA();
        }
        else if (typeOfCode(code).equalsIgnoreCase("RNA"))
        {
            rna = code;
            rnaBasesCount = countBasesRNA();
            dna = reverseTranscribe();
            dnaBasesCount = countBasesDNA();

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
            rna = transcribe();
        }
        else
        {
            rna = code;
            dna = reverseTranscribe();
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
        boolean go = false;
        HashMap<String, String> AminoAcidMap= getAminoAcidMap();

        for (int i = 0; i < numCodons(); i++)
        {
            String aminoAcid = AminoAcidMap.get(codons.get(i));
            if(equals(aminoAcid, "met"))
                go = true;
            else if (equals(aminoAcid, "stop"))
                go = false;
            if (go)
                polypeptide.add(aminoAcid);
        }

        return polypeptide;
    }

    /**
     * Returns index of the first occurence of specified codon within given codon ArrayList.
     * @param codons ArrayList<String>
     * @param find String to find
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
     * @param rna A String of RNA
     * @return ArrayList<String>
     */
    public static ArrayList<String> rnaToCodons(String rna)
    {
        ArrayList<String> codons = new ArrayList<String>();

        for (int i = 0; i < rna.length() - 3; i+=3)
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

    /**
     * Returns corresponding rna code from dna code.
     * @return String
     */
    public String transcribe(){
        String outRna = "";
        for(int i = 0; i < dna.length();i++){
            String letter = dna.substring(i,i+1);
            if(letter.equals("A")){
                outRna += "U";
            }
            else if(letter.equals("T")){
                outRna += "A";
            }
            else if(letter.equals("C")){
                outRna += "G";
            }
            else if(letter.equals("G")){
                outRna += "C";
            }
        }
        return outRna;
    }

    /**
     * Returns corresponding dna String from rna.
     * @return
     */
    public String reverseTranscribe(){
        String outDna = "";
        for(int i = 0; i < rna.length();i++){
            String letter = rna.substring(i,i+1);
            if(letter.equals("A")){
                outDna += "T";
            }
            else if(letter.equals("U")){
                outDna += "A";
            }
            else if(letter.equals("C")){
                outDna += "G";
            }
            else if(letter.equals("G")){
                outDna += "C";
            }
        }
        return outDna;
    }
    public int[] countBasesDNA(){
        int[] countBases = new int[4];
        for(int i=0; i<dna.length(); i++){
            String base = dna.substring(i,i+1);
            if(base.equals("A")){
                countBases[0] += 1;
            }
            else if (base.equals("C")){
                countBases[1] += 1;
            }
            else if(base.equals("G")){
                countBases[2] +=1;
            }
            else if(base.equals("T")){
                countBases[3] +=1;
            }
        }
        return countBases;
    }
    public int[] countBasesRNA(){
        int[] countBases = new int[4];
        for(int i=0; i<dna.length(); i++){
            String base = dna.substring(i,i+1);
            if(base.equals("A")){
                countBases[0] += 1;
            }
            else if (base.equals("C")){
                countBases[1] += 1;
            }
            else if(base.equals("G")){
                countBases[2] +=1;
            }
            else if(base.equals("U")){
                countBases[3] +=1;
            }
        }
        return countBases;
    }
}
