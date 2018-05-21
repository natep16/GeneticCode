public class GeneticCode {
    // instance variables
    String rna, dna;

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

    private typeOfCode(String code)
    {
        for (int i = 0; i < code.length(); i++)
            if (code.substring(i, i
    }

    /**
     * Translates mRNA into a polypeptide of amino acids.
     * @param none
     * @return ArrayList<String>
     */
    public ArrayList<String> translate()
    {
        ArrayList<String> codons = rnaToCodons(mRNA);
        ArrayList<String> polypeptide = new ArrayList<String>();
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
     * @param ArrayList<String> codons, String find
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
     * Finds first of any the given sequences in the code.
     * @param String code, String[] sequences
     * @return index of first occurence
     */
    public static int findFirstOccurence(String code, String[] sequences)
    {
        int[] indices = new int[sequences.length];
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < sequences.length; i++)
        {
            int temp = code.indexOf(sequences[i]);
            if (!(temp == -1))
                indices[i] = temp;
            else
                indices[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < sequences.length; i++)
            if (indices[i] < min)
                min = indices[i];

        return min;
    }

    /**
     * Converts RNA string to an ArrayList of codon Strings
     * @param String rna
     * @return ArrayList<String>
     */
    public static ArrayList<String> rnaToCodons(String rna)
    {
        ArrayList<String> codons = new ArrayList<String>();

        for (int i = 0; i < rna.length(); i+=3)
            codons.add(rna.substring(i, i + 3));

        return codons;
    }
}
